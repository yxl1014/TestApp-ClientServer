package client.ljy.net.myconnection;

import pto.TestProto;

public interface IConnection {
    void sendRequest();
    void setParameter(TestProto.TaskShell.Builder shell);
    
}
