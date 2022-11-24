package client.ly.service;

public interface ConsumerSend {

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     byte[] getTask(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     byte[] takeTask(byte[] data, String controller);

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
     byte[] delTask(byte[] data, String controller);

    /**
     *
     * @param data
     * @param controller
     * @return
     */
     byte[] allGetTask(byte[] data, String controller);

}
