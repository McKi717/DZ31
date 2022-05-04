package learnUp.dz19.service.orderDetails;

import learnUp.dz19.entity.*;
import learnUp.dz19.repository.AuthorRepository;
import learnUp.dz19.repository.BookRepository;
import learnUp.dz19.repository.BookWareHouseRepository;
import learnUp.dz19.repository.OrderDetailsRepository;
import learnUp.dz19.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Slf4j
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    private final BookRepository bookRepository;

    private final BookWareHouseRepository bookWareHouseRepository;

    private final BookService bookService;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, BookRepository bookRepository, BookWareHouseRepository bookWareHouseRepository, BookService bookService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.bookRepository = bookRepository;
        this.bookWareHouseRepository = bookWareHouseRepository;
        this.bookService = bookService;
    }

    public List<OrderDetails> getAllOrderDetails(){
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrderDetailsById(Long id){
        return orderDetailsRepository.findOrderDetailsById(id);
    }

    public OrderDetails addOrdersDetails(OrderDetails orderDetails){
        if(orderDetails.getOrders()==null){
            Orders orders = new Orders();
            Buyer buyer = new Buyer();
            orders.setBuyer(buyer);
            orders.setOrder_detailss(orderDetails);
            orderDetails.setOrders(orders);
        }
        return  orderDetailsRepository.save(orderDetails);
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void deleteBooksByOrdersDetails (OrderDetails orderDetails, Book book){
        try {
            int price = orderDetails.getPrice();
            int quant = orderDetails.getQuantity();

            orderDetails.removeBook(book);

                quant -= 1;
                price -= book.getPrice();
                if(quant<0)quant=0;
                if(price<0)price=0;

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

            if(book.getBookRemainder()==null){
                BookWareHouse bookWareHouse = new BookWareHouse();
                book.setBookRemainder(bookWareHouse);
            }

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

//    @Transactional
//    @Lock(value = LockModeType.OPTIMISTIC)
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
    try{OrderDetails orderDetails = orderDetailsRepository.findOrderDetailsById(id);

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
