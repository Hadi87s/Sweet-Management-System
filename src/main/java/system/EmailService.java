package system;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());
    private static final String EMAIL = "SweetSystemInstitution@gmail.com";
    private static final String PASSWORD = System.getenv("password");

    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_RESET = "\u001B[0m";


    private EmailService() {
        super();
    }


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
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });
    }


    public static void sendEmail(String toEmail, String subject, String body) throws EmailSendingException {
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            LOGGER.log(Level.INFO, "{0}Email sent successfully{1}", new Object[]{ANSI_BRIGHT_YELLOW, ANSI_RESET});

        } catch (MessagingException e) {
            throw new EmailSendingException("Failed to send email to " + toEmail, e);
        }
    }
}
