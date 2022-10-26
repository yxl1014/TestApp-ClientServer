package client.yxl.context;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.common.util.FileCommon;
import client.common.util.JWTUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pto.TestProto;

/**
 * 客户端上下文
 *
 * @author yxl
 * @date: 2022/10/25 下午3:28
 */


@Component
@Getter
public class ClientContext {

    private static final Logger logger = LogUtil.getLogger(ClientContext.class);

    private final FileCommon fileCommon;

    /**
     * 用户对象
     */
    private TestProto.User.Builder user;

    /**
     * 用户id
     */
    private int userId;


    /**
     * token
     */
    private String token;

    public ClientContext(FileCommon fileCommon) {
        this.fileCommon = fileCommon;
    }

    /**
     * 在服务启动的时候调用这个方法
     */
    public void onInit() {
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
}
