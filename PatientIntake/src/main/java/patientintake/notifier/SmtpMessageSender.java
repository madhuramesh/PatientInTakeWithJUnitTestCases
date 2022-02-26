package patientintake.notifier;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpMessageSender {

    public void sendNotification(String subj, String body, String addr){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host","localhost");
        Session session = Session.getDefaultInstance(properties,null);
        Message message = new MimeMessage(session);
        try{
            message.setFrom(new InternetAddress("system@patientintake.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(addr));
            message.setSubject(subj);
            message.setContent(body, "text/html");
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
