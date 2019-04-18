package springemail;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.mail.MessagingException;


class SenderTest {

    @Test
    void sendMail() {

        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("sender.xml");

        //Get the mailer instance
        Sender mailer = (Sender) context.getBean("mailService");

        //Send a composed mail
        mailer.sendMail("group1courseportal@gmail.com", "Test Subject", "Testing Simple message body");
    }

    @Test
    void sendMailWithAttachment() throws MessagingException {

        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("sender.xml");

        //Get the mailer instance
        Sender mailer = (Sender) context.getBean("mailService");

        //Send a composed mail
        mailer.sendMailWithAttachment("group1courseportal@gmail.com", "Test Subject", "Testing Mail With Attachment body","Null");
    }
}