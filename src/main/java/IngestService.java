import com.google.inject.Inject;

/**
 * Service class to read in a file and encapsulate it in a MessageGroup object.
 */
public class IngestService {
    private Ingestor ingestor;

    @Inject
    public IngestService(Ingestor ingestor) {
        this.ingestor = ingestor;
    }

    /**
     * Reads in a file and encapsulates it in a MessageGroup object
     *
     * @param path - The path of the file to ingest.
     * @return - A MessageGroup encapsulating the data in the file at path.
     */
    public MessageGroup ingest(String path) {
        return ingestor.ingest(path);
    }
}
