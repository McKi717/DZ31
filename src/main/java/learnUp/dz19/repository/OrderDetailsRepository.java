package learnUp.dz19.repository;

import learnUp.dz19.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @Query(value = "from OrderDetails a where a.id = :id")
    public OrderDetails findOrderDetailsById(Long id);
}
