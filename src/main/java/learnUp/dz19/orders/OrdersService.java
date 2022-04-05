package learnUp.dz19.orders;

import learnUp.dz19.entity.Orders;
import learnUp.dz19.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> getOrders() {return ordersRepository.findAll();}
}
