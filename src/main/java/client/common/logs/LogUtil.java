package client.common.logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pto.TestProto;

/**
 * @author yxl
 * @date: 2022/9/12 下午1:05
 */
public class LogUtil {
    public static Logger getLogger(Class<?> clz) {
        return LogManager.getLogger(clz);
    }
}
