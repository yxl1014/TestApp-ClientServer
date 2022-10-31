package client.ljy.net.myconnection;

import pto.TestProto;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class toUserByTcp implements IConnection {
    private Socket socket = null;
    private TestProto.TaskShell.Builder shell;
    @Override
    public void sendRequest() {
        long start = System.currentTimeMillis();
        try {
        OutputStream os= socket.getOutputStream();
            os.write(shell.getBodyBytes().toByteArray());
        } catch (IOException e) {
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
