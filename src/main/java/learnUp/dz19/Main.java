package learnUp.dz19;

import learnUp.dz19.service.author.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
/*@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})*/
//@ComponentScan({"learnUp.dz19"})
public class Main {

    private static  final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        Primers primers = context.getBean(Primers.class);

        log.info("{}", primers.logMethod(1));
        log.info(primers.workTime());

        AuthorService authorService = context.getBean(AuthorService.class);
        log.info("Authors: {}", authorService.getAuthor());
        }
}
