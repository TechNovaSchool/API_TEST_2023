package api.tests;

import Util.Config;
import api.model.Myfields;
import api.model.Record;
import api.model.RequestBody;
import api.model.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AirtableTests_Clean {
    Faker faker = new Faker();
    String myRecordId;


@Test(priority = 1)
    public void a_getRecords() throws JsonProcessingException {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
                .urlEncodingEnabled(false)
                .get(Config.getProperty("host"));

        Assert.assertEquals(response.statusCode(), 200);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBody rb = objectMapper.readValue(response.asString(), ResponseBody.class);

        for (Record record : rb.getRecords()) {
            String firstName = record.getFields().getFirstName();
            String lastName = record.getFields().getLastName();
            String email = record.getFields().getEmail();
            boolean isStudent = record.getFields().isStudent();
            String student = isStudent ? "student" : "not student";
            String address = record.getFields().getAddress();
            int age = record.getFields().getAge();
            if (firstName != null) {
                if (age > 18) {
                    System.out.println(firstName + " " + lastName + " " + email + " " + student + " " + address + " " + age);
                }
            }

        }
    }

    @Test(priority = 2)
    public void b_postRecord() throws JsonProcessingException {

        Myfields newStudent = new Myfields();
        newStudent.setAddress(faker.address().streetAddress());
        newStudent.setFirstName(faker.name().firstName());
        newStudent.setLastName(faker.name().lastName());
        newStudent.setEmail(faker.internet().emailAddress());
        newStudent.setAge(faker.number().numberBetween(0,100));
        newStudent.setStudent(true);

        Record record = new Record();
        record.setFields(newStudent);

        List<Record> myListOfStudents = new ArrayList<>();
        myListOfStudents.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(myListOfStudents);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.writeValueAsString(requestBody);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
                .urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .body(jsonValue)
                .post(Config.getProperty("host"));

        Assert.assertEquals(response.statusCode(), 200);

        ResponseBody rb = objectMapper.readValue(response.asString(), ResponseBody.class);
        myRecordId = rb.getRecords().get(0).getId();
    }

    @Test(priority = 3)
    public void c_patchRecord() throws JsonProcessingException {
        Myfields person = new Myfields();
        String randomName = faker.name().firstName();
        person.setFirstName(randomName);

        Record record = new Record();
        record.setFields(person);
        record.setId(myRecordId);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonValue = objectMapper.writeValueAsString(requestBody);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
                .urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .body(jsonValue)
                .patch(Config.getProperty("host"));

        Assert.assertEquals(response.statusCode(), 200);

        ResponseBody responseBody = objectMapper.readValue(response.asString(), ResponseBody.class);
        String str = responseBody.getRecords().get(0).getFields().getFirstName();
        Assert.assertEquals(str, randomName);
    }
    @Test(priority = 4)
    public void d_deleteRecord() {
        String recordId = myRecordId;
        String queryParam = "records[]";

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
                .urlEncodingEnabled(false)
                .queryParam(queryParam, recordId)
                .delete(Config.getProperty("host"));
        Assert.assertEquals(response.statusCode(), 200);

    }


}
