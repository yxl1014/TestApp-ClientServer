package client.ly.service;

import client.common.resource.PublicData;
import client.common.util.ProtocolUtil;
import client.yxl.context.ClientContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pto.TestProto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class SendHttp {

    @Autowired
    private PublicData publicData;

    @Autowired
    private ClientContext clientContext;


    public String sendHttp_login(byte[] data, String controller) {
        String url1= "http://" + publicData.getLOGIN_SERVER_IP() + ":" + publicData.getLOGIN_SERVER_PORT() + "/user/";

        OutputStream out = null;
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url1 + controller);
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
            TestProto.S2C_Login s2C_login = TestProto.S2C_Login.parseFrom(bytes);
            result.append(s2C_login.getStatus()).append("_").append(s2C_login.getMsg());
            clientContext.onLogin(s2C_login);

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

        return result.toString();

    }

    public String sendHttp_Register(byte[] data, String controller) {
        String url1= "http://" + publicData.getLOGIN_SERVER_IP() + ":" + publicData.getLOGIN_SERVER_PORT() + "/user/";
        OutputStream out = null;
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url1 + controller);
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
            TestProto.S2C_Register s2C_rester = TestProto.S2C_Register.parseFrom(bytes);
            result.append(s2C_rester.getStatus()).append("_").append(s2C_rester.getMsg());


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

        return result.toString();
    }

    public String sendHttp_updatepwd(byte[] data, String controller) {
        String url1= "http://" + publicData.getLOGIN_SERVER_IP() + ":" + publicData.getLOGIN_SERVER_PORT() + "/user/";
        OutputStream out = null;
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url1 + controller);
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
            TestProto.S2C_UpdateAll s2C_updatePwd = TestProto.S2C_UpdateAll.parseFrom(bytes);
            result.append(s2C_updatePwd.getStatus()).append("_").append(s2C_updatePwd.getMsg());


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

        return result.toString();
    }

    public String sendHttp_updateEmailByTle(byte[] data, String controller) {
        String url1= "http://" + publicData.getLOGIN_SERVER_IP() + ":" + publicData.getLOGIN_SERVER_PORT() + "/user/";
        OutputStream out = null;
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url1 + controller);
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
            TestProto.S2C_UpdateTel s2C_updateTel = TestProto.S2C_UpdateTel.parseFrom(bytes);
            result.append(s2C_updateTel.getStatus()).append("_").append(s2C_updateTel.getMsg());


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

        return result.toString();
    }



    public String sendHttp_bindMailbox(byte[] data, String controller) {
        String url1= "http://" + publicData.getLOGIN_SERVER_IP() + ":" + publicData.getLOGIN_SERVER_PORT() + "/user/";
        OutputStream out = null;
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url1 + controller);
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
            TestProto.S2C_BindMailBox s2C_updateTel = TestProto.S2C_BindMailBox.parseFrom(bytes);
            result.append(s2C_updateTel.getStatus()).append("_").append(s2C_updateTel.getMsg());


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

        return result.toString();
    }


    public String sendHttp_checkMailbox(byte[] data, String controller) {
        String url1= "http://" + publicData.getLOGIN_SERVER_IP() + ":" + publicData.getLOGIN_SERVER_PORT() + "/user/";
        OutputStream out = null;
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url1 + controller);
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
            TestProto.S2C_CheckMailBox s2C_updateTel = TestProto.S2C_CheckMailBox.parseFrom(bytes);
            result.append(s2C_updateTel.getStatus()).append("_").append(s2C_updateTel.getMsg());


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

        return result.toString();
    }

}