import com.google.inject.AbstractModule;

/**
 * Guice Module to bind GelfHttpSenderImpl to SenderService.
 */
public class SenderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Sender.class).to(GelfHttpSenderImpl.class);
    }
}
