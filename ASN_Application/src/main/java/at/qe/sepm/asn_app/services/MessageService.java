package at.qe.sepm.asn_app.services;
import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.MessageRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;


/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */

@Component
@ViewScoped
public class MessageService implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    
    private String text;

    public Collection<Message> getAllMessages(){
    	Collection<Message> temp = messageRepository.findAll();
        Collections.reverse( (List<?>) temp );
        return temp;
    }
    
    
    public Message saveMessage(String text){
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"MESSAGE POSTED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogRepository.save(log);
    	Message message = new Message(getAuthenticatedUser().getUsername(), text, new Date());
    	return messageRepository.save(message);
    }
    
    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

}
