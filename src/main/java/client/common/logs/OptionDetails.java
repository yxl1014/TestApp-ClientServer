package client.common.logs;

/**
 * @author yxl
 * @date: 2022/9/12 下午1:10
 */
public enum OptionDetails {

    //测试
    TEST_OK("测试", "成功", "无敌"),
    KAFKA_CONSUMER_TEST("kafka监听test","成功","收到的数据"),

    //---------------------LoginServer---------------------


    //自定义协议
    PROTOCOL_ERROR("协议错误", "失败", "协议错误"),
    PROTOBUF_ERROR("PROTO数据错误", "失败", "PROTO数据错误"),


    //系统
    PARAM_ERROR("参数异常", "失败", "参数异常"),
    SYSTEM_ERROR("系统错误", "失败", "系统错误"),


    //拦截器
    NO_CONTROLLER("拦截器错误", "失败", "没有此接口"),
    NO_TOKEN("拦截器错误", "失败", "没有携带token"),
    TOKEN_ERROR("拦截器错误", "失败", "token无效"),
    TOKEN_EXPIRES("拦截器错误", "失败", "token已过期"),


    NO_CHECK("拦截器日志", "成功", "访问无CHECK接口"),
    CHECK("拦截器日志", "成功", "访问有CHECK接口"),
    NO_PRODUCER("拦截器日志", "成功", "访问无Producer接口"),
    NO_Consumer("拦截器日志", "成功", "访问无Consumer接口"),
    PRODUCER("拦截器日志", "成功", "访问有Producer接口"),
    Consumer("拦截器日志", "成功", "访问有Consumer接口"),
    CHECK_OK("拦截器日志", "成功", "token验证成功"),


    //登录
    LOGIN_OK("登录", "成功", "登录成功"),
    UPDATE_IP("登录", "成功", "Ip地址发生变化"),
    LOGIN_TEL_PWD_ERROR("登陆", "失败", "电话或密码错误"),
    LOGIN_EMAIL_PWD_ERROR("登陆", "失败", "邮箱或密码错误"),
    LOGIN_TOKEN_ERROR("登录", "失败", "token验证失败"),


    //注册
    REGISTER_TEL_EXIST("注册", "失败", "此电话已存在"),
    REGISTER_OK("注册", "成功", "注册成功"),


    //过滤操作
    FILTER_MSG("过滤操作", "成功", "token不为空"),
    FILTER_MSG_ERROR("过滤操作", "成功", "token为空"),
    FILTER_MSG_OK("过滤操作", "成功", "token验证通过"),

    //身份验证操作
    IDENTITY_administrators("验证操作", "成功", "管理员访问"),
    IDENTITY_USER("验证操作", "成功", "普通用户访问被拦截"),


    //---------------------MainServer---------------------
    //工具类报错
    //Task
    TASK_EXIST("添加任务", "失败", "任务已存在"),
    TASK_NOT_FOUND("查询任务", "失败", "任务不存在"),
    P_TASK_START_OK("生产者开始任务", "成功", "生产者开始任务成功"),
    P_TASK_EXIST("生产者开始任务", "失败", "任务已经开始"),
    P_TASK_NOT_FOUND("生产者开始任务", "失败", "任务不存在"),
    P_TASK_NO_START("生产者结束任务", "失败", "任务未开始"),

    P_TASK_ADD_OK("生产者发布任务", "成功", "发布成功"),
    P_GET_ADD_TASKS_NO_USER("生产者获取发布的任务", "失败", "该用户不存在"),

    C_TAKE_IS_TAKE("消费者接受任务", "失败", "已接受该任务"),
    C_TAKE_TASK_OK("消费者接受任务", "成功", "接受成功"),
    C_START_NO_TAKE("消费者开始任务", "失败", "没有接受该任务"),
    C_START_TASK_NOT_START("消费者开始任务", "失败", "该任务没有开始"),
    C_START_TASK_OK("消费者接受任务", "成功", "任务开始成功"),
    C_END_TASK_NO_DOING("消费者结束任务", "失败", "没有正在执行的任务"),
    C_END_TASK_OK("消费者结束任务", "成功", "任务结束成功"),
    C_DEL_TASK_NO_EXIST("消费者放弃任务", "失败", "该用户没有接受该任务"),
    C_DEL_TASK_OK("消费者放弃任务", "成功", "任务放弃成功"),

    USER_NOT_FOUND("获取用户信息失败", "失败", "用户不存在"),
    PUBLIC_CONDUCT_NOT_FOUND("获取任务进行时失败", "失败", "任务进行时不存在"),

