package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.models.nursery.PrivateMessage;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.MessageService;
import at.qe.sepm.asn_app.services.PrivateMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

import javax.annotation.PostConstruct;



@Component
@Scope("view")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService messageService;
    
    private PrivateMessage message;
    
    public PrivateMessage getMessage() {
        return message;
    }

    @PostConstruct
    public void init(){
    	message = new PrivateMessage();
    }
    
    public void setMessage(PrivateMessage message) {
        this.message = message;
    }

    public void doReloadMessage() {
        message = messageService.loadPrivateMessage(message.getId());
    }

    public Collection<PrivateMessage> getMessages(){
        return messageService.getAllPrivateMessages();
    }
    
    public void doDeleteMessage() {
        this.messageService.deletePrivateMessage(message);
        message = null;
    }
    
    public Collection<PrivateMessage> getPrivateMessagesBySender(String username){
        return messageService.getAllPrivateMessagesBySender(username);
    }
    public Collection<PrivateMessage> getPrivateMessagesByReceiver(String username){
        return messageService.getAllPrivateMessagesByReceiver(username);
    }
}
