package api.books;

import lombok.Data;

@Data
public class CreateOrder {
    int bookId;
    String customerName;

}
