package learnUp.dz19.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Data
@ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float amountBuy;

    @Version
    public Long version;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_deatails_id")
    private OrderDetails order_details;

    @OneToOne(mappedBy = "orders")
    private Buyer buyer;
}
