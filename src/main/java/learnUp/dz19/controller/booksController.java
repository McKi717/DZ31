package learnUp.dz19.controller;

import learnUp.dz19.dao.book.BookFilter;
import learnUp.dz19.entity.Book;
import learnUp.dz19.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityExistsException;
import javax.persistence.LockModeType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class booksController {

    @Autowired
    private  BookService bookService;

    @GetMapping("/books")
    @Transactional
    public List<Book> getBooks(@RequestParam(value = "nameBook",required = false)String nameBook)
    {return bookService.getBooksBy(new BookFilter(nameBook))
            .stream()
            .collect(Collectors.toList());}

    @GetMapping("/books/{id}")
    @Transactional
    public Book getBookById (@PathVariable Long id){
        Book book = bookService.findBookById(id);
        if(book==null){
            throw new EntityExistsException("Не найден id " + id);
        }
        return book;
    }

    @PostMapping("/books")
    @Transactional
    public Book addBook(@RequestBody Book book){
        bookService.addBook(book);
        return book;
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.delete(id);
        return "Книга с id =  " + id + " удалена";
    }

    @PutMapping("/books/{id}")
    @Transactional
    public Book updateBook(@RequestParam("id") Long id, @RequestBody Book book){
        Book book1 = bookService.findBookById(id);
        if(book1==null){
            throw new EntityExistsException("Не найден id " + id);
        }
        bookService.updateBook(book);
        return book;
    }
}
