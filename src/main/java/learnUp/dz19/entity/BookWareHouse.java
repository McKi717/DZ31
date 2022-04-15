package learnUp.dz19.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="bookWareHouse")
@Data
public class BookWareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "bookRemainder",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<Book> books;

    @Column
    @Formula("(select count(b.id) from Book b where b.books_remainder = id)")
    public int remainder;

    public BookWareHouse(Long id, int remainder) {
        this.id = id;
        this.remainder = remainder;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder (int remainder) {
        this.remainder = remainder;
    }

    public BookWareHouse() {

    }
}
