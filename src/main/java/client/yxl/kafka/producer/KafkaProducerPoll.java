package client.yxl.kafka.producer;

import client.common.logs.LogClass;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.common.util.FinalData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yxl
 * @date: 2022/10/13 下午3:50
 */

@Component
public class KafkaProducerPoll {

    private final static Logger logger = LogUtil.getLogger(KafkaProducerPoll.class);

    @Qualifier("kafkaProperties")
    private Properties properties;

    /**
     * key:任务Id
     * value:KafkaProducer
     */
    private final Map<Integer, Producer<String, String>> producerMaps = new HashMap<>();


    /**
     * 创建producer对象
     *
     * @param taskId 任务ID
     * @return 成功与否
     */
    public synchronized boolean createProducer(int taskId) {
        String topicName = FinalData.getKafkaC2SName(taskId);
        if (producerMaps.containsKey(taskId)) {
            logger.info(LogClass.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_PRODUCER_CREATE_IS_EXIST)
                    .build("topic-name", topicName).log());
            return false;
        }

        Producer<String, String> producer = new KafkaProducer<>(this.properties);
        producerMaps.put(taskId, producer);
        logger.info(LogClass.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_PRODUCER_CREATE_SUCCESS)
                .build("topic-name", topicName).log());
        return true;
    }

    /**
     * 关闭producer对象
     *
     * @param taskId 任务ID
     * @return 成功与否
     */
    public synchronized boolean closeProducer(int taskId) {
        String topicName = FinalData.getKafkaC2SName(taskId);
        if (!producerMaps.containsKey(taskId)) {
            logger.info(LogClass.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_PRODUCER_CLOSE_NOT_EXIST)
                    .build("topic-name", topicName).log());
            return false;
        }
        Producer<String, String> producer = producerMaps.get(taskId);
        //清空缓存数据
        producer.flush();
        //关闭
        producer.close();
        //删除map
        producerMaps.remove(taskId);
        logger.info(LogClass.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_PRODUCER_CLOSE_SUCCESS)
                .build("topic-name", topicName).log());
        return true;
    }

    /**
     * @param taskId  任务id
     * @param userId  用户id
     * @param shellId 脚本id
     * @param msg     发送的内容，就是TestProto.TaskShell的Json对象
     * @return 成功或失败
     */
    public boolean sendMsg(int taskId, int userId, int shellId, String msg) {
        String topicName = FinalData.getKafkaC2SName(taskId);
        Producer<String, String> producer = producerMaps.get(taskId);
        if (producer == null) {
            logger.info(LogClass.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_PRODUCER_SEND_NO_PRODUCER)
                    .build("topic-name", topicName).log());
            return false;
        }
        producer.send(new ProducerRecord<>(topicName, userId + "_" + shellId, msg));
        producer.flush();
        logger.info(LogClass.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_PRODUCER_SEND_SUCCESS)
                .build("topic-name", topicName).log());
        return true;
    }

}
