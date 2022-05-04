package learnUp.dz19.controller;

import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.Buyer;
import learnUp.dz19.entity.OrderDetails;
import learnUp.dz19.entity.Orders;
import learnUp.dz19.service.buyer.BuyerService;
import learnUp.dz19.service.orderDetails.OrderDetailsService;
import learnUp.dz19.service.orders.OrdersService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class OrdersController {

    private final OrdersService ordersService;

    private final OrderDetailsService orderDetailsService;

    private final BuyerService buyerService;

    public OrdersController(OrdersService ordersService, OrderDetailsService orderDetailsService, BuyerService buyerService) {
        this.ordersService = ordersService;
        this.orderDetailsService = orderDetailsService;
        this.buyerService = buyerService;
    }

    @GetMapping("/orders")
    @Transactional
    public List<Orders> getAllOrders (){
        return ordersService.getOrders();
    }

    @GetMapping("/orders/{id}")
    @Transactional
    public Orders getOrderById(@PathVariable Long id){
        Orders orders = ordersService.findOrderById(id);
        if(orders==null){
            throw new EntityExistsException("Заказ c id " + id + " не найден");
        }
        return orders;
    }

    @PostMapping("/orders")
    @Transactional
    public Orders addOrder (@RequestBody Orders orders){
    return ordersService.addOrder(orders);
    }

    //добавление/удаление Деталей заказа по id
    @PutMapping("/orders/{id}/add/OD/{OD_id}")
    @Transactional
    public void addODinOrders(@PathVariable Long id, @PathVariable Long OD_id){
        Orders orders1 = ordersService.findOrderById(id);
        if(orders1==null){throw new EntityExistsException("Данный заказ не найден");}

        if(orders1.getOrder_detailss()!=null){
            throw new EntityExistsException("У заказа уже есть Детали заказа");
        }
        OrderDetails orderDetails = orderDetailsService.getOrderDetailsById(OD_id);
        if(orderDetails==null){
            throw new EntityExistsException("Деталей заказа не найден");
        }
        orders1.setOrder_detailss(orderDetails);

        ordersService.addODinOrder(orders1);
    }

    @DeleteMapping("/orders/{id}/delete/OD/{OD_id}")
    @Transactional
    public void deleteODinOrders(@PathVariable Long id, @PathVariable Long OD_id){
        Orders orders1 = ordersService.findOrderById(id);
        if(orders1==null){throw new EntityExistsException("Данный заказ не найден");}

        OrderDetails orderDetails = orderDetailsService.getOrderDetailsById(OD_id);
        if(orderDetails==null){throw new EntityExistsException("У заказа нет данных Деталей заказа");}

        if(orders1.getOrder_detailss().getId()==orderDetails.getId()){
            Set<Book> a = orderDetails.getBooks();
            for (Book b: a
                 ) {orderDetailsService.deleteBooksByOrdersDetails(orderDetails, b);
            }
            orders1.setOrder_detailss(null);
        }

        ordersService.deleteODinOrder(orders1);
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrderDetails(@PathVariable Long id){
        Orders orders = ordersService.findOrderById(id);
        if(orders==null){
            return "Not find Orders";
        }
        ordersService.delete(orders);
        return "OrderDetails с id =  " + id + " удалена";
    }
}

