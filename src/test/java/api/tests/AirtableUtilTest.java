package api.tests;

import Util.APIUtil;
import Util.Config;
import api.model.Myfields;
import api.model.Record;
import api.model.RequestBody;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AirtableUtilTest {

    private static String recordId;
    private static String path;
    private static String tableID;
    private Faker faker = new Faker();

    private Record createFakeRecord(){
        Myfields myfields = new Myfields();
        myfields.setFirstName(faker.name().firstName());
        myfields.setLastName(faker.name().lastName());
        myfields.setAddress(faker.address().streetAddress());
        myfields.setEmail(faker.internet().emailAddress());
        myfields.setAge(27);
        myfields.setStudent(true);

        Record record = new Record();
        record.setFields(myfields);
        return record;
    }


    @Test(priority = 1)
    public void getMethod() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");
        APIUtil.callGET(path, tableID);
    }

    @Test(priority = 2)
    public void postMethod() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");

        Record record = createFakeRecord();

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        APIUtil.callPOST(path, tableID, requestBody);

        recordId = APIUtil.getResponseBody().getRecords().get(0).getId();
        System.out.println(recordId);

        APIUtil.getResponse().then().assertThat().body(matchesJsonSchemaInClasspath("test/resources/body.json"));
    }

    @Test(priority = 3)
    public void patchMethod() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");

        Myfields myfields = new Myfields();
        myfields.setFirstName(faker.name().firstName());

        Record record = new Record();
        record.setFields(myfields);
        record.setId(recordId);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        APIUtil.callPATCH(path, tableID, requestBody);

    }

    @Test(priority = 4)
    public void deleteMethod() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");
        APIUtil.callDELETE(path, tableID, recordId);
    }


    @Test
    public void testJsonSchema() {

    }


    @AfterMethod
    public void tearDown() {
        Assert.assertEquals(APIUtil.getResponse().statusCode(),200);
    }

}
