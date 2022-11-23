package client.zyb.timer;

import client.ljy.net.myconnection.IConnection;
import client.yxl.context.ClientContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pto.TestProto;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhang
 * @Date 2022/10/25
 */

@Component
public class Schedule {

    @Autowired
    private ConnectionThread connectionThread;

    public void startSchedule(IConnection iConnection, TestProto.TaskShell.Builder shell, ClientContext clientContext)//IConnection
    {
        connectionThread.init(iConnection, shell, clientContext);
        connectionThread.startSchedule();
    }
}
