package client.zyb.timer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhang
 * @Date 2022/11/1
 */
@Configuration
public class ScheduleBean {
    @Bean
    public Schedule getSchedule()
    {
        return new Schedule();
    }
}
