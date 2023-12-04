//package api;
//
//import api.model.Myfields;
//import api.model.Record;
//import api.model.RequestBody;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.http.ContentType;
//import util.Config;
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//
//
//public class MyFirstAPITest {
//
//    // What info is needed for the api request:
//    //authorization, endpoint, params, methods( get, post, patch, put, delete)
//    //headers, request body
//
//    @Test
//    public void getListOfBooks() {
//        Response response = RestAssured.get("https://simple-books-api.glitch.me/books");
////        System.out.println(response.asString());
////        System.out.println(response.statusCode());
//        Assert.assertEquals(200, response.statusCode());
//
//
//        JsonPath jsonPath = response.jsonPath();
//        String str = jsonPath.get("[3].name");
////        System.out.println(str);
//        int size = jsonPath.getList("").size();
////        System.out.println(size);
//        List<String> list = jsonPath.getList("");
//
//        for (int i = 0; i < size; i++) {
//            String namesOfBooks = jsonPath.get("[" + i + "].name");
//            System.out.println(namesOfBooks);
//        }
////        for(String names: list) {
////            String namesOfBooks = list.get(0
////            System.out.println(names);
////        }
//    }
//
//    @Test
//    public void getAllOrders() {
//        String baseUrl = "https://simple-books-api.glitch.me";
//        Response response = RestAssured.given()
//                .header("Authorization", "Bearer e614a04cb2e3fb0cdb9ce8d8b745c7a235311a5cf36fbb612d64f39720d45f64")
//                .get(baseUrl + "/orders");
//        System.out.println(response.asString());
//        System.out.println(response.statusCode());
//    }
//
//    @Test
//    public void airtableAuth() {
//        Response response = RestAssured.given()
//                .header("Authorization", "Bearer patZypNipTwv7JDmB.0f5ddb3977674abf5d98921f112ef0c0a62f71d8a6bb7071bca110f2c947ed24")
//                .urlEncodingEnabled(false)
//                .get("https://api.airtable.com/v0/app98knPZX5A4I6TC/Table%201");
//        System.out.println(response.statusCode());
//
//    }
//@Test
//    public void testJsonSchema2() {
//
//    RestAssured.given()
//            .header("Authorization", "Bearer patZypNipTwv7JDmB.0f5ddb3977674abf5d98921f112ef0c0a62f71d8a6bb7071bca110f2c947ed24")
//            .urlEncodingEnabled(false)
//            .get("https://api.airtable.com/v0/app98knPZX5A4I6TC/Table%201/recsHIZ8M9D9UzCKP")
//            .then().assertThat().body(matchesJsonSchemaInClasspath("body.json"));
//    }
//
//
//    @Test
//    public void testJsonSchema() {
//        RestAssured.given()
//                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
//                .urlEncodingEnabled(false)
//                .get(Config.getProperty("baseURL_Airtable") + Config.getProperty("tableID")+"/Table%201/rec2aUNY6ykbaavoP")
//                .then().assertThat().body(matchesJsonSchemaInClasspath("body.json"));
//    }
//
//    @Test
//    public void testJsonSchemaPost() {
//        Myfields newStudent = new Myfields();
//        newStudent.setAddress("1718 Road");
//        newStudent.setFirstName("Joe");
//        newStudent.setLastName("Biden");
//        newStudent.setEmail("test@gmail.com");
//        newStudent.setAge(80);
//        newStudent.setStudent(true);
//
//        Record record = new Record();
//        record.setFields(newStudent);
//
//        List<Record> myListOfStudents = new ArrayList<>();
//        myListOfStudents.add(record);
//
//        RequestBody requestBody = new RequestBody();
//        requestBody.setRecords(myListOfStudents);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String jsonValue = null;
//        try {
//            jsonValue = objectMapper.writeValueAsString(requestBody);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//
//       Response response = RestAssured.given()
//                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
//                .urlEncodingEnabled(false)
//                .contentType(ContentType.JSON)
//                .body(jsonValue)
//                .post(Config.getProperty("host"));
//        System.out.println(response.statusCode());
//
//       response.then().assertThat().body(matchesJsonSchemaInClasspath("postPayload.json"));
//    }
//
//
//    //*[.="All Questions"]
//}
