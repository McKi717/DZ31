package learnUp.dz19.controller;

import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.OrderDetails;
import learnUp.dz19.service.book.BookService;
import learnUp.dz19.service.orderDetails.OrderDetailsService;
import org.aspectj.weaver.ast.Or;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersDetailsControler {

    private final OrderDetailsService orderDetailsService;

    private final BookService bookService;

    public OrdersDetailsControler(OrderDetailsService orderDetailsService, BookService bookService) {
        this.orderDetailsService = orderDetailsService;
        this.bookService = bookService;
    }

    @GetMapping("/od/{id}")
    @Transactional
    public OrderDetails getOrderDetailsById (@PathVariable Long id){
        OrderDetails orderDetails = orderDetailsService.getOrderDetailsById(id);
        if(orderDetails==null){
            throw new EntityExistsException("Не найден id " + id);
        }
        return orderDetails;
    }

    @GetMapping("/od")
    @Transactional
    public List<OrderDetails> getAllOD(){
        return orderDetailsService.getAllOrderDetails();
    }


    @PostMapping("/od")
    @Transactional
    public OrderDetails addOD(@RequestBody OrderDetails orderDetails){
        return orderDetailsService.addOrdersDetails(orderDetails);
    }

    @PostMapping("/od/{id}/{book_id}")
    @Transactional
    public void addBookOnOD(@PathVariable("id") Long id, @PathVariable("book_id") Long bookId,
                            @RequestBody(required = false) OrderDetails orderDetails){
        OrderDetails orderDetails1 = orderDetailsService.getOrderDetailsById(id);
        Book book1 = bookService.findBookById(bookId);
        if(orderDetails1==null){
            throw new EntityExistsException("Заказа с id " + id + " не существует");
        }
        else{

             orderDetailsService.addBooksByOrdersDetails(orderDetails1, book1);
        }
    }

    @DeleteMapping("/od/{id}/delete/{book_id}")
    @Transactional
    public void deleteBookOnOD(@PathVariable("id") Long id, @PathVariable("book_id") Long bookId){
        OrderDetails orderDetails1 = orderDetailsService.getOrderDetailsById(id);
        Book book1 = bookService.findBookById(bookId);
        if(orderDetails1==null){
            throw new EntityExistsException("Заказа с id " + id + " не существует");
        }

        orderDetailsService.deleteBooksByOrdersDetails(orderDetails1, book1);
    }

    @DeleteMapping("/od/{id}")
    public String deleteOrderDetails(@PathVariable Long id){
        OrderDetails orderDetails = orderDetailsService.getOrderDetailsById(id);
        if(orderDetails==null){
            return "Not find OrdersDetails";
        }

        orderDetailsService.delete(id);
        return "OrderDetails с id =  " + id + " удалена";
    }
}
