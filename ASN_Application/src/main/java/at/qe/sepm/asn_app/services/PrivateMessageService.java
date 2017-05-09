package at.qe.sepm.asn_app.services;
import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.PrivateMessage;
import at.qe.sepm.asn_app.repositories.PrivateMessageRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;


import javax.faces.bean.ViewScoped;
import java.io.Serializable;



@Component
@ViewScoped
public class PrivateMessageService implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private PrivateMessageRepository privateMessageRepository;

    public Collection<PrivateMessage> getAllPrivateMessages(){
        return privateMessageRepository.findAll();
    }

    public PrivateMessage savePrivateMessage(PrivateMessage task){
        return privateMessageRepository.save(task);
    }

    public void deletePrivateMessage(PrivateMessage message){
    	privateMessageRepository.delete(message);
    }

    public PrivateMessage loadPrivateMessage(Long id){
        return privateMessageRepository.findOne(id);
    }

    public Collection<PrivateMessage> getAllPrivateMessagesBySender(String username){
        return privateMessageRepository.getPrivateMessagesBySender(username);
    }

    public Collection<PrivateMessage> getAllPrivateMessagesByReceiver(String username){
        return privateMessageRepository.getPrivateMessagesByReceiver(username);
    }
    

    
}