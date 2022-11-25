package client.common.logs;

/**
 * @author yxl
 * @date: 2022/9/12 下午12:47
 */

public enum LogMsg {

    //---------------------ClientServer---------------------
    TEST("TEST"),

    TOKEN("TOKEN"),
    CLIENT_CONTEXT("CLIENT_CONTEXT"),
    PROTO("PROTO"),

    FILE("FILE"),
    KAFKA("KAFKA"),

    NET("NET"),
    FORMAT("FORMAT"),
    THREAD("THREAD")
    ;


    private String name;

    LogMsg(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
