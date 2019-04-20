package springemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
<<<<<<< HEAD
=======
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

=======
>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

<<<<<<< HEAD

=======
>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
@Service("mailService")
public class Sender {

    @Autowired
    private MailSender mailSender;

<<<<<<< HEAD
    @Autowired()
=======
    @Autowired
>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
    private JavaMailSender javaMailSender;

    /**
     * This method will send compose and send the message
     * */
<<<<<<< HEAD
    public void sendMail(String to, String subject, String body) {
=======
    public void sendMail(String to, String subject, String body)
    {
>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

<<<<<<< HEAD
    public void sendEmailWithAttachment(String to, String subject, String body, String pathToAttachment) throws MessagingException {
=======
    /**
     * This method will send compose and send the message with attachment
     * */
    public void sendMailWithAttachment(String to, String subject, String text, String fullPathToAttachment) throws MessagingException {
>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
<<<<<<< HEAD
        helper.setText(body);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));

        helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(message);

=======
        helper.setText(text);

        if (fullPathToAttachment != null) {

            FileSystemResource file
                    = new FileSystemResource(new File(fullPathToAttachment));
            helper.addAttachment(file.getFilename(), file);
        }

        javaMailSender.send(message);
>>>>>>> f9ad89d242949353142f0fcfe932c7790f1acd89
    }

}
