package learnUp.dz19;

import learnUp.dz19.annotation.LogMethod;
import learnUp.dz19.annotation.WorkTime;
import org.springframework.stereotype.Service;

@Service
public class Primers {

    @WorkTime
    public String workTime() throws InterruptedException {
        Thread.sleep(3000);
        return null;
    }

    @LogMethod
    public boolean logMethod(int a){
        if(a<1){
            return true;
        }
            return false;
    }

}
