package client.ljy.net.factory;


import client.ljy.net.myconnection.IConnection;
import client.ljy.net.myconnection.toUserByHttp;
import client.ljy.net.myconnection.toUserByTcp;
import client.ljy.net.myconnection.toUserByUdp;
import pto.TestProto;

public class ConnectionFactory {
    TestProto.TaskShell.Builder builder=TestProto.TaskShell.newBuilder();

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
            return createHttp(shell);
        }else if(type.equals(TestProto.TaskProtocl.UDP)){
            return createUdp(shell);
        } else if (type.equals(TestProto.TaskProtocl.TCP)) {
            return  createTcp(shell);
        } else{
          return null;
        }
    }
}
