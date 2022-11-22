package client.zyb.setKafka;

import client.yxl.kafka.config.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import pto.TestProto;

import java.util.Properties;

/**
 * @Author zhang
 * @Date 2022/11/2
 */
public class KafkaMsg {
    @Autowired
    private KafkaConfig kafkaProperties;
    public boolean setKafkaMsg(TestProto.KafkaMsg kafkaMsg)
    {
        Properties kafkaConsumerProperties = kafkaProperties.getKafkaConsumerProperties();
        KafkaProducer<String, String> producer = new KafkaProducer(kafkaConsumerProperties);
        //
        return true;
    }
}
