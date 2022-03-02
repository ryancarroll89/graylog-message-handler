import org.json.JSONArray;

/**
 * Class to encapsulate a group of messages to be sent to Graylog server.
 */
public class MessageGroup {
    //
    private JSONArray jsonArray;

    public MessageGroup(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }
}
