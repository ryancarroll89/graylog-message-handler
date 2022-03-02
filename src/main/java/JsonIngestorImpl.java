import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonIngestorImpl implements Ingestor {

    @Override
    public Message ingest(String path) {

        //JSONArray array = readFile(path);

//        for(int i=0; i<array.length(); i++) { // loop through the nodes
//            JSONObject temp = array.getJSONObject(i);
//            System.out.println(temp.get("ClientIP"));
//        }


        //System.out.println(readFile(path));
        //System.out.println(json.getJSONArray("ClientDeviceType"));
        //readFile(path);

        return new Message(readFile(path));
    }

    private JSONArray readFile(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        try {
            String line = reader.readLine();

            while (line != null) {
                jsonArray.put(new JSONObject(line));
                line = reader.readLine();
            }
            //System.out.println(jsonArray.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }
}
