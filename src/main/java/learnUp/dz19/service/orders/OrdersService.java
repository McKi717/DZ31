package learnUp.dz19.service.orders;

import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.OrderDetails;
import learnUp.dz19.entity.Orders;
import learnUp.dz19.repository.BuyerRepository;
import learnUp.dz19.repository.OrderDetailsRepository;
import learnUp.dz19.repository.OrdersRepository;
import learnUp.dz19.service.orderDetails.OrderDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final OrderDetailsService orderDetailsService;

    private final OrderDetailsRepository orderDetailsRepository;

    public OrdersService(OrdersRepository ordersRepository, OrderDetailsService orderDetailsService, OrderDetailsRepository orderDetailsRepository) {
        this.ordersRepository = ordersRepository;
        this.orderDetailsService = orderDetailsService;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    public Orders findOrderById(Long id) {
        return ordersRepository.findOrdersById(id);
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public Orders addOrder(Orders orders) {
        if (orders.getBuyer() == null) {
            throw new EntityExistsException("Добавьте к заказу покупателя");
        }
        if (orders.getOrder_detailss() == null) {
            throw new EntityExistsException(("Добавьте детали заказа"));
        }
        float amountBuy = orders.getOrder_detailss().getPrice();
        orders.setAmountBuy(amountBuy);
        return ordersRepository.save(orders);
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void addODinOrder(Orders orders) {
        try {
            orderDetailsRepository.save(orders.getOrder_detailss());
            ordersRepository.save(orders);
        } catch (OptimisticLockException e) {
            log.warn("Optimistick lock");
            throw e;
        }
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public Boolean deleteODinOrder(Orders orders) {
        orderDetailsRepository.save(orders.getOrder_detailss());
        ordersRepository.save(orders);
        return true;
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public Boolean delete(Orders orders) {
        try {
            OrderDetails orderDetails = orders.getOrder_detailss();
            ordersRepository.delete(orders);

            Set<Book> a = orderDetails.getBooks();
            for (Book b : a
            ) {
                orderDetailsService.deleteBooksByOrdersDetails(orderDetails, b);
            }
            ordersRepository.save(orders);
            return  true;
        } catch (OptimisticLockException e) {
            log.warn("Optimistick lock");
            throw e;
        }
    }
}
