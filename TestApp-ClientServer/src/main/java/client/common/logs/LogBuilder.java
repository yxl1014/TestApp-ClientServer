package client.common.logs;

/**
 * @author yxl
 * @date: 2022/10/25 下午2:41
 */

public class LogBuilder {

    private StringBuilder builder = new StringBuilder();

    private LogBuilder() {
    }

    public static LogBuilder initLog(LogMsg logMsg, OptionDetails optionDetails) {
        LogBuilder clz = new LogBuilder();
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

    public LogBuilder build(String name, Object msg) {
        this.builder.append("---").append(name != null ? name : " ");
        this.builder.append(":").append(msg != null ? msg : " ");
        return this;
    }

    public String log() {
        return this.builder.toString();
    }

}
