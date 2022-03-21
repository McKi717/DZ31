package learnUp.dz19;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static AnnotationConfigApplicationContext cx = new AnnotationConfigApplicationContext(EventConfig.class);
    public static ResourceBundleMessageSource rBM = cx.getBean(ResourceBundleMessageSource.class);

    public static void main(String[] args) {

        Locale locale = Locale.US;

        System.out.println(rBM.getMessage("1", null, locale));
        System.out.println(rBM.getMessage("2", null, locale));

        MyEventPublisher publisher = cx.getBean(MyEventPublisher.class);

        boolean isTrue = false;
    do{
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        publisher.publishEvent(num);
    } while (!isTrue);
    }
    }
