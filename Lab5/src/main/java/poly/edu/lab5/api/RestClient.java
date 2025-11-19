package poly.edu.lab5.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import poly.edu.lab5.entity.Student;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class RestClient {
    static String host = "https://java6-37c94-default-rtdb.firebaseio.com";
    static Gson gson = new Gson();

    private static void jsonPrettyPrint(String json){
        var gsonPretty = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(json);
        String prettyJson = gsonPretty.toJson(jsonElement);
        System.out.println(prettyJson);
    }

     public static Map<String, Student> getAll() throws IOException{
        var url = host + "/student.json";
        var connection = HttpClient.openConnection("GET", url);
        var response = HttpClient.readData(connection);
        String jsonString = new String(response);

        Type studentMapType = new TypeToken<Map<String, Student>>() {}.getType();
        Map<String, Student> studentMap = gson.fromJson(jsonString, studentMapType);
        return studentMap;
    }

    public static Student getByKey(String key) throws IOException{
        var url = host + "/student/"+ key + ".json";
        var connection = HttpClient.openConnection("GET", url);
        var response = HttpClient.readData(connection);
        String jsonString = new String(response);
        Student student = gson.fromJson(jsonString, Student.class);
        return student;
    }

    public static void post(Student student) {
        var url = host +"/student.json";
        try {
            String jsonStringData = gson.toJson(student);

            byte[] data = jsonStringData.getBytes("UTF-8");

            var connection = HttpClient.openConnection("POST", url);

            var response = HttpClient.writeData(connection, data);

            String jsonString = new String(response);
            jsonPrettyPrint(jsonString);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void put(Student student, String key) {
        var url = host + "/student/"+ key +".json";
        try {
            String jsonStringData = gson.toJson(student);

            byte[] data = jsonStringData.getBytes("UTF-8");

            var connection = HttpClient.openConnection("PUT", url);

            var response = HttpClient.writeData(connection, data);

            String jsonString = new String(response);
            jsonPrettyPrint(jsonString);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public static void delete(String key) {
        var url = host +"/student/" + key +".json";
        try {
            var connection = HttpClient.openConnection("DELETE", url);
            HttpClient.readData(connection);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public static void main(String[] args) throws IOException{
            Map<String, Student> students = getAll();
            for(var student : students.values()){
                System.out.println(student);
            }
//        getByKey("-OdlkYTN1xSUuaGjFvJ0");
//        post();
//        put("-OdlGmVoa4BpuY8HS-8x");
//        delete("-OdlGmVoa4BpuY8HS-8x");
    }
}
