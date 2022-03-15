package learnUp.dz19;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    public int ourNum;

    public MyEvent (int ourNum) {
        super(ourNum);
        this.ourNum = ourNum;
    }
}
