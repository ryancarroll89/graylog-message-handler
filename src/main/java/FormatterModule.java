import com.google.inject.AbstractModule;

public class FormatterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Formatter.class).to(GelfFormatterImpl.class);
    }
}
