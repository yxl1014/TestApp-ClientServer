package client.ljy.net.myconnection;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import org.apache.logging.log4j.Logger;
import pto.TestProto;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class toUserByUdp implements IConnection {
    private final static Logger logger = LogUtil.getLogger(toUserByUdp.class);
    private DatagramPacket dp;
    private InetAddress inetAddress;
    private DatagramSocket ds ;
    private DatagramPacket dp1 ;

    @Override
    public void setParameter(TestProto.TaskShell.Builder shell) {
        byte[] data=shell.getBodyBytes().toByteArray();
        try {
            inetAddress= InetAddress.getByName(shell.getIp());
        } catch (UnknownHostException e) {

            throw new RuntimeException(e);
        }
        dp=new DatagramPacket(data,data.length,inetAddress, Integer.parseInt(shell.getPort()));

    }


    @Override
    public void sendRequest() {
        long start = System.currentTimeMillis();

        try {
            ds=new DatagramSocket();
            ds.send(dp);
            //      data1 用来接受反馈的信息
            byte[] data1=new byte[1024];
            dp1=new DatagramPacket(data1,data1.length);
            ds.receive(dp1);
            int length =dp1.getLength();
            System.out.println(new String(data1,0,length));
        } catch (Exception e) {
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_SEND_UDP_FAIL));
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("Total Time：%d ms", end - start));
    }
}


/*    @Override
    public void sendConnect(Object connection) throws IOException {
        byte[] data =" hello,udp！".getBytes();
        InetAddress inetAddress = null;
        inetAddress = InetAddress.getByName("127.0.0.1");
        DatagramPacket dp = new DatagramPacket(data,data.length,inetAddress,6000);
//      实例化，发送到端口6000；
        DatagramSocket ds = new DatagramSocket();
        ds.send(dp);
//      data1 用来接受反馈的信息
        byte[] data1 =new byte[1024];
        DatagramPacket dp1 = new DatagramPacket(data1,data1.length);
        ds.receive(dp1);

        int length =dp1.getLength();
        System.out.println(new String(data1,0,length));
        ds.close();
    }

    @Override
    public Connection setParameter(BuilderTask builder) {
        return null;
    }*/

