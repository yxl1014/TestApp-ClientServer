package client.yxl.context;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.common.util.FileCommon;
import client.common.util.JWTUtil;
import client.common.util.queue.CircleArrayQueue;
import client.ljy.net.factory.ConnectionFactory;
import client.ljy.net.myconnection.IConnection;
import client.yxl.kafka.consumer.common.KafkaConsumerConnectPoll;
import client.zyb.timer.Schedule;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pto.TestProto;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Queue;

/**
 * 客户端上下文
 *
 * @author yxl
 * @date: 2022/10/25 下午3:28
 */


@Component
public class ClientContext {

    private static final Logger logger = LogUtil.getLogger(ClientContext.class);

    /**
     * 文件操作类
     */
    private final FileCommon fileCommon;

    /**
     * 本地任务详细信息缓存
     */
    private final CircleArrayQueue<TestProto.Task> taskCacheQueue;


    private final KafkaConsumerConnectPoll consumerPoll;

    public CircleArrayQueue<TestProto.Task> getTaskCacheQueue() {
        return taskCacheQueue;
    }

    /**
     * 用户对象
     */
    private TestProto.User.Builder user;

    public TestProto.User.Builder getUser() {
        return user;
    }

    /**
     * 用户id
     */
    private int userId;

    public int getUserId() {
        return userId;
    }

    /**
     * token
     */
    private String token;


    /**
     * 本地对象任务数据
     */
    private final TestProto.C_User.Builder cUser = TestProto.C_User.newBuilder();

    /**
     * 正在执行的任务shell
     */
    private TestProto.TaskShell.Builder taskShell;

    public TestProto.TaskShell.Builder getTaskShell() {
        return taskShell;
    }

    /**
     * to被测端链接工厂
     */
    private final ConnectionFactory connectionFactory;


    public ClientContext(FileCommon fileCommon,
                         CircleArrayQueue<TestProto.Task> taskCacheQueue,
                         KafkaConsumerConnectPoll consumerPoll,
                         ConnectionFactory connectionFactory,
                         Schedule schedule) {
        this.fileCommon = fileCommon;
        this.taskCacheQueue = taskCacheQueue;
        this.consumerPoll = consumerPoll;
        this.connectionFactory = connectionFactory;
        this.schedule = schedule;
    }

    /**
     * 连续测试请求器
     */
    private final Schedule schedule;

    /**
     * 在服务启动的时候调用这个方法
     */
    @PostConstruct
    public void onInit() {
        logger.info(LogBuilder.initLog(LogMsg.TOKEN, OptionDetails.CLIENT_CONTEXT_ON_INIT_START).log());
        String token = this.fileCommon.getTokenFromLocal();
        if (token == null)
            return;

        byte[] unSign = JWTUtil.unsign(token, byte[].class);

        if (unSign == null) {
            logger.warn(LogBuilder.initLog(LogMsg.TOKEN, OptionDetails.TOKEN_EXPIRES)
                    .build("methodName", "onInit").log());
        }
        TestProto.User u = null;
        try {
            u = TestProto.User.parseFrom(unSign);
        } catch (InvalidProtocolBufferException e) {
            logger.warn(LogBuilder.initLog(LogMsg.PROTO, OptionDetails.PROTOBUF_ERROR)
                    .build("methodName", "onInit").log());
        }
        if (u == null) {
            return;
        }

        this.user = u.toBuilder();
        this.userId = u.getUserId();
        this.token = token;

        logger.info(LogBuilder.initLog(LogMsg.TOKEN, OptionDetails.CLIENT_CONTEXT_ON_INIT_OK).log());
    }

