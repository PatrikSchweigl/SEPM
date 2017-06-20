package at.qe.sepm.asn_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 13.05.2017
 */
@Component
@Scope("application")
public class MailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(final String recipient, final String subject, final String message){

        MimeMessagePreparator preparator = getMessagePreparator(recipient, subject, message);

        try{
            mailSender.send(preparator);
        }catch(MailException ex){
            ex.printStackTrace();
        }

    }

    private MimeMessagePreparator getMessagePreparator(final String recipientMail, final String subject, final String message) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("care4fun.sepm@gmail.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipientMail));
                mimeMessage.setText(message);
                mimeMessage.setSubject(subject);
            }
        };
        return preparator;
    }
}
