import com.google.inject.Inject;

public class IngestService {
    private Ingestor ingestor;

    @Inject
    public IngestService(Ingestor ingestor) {
        this.ingestor = ingestor;
    }

    public MessageGroup ingest(String path) {
        return ingestor.ingest(path);
    }
}
