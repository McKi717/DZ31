package learnUp.dz19.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="buyer")
@Getter
@Setter
@ToString
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;

    @Column
    private Date birthDay;

    @Version
    public Long version;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private Orders orders;
}
