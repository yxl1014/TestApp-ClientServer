package client.ly.service.impl;

import client.common.resource.PublicData;
import client.common.util.ProtocolUtil;
import client.ly.service.ConsumerSend;
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
public class ConsumerSendImpl implements ConsumerSend {

    @Autowired
    private PublicData publicData;

    @Autowired
    private ClientContext clientContext;

    @Override
    public TestProto.Task getTask(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Consumer/";
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1 = null;
        TestProto.S2C_Get_Task s2C_get_task = null;
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
            s2C_get_task = TestProto.S2C_Get_Task.parseFrom(bytes);
            clientContext.onGetTask(s2C_get_task.getTask());
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
        return s2C_get_task.getTask();
    }

    @Override
    public TestProto.ResponseMsg takeTask(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Consumer/";
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1 = null;
        TestProto.S2C_Cons_TakeTask s2C_cons_takeTask = null;
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
            s2C_cons_takeTask = TestProto.S2C_Cons_TakeTask.parseFrom(bytes);
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
        return s2C_cons_takeTask.getMsg();
    }

    @Override
    public TestProto.ResponseMsg startTask(byte[] data, String controller, int taskId) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Consumer/";
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1 = null;
        TestProto.S2C_Cons_StartTask s2C_cons_startTask = null;
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
            s2C_cons_startTask = TestProto.S2C_Cons_StartTask.parseFrom(bytes);
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
        return s2C_cons_startTask.getMsg();
    }

    @Override
    public TestProto.ResponseMsg endTask(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Consumer/";
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1 = null;
        TestProto.S2C_Cons_EndTask s2C_cons_endTask = null;
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
            s2C_cons_endTask = TestProto.S2C_Cons_EndTask.parseFrom(bytes);
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
        return s2C_cons_endTask.getMsg();
    }

    @Override
    public TestProto.ResponseMsg delTask(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Consumer/";
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1 = null;
        TestProto.S2C_Cons_DelTask s2C_cons_delTask = null;
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
            s2C_cons_delTask = TestProto.S2C_Cons_DelTask.parseFrom(bytes);
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
        return s2C_cons_delTask.getMsg();
    }

    @Override
    public TestProto.ConsGetTasks allGetTask(byte[] data, String controller) {
        String url = "http://" + publicData.getMAIN_SERVER_IP() + ":" + publicData.getMAIN_SERVER_PORT() + "/Consumer/";
        OutputStream out = null;
        InputStream inputStream = null;
        byte[] bytes1 = null;
        TestProto.S2C_Cons_AllGetTasks s2C_cons_allGetTasks = null;
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
            s2C_cons_allGetTasks = TestProto.S2C_Cons_AllGetTasks.parseFrom(bytes);
            clientContext.onGetTask(s2C_cons_allGetTasks.getTasks().getTasksList());
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
        return s2C_cons_allGetTasks.getTasks();
    }
}
