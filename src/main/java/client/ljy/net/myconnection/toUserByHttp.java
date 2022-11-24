package client.ljy.net.myconnection;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import client.ljy.net.factory.ConnectionFactory;
import org.apache.logging.log4j.Logger;
import pto.TestProto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class toUserByHttp implements IConnection {


    private final static Logger logger = LogUtil.getLogger(toUserByHttp.class);
    private HttpURLConnection connection;

    private TestProto.TaskShell.Builder shell;

    private TestProto.ConnectionResulte.Builder connectBody;


    @Override
    public TestProto.ConnectionResulte.Builder sendRequest() {

        long start = System.currentTimeMillis();

        StringBuilder result = new StringBuilder();
        BufferedWriter out = null;
        BufferedReader in = null;
        try {
            connection.connect();
            out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out.write(String.valueOf(shell.getBodyBytes()));
            out.flush();
            connection.connect();
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }

        } catch (IOException e) {
            connectBody.setType("2");
            logger.info(LogBuilder.initLog(LogMsg.NET, OptionDetails.CONNECTION_SEND_HTTP_FAIL));
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        connectBody.setType("1");
        connectBody.setBody(result.toString());
        connectBody.setTime(end-start);
        System.out.println(String.format("Total Time：%d ms", end - start));

        return connectBody;
    }



    @Override
    public void setParameter(TestProto.TaskShell.Builder shell) {
        this.shell = shell;
        try {
            URL realUrl = new URL("http://" + shell.getIp() + ":" + shell.getPort()+"/");
            connection = (HttpURLConnection) realUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            if (shell.getHeadsCount()!=0){
                for (Map.Entry<String,String> entry:shell.getHeadsMap().entrySet()){
                    connection.setRequestProperty(entry.getKey(),entry.getValue());
                }
            }

//            // 设置请求类型为 application/json
//            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            // 设置可接受的数据类型
//            connection.setRequestProperty("Accept", "*/*");
//            // 设置保持长链接
//            connection.setRequestProperty("Connection", "Keep-Alive");
//            // 设置自定义头 Token
//            connection.setRequestProperty("Token", "123456");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
