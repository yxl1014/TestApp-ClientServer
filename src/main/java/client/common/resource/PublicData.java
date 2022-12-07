package client.common.resource;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 获取yml中配置的参数
 * 像一些可变的参数就可以写在这个里面 比如连接池的大小 就可以写在yml里注入到这个里面
 * @author yxl
 * @date: 2022/10/24 下午4:41
 */


@Configuration
@Data
public class PublicData {

    @Value("${mainServer.port}")
    private String MAIN_SERVER_PORT;

    @Value("${mainServer.ip}")
    private String MAIN_SERVER_IP;

    @Value("${loginServer.port}")
    private String LOGIN_SERVER_PORT;

    @Value("${loginServer.ip}")
    private String LOGIN_SERVER_IP;
}
