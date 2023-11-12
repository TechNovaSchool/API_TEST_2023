package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MyFirstAPITest {

    // What info is needed for the api request:
    //authorization, endpoint, params, methods( get, post, patch, put, delete)
    //headers, request body

    @Test
    public void getListOfBooks() {
        Response response = RestAssured.get("https://simple-books-api.glitch.me/books");
//        System.out.println(response.asString());
//        System.out.println(response.statusCode());
        Assert.assertEquals(200, response.statusCode());


        JsonPath jsonPath = response.jsonPath();
        String str = jsonPath.get("[3].name");
//        System.out.println(str);
        int size = jsonPath.getList("").size();
//        System.out.println(size);
        List<String> list = jsonPath.getList("");

        for (int i = 0; i < size; i++) {
            String namesOfBooks = jsonPath.get("[" + i + "].name");
            System.out.println(namesOfBooks);
        }
//        for(String names: list) {
//            String namesOfBooks = list.get(0
//            System.out.println(names);
//        }
    }

    @Test
    public void getAllOrders() {
        String baseUrl = "https://simple-books-api.glitch.me";
        Response response = RestAssured.given()
                .header("Authorization", "Bearer e614a04cb2e3fb0cdb9ce8d8b745c7a235311a5cf36fbb612d64f39720d45f64")
                .get(baseUrl + "/orders");
        System.out.println(response.asString());
        System.out.println(response.statusCode());
    }

    @Test
    public void airtableAuth() {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer patZypNipTwv7JDmB.0f5ddb3977674abf5d98921f112ef0c0a62f71d8a6bb7071bca110f2c947ed24")
                .urlEncodingEnabled(false)
                .get("https://api.airtable.com/v0/app98knPZX5A4I6TC/Table%201");
        System.out.println(response.statusCode());

    }


}
