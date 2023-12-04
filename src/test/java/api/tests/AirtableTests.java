//package api.tests;
//
//import util.Config;
//import api.model.Myfields;
//import api.model.Record;
//import api.model.RequestBody;
//import api.model.ResponseBody;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AirtableTests {
//
//
//    @Test
//    public void getRecords() throws JsonProcessingException {
//        Response response = RestAssured.given()
//                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
//                .urlEncodingEnabled(false)
//                .get(Config.getProperty("host"));
//
//        System.out.println(response.statusCode());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        ResponseBody rb = objectMapper.readValue(response.asString(), ResponseBody.class);
//
////        System.out.println(rb.getRecords().get(0).getFields().getFirstName());
////        System.out.println(rb.getRecords().get(0).getId());
////        System.out.println("Kim Twist email address age student");
//        int size = rb.getRecords().size();
//
////        for (int i = 0; i < size; i++) {
////            String firstName = rb.getRecords().get(i).getFields().getFirstName();
////            String lastName = rb.getRecords().get(i).getFields().getLastName();
////            String email = rb.getRecords().get(i).getFields().getEmail();
////            String address = rb.getRecords().get(i).getFields().getAddress();
////            int age = rb.getRecords().get(i).getFields().getAge();
////            boolean isAStudent = rb.getRecords().get(i).getFields().isStudent();
////            String student;
////            if(isAStudent){
////                student = "is a student";
////            }
////            else {
////                student = "is not a student";
////            }
////
////            System.out.println(firstName +
////                    " " + lastName +
////                    " " + email +
////                    " " +address +
////                    " " +age +
////                    " " + student
////            );
////        }
////////// Amir
////        List<Record>records = rb.getRecords();
////        for(Record fields : records){
////            String firstName = fields.getFields().getFirstName();
////            int age = fields.getFields().getAge();
////
////            if (age >= 18 ){
////                System.out.println(firstName + "  " + age);
////            }
////        }
//
//        for (Record record : rb.getRecords()) {
//            String firstName = record.getFields().getFirstName();
//            String lastName = record.getFields().getLastName();
//            String email = record.getFields().getEmail();
//            boolean isStudent = record.getFields().isStudent();
//            String student = isStudent ? "student" : "not student";
//            String address = record.getFields().getAddress();
//            int age = record.getFields().getAge();
//            if(firstName != null){
//                if(age >18){
//                    System.out.println(firstName + " " + lastName + " " + email + " " + student + " " + address + " " + age);
//                }
//            }
//
//        }
//    }
//
//    @Test
//    public void postRecord() throws JsonProcessingException {
//
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
//        String jsonValue = objectMapper.writeValueAsString(requestBody);
//        System.out.println(jsonValue);
//
//
//        Response response = RestAssured.given()
//                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
//                .urlEncodingEnabled(false)
//                .contentType(ContentType.JSON)
//                .body(jsonValue)
//                .post(Config.getProperty("host"));
//
//        System.out.println(response.statusCode());
//
//        ResponseBody rb = objectMapper.readValue(response.asString(), ResponseBody.class);
//        System.out.println(rb.getRecords().get(0).getId());
//    }
//
//
//    @Test
//    public void patchRecord() throws JsonProcessingException {
//        Myfields person = new Myfields();
//        person.setFirstName("Henry");
//
//        Record record = new Record();
//        record.setFields(person);
//        record.setId("recAkBNIw5rguGNpE");
//
//        List<Record> records = new ArrayList<>();
//        records.add(record);
//
//        RequestBody requestBody = new RequestBody();
//        requestBody.setRecords(records);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String jsonValue = objectMapper.writeValueAsString(requestBody);
////        System.out.println(jsonValue);
//
//        Response response = RestAssured.given()
//                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
//                .urlEncodingEnabled(false)
//                .contentType(ContentType.JSON)
//                .body(jsonValue)
//                .patch(Config.getProperty("host"));
//
//        System.out.println(response.statusCode());
//
//        ResponseBody responseBody = objectMapper.readValue(response.asString(), ResponseBody.class);
//
//        String str = responseBody.getRecords().get(0).getFields().getFirstName();
//
//        Assert.assertEquals("Henry", str);
//    }
//
//    @Test
//    public void deleteRecord(){
//        String recordId = "recAkBNIw5rguGNpE";
//        String queryParam = "records[]";
//
//        Response response = RestAssured.given()
//                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
//                .urlEncodingEnabled(false)
//                .queryParam(queryParam,recordId)
//                .delete(Config.getProperty("host"));
//        System.out.println(response.statusCode());
//    }
//
//
//}
