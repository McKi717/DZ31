package learnUp.dz19;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class EventConfig {

    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.addBasenames("text");
        return messageSource;
    }
    @Bean
    public MyEventPublisher getEventPublisher(){
        return  new MyEventPublisher();
    }

    @Bean
    public MyEventListener getEventListener(){
        return new MyEventListener();
    }
}
