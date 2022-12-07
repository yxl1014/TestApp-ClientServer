package client.ly.service;

import pto.TestProto;

public interface ConsumerSend {

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     TestProto.Task getTask(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     TestProto.ResponseMsg takeTask(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @param taskId
     * @return
     */
    TestProto.ResponseMsg startTask(byte[] data, String controller,int taskId);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
    TestProto.ResponseMsg endTask(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
    TestProto.ResponseMsg delTask(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     TestProto.ConsGetTasks allGetTask(byte[] data, String controller);

}
