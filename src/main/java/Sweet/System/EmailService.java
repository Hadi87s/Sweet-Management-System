package Sweet.System;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

    private static final String username = "SweetSystemInstitution@gmail.com"; // replace with your email
    private static final String password = "dgyi ghtp rcnb oamn"; // replace with your email password
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

    private static Session getSession() {
        return Session.getInstance(getProperties(), new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public static void sendEmail(String toEmail, String subject, String body) {
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println(ANSI_BRIGHT_YELLOW + "Email sent successfully" + ANSI_RESET);
            System.out.println();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
