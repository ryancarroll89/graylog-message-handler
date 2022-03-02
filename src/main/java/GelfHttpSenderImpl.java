import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;

/**
 * Sender Implementation to send GELF formatted messages over HTTP.
 */
public class GelfHttpSenderImpl implements Sender {
    static Logger log = Logger.getLogger(GelfHttpSenderImpl.class.getName());

    @Override
    public void send(MessageGroup messageGroup, String host, int port) {
        for (Object jsonObject : messageGroup.getJsonArray()) {
            sendMessage(jsonObject.toString(), host, port);
        }
    }

    /**
     * Sends a JSON string over HTTP to the host and port specified.
     *
     * @param jsonInputString - The JSON string to send.
     * @param host            - The receiving host to send to.
     * @param port            - The recieving port on the host.
     */
    private static void sendMessage(String jsonInputString, String host, int port) {
        HttpURLConnection connection = null;

        // Build URL for connection (e.g. http://localhost:12201/gelf)
        URL url = null;
        try {
            url = new URL("http://" + host + ":" + port + "/gelf");
        } catch (MalformedURLException e) {
            log.error("Malformed URL for host: " + host + " and port: " + port);
            e.printStackTrace();
            return;
        }

        // Establish connection with Graylog server
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error establishing connection");
            return;
        }

        // Set the request method
        try {
            connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        if (connection == null) {
            log.error("Could not establish connection");
            return;
        }

        // Set the header info
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        // Create the request body from the JSON input
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (IOException e) {
            log.error("Error writing to connection");
            e.printStackTrace();
            return;
        }

        // Read the response from the input streams
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            log.info("Response: " + response.toString());
        } catch (UnsupportedEncodingException e) {
            log.error("Unsupported encoding");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Error receiving connection response");
            e.printStackTrace();
        }
    }
}
