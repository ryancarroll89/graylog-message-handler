import com.google.inject.Inject;

public class SenderService {
    private Sender sender;

    @Inject
    public SenderService(Sender sender) {
        this.sender = sender;
    }

    public void send(MessageGroup messageGroup, String host, int port) {
        sender.send(messageGroup, host, port);
    }
}
