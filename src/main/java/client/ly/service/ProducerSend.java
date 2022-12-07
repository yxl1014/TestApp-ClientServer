package client.ly.service;

import pto.TestProto;

public interface ProducerSend {

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     TestProto.ResponseMsg addTask(byte[] data, String controller);

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
    TestProto.TaskResult getTaskResults(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
    TestProto.ProdAddTasks allGetTask(byte[] data, String controller);
}
