package learnUp.dz19.repository;

import learnUp.dz19.entity.OrderDetails;
import learnUp.dz19.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "from Orders o where o.id = :id")
    public Orders findOrdersById (Long id);

}
