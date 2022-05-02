package learnUp.dz19.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="bookWareHouse")
@Getter
@Setter
@ToString
public class BookWareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "bookRemainder",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @JsonIgnore
    private Set<Book> books;

    @JsonIgnore
    @Version
    public Long version;


    //@Formula("(select count(b.id) from Book b where b.books_remainder = id)")
    @Column
    public int remainder;

    public BookWareHouse(Long id, int remainder) {
        this.id = id;
        this.remainder = remainder;
    }


    public BookWareHouse() {

    }
}
