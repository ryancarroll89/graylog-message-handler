import com.google.inject.AbstractModule;

/**
 * Guice Module to bind JsonIngestorImpl to IngestorService.
 */
public class IngestorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Ingestor.class).to(JsonIngestorImpl.class);
    }
}
