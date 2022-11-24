package client.zyb.timer.bean;

import client.zyb.timer.ScheduleTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhang
 * @Date 2022/11/23
 */
@Configuration
public class ScheduleTaskBean {
    @Bean
    public ScheduleTask getScheduleTask()
    {
        return new ScheduleTask();
    }
}
