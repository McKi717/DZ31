package learnUp.dz19.repository;

import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.BookWareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookWareHouseRepository extends JpaRepository<BookWareHouse, Long> {

    @Query(value = "from BookWareHouse bhw where bhw.id = :id")

    public BookWareHouse findBWHbyId(long id);

}
