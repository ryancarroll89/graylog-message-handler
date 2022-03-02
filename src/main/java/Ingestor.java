/**
 * Interface to implement a file Ingestor.
 */
public interface Ingestor {
    /**
     * Read and ingest a file specified at path and return an encapsulating MessageGroup object.
     *
     * @param path - The path of the file.
     * @return - A MessageGroup containing encapsulating the data read in from the file at path.
     */
    MessageGroup ingest(String path);
}
