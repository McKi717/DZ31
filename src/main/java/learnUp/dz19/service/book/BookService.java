package learnUp.dz19.service.book;

import learnUp.dz19.dao.book.BookFilter;
import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.BookWareHouse;
import learnUp.dz19.entity.BookWareHouse;
import learnUp.dz19.repository.BookRepository;
import learnUp.dz19.repository.BookWareHouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.List;

import static learnUp.dz19.repository.BookSpecifiacation.byFilter;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    private final BookWareHouseRepository bookWareHouseRepository;

    public BookService(BookRepository bookRepository, BookWareHouseRepository bookWareHouseRepository) {
        this.bookRepository = bookRepository;
        this.bookWareHouseRepository = bookWareHouseRepository;
    }

    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();}

    public List<Book> getBooksByNameAuthor(String author){
        return  bookRepository.findBookByAuthor(author);
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void updateBook(Book book){
        try {
            bookRepository.save(book);
        }
        catch (OptimisticLockException e){
            log.warn("Optimistic lock exception");
            throw e;
        }
    }

    @Transactional
    public Book addBook (Book book){
        for (Book b: getAllBooks()
             ) {if(b.getNameBook().equals(book.getNameBook()))
             {
                 BookWareHouse bookWareHouse = b.getBookRemainder();
                 bookWareHouse.setRemainder(bookWareHouse.getRemainder() + 1);
                 b.setBookRemainder(bookWareHouse);
                 updateBook(b);
                 bookWareHouseRepository.save(b.getBookRemainder());
                 return null;
             }
        }
        BookWareHouse bookWareHouse = new BookWareHouse();
        bookWareHouse.setRemainder(bookWareHouse.getRemainder()+1);
        book.setBookRemainder(bookWareHouse);
        bookWareHouseRepository.save(book.getBookRemainder());
       return bookRepository.save(book);
    }

    @Transactional
    @Lock(value= LockModeType.OPTIMISTIC)
    public Boolean delete(Long id) {
       try{bookRepository.delete(bookRepository.findBookById(id));
           return true;}
       catch (OptimisticLockException e){
           log.warn("Optimistick lock for book delete with id " + id);
           throw e;
       }
    }

    public List<Book> getBooksBy(BookFilter filter){
        Specification<Book> specification = where(byFilter(filter));
        return bookRepository.findAll(specification);
    }

    public Book findBookById (Long id){return bookRepository.findBookById(id);}
}
