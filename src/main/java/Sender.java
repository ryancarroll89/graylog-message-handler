/**
 * Interface to implement a way to send messages to a host.
 */
public interface Sender {
    /**
     * Sends the messages in messageGroup to the host on the specified port.
     * @param messageGroup - The messages to send to the host.
     * @param host - The host to send messages to.
     * @param port - The port on the host to send to.
     */
    void send(MessageGroup messageGroup, String host, int port);
}
