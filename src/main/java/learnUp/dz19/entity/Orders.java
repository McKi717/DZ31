package learnUp.dz19.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float amountBuy;

    @JsonIgnore
    @Version
    public Long version;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_deatails_id")
    private OrderDetails order_detailss;

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    private Buyer buyer;
}
