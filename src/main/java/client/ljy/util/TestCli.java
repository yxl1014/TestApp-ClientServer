package client.ljy.util;


import client.ljy.net.factory.ConnectionFactory;
import client.ljy.net.myconnection.IConnection;
import pto.TestProto;

import java.io.IOException;

public class TestCli {
    public static void main(String[] args) throws IOException {

        TestProto.TaskShell.Builder builder=TestProto.TaskShell.newBuilder();
        builder.setIp("localhost");
        builder.setPort("9999");
        builder.putHeads("Connection", "Keep-Alive");
        builder.setBody("xxx");

        TestProto.TaskProtocl xxx=TestProto.TaskProtocl.HTTP;
        //http/udp/tct
        IConnection connection = ConnectionFactory.connection(xxx,builder);
        
        connection.setParameter(builder);

        //发送
        connection.sendRequest();

    }
}
