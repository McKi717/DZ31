package learnUp.dz19;

import org.springframework.context.ApplicationListener;

import java.util.Locale;

public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        if (event.ourNum < event.getRandomNum()) {
            System.out.println(Main.context.getMessage("4", null, Locale.US));
        } else if (event.ourNum > event.getRandomNum()) {
            System.out.println(Main.context.getMessage("3", null, Locale.US));
        } else {
            System.out.println(Main.context.getMessage("5"+ event.getRandomNum(), new Object[] {event.getRandomNum()}, Locale.US));
        }
    }
}
