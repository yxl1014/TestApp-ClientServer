package client.ljy.net.factory;


import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.ljy.net.myconnection.IConnection;
import client.ljy.net.myconnection.toUserByHttp;
import client.ljy.net.myconnection.toUserByTcp;
import client.ljy.net.myconnection.toUserByUdp;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pto.TestProto;


@Component
public class ConnectionFactory {
    private final static Logger logger = LogUtil.getLogger(ConnectionFactory.class);


    public IConnection createHttp(TestProto.TaskShell.Builder shell) {
        IConnection toUserByHttp = new toUserByHttp();
        toUserByHttp.setParameter(shell);
        return toUserByHttp;
    }

    public IConnection createUdp(TestProto.TaskShell.Builder shell) {
        toUserByUdp toUserByUdp = new toUserByUdp();
        toUserByUdp.setParameter(shell);
        return toUserByUdp;
    }

    public IConnection createTcp(TestProto.TaskShell.Builder shell) {
        toUserByTcp toUserByTcp = new toUserByTcp();
        toUserByTcp.setParameter(shell);
        return toUserByTcp;
    }

    public IConnection connection(TestProto.TaskProtocl type, TestProto.TaskShell.Builder shell) {
        if (type.equals(TestProto.TaskProtocl.HTTP)) {
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_CREATE_HTTP_OK));
            return createHttp(shell);
        } else if (type.equals(TestProto.TaskProtocl.UDP)) {
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_CREATE_UDP_OK));
            return createUdp(shell);
        } else if (type.equals(TestProto.TaskProtocl.TCP)) {
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_CREATE_TCP_OK));
            return createTcp(shell);
        } else {
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_CREATE_ALL_FAIL));
            return null;
        }
    }
}
