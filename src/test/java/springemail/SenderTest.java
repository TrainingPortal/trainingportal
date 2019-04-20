package springemail;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
<<<<<<< HEAD
import javax.mail.MessagingException;

class SenderTest {


    @Test
    void sendMail() {
=======

import javax.mail.MessagingException;


class SenderTest {

    @Test
    void sendMail() {

>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("sender.xml");

        //Get the mailer instance
<<<<<<< HEAD
        Sender sender = (Sender) context.getBean("mailService");

        //Send a composed mail
        sender.sendMail("group1courseportal@gmail.com", "Test Subject","Testing sendMail Simple message body");

    }

    @Test
    void sendEmailWithAttachment() throws MessagingException {
=======
        Sender mailer = (Sender) context.getBean("mailService");

        //Send a composed mail
        mailer.sendMail("group1courseportal@gmail.com", "Test Subject", "Testing Simple message body");
    }

    @Test
    void sendMailWithAttachment() throws MessagingException {

>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("sender.xml");

        //Get the mailer instance
<<<<<<< HEAD
        Sender sender = (Sender) context.getBean("mailService");

        //Send a composed mail
        sender.sendEmailWithAttachment("group1courseportal@gmail.com", "Test Subject", "Testing sendEmailWithAttachment message boddy","/Users/mrlova/Downloads/log.txt");

=======
        Sender mailer = (Sender) context.getBean("mailService");

        //Send a composed mail
        mailer.sendMailWithAttachment("group1courseportal@gmail.com", "Test Subject", "Testing Mail With Attachment body","Null");
>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
    }
}