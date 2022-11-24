package client.ljy.net.myconnection;

import pto.TestProto;

public interface IConnection {
    TestProto.ConnectionResulte.Builder sendRequest();
    void setParameter(TestProto.TaskShell.Builder shell);
}
