package learnUp.dz19.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="book")
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nameBook;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    private Author author;

    @Column
    private int releaseYear;

    @Column
    private int pages;

    @Column
    private float price;

    @ManyToOne
    @JoinColumn(name = "booksFromBookWareHouse")
    @Fetch(FetchMode.JOIN)
    private BookWareHouse bookWareHouse;

    @ManyToOne
    @JoinColumn(name = "booksOrderDetails_id")
    @Fetch(FetchMode.JOIN)
    private OrderDetails booksOrderDetails;
}
