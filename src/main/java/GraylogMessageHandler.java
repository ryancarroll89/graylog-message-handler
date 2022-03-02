import com.google.inject.Guice;
import com.google.inject.Injector;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.net.Socket;

public class GraylogMessageHandler {
    public static void main(String[] args) {
        Injector ingestorInjector = Guice.createInjector(new IngestorModule());
        IngestService ingestService = ingestorInjector.getInstance(IngestService.class);
        Message message = ingestService.ingest("/home/ryan/graylog-interview/sample-messages.txt");

        //System.out.println(message.toString());

        Injector formatterInjector = Guice.createInjector(new FormatterModule());
        FormatService formatService = formatterInjector.getInstance(FormatService.class);
        formatService.format(message);

        JSONArray array = message.getJsonMessage();

//        for(int i=0; i<array.length(); i++) { // loop through the nodes
//            JSONObject temp = array.getJSONObject(i);
//            System.out.println(temp.toString());
//            System.out.println();
//        }

        //System.out.println(message.getJsonMessage().toString());

        sendMessage(array.getJSONObject(0).toString());
    }

    private static void sendMessage(String message){

        try{
            Socket socket = new Socket("localhost", 12201);

            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(message);
            output.flush();
            output.close();

        }
        catch(Exception io){
            System.out.println("error? " + io.getMessage());
        }
    }
}