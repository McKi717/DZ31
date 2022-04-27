package learnUp.dz19.service.orderDetails;

import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.BookWareHouse;
import learnUp.dz19.entity.OrderDetails;
import learnUp.dz19.repository.BookRepository;
import learnUp.dz19.repository.BookWareHouseRepository;
import learnUp.dz19.repository.OrderDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.apache.coyote.http11.Constants.a;

@Service
@Slf4j
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    private final BookRepository bookRepository;

    private final BookWareHouseRepository bookWareHouseRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, BookRepository bookRepository, BookWareHouseRepository bookWareHouseRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.bookRepository = bookRepository;
        this.bookWareHouseRepository = bookWareHouseRepository;
    }

    public List<OrderDetails> getAllOrderDetails(){
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrderDetailsById(Long id){
        return orderDetailsRepository.findOrderDetailsById(id);
    }

    public OrderDetails addOrdersDetails(OrderDetails orderDetails){

        return  orderDetailsRepository.save(orderDetails);
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void deleteBooksByOrdersDetails (OrderDetails orderDetails, Book book){
        try {
            int price = orderDetails.getPrice();
            int quant = orderDetails.getQuantity();
            boolean a = false;

            Set<Book> books = orderDetails.getBooks();

                books.remove(book);
                quant -= 1;
                price -= book.getPrice();


            if (book.getBookRemainder() != null) {
                BookWareHouse bookWareHouse = book.getBookRemainder();
                bookWareHouse.setRemainder(bookWareHouse.getRemainder() + 1);

                book.setBookRemainder(bookWareHouse);
                bookWareHouseRepository.save(book.getBookRemainder());
            } else {
                BookWareHouse bookWareHouse = new BookWareHouse();
                bookWareHouse.setRemainder(1);
                book.setBookRemainder(bookWareHouse);
                bookWareHouseRepository.save(book.getBookRemainder());
            }

            orderDetails.setQuantity(quant);
            orderDetails.setBooks(books);
            book.setOrder_details(orderDetails);

            orderDetails.setPrice(price);

            bookRepository.save(book);
            orderDetailsRepository.save(orderDetails);
        }
        catch(OptimisticLockException e){
            log.warn("Optimistick lock");
            throw e;
        }
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void addBooksByOrdersDetails (OrderDetails orderDetails, Book book){
        try {
            int price = orderDetails.getPrice();
            int quant = orderDetails.getQuantity();

            Set<Book> books = orderDetails.getBooks();

            if(book.getBookRemainder().getRemainder()>0){
                books.add(book);
                quant += 1;
                price += book.getPrice();
            }
            else{throw new EntityExistsException("Книги " + book.getNameBook() + " нет в наличии");}


                if (book.getBookRemainder() != null) {
                    BookWareHouse bookWareHouse = book.getBookRemainder();
                    bookWareHouse.setRemainder(bookWareHouse.getRemainder() - 1);

                    if (bookWareHouse.getRemainder() < 0) {
                        throw new EntityExistsException("книги " + book.getNameBook() + " нет в наличии");
                    }

                    book.setBookRemainder(bookWareHouse);
                    bookWareHouseRepository.save(book.getBookRemainder());
                } else {
                    BookWareHouse bookWareHouse = new BookWareHouse();
                    bookWareHouse.setRemainder(0);
                    book.setBookRemainder(bookWareHouse);
                    bookWareHouseRepository.save(book.getBookRemainder());
                }

                orderDetails.setQuantity(quant);
                orderDetails.setBooks(books);
                book.setOrder_details(orderDetails);

                orderDetails.setPrice(price);

                bookRepository.save(book);
                orderDetailsRepository.save(orderDetails);
            }
        catch(OptimisticLockException e){
                log.warn("Optimistick lock");
                throw e;
            }
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void updateOrderDetails(OrderDetails orderDetails){
        try {
            orderDetailsRepository.save(orderDetails);
        }
        catch (OptimisticLockException e){
            log.warn("Optimistic lock exception");
            throw e;
        }
    }

    @Transactional
    @Lock(value= LockModeType.OPTIMISTIC)
    public Boolean delete(Long id) {
        try{
            OrderDetails orderDetails = orderDetailsRepository.findOrderDetailsById(id);

            Set<Book> books = orderDetails.getBooks();

            for (Book b: books
                 ) {
                BookWareHouse bookWareHouse = b.getBookRemainder();
                bookWareHouse.setRemainder(bookWareHouse.getRemainder()+1);
                b.setBookRemainder(bookWareHouse);
                bookWareHouseRepository.save(bookWareHouse);
            }

            orderDetailsRepository.delete(orderDetails);
            return true;}
        catch (OptimisticLockException e){
            log.warn("Optimistick lock for book delete with id " + id);
            throw e;
        }
    }

}
