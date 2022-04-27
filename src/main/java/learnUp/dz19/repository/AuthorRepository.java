package learnUp.dz19.repository;

import learnUp.dz19.entity.Author;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "from Author a where a.id = :id")
    public Author findAuthorById(Long id);

    List<Author> findAll (Specification<Author> specification);
}
