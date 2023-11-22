package api.tests;

import util.Config;
import api.books.ResponseBodyBooks;
import api.books.CreateOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
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
                .header("Authorization", "Bearer " + Config.getProperty("bookApi_Token"))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://simple-books-api.glitch.me/orders");

        System.out.println(response.statusCode());
        System.out.println(response.asString());
    }

    @Test
    public void getSingleOrder() throws JsonProcessingException {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Config.getProperty("bookApi_Token"))
                .get(Config.getProperty("baseURl") + "/orders/5GNprtqbi3zTF6xMQvJAK");
        System.out.println(response.statusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseBodyBooks rb = objectMapper.readValue(response.asString(), ResponseBodyBooks.class);

        String str = rb.getCustomerName();

        System.out.println(rb.getBookId());
        Assert.assertEquals(3, rb.getBookId());


    }

}
