package learnUp.dz19;

import org.springframework.context.ApplicationListener;

import java.util.Locale;

public class MyEventListener implements ApplicationListener<MyEvent> {

    private final int a = 0;
    private final int b = 1000;
    private final int randomNum = a + (int) (Math.random() * b);


    @Override
    public void onApplicationEvent(MyEvent event) {
        if (event.ourNum < randomNum) {
            System.out.println(Main.rBM.getMessage("3", null, Locale.US));
        } else if (event.ourNum > randomNum) {
            System.out.println(Main.rBM.getMessage("4", null, Locale.US));
        } else {
            System.out.println(Main.rBM.getMessage("5"+ randomNum, new Object[] {randomNum}, Locale.US));
        }
    }
}
