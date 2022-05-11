package learnUp.dz19.controller;

import learnUp.dz19.dao.book.BookFilter;
import learnUp.dz19.entity.Author;
import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.BookWareHouse;
import learnUp.dz19.service.author.AuthorService;
import learnUp.dz19.service.book.BookService;
import learnUp.dz19.service.bookWareHouse.BookWareHouseService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class booksController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final BookWareHouseService bookWareHouseService;

    public booksController(BookService bookService, AuthorService authorService, BookWareHouseService bookWareHouseService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookWareHouseService = bookWareHouseService;
    }

    @GetMapping("/books")
    @Transactional
    public List<Book> getBooks(@RequestParam(value = "nameBook",required = false)String nameBook)
    {return bookService.getBooksBy(new BookFilter(nameBook))
            .stream()
            .collect(Collectors.toList());}

    @GetMapping("/books/{id}")
    public Book getBookById (@PathVariable Long id){
        Book book = bookService.findBookById(id);
        if(book==null){
            throw new EntityExistsException("Не найден id " + id);
        }
        return book;
    }

    @PostMapping("/books")
    @Transactional
    @Secured("ROLE_ADMIN")
    public Book addBook(@RequestBody Book book){
        bookService.addBook(book);
        return book;
    }

    @DeleteMapping("/books/{id}")
    @Secured("ROLE_ADMIN")
    public String deleteBook(@PathVariable Long id){
        bookService.delete(id);
        return "Книга с id =  " + id + " удалена";
    }

    @PutMapping("/books/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        Book book1 = bookService.findBookById(id);
        if(book1==null){throw new EntityExistsException("Нет такого id книги");}
        book1.setNameBook(book.getNameBook());
        book1.setPages(book.getPages());
        book1.setPrice(book.getPrice());
        book1.setReleaseYear(book.getReleaseYear());

        //Изменение автора
        Author author1 = new Author();

        if(book.getAuthor()==null){
            throw new EntityExistsException("Поле автора пустое ");
        }

        List<Author> sd = authorService.getAllAuthors();
        for (Author a: sd
             ) {if(a.getId()==book.getAuthor().getId()){
                 author1=a;}
        }
        book1.setAuthor(author1);

        //Изменение остатка
        BookWareHouse bookWareHouse = new BookWareHouse();

        if(book.getBookRemainder()==null){
            book.setBookRemainder(book1.getBookRemainder());
        }

        List<BookWareHouse> bookWareHouseList = bookWareHouseService.getBookWareHouse();
        for (BookWareHouse b: bookWareHouseList
             ) {if(b.getId()==book.getBookRemainder().getId()){
                 bookWareHouse = b;
        }
            if(book.getBookRemainder().getRemainder()>-1){
                bookWareHouse.setRemainder(book.getBookRemainder().getRemainder());
            }
            else{throw new EntityExistsException("остаток книг не может быть меньше 0");}
        }
        book1.setBookRemainder(bookWareHouse);


        bookService.updateBook(book1);
        return book1;
    }
}