    //数据接口:PROD
    ACCESS_DATA_INTERFACE_ERROR("访问数据接口", "失败", "访问数据解析错误"),
    ACCESS_DATA_INTERFACE_NULL("访问数据接口", "失败", "访问数据为空"),
    ADD_TASK_USERID_TRUE("添加任务", "成功", "UserId无误，任务添加成功"),
    ADD_TASK_USERID_FLOAT("添加任务", "失败", "UserId错误，添加任务失败"),
    END_TASK_USERID_TRUE("关闭任务", "成功", "数据解析成功,任务关闭成功"),
    END_TASK_USERID_FLOAT("关闭任务", "失败", "数据解析失败,关闭任务失败"),
    PROD_TASK_START_TRUE("开始任务", "成功", "数据解析成功，任务开始成功"),
    PROD_TASK_START_FLOAT("开始任务", "失败", "数据解析失败，任务开始失败"),
    PROD_GetResult_TRUE("获取任务测试结果集", "成功", "数据解析成功，获取结果成功"),
    PROD_GetResult_FLOAT("获取任务测试结果集", "失败", "数据解析失败，获取结果失败"),
    PROD_Get_Task_TRUE("获取任务信息", "成功", "数据解析成功，获取任务信息结果成功"),
    PROD_Get_Task_FLOAT("获取任务信息", "失败", "数据解析失败，获取任务信息结果失败"),
    PROD_GET_ALL_ADD_TASKS_TRUE("获取发布的所有任务的详细信息", "成功", "数据解析成功，获取所有任务信息结果成功"),
    PROD_GET_ALL_ADD_TASKS_FLOAT("获取任务信息", "失败", "数据解析失败，获取所有任务信息结果失败"),

    //CONS_
    CONS_TAKE_TASk_TRUE("消费者接受任务", "成功", "数据解析成功，消费者接受任务成功"),
    CONS_TAKE_TASk_FLOAT("消费者接受任务", "失败", "数据解析失败，消费者接受任务失败"),
    CONS_START_TASk_TRUE("消费者开始任务", "成功", "数据解析成功，消费者开始任务成功"),
    CONS_START_TASk_FLOAT("消费者开始任务", "失败", "数据解析失败，消费者开始任务失败"),
    CONS_END_TASk_TRUE("消费者结束任务", "成功", "数据解析成功，消费者结束任务成功"),
    CONS_END_TASk_FLOAT("消费者结束任务", "失败", "数据解析失败，消费者结束任务失败"),
    CONS_DEL_TASk_TRUE("消费者放弃任务", "成功", "数据解析成功，消费者放弃任务成功"),
    CONS_DEL_TASk_FLOAT("消费者放弃任务", "失败", "数据解析失败，消费者放弃任务失败"),
    CONS_ALL_GET_TASkS_TRUE("消费者获取接受的所有任务的详细信息", "成功", "数据解析成功，消费者获取接受的所有任务的详细信息成功"),
    CONS_ALL_GET_TASkS_FLOAT("消费者放弃任务", "失败", "数据解析失败，消费者获取接受的所有任务的详细信息失败"),

    //定时器
    SCHEDULED_EXECUTE_START("任务开始执行", "成功", "成功"),
    SCHEDULED_EXECUTE_OVER("任务执行完成", "成功", "成功"),

    //Lru
    LRU_GET_LIST_OK("获取List", "成功", "成功"),
    LRU_GET_VALUE_NULL("获取value", "失败", "该任务Id不存在"),
    LRU_GET_VALUE_OK("获取value", "成功", "成功"),
    LRU_PUT_TASK_OK("存放TASK", "成功", "成功"),


    //KAFKA
    KAFKA_CONSUMER_TOPIC_CLOSE_NO_TOPIC("关闭kafka监听器", "失败", "该topic不存在"),
    KAFKA_CONSUMER_TOPIC_CLOSE_OK("关闭kafka监听器", "成功", "成功"),
    KAFKA_CONSUMER_TOPIC_START_ERROR("启动监听线程", "失败", "线程启动失败"),
    KAFKA_CONSUMER_TOPIC_START_OK("启动监听线程", "成功", "线程启动成功"),

    KAFKA_CONSUMER_TOPIC_START_EXIST_OK("启动监听线程", "成功", "该topic存在,关闭"),

