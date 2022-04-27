package learnUp.dz19.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="book")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameBook;

    @Version
    public Long version;

    public Book(String nameBook, Author author, int releaseYear, int pages, int price, BookWareHouse bookWareHouse) {
        this.nameBook = nameBook;
        this.author = author;
        this.releaseYear = releaseYear;
        this.pages = pages;
        this.price = price;
        this.bookRemainder = bookWareHouse;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Fetch(FetchMode.JOIN)
    private Author author;

    @Column
    private int releaseYear;

    @Column
    private int pages;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn(name = "booksRemainder")
    @Fetch(FetchMode.JOIN)
    private BookWareHouse bookRemainder;

    @ManyToOne
    @JoinColumn(name = "booksFromOrDetails")
    @Fetch(FetchMode.JOIN)
    @ToString.Exclude
    @JsonIgnore
    private OrderDetails order_details;

}
