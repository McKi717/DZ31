package learnUp.dz19.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "author")
@Data
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<Book> books;

}
