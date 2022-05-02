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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_deatails_id")
    private OrderDetails order_detailss;

    @OneToOne(mappedBy = "orders")
    private Buyer buyer;
}
