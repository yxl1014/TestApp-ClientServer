package client.ljy.util;

import java.io.IOException;

public class TestServer {
    public static void main(String[] args) throws IOException {

        //TCP测试
/*
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            Socket socket = serverSocket.accept();
            //通过inputstream接收client发送过来的消息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = null;
            //通过outputstream给client发送消息
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while ((msg = reader.readLine()) != null) {
                System.out.println("get message from client: " + msg);
                writer.write(msg + "\n");
                writer.flush();
            }
        }
*/

/*
        //UDP测试
        DatagramSocket socket = new DatagramSocket(9999);  // 1.创建服务器端DatagramSocket，指定端口
        // 2.创建数据报，用于接收客户端发送的数据
        byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
        DatagramPacket packet = new DatagramPacket(data, data.length);

        // 3.接收客户端发送的数据
        System.out.println("****服务器端已经启动，等待客户端发送数据");//输出提示信息
        while (true) {//通过循环不停的向客户端发送数据和接收数据
            socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
            // 4.读取数据
            String info = new String(data, 0, packet.getLength());//创建字符串对象
            System.out.println("我是服务器，客户端说：" + info);//输出提示信息

            *//*
             * 向客户端响应数据
             *//*
            // 1.定义客户端的地址、端口号、数据
            InetAddress address = packet.getAddress();//获取发送端的地址
            int port = packet.getPort();//获取 发送端进程所绑定的端口
            Scanner scanner = new Scanner(System.in);//从键盘接受数据
            String send = scanner.nextLine();//nextLine方式接受字符串
            byte[] data2 = send.getBytes();//将接收到的数据转换为字节数组
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);// 2.创建数据报，包含响应的数据信息
            socket.send(packet2); // 3.响应客户端
        }
*/
        //http


    }
}
