package client.ljy.net.factory;


import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.ljy.net.myconnection.IConnection;
import client.ljy.net.myconnection.toUserByHttp;
import client.ljy.net.myconnection.toUserByTcp;
import client.ljy.net.myconnection.toUserByUdp;
import client.yxl.kafka.producer.KafkaProducerPoll;
import client.zyb.timer.Schedule;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pto.TestProto;




public class ConnectionFactory {

    @Autowired
    private Schedule schedule;
    TestProto.TaskShell.Builder builder=TestProto.TaskShell.newBuilder();

    private final static Logger logger = LogUtil.getLogger(ConnectionFactory.class);


    public static IConnection createHttp(TestProto.TaskShell.Builder shell){
        IConnection toUserByHttp=new toUserByHttp();
        toUserByHttp.setParameter(shell);
        return toUserByHttp;
    }
    public static IConnection createUdp(TestProto.TaskShell.Builder shell){
        toUserByUdp toUserByUdp=new toUserByUdp();
        toUserByUdp.setParameter(shell);
        return toUserByUdp;
    }

    public static IConnection createTcp(TestProto.TaskShell.Builder shell){
        toUserByTcp toUserByTcp=new toUserByTcp();
        toUserByTcp.setParameter(shell);
        return toUserByTcp;
    }

    public static IConnection connection(TestProto.TaskProtocl type,TestProto.TaskShell.Builder shell){
        if(type.equals(TestProto.TaskProtocl.HTTP)){
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_CREATE_HTTP_OK));
            return createHttp(shell);
        }else if(type.equals(TestProto.TaskProtocl.UDP)){
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_CREATE_UDP_OK));
            return createUdp(shell);
        } else if (type.equals(TestProto.TaskProtocl.TCP)) {
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_CREATE_TCP_OK));
            return  createTcp(shell);
        } else{
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_CREATE_ALL_FAIL));
          return null;
        }
    }
}
