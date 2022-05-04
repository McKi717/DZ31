package learnUp.dz19.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="orderDetails")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public OrderDetails(Integer quantity, Integer price, Orders orders, Set<Book> books) {
        this.quantity = quantity;
        this.price = price;
        this.orders = orders;
        this.books = books;
    }

    @Column
    private Integer quantity;

    @Column
    private Integer price;

    @JsonIgnore
    @OneToOne(mappedBy = "order_detailss", cascade = CascadeType.ALL)
    private Orders orders;

    @JsonIgnore
    @Version
    public Long version;

    @OneToMany(mappedBy = "order_details", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Book> books;

    public void removeBook(Book book){
        books.remove(book);
        book.setOrder_details(null);
    }
}
