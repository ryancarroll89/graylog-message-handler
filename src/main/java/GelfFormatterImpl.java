import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class GelfFormatterImpl implements Formatter {
    static Logger log = Logger.getLogger(GelfFormatterImpl.class.getName());

    @Override
    public void format(MessageGroup messageGroup) {
        addGelfFields(messageGroup);
    }

    private void addGelfFields(MessageGroup messageGroup) {
        JSONArray jsonArray = messageGroup.getJsonArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            appendPrefixes(jsonArray.getJSONObject(i));
            addGelfFields(jsonArray.getJSONObject(i), i);
        }
    }

    private void appendPrefixes(JSONObject jsonObject) {
        Set<String> keys = new HashSet<String>(jsonObject.keySet());
        for (String key : keys) {
            jsonObject.put("_" + key, jsonObject.get(key));
            jsonObject.remove(key);
        }
    }

    private void addGelfFields(JSONObject jsonObject, int i) {
        jsonObject.put("version", "1.1");
        jsonObject.put("host", "example.org");
        jsonObject.put("short_message", "A short message that helps you identify what is going on " + i);
        jsonObject.put("full_message", "Backtrace here\\n\\nmore stuff");
        jsonObject.put("timestamp", Instant.now().getEpochSecond());
        jsonObject.put("level", 1);
    }
}
