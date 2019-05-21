package trainingportal.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import springemail.Sender;

@Configuration
public class EmailSenderConfig {

    @Bean
    public Sender getJavaMailSender() {
        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:sender.xml");
        //Get the mailer instance
        Sender mailSender = (Sender) context.getBean("mailService");


        return mailSender;
    }
}
