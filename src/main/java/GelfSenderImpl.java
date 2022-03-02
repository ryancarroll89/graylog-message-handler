import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;

public class GelfSenderImpl implements Sender {
    static Logger log = Logger.getLogger(GelfSenderImpl.class.getName());

    @Override
    public void send(MessageGroup messageGroup, String host, int port) {
        for (Object jsonObject : messageGroup.getJsonArray()) {
            sendMessage(jsonObject.toString(), host, port);
        }
    }

    private static void sendMessage(String jsonInputString, String host, int port) {
        HttpURLConnection connection = null;

        URL url = null;
        try {
            url = new URL("http://" + host + ":" + port + "/gelf");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Response: " + response.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
