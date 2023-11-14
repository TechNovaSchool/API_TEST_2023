package api.books;

import lombok.Data;

@Data
public class ResponseBody {
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
    String createdBy;
    long timestamp;

}