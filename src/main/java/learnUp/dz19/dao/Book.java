package learnUp.dz19.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private long id;
    private String nameBook;
    private String nameAuthor;
    private int orders;
}
