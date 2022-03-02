import com.google.inject.AbstractModule;

/**
 * Guice Module to bind GelfFormatterImpl to FormatterService.
 */
public class FormatterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Formatter.class).to(GelfFormatterImpl.class);
    }
}
