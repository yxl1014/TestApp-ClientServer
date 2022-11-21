package client.zyb.timer;

import client.ljy.net.myconnection.IConnection;
import client.yxl.context.ClientContext;
import client.yxl.kafka.producer.KafkaProducerPoll;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import kafka.utils.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import pto.TestProto;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhang
 * @Date 2022/11/21
 */
public class ConnectionThread  implements Runnable {
    @Autowired
    private ClientContext clientContext;
    @Autowired
    private KafkaProducerPoll kafkaProducerPoll;
    private IConnection iConnection;
    private TestProto.TaskShell.Builder taskShell;
    public static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
    public void init(IConnection iConnection, TestProto.TaskShell.Builder taskShell)
    {
        this.iConnection = iConnection;
        this.taskShell = taskShell;
    }
    public ConnectionThread(){}
    public void startSchedule()
        {
            long delay = getDelay(taskShell.getStartTime(),Instant.now().getEpochSecond(),taskShell.getIntervalTime());
            scheduledExecutorService.schedule(this::run,delay, TimeUnit.SECONDS);
        }
    @Override
    public void run() {
        TestProto.KafkaMsg.Builder kafkaMag = null;
        //发送之前的时间
        long start = System.currentTimeMillis();
        iConnection.sendRequest();
        //发送之后的时间
        long end = System.currentTimeMillis();

        //给kafkaMsg添加数据
        int taskId = 0 ;
        kafkaMag.setUserId(clientContext.getUserId());
        String body = clientContext.getTaskShell().getBody();
        try {
            TestProto.Task task = TestProto.Task.parseFrom(body.getBytes());
            kafkaMag.setTaskId(task.getTaskId());
            taskId = task.getTaskId();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        kafkaMag.setShellId(taskShell.getShellId());
        kafkaMag.setIp(taskShell.getIp());
        kafkaMag.setPort(taskShell.getPort());
        kafkaMag.setCostTime(start - end);
        kafkaMag.setRequestMsg(null);
        kafkaMag.setResponseMsg(null);
        kafkaMag.setSuccess(true);
        String kafkaMagJson = null ;
        try {
            JsonFormat.parser().merge(kafkaMagJson,kafkaMag);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        kafkaProducerPoll.sendMsg(taskId,clientContext.getUserId(),taskShell.getShellId(),kafkaMagJson);
    }
    private long getDelay(long startTime, long nowSecond, long intervalTime)
    {
        return startTime - nowSecond + intervalTime;
    }
}
