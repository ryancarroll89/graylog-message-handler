import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Ingestor Implementation to read in a JSON formatted text file and encapsulate it in a MessageGroup object.
 */
public class JsonIngestorImpl implements Ingestor {
    static Logger log = Logger.getLogger(JsonIngestorImpl.class.getName());

    @Override
    public MessageGroup ingest(String path) {
        BufferedReader reader = getBufferedReader(path);
        return new MessageGroup(readFile(reader));
    }

    /**
     * Reads in a file specified at path, parses the lines into JSON objects,
     * and returns a JSON array containing all JSON objects.
     *
     * @param reader - A BufferedReader for the file to read in
     * @return - A JSONArray of the JSON objects in the file
     */
    public JSONArray readFile(BufferedReader reader) {
        JSONArray jsonArray = new JSONArray();

        try {
            String line = reader.readLine();

            while (line != null) {
                jsonArray.put(new JSONObject(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error("Exception reading file");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Exception closing file");
                    e.printStackTrace();
                }
            }
        }
        return jsonArray;
    }

    /**
     *
     * @param path - The path of the file to read.
     * @return - A BufferedReader object for the file specified at path.
     */
    private BufferedReader getBufferedReader(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            log.error("File not found for path: " + path);
            e.printStackTrace();
        }
        return reader;
    }
}
