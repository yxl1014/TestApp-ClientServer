package client.ly.service;

public interface ProducerSend {

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     byte[] addTask(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @param taskId
     * @return
     */
     byte[] startTask(byte[] data, String controller,int taskId);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     byte[] endTask(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     byte[] getTaskResults(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     byte[] allGetTask(byte[] data, String controller);
}
