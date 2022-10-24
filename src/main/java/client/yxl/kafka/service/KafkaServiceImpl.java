package client.yxl.kafka.service;

import client.yxl.kafka.consumer.common.KafkaConsumerConnectPoll;
import client.yxl.kafka.producer.KafkaProducerPoll;
import org.springframework.stereotype.Service;

/**
 * kafka操作类
 *
 * @author yxl
 * @date: 2022/10/24 下午5:09
 */

@Service
public class KafkaServiceImpl {
    private final KafkaConsumerConnectPoll consumerPoll;

    private final KafkaProducerPoll producerPoll;

    public KafkaServiceImpl(KafkaConsumerConnectPoll consumerPoll, KafkaProducerPoll producerPoll) {
        this.consumerPoll = consumerPoll;
        this.producerPoll = producerPoll;
    }
}
