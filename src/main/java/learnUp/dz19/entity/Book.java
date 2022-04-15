package learnUp.dz19.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameBook;

    public Book(String nameBook, Author author, int releaseYear, int pages, float price, BookWareHouse bookWareHouse) {
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
    private float price;

    @ManyToOne
    @JoinColumn(name = "booksRemainder")
    @Fetch(FetchMode.JOIN)
    private BookWareHouse bookRemainder;

    @ManyToOne
    @JoinColumn(name = "booksFromOrDetails")
    @Fetch(FetchMode.JOIN)
    private OrderDetails order_details;

    public Book() {

    }
}
