package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

import javax.annotation.PostConstruct;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 02.05.2017
 */

@Component
@Scope("view")
public class MessageController {

    @Autowired
    private MessageService messageService;
    
    private Message message;
    
    public Message getMessage() {
        return message;
    }

    @PostConstruct
    public void init(){
    	message = new Message();
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }

    public void doReloadMessage() {
        message = messageService.loadMessage(message.getId());
    }

    public Collection<Message> getMessages(){
        return messageService.getAllMessages();
    }
    
    public void doDeleteParent() {
        this.messageService.deleteMessage(message);
        message = null;
    }
}
