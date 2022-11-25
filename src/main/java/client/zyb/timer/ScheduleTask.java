package client.zyb.timer;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.ljy.net.myconnection.IConnection;
import client.yxl.context.ClientContext;
import client.yxl.kafka.consumer.common.KafkaConsumerConnectPoll;
import client.yxl.kafka.producer.KafkaProducerPoll;
import client.zyb.util.utils;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pto.TestProto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author zhang
 * @Date 2022/11/23
 */
public class ScheduleTask implements Runnable {
    private final static Logger logger = LogUtil.getLogger(ScheduleTask.class);
    private ClientContext clientContext;
    @Autowired
    private utils utils;
    @Autowired
    private KafkaProducerPoll kafkaProducerPoll;
    private IConnection iConnection;
    private TestProto.TaskShell.Builder taskShell;

    public List<Boolean> resultStatistics = Collections.synchronizedList(new ArrayList());

    public List getResultStatistics() {
        return resultStatistics;
    }

    public void init(IConnection iConnection, TestProto.TaskShell.Builder taskShell,ClientContext clientContext)
    {
        this.iConnection = iConnection;
        this.taskShell = taskShell;
        this.clientContext = clientContext;
    }
    @Override
    public void run() {
        TestProto.KafkaMsg.Builder kafkaMsg = utils.getKafkaMsg(iConnection, taskShell);
        String kafkaMagJson = null;
        try {
            kafkaMagJson = JsonFormat.printer().print(kafkaMsg);
        } catch (InvalidProtocolBufferException e) {
            /**
             * 日志处理
             */
            logger.info(LogBuilder.initLog(LogMsg.FORMAT, OptionDetails.KAFKA_PROTO_TO_JSON));
        }
        kafkaProducerPoll.sendMsg(kafkaMsg.getTaskId(),clientContext.getUserId(),taskShell.getShellId(),kafkaMagJson);
        resultStatistics.add(kafkaMsg.getSuccess());
    }
}
