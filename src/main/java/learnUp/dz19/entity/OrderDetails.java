package learnUp.dz19.entity;

import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="orderDetails")
@Data
public class OrderDetails {
    @Id
    private Long id;

    @Column
    private long idOrders;

    @Column
    private long idBooks;

    @Column
    private int quantity;

    @Column
    private int price;

    @OneToMany(mappedBy = "orderDetails", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private Set<Orders> orders;

    @OneToMany(mappedBy = "booksOrderDetails", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private Set<Book> books;

}
