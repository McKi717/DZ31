package learnUp.dz19.repository;

import learnUp.dz19.entity.BookWareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookWareHouseRepository extends JpaRepository<BookWareHouse, Long> {
}
