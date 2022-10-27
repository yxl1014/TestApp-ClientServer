package client.yxl.kafka.consumer.common;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.common.util.FinalData;
import client.yxl.context.ClientContext;
import client.yxl.kafka.consumer.runnable.KafkaConsumerRunner;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * kafkaTopic连接池
 *
 * @author yxl
 * @date: 2022/10/5 下午5:24
 */

@Component
public class KafkaConsumerConnectPoll {

    private final static Logger logger = LogUtil.getLogger(KafkaConsumerConnectPoll.class);

    @Qualifier("kafkaProperties")
    private Properties properties;

    /**
     * String               topicName
     * KafkaConsumerRunner  kafka异步消费对象
     */
    private final Map<String, KafkaConsumerRunner> consumers = new HashMap<>();

    private final ClientContext clientContext;

    public KafkaConsumerConnectPoll(ClientContext clientContext) {
        this.clientContext = clientContext;
    }


    /**
     * 关闭这条队列的监听
     *
     * @param taskId taskId
     */
    public void shutdown(int taskId) {
        String topicName = FinalData.getKafkaS2CName(taskId);
        if (consumers.containsKey(topicName)) {
            consumers.get(topicName).shutdown();
            logger.info(LogBuilder.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_CONSUMER_TOPIC_CLOSE_OK)
                    .build("topic-name", topicName).log());
            this.consumers.remove(topicName);
        } else {
            logger.info(LogBuilder.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_CONSUMER_TOPIC_CLOSE_NO_TOPIC)
                    .build("topic-name", topicName).log());
        }
    }


    /**
     * 获取consumerRunner
     *
     * @param taskId taskId
     * @return 返回一个runner
     */
    public KafkaConsumerRunner getConsumer(int taskId) {
        String topicName = FinalData.getKafkaS2CName(taskId);
        return consumers.get(topicName);
    }

    /**
     * 初始化并开始消费这条队列
     *
     * @param taskId taskId
     */
    public void initKafkaTopic(int taskId) {
        String topicName = FinalData.getKafkaS2CName(taskId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(this.properties);
        KafkaConsumerRunner kafkaConsumerRunner = new KafkaConsumerRunner(topicName, consumer, clientContext);
        KafkaConsumerRunner old = this.consumers.get(topicName);

        //若有旧的topic且他还没有关闭
        if (old != null && !old.getClosed()) {
            logger.info(LogBuilder.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_CONSUMER_TOPIC_START_EXIST_OK)
                    .build("topic-name", topicName).log());
            old.shutdown();
        }
        this.consumers.put(topicName, kafkaConsumerRunner);
        Thread thread = new Thread(kafkaConsumerRunner);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.info(LogBuilder.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_CONSUMER_TOPIC_START_ERROR)
                    .build("topic-name", topicName).log());
            logger.warn(e);
        }
        logger.info(LogBuilder.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_CONSUMER_TOPIC_START_OK)
                .build("topic-name", topicName).log());
    }

    /**
     * 获取这个监听队列,若为null则初始化并开始一个
     *
     * @param taskId taskId
     * @return 返回一个runner
     */
    public KafkaConsumerRunner getConsumerOrInit(int taskId) {
        String topicName = FinalData.getKafkaS2CName(taskId);
        KafkaConsumerRunner consumer = getConsumer(taskId);
        if (consumer != null) {
            logger.info(LogBuilder.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_CONSUMER_TOPIC_GET_EXIST)
                    .build("topic-name", topicName).log());
            return consumer;
        }
        logger.info(LogBuilder.initLog(LogMsg.KAFKA, OptionDetails.KAFKA_CONSUMER_TOPIC_GET_NO_EXIST)
                .build("topic-name", topicName).log());
        initKafkaTopic(taskId);
        return getConsumer(taskId);
    }


}
