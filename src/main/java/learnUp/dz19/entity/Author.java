package learnUp.dz19.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "author")
@Data
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

}
