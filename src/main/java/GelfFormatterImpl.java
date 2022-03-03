import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Formatter Implementation to format a MessageGroup to GELF specifications.
 */
public class GelfFormatterImpl implements Formatter {
    static Logger log = Logger.getLogger(GelfFormatterImpl.class.getName());

    @Override
    public void format(MessageGroup messageGroup) {
        addGelfFields(messageGroup);
    }

    /**
     * Helper Function that takes a JSON object and adds an underscore prefix to all key names.
     *
     * @param jsonObject - JSON object whose keys to add underscores to
     */
    public void appendPrefixes(JSONObject jsonObject) {
        Set<String> keys = new HashSet<>(jsonObject.keySet());
        for (String key : keys) {
            jsonObject.put("_" + key, jsonObject.get(key));
            jsonObject.remove(key);
        }
    }

    /**
     * Takes a messageGroup and adds the required GELF formatting to each message in the group.
     *
     * @param messageGroup - The messages to add GELF fields to.
     */
    public void addGelfFields(MessageGroup messageGroup) {
        JSONArray jsonArray = messageGroup.getJsonArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            appendPrefixes(jsonArray.getJSONObject(i));
            addGelfFields(jsonArray.getJSONObject(i), i);
        }
    }

    /**
     * Helper function that takes a JSON object and adds the new fields required by GELF specifications.
     * (This information is not very meaningful, simply serving as a placeholder for this project.)
     *
     * @param jsonObject - The object to add GELF fields to.
     * @param i          - The index of the message to more easily differentiate messages in Graylog UI.
     */
    public void addGelfFields(JSONObject jsonObject, int i) {
        jsonObject.put("version", "1.1");
        jsonObject.put("host", "example.org");
        jsonObject.put("short_message", "A short message that helps you identify what is going on " + i);
        jsonObject.put("full_message", "Backtrace here\\n\\nmore stuff");
        jsonObject.put("timestamp", Instant.now().getEpochSecond());
        jsonObject.put("level", 1);
    }
}
