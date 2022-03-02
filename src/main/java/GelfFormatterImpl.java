import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;

public class GelfFormatterImpl implements Formatter{

    @Override
    public void format(Message message) {
        addGelfFields(message);
    }

    private void addGelfFields(Message message) {
        JSONArray jsonArray = message.getJsonMessage();
        for (int i = 0; i < jsonArray.length(); i++) {
            addGelfFields(jsonArray.getJSONObject(i));
        }
    }

    private void addGelfFields(JSONObject jsonObject) {
        jsonObject.put("version", "1.1");
        jsonObject.put("host", "example.org");
        jsonObject.put("short_message", "A short message that helps you identify what is going on");
        jsonObject.put("full_message", "Backtrace here\\n\\nmore stuff");
        jsonObject.put("timestamp", Instant.now().getEpochSecond());
        jsonObject.put("level", 1);
    }
}
