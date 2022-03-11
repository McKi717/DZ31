package learnUp.dz19;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;
import java.util.Scanner;

public class Main{

    public static ApplicationContext context = new ClassPathXmlApplicationContext("configuration.xml");

    public static void main(String[] args) {

        Locale locale = Locale.US;

        System.out.println(context.getMessage("1", null, locale));

        System.out.println(context.getMessage("2", null, locale));

        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);

        boolean isTrue = false;
    do{
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        publisher.publishEvent(num);
    } while (!isTrue);
    }
}
