package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.services.MessageService;
import at.qe.sepm.asn_app.services.UserService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 02.05.2017
 */

@Component
//@Scope("view")
@Scope("application")
public class MessageController extends Thread {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    private Message message;
    private Collection<Message> messages;

    public Message getMessage() {
        return message;
    }

    @PostConstruct
    public void init() {
        message = new Message();
    }

    @PostConstruct
    public void initList() {
        setMessages(messageService.getAllMessages());
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public void setMessage(Message message) {
        this.message = message;
        doReloadMessage();
    }

    public void setMessage2(Message message) {
        this.message = message;
    }

    public void doReloadMessage() {
        message = messageService.loadMessage(message.getId());
    }

    public Message doSaveMessage() {
        Message messageReturn = null;
        if (message == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Leere Nachrichten können nicht gesendet werden!", null));
        } else {
            message = messageService.saveMessage(message);
            messageReturn = message;
            Thread t = new Thread();
            t.start();

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('createMessage').hide()");

            init();
            initList();
        }
        return messageReturn;
    }

    @Override
    public void run() {
        Iterator<UserData> iterator = userService.getParentsByNotification().iterator();
        while (iterator.hasNext()) {
            UserData user = iterator.next();
            UserData sender = userService.loadUser(message.getUsername());
            mailService.sendEmail(user.getEmail(), "Care4Fun - Neuigkeiten", "Guten Tag " + user.getFirstName() + " " + user.getLastName() +
                    "!\n\nEs gibt eine neue Nachricht von " + sender.getFirstName() + " " + sender.getLastName() + " auf unserer Pinnwand für Sie.\n\n" +
                    "Schönen Tag wünscht das Kinderkrippen-Team!");

        }
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void doDeleteMessage(Message message) {
        messageService.deleteMessage(message);
        initList();
    }
}
