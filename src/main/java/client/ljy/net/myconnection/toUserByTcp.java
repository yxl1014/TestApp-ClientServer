package client.ljy.net.myconnection;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import org.apache.logging.log4j.Logger;
import pto.TestProto;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class toUserByTcp implements IConnection {
    private final static Logger logger = LogUtil.getLogger(toUserByTcp.class);
    private Socket socket = null;
    private TestProto.TaskShell.Builder shell;
    @Override
    public void sendRequest() {
        long start = System.currentTimeMillis();
        try {
        OutputStream os= socket.getOutputStream();
            os.write(shell.getBodyBytes().toByteArray());
        } catch (IOException e) {
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_SEND_UDP_FAIL));
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("Total Time：%d ms", end - start));
    }


    @Override
    public void setParameter(TestProto.TaskShell.Builder shell) {
        this.shell=shell;
        try {
            socket = new Socket(shell.getIp(), Integer.parseInt(shell.getPort()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
