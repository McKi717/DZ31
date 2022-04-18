package learnUp.dz19;

import learnUp.dz19.entity.Author;
import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.BookWareHouse;
import learnUp.dz19.entity.Product;
import learnUp.dz19.repository.BookRepository;
import learnUp.dz19.repository.BookWareHouseRepository;
import learnUp.dz19.repository.ProductRepository;
import learnUp.dz19.service.author.AuthorService;
import learnUp.dz19.service.book.BookService;
import learnUp.dz19.service.bookWareHouse.BookWareHouseService;
import learnUp.dz19.service.product.ProductService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
/*@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})*/
//@ComponentScan({"learnUp.dz19"})
public class Main {

    private static  final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
//
//       AuthorService authorService = context.getBean(AuthorService.class);
//////        log.info("Authors: {}", authorService.getAuthor());
////
//        BookWareHouseService bookWareHouseService = context.getBean(BookWareHouseService.class);
//////        BookService bookService = context.getBean(BookService.class);
////////        log.info("Authors: {}", bookService.getBook());
////
//       BookService bookService = context.getBean(BookService.class);
////
//        Author author1 = authorService.getAuthorById(1L);
//       BookWareHouse bookWareHouse = bookWareHouseService.getBHWById(1L);
//        Book book = new Book("Cba", author1,  2000, 150, 250, bookWareHouse );
//        bookService.addBook(book);
//
//
//
        }


}
