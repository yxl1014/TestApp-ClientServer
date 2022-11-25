package client.zyb.timer;

import client.ljy.net.myconnection.IConnection;
import client.yxl.context.ClientContext;
import client.zyb.util.utils;
import org.springframework.stereotype.Component;
import pto.TestProto;
import java.time.Instant;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhang
 * @Date 2022/11/23
 */
@Component
public class Schedule {

    utils utils = new utils();
    private TestProto.TaskShell.Builder taskShell;
    //定义定时任务
    public static ScheduledExecutorService ScheduledExecutorService = new ScheduledThreadPoolExecutor(5);
    long delay = utils.getDelay(taskShell.getStartTime(),Instant.now().getEpochSecond(),taskShell.getIntervalTime());
    long period = taskShell.getIntervalTime();
    /**
     * 调用此方法开启定时任务
     */
    public TestProto.ResponseMsg.Builder startSchedule(IConnection iConnection, TestProto.TaskShell.Builder taskShell,ClientContext clientContext)
    {

        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.init(iConnection,taskShell,clientContext);
        ScheduledExecutorService.scheduleAtFixedRate(scheduleTask,delay,period,TimeUnit.SECONDS);
        TestProto.ResponseMsg.Builder getresult = utils.getresult(ScheduledExecutorService, scheduleTask);
        return getresult;

    }
    //循环判断返回值结果信息，长时间未返回，或者大批量错误信息则认定该任务测试未通过，立即暂定定时任务



}
