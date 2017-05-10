package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.models.nursery.PrivateMessage;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.MessageService;
import at.qe.sepm.asn_app.services.PrivateMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;



@Component
@Scope("view")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService messageService;
	@Autowired
	private UserRepository userRepository;

    private PrivateMessage message;
    
    private String privateMessage;
    
    private String username;
    
    public PrivateMessage getMessage() {
        return message;
    }

    @PostConstruct
    public void init(){
    	message = new PrivateMessage();
    	privateMessage = new String();
    }
    
    public void setMessage(PrivateMessage message) {
        this.message = message;
    }

    public void doReloadMessage() {
        message = messageService.loadPrivateMessage(message.getId());
    }

    public Collection<PrivateMessage> getAllPrivateMessages(){
		System.err.println("HHHHHHHHHHHHHHHHHHHHH");
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
        return messageService.getAllPrivateMessages();
    }
    
    public void doDeleteMessage() {
        this.messageService.deletePrivateMessage(message);
        message = null;
    }
    
    public void savePrivateMessage(){
		System.err.println("HHHHHHHHHHHHHHHHHHHHH");
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
    	PrivateMessage privateMessages = new PrivateMessage();
    	privateMessages.setDate(new Date());
    	privateMessages.setMessage(privateMessage);
    	privateMessages.setUsernameSender(getAuthenticatedUser().getUsername());
    	privateMessages.setUsernameReceiver(username);
    	messageService.savePrivateMessage(privateMessages);
    }
    
    public Collection<PrivateMessage> getPrivateMessagesBySender(String usernameS, String usernameR){
    	if(username == null){
    		System.err.println("HHHHHHHHHHHHHHHHHHHHH");
            return messageService.getAllPrivateMessagesBySender("cheng", "fatima");
 
    	}
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
        return messageService.getAllPrivateMessagesBySender(usernameS, usernameR);
    }
    public Collection<PrivateMessage> getPrivateMessagesByReceiver(String username){

        return messageService.getAllPrivateMessagesByReceiver(username);
    }
   
    
    
    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return userRepository.findFirstByUsername(auth.getName());
    }

	public String getUsername() {
		System.err.println("HHHHHHHHHHHHHHHHHHHHH");
		System.err.println(username);
		System.err.println("TTTTTTTTTTTTTTTTTTTTT");
		return username;
	}

	public String setUsername(String username) {
		System.err.println("HHHHHHHHHHHHHHHHHHHHH");
		System.err.println(username);
		System.err.println("HHHHHHHHHHHHHHHHHHHHH");

		this.username = username;
		return username;
	}

	public String getPrivateMessage() {
		return privateMessage;
	}

	public void setPrivateMessage(String privateMessage) {
		this.privateMessage = privateMessage;
	}
}
