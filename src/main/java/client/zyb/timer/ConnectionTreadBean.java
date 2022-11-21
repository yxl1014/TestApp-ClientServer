package client.zyb.timer;

import client.ljy.net.myconnection.IConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pto.TestProto;

/**
 * @Author zhang
 * @Date 2022/11/21
 * IConnection iConnection, TestProto.TaskShell.Builder taskShell
 */
@Configuration
public class ConnectionTreadBean {

    @Bean
    public ConnectionThread getConnectionThread()
    {
        return new  ConnectionThread();
    }
}
