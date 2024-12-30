package DB.export.demo1.utils;

import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {
    public static void writeToFile(JSONArray jsonArray, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
