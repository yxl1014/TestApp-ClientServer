package client.zyb.timer.bean;

import client.zyb.util.utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhang
 * @Date 2022/11/23
 */
@Configuration
public class UtilsBean {
    @Bean
    public utils getUtils()
    {
        return new utils();
    }
}
