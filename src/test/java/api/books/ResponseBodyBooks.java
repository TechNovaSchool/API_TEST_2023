package api.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponseBodyBooks {
//    {
//        "id": "5GNprtqbi3zTF6xMQvJAK",
//            "bookId": 3,
//            "customerName": "Dan Wolf",
//            "quantity": 1,
//    }
    String id;
    int bookId;
    String customerName;
    int quantity;
//    String createdBy;
//    long timestamp;

}
