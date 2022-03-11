package learnUp.dz19;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    private int a = 0;
    private int b = 1000;
    private final int randomNum = a + (int) (Math.random() * b);

    public int getRandomNum() {
        return randomNum;
    }

    public int ourNum;

    public MyEvent (int ourNum) {
        super(ourNum);
        this.ourNum = ourNum;
    }
}
