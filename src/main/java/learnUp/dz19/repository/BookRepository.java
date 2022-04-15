package learnUp.dz19.repository;

import learnUp.dz19.entity.Author;
import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.BookWareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

   @Query(value = "select * from book b left join author a on b.author_id = a.id where a.full_name = :authors", nativeQuery = true)
   List<Book> findBookByAuthor(@Param("authors") String authors);

   @Query(value = "from Book a where a.id = :id")
   public Book findBookById(Long id);

}
