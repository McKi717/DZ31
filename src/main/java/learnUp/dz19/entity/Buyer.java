package learnUp.dz19.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="buyer")
@Data
public class Buyer {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private Date birthDay;

    @ManyToOne
    @JoinColumn(name = "buyerOrder_id")
    @Fetch(FetchMode.JOIN)
    private Orders buyerOrder;
}
