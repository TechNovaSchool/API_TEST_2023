package api.tests;

import api.books.CreateOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class BookApiTests {

    @Test
    public void createBookOrder() throws JsonProcessingException {
        CreateOrder bookOrder = new CreateOrder();
        bookOrder.setBookId(3);
        bookOrder.setCustomerName("Test");

        ObjectMapper objectMapper = new ObjectMapper();// this comes from jackson library
        String orderInJSON = objectMapper.writeValueAsString(bookOrder);

        System.out.println(orderInJSON);
    }


    @Test
    public void postAnOrder() throws JsonProcessingException {
        CreateOrder createOrder = new CreateOrder();
        createOrder.setBookId(1);
        createOrder.setCustomerName("Jim Thomas");


        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(createOrder);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer 78c36186d0c8e201f8708a5e93c74776d7617fa08dd78022d629d45e971c77b6")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://simple-books-api.glitch.me/orders");

        System.out.println(response.statusCode());
        System.out.println(response.asString());


    }

}
