package client.ly.service.impl;

import client.common.resource.PublicData;
import client.common.util.ProtocolUtil;
import client.ly.service.ProducerSend;
import client.yxl.context.ClientContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pto.TestProto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class ProducerSendImpl implements ProducerSend {

    @Autowired
    private PublicData publicData;

    private final String url= "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Producer";

    @Autowired
    private ClientContext clientContext;

    @Override
    public byte[] addTask(byte[] data, String controller) {
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1= null;
        try {
            URL realUrl = new URL(url + controller);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "binary/octet-stream");
            connection.connect();

            out = connection.getOutputStream();

            // 发送请求参数，防止中文乱码
            out.write(data);
            // flush输出流的缓冲
            out.flush();

            byte[] b = new byte[102400];
            inputStream = connection.getInputStream();
            int len = inputStream.read(b);
            byte[] bb = new byte[len];
            System.arraycopy(b, 0, bb, 0, len);
            byte[] bytes = new ProtocolUtil().decodeProtocol(bb);
            TestProto.S2C_prodAddTask s2C_prodAddTask = TestProto.S2C_prodAddTask.parseFrom(bytes);
            bytes1=s2C_prodAddTask.toByteArray();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return bytes1;
    }

    @Override
    public byte[] startTask(byte[] data, String controller,int taskId) {
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1= null;
        try {
            URL realUrl = new URL(url + controller);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "binary/octet-stream");
            connection.connect();

            out = connection.getOutputStream();

            // 发送请求参数，防止中文乱码
            out.write(data);
            // flush输出流的缓冲
            out.flush();

            byte[] b = new byte[102400];
            inputStream = connection.getInputStream();
            int len = inputStream.read(b);
            byte[] bb = new byte[len];
            System.arraycopy(b, 0, bb, 0, len);
            byte[] bytes = new ProtocolUtil().decodeProtocol(bb);
            TestProto.S2C_ProdStartTask s2C_prodStartTask = TestProto.S2C_ProdStartTask.parseFrom(bytes);
            clientContext.onStartTask(taskId);
            bytes1=s2C_prodStartTask.toByteArray();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return bytes1;
    }

    @Override
    public byte[] endTask(byte[] data, String controller) {
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1= null;
        try {
            URL realUrl = new URL(url + controller);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "binary/octet-stream");
            connection.connect();

            out = connection.getOutputStream();

            // 发送请求参数，防止中文乱码
            out.write(data);
            // flush输出流的缓冲
            out.flush();

            byte[] b = new byte[102400];
            inputStream = connection.getInputStream();
            int len = inputStream.read(b);
            byte[] bb = new byte[len];
            System.arraycopy(b, 0, bb, 0, len);
            byte[] bytes = new ProtocolUtil().decodeProtocol(bb);
            TestProto.S2C_prod_EndTask s2C_prod_endTask = TestProto.S2C_prod_EndTask.parseFrom(bytes);
            clientContext.onEndTask();
            bytes1=s2C_prod_endTask.toByteArray();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return bytes1;
    }

    @Override
    public byte[] getTaskResults(byte[] data, String controller) {
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1= null;
        try {
            URL realUrl = new URL(url + controller);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "binary/octet-stream");
            connection.connect();

            out = connection.getOutputStream();

            // 发送请求参数，防止中文乱码
            out.write(data);
            // flush输出流的缓冲
            out.flush();

            byte[] b = new byte[102400];
            inputStream = connection.getInputStream();
            int len = inputStream.read(b);
            byte[] bb = new byte[len];
            System.arraycopy(b, 0, bb, 0, len);
            byte[] bytes = new ProtocolUtil().decodeProtocol(bb);
            TestProto.S2C_prod_GetResult s2C_prod_getResult = TestProto.S2C_prod_GetResult.parseFrom(bytes);
            bytes1=s2C_prod_getResult.toByteArray();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return bytes1;
    }

    @Override
    public byte[] allGetTask(byte[] data, String controller) {
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1= null;
        try {
            URL realUrl = new URL(url + controller);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "binary/octet-stream");
            connection.connect();

            out = connection.getOutputStream();

            // 发送请求参数，防止中文乱码
            out.write(data);
            // flush输出流的缓冲
            out.flush();

            byte[] b = new byte[102400];
            inputStream = connection.getInputStream();
            int len = inputStream.read(b);
            byte[] bb = new byte[len];
            System.arraycopy(b, 0, bb, 0, len);
            byte[] bytes = new ProtocolUtil().decodeProtocol(bb);
            TestProto.S2C_prod_GetAllAddTasks s2C_prod_getAllAddTasks = TestProto.S2C_prod_GetAllAddTasks.parseFrom(bytes);
            clientContext.onGetTask(s2C_prod_getAllAddTasks.getTasks().getTasksList());
            bytes1=s2C_prod_getAllAddTasks.toByteArray();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return bytes1;
    }
}
