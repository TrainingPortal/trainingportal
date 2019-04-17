package email.mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Sender {

    static final String ENCODING = "UTF-8";

    //Default GMIAL configurations

    String subject = "Subject";
    String content = "Testing the mail from Sender class";
    String address = "group1courseportal@gmail.com";
    String login = "group1courseportal@gmail.com";
    String password = "group1WLGy3NC";
    String smtpHost = "smtp.gmail.com";
    String smtpPort = "587";

    static File attachFiles = null;

    public static void sendSimpleMessage(String login, String password, String from, String to, String content, String subject, String smtpPort, String smtpHost) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {

        Properties props = System.getProperties();
        setProps(props,smtpPort,smtpHost);


        Authenticator auth = new MyAuthenticator(login, password);
        Session session = Session.getDefaultInstance(props, auth);

        //create new msg
        Message msg = new MimeMessage(session);
        //set from who sessage will be sent
        msg.setFrom(new InternetAddress(from));
        //set why is recipient
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //set the topic/subject/theme in msg
        msg.setSubject(subject);
        //set we context of the msg
        msg.setText(content);
        //sending msg to recipient
        Transport.send(msg);

        System.out.println("Sent message successfully....");
    }

    public static void sendMultiMessage(String login, String password, String from, String to, String content, String subject, String attachment, String[] attachfiles, String smtpPort, String smtpHost) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {

        Properties props = System.getProperties();
        setProps(props,smtpPort,smtpHost);

        Authenticator auth = new MyAuthenticator(login, password);
        Session session = Session.getDefaultInstance(props, auth);

        //create new msg
        MimeMessage msg = new MimeMessage(session);
        //set from who sessage will be sent
        msg.setFrom(new InternetAddress(from));
        //set why is recipient
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //set the topic/subject/theme in msg
        msg.setSubject(subject, ENCODING);

        //create body part of MimeBodyPart
        BodyPart messageBodyPart = new MimeBodyPart();
        //set the content of parts to messageBodyPart
        messageBodyPart.setContent(content, "text/plain; charset=" + ENCODING + "");

        Multipart multipart = new MimeMultipart();
        //add part to multipart
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment);

        //HERE WE CHECK THE INPUT ARRAY FILES THAT NEED TO BEE ADDED TO MAIL IF THERE MORE THEN ONE FILE
        if (attachfiles != null && attachfiles.length > 0) {

            for (String file : attachfiles) {

                attachFiles = new File(file);

                if (attachFiles.exists()) {
                    //attach file
                    attachFile(attachFiles, multipart, new MimeBodyPart());
                }

            }
        }

        //There we inject/add attachment NOT ATTACHFILES and it can not be equal to null otherwise it throw java.lang.NullPointerException

        //add source to DataHandler and deliver it to MimeBodyPart
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName()));
        multipart.addBodyPart(attachmentBodyPart);

        msg.setContent(multipart);

        Transport.send(msg);

        System.out.println("Sent message successfully....");
    }

    public static void sendSimpleMessageGMAIL(String login, String password, String from, String to, String content, String subject) throws UnsupportedEncodingException, MessagingException {
        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";
        sendSimpleMessage(login,password,from,to,content,subject,smtpPort,smtpHost);
    }

    public static void sendMultiMessageGMAIL(String login, String password, String from, String to, String content, String subject, String attachment, String[] attachfiles) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";
        sendMultiMessage(login,password,from,to,content,subject,attachment,attachfiles,smtpPort,smtpHost);
    }

    private static void setProps(Properties props, String smtpPort, String smtpHost){
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", ENCODING);
        props.put("mail.smtp.starttls.enable", "true");
    }

    public static void attachFile(File file, Multipart multipart, MimeBodyPart attachmentBodyPart) throws javax.mail.MessagingException {
        DataSource source = new FileDataSource(file);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(file.getName());
        multipart.addBodyPart(attachmentBodyPart);
    }
}