    /**
     * 在用户登录结束之后调用这个方法
     *
     * @param data 登录结果
     */
    public void onLogin(TestProto.S2C_Login data) {
        if (!data.getStatus()) {
            return;
        }
        byte[] unSign = JWTUtil.unsign(data.getToken(), byte[].class);

        if (unSign == null) {
            logger.warn(LogBuilder.initLog(LogMsg.TOKEN, OptionDetails.TOKEN_EXPIRES)
                    .build("methodName", "onLogin").log());
        }
        TestProto.User u = null;
        try {
            u = TestProto.User.parseFrom(unSign);
        } catch (InvalidProtocolBufferException e) {
            logger.warn(LogBuilder.initLog(LogMsg.PROTO, OptionDetails.PROTOBUF_ERROR)
                    .build("methodName", "onLogin").log());
        }
        if (u == null) {
            return;
        }

        this.user = u.toBuilder();
        this.userId = u.getUserId();
        this.token = data.getToken();

        fileCommon.loadTokenToLocal(this.token);
        logger.info(LogBuilder.initLog(LogMsg.TOKEN, OptionDetails.CLIENT_CONTEXT_ON_LOGIN_OK).log());
    }


    /**
     * 在用户接受任务详情是调用
     *
     * @param tasks 任务
     */
    public void onGetTask(List<TestProto.Task> tasks) {
        LogBuilder logBuilder = LogBuilder.initLog(LogMsg.CLIENT_CONTEXT, OptionDetails.CLIENT_CONTEXT_ON_GET_TASK_LIST);
        long start = System.currentTimeMillis();
        for (TestProto.Task task : tasks) {
            this.taskCacheQueue.add(task.getTaskId(), task);
        }
        long end = System.currentTimeMillis();
        logBuilder.build("耗时", end - start);
        logBuilder.log();
    }


    /**
     * 在用户接受任务详情是调用
     *
     * @param task 任务
     */
    public void onGetTask(TestProto.Task task) {
        LogBuilder logBuilder = LogBuilder.initLog(LogMsg.CLIENT_CONTEXT, OptionDetails.CLIENT_CONTEXT_ON_GET_TASK);
        long start = System.currentTimeMillis();
        this.taskCacheQueue.add(task.getTaskId(), task);
        long end = System.currentTimeMillis();
        logBuilder.build("耗时", end - start);
        logBuilder.log();
    }


    /**
     * 在开始任务时执行
     *
     * @param taskId 任务id
     */
    public void onStartTask(int taskId) {
        this.cUser.setDoingTaskId(taskId);
        this.consumerPoll.initKafkaTopic(taskId, this);
        LogBuilder.initLog(LogMsg.CLIENT_CONTEXT, OptionDetails.CLIENT_CONTEXT_ON_START_TASK).log();
    }

    /**
     * 在结束任务时执行
     */
    public void onEndTask() {
        this.consumerPoll.shutdown(this.cUser.getDoingTaskId());
        this.cUser.setDoingTaskId(-1);
        this.taskShell = null;
        LogBuilder.initLog(LogMsg.CLIENT_CONTEXT, OptionDetails.CLIENT_CONTEXT_ON_END_TASK).log();
    }

    /**
     * 在kafka监听到脚本时执行
     *
     * @param taskShell 任务脚本
     */
    public void onListenerShell(TestProto.TaskShell.Builder taskShell) {
        this.taskShell = taskShell;
        TestProto.Task task = this.taskCacheQueue.get(cUser.getDoingTaskId());
        if (task == null) {
            //TODO 如果这个任务不存在则调用ly的方法去mainServer查
        }
        IConnection connection = connectionFactory.connection(task.getTaskProtocl(), taskShell);
        schedule.startSchedule(connection, taskShell,this);
        LogBuilder.initLog(LogMsg.CLIENT_CONTEXT, OptionDetails.CLIENT_CONTEXT_ON_LISTENER_SHELL).log();
    }

    /**
     * 在获取用户数据时调用server端的 sUser
     */
    public void onGetUserMsg(TestProto.S_User sUser) {
        this.cUser.addAllAddTasks(sUser.getAddTasksList());
        this.cUser.addAllGetTasks(sUser.getTaskIdsList());
        LogBuilder.initLog(LogMsg.CLIENT_CONTEXT, OptionDetails.CLIENT_CONTEXT_ON_GET_USER_MSG).log();
    }
}
