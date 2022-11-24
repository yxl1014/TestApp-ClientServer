package client.yxl.kafka.consumer.runnable;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.yxl.context.ClientContext;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pto.TestProto;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yxl
 * @date: 2022/10/5 下午5:00
 */
public class KafkaConsumerRunner implements Runnable {

    private final static Logger logger = LogUtil.getLogger(KafkaConsumerRunner.class);
    private final String topicName;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer<String, String> consumer;

    private ClientContext clientContext;


    public KafkaConsumerRunner(String topicName, KafkaConsumer<String, String> consumer, ClientContext clientContext) {
        this.topicName = topicName;
        this.consumer = consumer;
        this.clientContext = clientContext;
    }

    @Override
    public void run() {
        try {
            consumer.subscribe(Collections.singleton(topicName));
            while (!closed.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    if (Integer.parseInt(record.key()) == clientContext.getUserId()) {
                        TestProto.TaskShell.Builder builder = TestProto.TaskShell.newBuilder();
                        JsonFormat.parser().merge(record.value(), builder);
                        LogBuilder logBuilder = LogBuilder.initLog(LogMsg.TEST, OptionDetails.KAFKA_CONSUMER_TEST);
                        logBuilder.build("key", record.key());
                        logBuilder.build("msg", builder.toString());
                        logger.info(logBuilder.log());
                        this.clientContext.onListenerShell(builder);
                    }
/*
                    System.out.printf("offset = %d, key = %s, value = %s\n",
                            record.offset(), record.key(), record.value());*/
                }
            }
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!closed.get()) {
                logger.error("topicName : " + topicName + ",error :" + e.getMessage());
                throw e;
            }
        } catch (InvalidProtocolBufferException e) {
            if (!closed.get()) {
                logger.error("topicName : " + topicName + ",error :" + e.getMessage() + ",json转换失败！");
            }
        } finally {
            consumer.close();
        }
    }

    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }

    public boolean getClosed() {
        return closed.get();
    }

}
