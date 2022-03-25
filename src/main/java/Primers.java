import learnUp.dz19.annotation.LogMethod;
import learnUp.dz19.annotation.WorkTime;

import org.springframework.stereotype.Service;

@Service
public class Primers {

    @WorkTime
    public void workTime() throws InterruptedException {
        Thread.sleep(3000);
    }

    @LogMethod
    public boolean logMethod(int a){
        if(a<1){
            return true;
        }
            return false;
    }

}
