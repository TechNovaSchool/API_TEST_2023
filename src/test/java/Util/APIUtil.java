package Util;

import api.model.RequestBody;
import api.model.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIUtil {
    //CRUD
    //GET,POST,PATCH, DELETE
    //hitting a request

    public static void callGET(String path, String tableID) {
        String endpoint = Config.getProperty("baseURL_Airtable") + tableID + path;

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
                .urlEncodingEnabled(false)
                .get(endpoint);

        System.out.println(response.statusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ResponseBody responseBody = objectMapper.readValue(response.asString(), ResponseBody.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void callPOST(String path, String tableID, RequestBody body){

        String endpoint = Config.getProperty("baseURL_Airtable") + tableID + path;

        ObjectMapper objectMapper = new ObjectMapper();

        String bodyJSON = "";
        try {
            bodyJSON = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Config.getProperty("tokenAirtable"))
                .urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .body(bodyJSON)
                .post(endpoint);

        System.out.println(response.statusCode());

        try {
            ResponseBody responseBody = objectMapper.readValue(response.asString(), ResponseBody.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
