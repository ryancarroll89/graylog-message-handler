import com.google.inject.AbstractModule;

public class IngestorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Ingestor.class).to(JsonIngestorImpl.class);
    }
}
