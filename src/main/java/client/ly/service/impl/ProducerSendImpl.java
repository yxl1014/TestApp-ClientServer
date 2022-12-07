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

    @Autowired
    private ClientContext clientContext;

    @Override
    public TestProto.ResponseMsg addTask(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Producer/";
        OutputStream out = null;
        InputStream inputStream = null;
        TestProto.S2C_prodAddTask s2C_prodAddTask = null;
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
            s2C_prodAddTask = TestProto.S2C_prodAddTask.parseFrom(bytes);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
            return null;
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
        return s2C_prodAddTask.getMsg();
    }

    @Override
    public TestProto.ResponseMsg startTask(byte[] data, String controller, int taskId) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Producer/";
        OutputStream out = null;
        InputStream inputStream = null;
        TestProto.S2C_ProdStartTask s2C_prodStartTask = null;
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
            s2C_prodStartTask = TestProto.S2C_ProdStartTask.parseFrom(bytes);
            clientContext.onStartTask(taskId);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
            return null;
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
        return s2C_prodStartTask.getMsg();
    }

    @Override
    public TestProto.ResponseMsg endTask(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Producer/";
        OutputStream out = null;
        InputStream inputStream = null;
        TestProto.S2C_prod_EndTask s2C_prod_endTask = null;
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
            s2C_prod_endTask = TestProto.S2C_prod_EndTask.parseFrom(bytes);
            clientContext.onEndTask();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
            return null;
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
        return s2C_prod_endTask.getMsg();
    }

    @Override
    public TestProto.TaskResult getTaskResults(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Producer/";
        OutputStream out = null;
        InputStream inputStream = null;
        TestProto.S2C_prod_GetResult s2C_prod_getResult = null;
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
            s2C_prod_getResult = TestProto.S2C_prod_GetResult.parseFrom(bytes);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
            return null;
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
        return s2C_prod_getResult.getTaskResult();
    }

    @Override
    public TestProto.ProdAddTasks allGetTask(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Producer/";
        OutputStream out = null;
        InputStream inputStream = null;
        TestProto.S2C_prod_GetAllAddTasks s2C_prod_getAllAddTasks = null;
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
            s2C_prod_getAllAddTasks = TestProto.S2C_prod_GetAllAddTasks.parseFrom(bytes);
            clientContext.onGetTask(s2C_prod_getAllAddTasks.getTasks().getTasksList());
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
            return null;
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
        return s2C_prod_getAllAddTasks.getTasks();
    }
}
