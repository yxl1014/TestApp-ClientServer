package client.zyb.timer;

import client.ljy.net.myconnection.IConnection;
import client.yxl.context.ClientContext;
import org.springframework.beans.factory.annotation.Autowired;
import pto.TestProto;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhang
 * @Date 2022/10/25
 */
public class Schedule {

    @Autowired
    private ClientContext clientContext;

    public void startSchedule( IConnection iConnection , TestProto.TaskShell.Builder shell)//IConnection
    {

        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //发送之前的时间
                TestProto.KafkaMsg.Builder  kafkaMag= null;
                long start = System.currentTimeMillis();
                iConnection.sendRequest();
                long end = System.currentTimeMillis();
                //发送之后的时间
                TestProto.TaskShell.Builder taskShell = clientContext.getTaskShell();
                TestProto.User.Builder user = clientContext.getUser();

                //给kafkaMsg添加数据
                kafkaMag.setCostTime(start-end);
                kafkaMag.setUserId(clientContext.getUserId());
                kafkaMag.setIp(user.getUserIp());
                kafkaMag.setPort(user.getUserPos());

                System.out.println(LocalDateTime.now());
            }
        };
        scheduledExecutorService.schedule(r,5, TimeUnit.SECONDS);//参数：1.启动的线程 2.第一次启动延迟 3.时间单位
    }
}
