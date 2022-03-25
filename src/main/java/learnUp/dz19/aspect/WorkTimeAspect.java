package learnUp.dz19.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class WorkTimeAspect {

    private static  final Logger log = LoggerFactory.getLogger(WorkTimeAspect.class);

  @After("@annotation(learnUp.dz19.annotation.WorkTime)")
    public Object workTime(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.getTarget();
        long workTimes = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + "Время работы метода" + start + " равно" +  workTimes + " мс");

        return proceed;
    }

    @AfterReturning(("@annotation(learnUp.dz19.annotation.LogMethod)"))
            public void logMethod (JoinPoint joinPoint){
      log.info("{имя метода}", joinPoint.getSignature().getName());
      log.info("{параметры метода}", joinPoint.getArgs());
        Signature signature = joinPoint.getSignature();
      log.info("{возвращаемое значение}", ((MethodSignature) signature).getReturnType());
            }

}
