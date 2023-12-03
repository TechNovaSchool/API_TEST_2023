package step_def;

import util.APIUtil;
import util.Config;
import api.model.Myfields;
import api.model.Record;
import api.model.RequestBody;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class airtable_steps {
    String path = "";
    String tableID = "";
    String recordID = "";
    RequestBody requestBody = new RequestBody();

    @When("a user calls a GET endpoint")
    public void a_user_calls_a_get_endpoint() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");
        APIUtil.callGET(path, tableID);
    }

    @Then("user will receive status {string}")
    public void user_will_receive_status(String actualStatus) {
        Assert.assertEquals(actualStatus, "200");
    }

    @Then("user will receive status {int}")
    public void user_will_receive_status(int expectedStatus) {
        Assert.assertEquals(APIUtil.getResponse().statusCode(), expectedStatus);
    }

    @Then("user verifies the first name")
    public void user_verifies_the_first_name() {
        String expectedName = "Gavin";
        String actualName = APIUtil.getResponseBody().getFields().getFirstName();
        Assert.assertEquals(actualName, expectedName);

    }

    @When("a user calls a GET endpoint for a single record")
    public void a_user_calls_a_get_endpoint_for_a_single_record() {
        path = "/Table%201/recd5sWfWUUx0XJhD";
        tableID = Config.getProperty("tableID");
        APIUtil.callGET(path, tableID);
    }

    @When("a user calls a POST endpoint for a new record")
    public void a_user_calls_a_post_endpoint_for_a_new_record() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");

        Faker faker = new Faker();
        Myfields newStudent = new Myfields();
        newStudent.setAddress(faker.address().streetAddress());
        newStudent.setFirstName(faker.name().firstName());
        newStudent.setLastName(faker.name().lastName());
        newStudent.setEmail(faker.internet().emailAddress());
        newStudent.setAge(faker.number().numberBetween(0, 100));
        newStudent.setStudent(true);

        Record record = new Record();
        record.setFields(newStudent);

        List<Record> myListOfStudents = new ArrayList<>();
        myListOfStudents.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(myListOfStudents);


        APIUtil.callPOST(path, tableID, requestBody);
        APIUtil.jsonSchemaMatcher("postPayload.json");

    }

    @Then("user extracts and saves the recordID from the response")
    public void user_extracts_and_saves_the_record_id_from_the_response() {
        recordID = APIUtil.getResponseBody().getRecords().get(0).getId();

    }

    @When("a user calls a PATCH endpoint to update the email field")
    public void a_user_calls_a_patch_endpoint_to_update_the_email_field() {
        Faker faker = new Faker();
        path = "/Table%201";
        tableID = Config.getProperty("tableID");
        Myfields newStudent = new Myfields();
        newStudent.setEmail(faker.internet().emailAddress());

        Record record = new Record();
        record.setFields(newStudent);

        List<Record> myListOfStudents = new ArrayList<>();
        myListOfStudents.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(myListOfStudents);

        APIUtil.callPATCH(path, tableID, requestBody);

    }

    @When("a user calls a DELETE endpoint to delete the record")
    public void a_user_calls_a_delete_endpoint_to_delete_the_record() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");
        APIUtil.callDELETE(path, tableID, recordID);

    }


    @When("user creates a record with incorrect payload")
    public void user_creates_a_record_with_incorrect_payload() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");
        RequestBody requestBody = new RequestBody();

        APIUtil.callPOST(path, tableID, requestBody);
    }

    @When("a user calls a POST endpoint with custom data")
    public void a_user_calls_a_post_endpoint_with_custom_data() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");
        APIUtil.callPOST(path, tableID, requestBody);
    }

    @When("user has {string} {string} {string} {int}")
    public void user_has(String firstName, String lastName, String email, int age) {

        Myfields myfields = new Myfields();
        myfields.setFirstName(firstName);
        myfields.setLastName(lastName);
        myfields.setEmail(email);
        myfields.setAge(age);

        Record record = new Record();
        record.setFields(myfields);

        List<Record> recordList = new ArrayList<>();
        recordList.add(record);
        requestBody.setRecords(recordList);
        System.out.println(requestBody);
        String locator = " //*[.='" + lastName + "']";

    }
}
