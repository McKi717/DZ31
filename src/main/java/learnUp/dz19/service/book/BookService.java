package learnUp.dz19.service.book;

import learnUp.dz19.entity.Book;
import learnUp.dz19.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBook(){return bookRepository.findAll();}
}
