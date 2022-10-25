package client.common.logs;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author yxl
 * @date: 2022/10/25 下午2:41
 */

public class LogClass {

    private StringBuilder builder = new StringBuilder();

    private LogClass() {
    }

    public static LogClass initLog(LogMsg logMsg, OptionDetails optionDetails) {
        LogClass clz = new LogClass();
        if (logMsg != null) {
            clz.builder.append(logMsg.getName());
        }
        if (optionDetails != null) {
            clz.builder.append("---").append(optionDetails.getType()).append("---");
            clz.builder.append(optionDetails.getStatus()).append("---");
            clz.builder.append(optionDetails.getMsg());
        }
        return clz;
    }

    public LogClass build(String name, Object msg) {
        this.builder.append("---").append(name != null ? name : " ");
        this.builder.append(":").append(msg != null ? msg : " ");
        return this;
    }

    public String log() {
        return this.builder.toString();
    }

}
