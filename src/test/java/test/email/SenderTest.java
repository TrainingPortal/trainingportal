package test.email;

import email.mail.Sender;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;

import java.io.UnsupportedEncodingException;

//@RunWith(Arquillian.class)
public class SenderTest {
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(Sender.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }

    //String login,password,address,content,subject,attachment,smtpPort,smtpHost;
    //String attachment;
    //String[] attachment2;

    String subject = "Subject",
           content = "Testing the email from Sender class",
           smtpHost="smtp.gmail.com",
           address="group1courseportal@gmail.com",
           login="group1courseportal@gmail.com",
           password="group1WLGy3NC",
           smtpPort="587";

    //IMPORTANT TO HAVE CORRECT FULL PATH OR IT THROW EXCEPTION
    String attachment = "/Users/mrlova/Downloads/log.txt";

    //IT CAN BE NULL
    String[] attachment2 = {"/Users/mrlova/Downloads/corejava.pdf" , "/Users/mrlova/Downloads/IMG_0290.jpg"};

    @org.junit.Test
    public void sendSimpleMessage() throws UnsupportedEncodingException, javax.mail.MessagingException {
        Sender.sendSimpleMessage(login,password,address,address,content,subject,smtpPort,smtpHost);
    }

    @org.junit.Test
    public void sendMultiMessage() throws UnsupportedEncodingException, javax.mail.MessagingException {
        Sender.sendMultiMessage(login,password,address,address,content,subject,attachment,attachment2,smtpPort,smtpHost);
    }

    @org.junit.Test
    public void sendSimpleMessageGMAIL() throws UnsupportedEncodingException, javax.mail.MessagingException {
        Sender.sendSimpleMessageGMAIL(login,password,address,address,content,subject);
    }

    @org.junit.Test
    public void sendMultiMessageGMAIL() throws UnsupportedEncodingException, javax.mail.MessagingException {
        Sender.sendMultiMessageGMAIL(login,password,address,address,content,subject,attachment,attachment2);
    }
}
