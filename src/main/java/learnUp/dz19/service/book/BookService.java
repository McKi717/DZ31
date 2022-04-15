package learnUp.dz19.service.book;

import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.BookWareHouse;
import learnUp.dz19.repository.BookRepository;
import learnUp.dz19.repository.BookWareHouseRepository;
import learnUp.dz19.service.bookWareHouse.BookWareHouseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBook(){return bookRepository.findAll();}

    public List<Book> getBooksByNameAuthor(String author){
        return  bookRepository.findBookByAuthor(author);
    }

    public Book addBook (Book book){
        return bookRepository.save(book);
    }

    public Book findBookById (Long id){return bookRepository.findBookById(id);}
}
