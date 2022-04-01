package learnUp.dz19;

import learnUp.dz19.dao.Book;
import learnUp.dz19.dao.BookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

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

    }
}
