import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Ingestor Implementation to read in a JSON formatted text file and encapsulate it in a MessageGroup object.
 */
public class JsonIngestorImpl implements Ingestor {
    static Logger log = Logger.getLogger(JsonIngestorImpl.class.getName());

    @Override
    public MessageGroup ingest(String path) {
        return new MessageGroup(readFile(path));
    }

    /**
     * Reads in a file specified at path, parses the lines into JSON objects,
     * and returns a JSON array containing all JSON objects.
     *
     * @param path - The path of the file to read in
     * @return - A JSONArray of the JSON objects in the file
     */
    private JSONArray readFile(String path) {
        JSONArray jsonArray = new JSONArray();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                jsonArray.put(new JSONObject(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error("Exception reading file for path:" + path);
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Exception closing file for path:" + path);
                    e.printStackTrace();
                }
            }
        }
        return jsonArray;
    }
}
