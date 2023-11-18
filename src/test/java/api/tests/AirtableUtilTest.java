package api.tests;

import Util.APIUtil;
import Util.Config;
import api.model.Myfields;
import api.model.Record;
import api.model.RequestBody;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AirtableUtilTest {

    @Test
    public void getMethod(){
        String path = "/Table%201";
        String tableID = Config.getProperty("tableID");
        APIUtil.callGET(path,tableID);
    }

    @Test
    public void postMethod() {
        Faker faker = new Faker();
        String path = "/Table%201";
        String tableID = Config.getProperty("tableID");

        Myfields myfields = new Myfields();
        myfields.setFirstName(faker.name().firstName());
        myfields.setLastName(faker.name().lastName());
        myfields.setAddress(faker.address().streetAddress());
        myfields.setEmail(faker.internet().emailAddress());
        myfields.setAge(27);
        myfields.setStudent(true);

        Record record = new Record();
        record.setFields(myfields);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        APIUtil.callPOST(path,tableID,requestBody);
    }

    @Test
    public void patchMethod() {
        Faker faker = new Faker();
        String path = "/Table%201";
        String tableID = Config.getProperty("tableID");

        Myfields myfields = new Myfields();
        myfields.setFirstName(faker.name().firstName());

        Record record = new Record();
        record.setFields(myfields);
        record.setId("recs2WKpBCNQusxvy");

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        APIUtil.callPATCH(path,tableID,requestBody);
    }

    @Test
    public void deleteMethod() {
        String path = "/Table%201";
        String tableID = Config.getProperty("tableID");
        String myRecordID = "recqTsPPksO5wGUo4";
        APIUtil.callDELETE(path,tableID,myRecordID);
    }

}
