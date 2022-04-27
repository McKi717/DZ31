package learnUp.dz19;

import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.OrderDetails;
import learnUp.dz19.entity.Role;
import learnUp.dz19.entity.User;
import learnUp.dz19.repository.RoleRepository;
import learnUp.dz19.repository.UserRepository;
import learnUp.dz19.service.author.AuthorService;
import learnUp.dz19.service.book.BookService;
import learnUp.dz19.service.orderDetails.OrderDetailsService;
import learnUp.dz19.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class Main {

    private static  final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);


        }




}
