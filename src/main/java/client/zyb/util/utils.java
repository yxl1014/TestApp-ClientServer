package client.zyb.util;
import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.ljy.net.myconnection.IConnection;
import client.yxl.context.ClientContext;
import client.zyb.timer.ScheduleTask;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pto.TestProto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author zhang
 * @Date 2022/11/23
 */
public class utils {

    @Autowired
    private ClientContext clientContext;
    private final static Logger logger = LogUtil.getLogger(utils.class);

    public  TestProto.KafkaMsg.Builder getKafkaMsg(IConnection iConnection, TestProto.TaskShell.Builder taskShell )
    {
        TestProto.ConnectionResulte.Builder responseBuilder = iConnection.sendRequest();
        TestProto.KafkaMsg.Builder kafkaMag = null;
        kafkaMag.setUserId(clientContext.getUserId());
        kafkaMag.setTaskId(clientContext.getcUser().getDoingTaskId());
        kafkaMag.setShellId(taskShell.getShellId());
        kafkaMag.setIp(taskShell.getIp());
        kafkaMag.setPort(taskShell.getPort());
        kafkaMag.setCostTime(responseBuilder.getTime());
        kafkaMag.setRequestMsg(taskShell.getBody());
        kafkaMag.setResponseMsg(responseBuilder.getBody());//金雨给我
        kafkaMag.setSuccess(getSuccess(responseBuilder.getType()));//金雨给我
        return kafkaMag;
    }

    //判断测试结果数据。除了失败成功后期可加别的状态
    public boolean getSuccess(String x)
    {
        if (x.equals("1"))
        {
            return true;
        }else
        {
            return false;
        }
    }

    //计算开始时间
    public  long getDelay(long startTime, long nowSecond, long intervalTime)
    {
        return startTime - nowSecond + intervalTime;
    }

    //失败数量结果统计
    int successNum = 0,failNum = 0,failureRate = 0;
    int maximumContinuous = 0,continuous = 0;//最大连续数
    int monitor = 0;//返回数据状态监控值
    public TestProto.ResponseMsg.Builder getresult(ScheduledExecutorService ScheduledExecutorService, ScheduleTask scheduleTask)
    {
        while (true)
        {
            //判断定时任务是否暂停
            if (ScheduledExecutorService.isShutdown())
            {
                break;
            }
            //失败数量概率统计
            List<Boolean> resultStatistics = scheduleTask.getResultStatistics();

            //初始统计
            if (resultStatistics.size()==0)
            {
                try {
                    Thread.sleep(100);//毫秒
                } catch (InterruptedException e) {
                    /**
                     * 异常日志处理
                     */
                    logger.info(LogBuilder.initLog(LogMsg.THREAD, OptionDetails.THREAD_SLEEP));
                }
                monitor++;
                if (monitor>10)//判断无返回状态次数是否大于10； TOO
                {
                    TestProto.ResponseMsg.Builder msg = null;
                    msg.setStatus(false);
                    msg.setMsg("测试任务不能顺利进行请排查错误,即将取消任务");
                    return msg;
                }
                continue;
            }
            //失败数量概率统计
            for (int i = 0; i <resultStatistics.size() ; i++) {
                Boolean result = resultStatistics.get(i);
                if(result==true){
                    successNum++;
                }else {
                    failNum++;
                }
            }
            failureRate = failNum*100/(successNum+failNum);

            //连输失败数量统计（滑动窗口）
            ArrayList<Integer> arrayList = new ArrayList();
            for (int i = 0; i <resultStatistics.size() ; i++) {

                if (i==resultStatistics.size()-1&&!resultStatistics.get(i))
                {
                    continuous++;
                    arrayList.add(continuous);
                }
                if (!resultStatistics.get(i))
                {
                    continuous++;
                }
                else {
                    arrayList.add(continuous);
                    continuous = 0;

                }
            }
            for (int i = 0; i <arrayList.size() ; i++) {
                if (arrayList.get(i)>maximumContinuous)
                    maximumContinuous = arrayList.get(i);
            }


            if(maximumContinuous>15||failureRate>20)
            {
                TestProto.ResponseMsg.Builder msg = null;
                msg.setStatus(false);
                msg.setMsg("测试结果错误数量超过百分之20，连续测试错误15次以上，即将取消任务");
                return msg;
            }
        }
        TestProto.ResponseMsg.Builder msg = null;
        msg.setStatus(true);
        msg.setMsg("成功");
        return msg;
    }
}
