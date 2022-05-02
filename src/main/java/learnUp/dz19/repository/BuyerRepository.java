package learnUp.dz19.repository;

import learnUp.dz19.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    @Query(value = "from Buyer b where b.id = :id")
    public Buyer findBuyerById(Long id);
}
