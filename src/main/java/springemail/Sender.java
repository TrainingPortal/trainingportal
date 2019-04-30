package springemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service("mailService")
public class Sender {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendMailWithAttachment(String to, String subject, String text, String fullPathToAttachment) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        if (fullPathToAttachment != null) {

            FileSystemResource file
                    = new FileSystemResource(new File(fullPathToAttachment));
            helper.addAttachment(file.getFilename(), file);
        }

        javaMailSender.send(message);
    }

}
