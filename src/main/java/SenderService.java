import com.google.inject.Inject;

/**
 * Service class to send a MessageGroup to a host.
 */
public class SenderService {
    private Sender sender;

    @Inject
    public SenderService(Sender sender) {
        this.sender = sender;
    }

    /**
     * Sends the messages in messageGroup to the host on the specified port.
     * @param messageGroup - The messages to send to the host.
     * @param host - The host to send messages to.
     * @param port - The port on the host to send to.
     */
    public void send(MessageGroup messageGroup, String host, int port) {
        sender.send(messageGroup, host, port);
    }
}
