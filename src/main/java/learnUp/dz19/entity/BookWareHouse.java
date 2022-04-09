package learnUp.dz19.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="bookWareHouse")
@Data
public class BookWareHouse {

    @Id
    private Long id;

    @Column
    private int remainder;

    @OneToMany(mappedBy = "bookWareHouse_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private Set<Book> books;
}
