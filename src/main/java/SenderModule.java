import com.google.inject.AbstractModule;

public class SenderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Sender.class).to(GelfSenderImpl.class);
    }
}
