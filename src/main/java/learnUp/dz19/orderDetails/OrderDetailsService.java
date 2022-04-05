package learnUp.dz19.orderDetails;

import learnUp.dz19.entity.OrderDetails;
import learnUp.dz19.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<OrderDetails> getOrderDetails() {return  orderDetailsRepository.findAll();}
}
