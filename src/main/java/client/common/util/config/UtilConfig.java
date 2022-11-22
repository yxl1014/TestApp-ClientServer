package client.common.util.config;

import client.common.util.ProtocolUtil;
import client.common.util.queue.CircleArrayQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pto.TestProto;

/**
 * @author yxl.testapp.domain.yxl
 * @date: 2022/9/12 下午2:05
 * 工具bean载入配置文件
 */

@Configuration
public class UtilConfig {

    @Value("${local.taskCache.size}")
    private int taskCacheSize;

    @Bean
    public ProtocolUtil getProtocolUtil() {
        return new ProtocolUtil();
    }


    @Bean
    public CircleArrayQueue<TestProto.Task> getTaskQueue() {
        return new CircleArrayQueue<>(this.taskCacheSize);
    }

}