    KAFKA_CONSUMER_TOPIC_GET_EXIST("获取监听线程", "成功", "该队列已存在,直接返回"),
    KAFKA_CONSUMER_TOPIC_GET_NO_EXIST("获取监听线程", "成功", "该队列不存在,初始化一个"),
    KAFKA_PRODUCER_CREATE_IS_EXIST("创建producer对象", "失败", "该对象已存在"),
    KAFKA_PRODUCER_CREATE_SUCCESS("创建producer对象", "成功", "创建成功"),

    KAFKA_PRODUCER_CLOSE_NOT_EXIST("关闭producer对象", "失败", "该对象不存在"),
    KAFKA_PRODUCER_CLOSE_SUCCESS("关闭producer对象", "成功", "关闭成功"),

    KAFKA_PRODUCER_SEND_NO_PRODUCER("发送信息", "失败", "producer对象不存在"),
    KAFKA_PRODUCER_SEND_SUCCESS("发送信息", "成功", "发送成功"),

    CONTROL_START_S2C_TOPIC("启动同步调用推送", "启动", "启动"),
    CONTROL_RE_CONTROL_ERROR_CONDUCT_NOT_EXIST("重新分配任务","失败","进行时上下文不存在"),

    CONTROL_RE_CONTROL_ERROR_KAFKA_CONTEXT_NOT_EXIST("重新分配任务","失败","任务上下文不存在"),

    CONTROL_ALL_START("重新分配所有正在进行的任务","开始","开始重新分配所有正在进行的任务"),
    CONTROL_ALL_END("重新分配所有正在进行的任务","结束","重新分配所有正在进行的任务结束"),
    CONTROL_ONE_START("重新分配一个正在进行的任务","开始","开始重新分配一个正在进行的任务"),
    CONTROL_ONE_END("重新分配一个正在进行的任务","成功","重新分配一个正在进行的任务结束"),

    //es
    ELASTICSEARCH_SEARCH("es数据搜索","失败","可能是无此映射、es服务器异常等"),ELASTICSEARCH_CLOSE("es数据搜索","失败","关闭es连接失败"),
    ELASTICSEARCH_JSON_FORMAT("json解析","失败","json解析失败"),


    //---------------------ClientServer---------------------
    //file
    READ_FILE_NOT_EXIST("读取文件","失败","文件不存在"),
    READ_FILE_IS_NULL("读取文件","失败","文件内容为空"),

    WRITE_FILE_TOKEN_OK("写入文件","成功","token写入成功"),
    //clientContext
    CLIENT_CONTEXT_ON_LOGIN_OK("客户端上下文","成功","onLogin执行成功"),
    CLIENT_CONTEXT_ON_INIT_START("客户端上下文","开始","onInit开始执行"),
    CLIENT_CONTEXT_ON_INIT_OK("客户端上下文","成功","onInit执行成功"),
    CLIENT_CONTEXT_ON_GET_TASK("客户端上下文","成功","onGetTask执行成功"),
    CLIENT_CONTEXT_ON_GET_TASK_LIST("客户端上下文","成功","onGetTask_List执行成功"),

    CLIENT_CONTEXT_ON_START_TASK("客户端上下文","成功","onStartTask执行成功"),
    CLIENT_CONTEXT_ON_END_TASK("客户端上下文","成功","onEndTask执行成功"),
    CLIENT_CONTEXT_ON_LISTENER_SHELL("客户端上下文","成功","onListenerShell执行成功"),
    CLIENT_CONTEXT_ON_GET_USER_MSG("客户端上下文","成功","onGetUserMsg执行成功"),

    //NET
    CONNECTION_CREATE_ALL_FAIL("创建请求","失败","无此协议"),
    CONNECTION_CREATE_HTTP_OK("创建HTTP请求","成功","创建HTTP请求成功"),
    CONNECTION_CREATE_UDP_OK("创建UDP请求","成功","创建UDP请求成功"),
    CONNECTION_CREATE_TCP_OK("创建TCP请求","成功","创建TCP请求成功"),

    CONNECTION_SEND_HTTP_FAIL("发送HTTP请求","失败","发送HTTP请求成功"),
    CONNECTION_SEND_UDP_FAIL("发送UDP请求","失败","发送UDP请求成功"),
    CONNECTION_SEND_TCP_FAIL("发送TCP请求","失败","发送TCP请求成功"),

    //PROTO TO JSON
    KAFKA_PROTO_TO_JSON("数据格式转换","失败","proto转换json失败"),

    //THREAD
    THREAD_SLEEP("线程sleep","失败","可能存在线程中断")


    ;
    private String type;
    private String status;
    private String msg;

    OptionDetails(String type, String status, String msg) {
        this.type = type;
        this.status = status;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAll() {
        return this.status + "---" + this.msg;
    }
}
