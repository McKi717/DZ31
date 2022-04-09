package learnUp.dz19;

import learnUp.dz19.entity.Book;
import learnUp.dz19.repository.BookRepository;
import learnUp.dz19.service.author.AuthorService;
import learnUp.dz19.service.book.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
/*@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})*/
//@ComponentScan({"learnUp.dz19"})
public class Main {

    private static  final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

//        AuthorService authorService = context.getBean(AuthorService.class);
//        log.info("Authors: {}", authorService.getAuthor());

//        BookService bookService = context.getBean(BookService.class);
////        log.info("Authors: {}", bookService.getBook());

        BookRepository bookRepository = context.getBean(BookRepository.class);
        BookService bookService = context.getBean(BookService.class);

        log.info("BooksAuthors: {}", bookService.getBooksByNameAuthor("Leonov"));

        }
}
