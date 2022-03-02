import org.json.JSONArray;

public class Message {
    private JSONArray jsonMessage;
    private String gelfMessage;

    public Message(JSONArray jsonArray) {
        this.jsonMessage = jsonArray;
    }

    public JSONArray getJsonMessage() {
        return jsonMessage;
    }

    public void setJsonMessage(JSONArray jsonMessage) {
        this.jsonMessage = jsonMessage;
    }

    public String getGelfMessage() {
        return gelfMessage;
    }

    public void setGelfMessage(String gelfMessage) {
        this.gelfMessage = gelfMessage;
    }
}
