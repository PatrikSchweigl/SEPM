package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.nursery.PrivateMessage;
import at.qe.sepm.asn_app.repositories.PrivateMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Collection;


@Component
@ViewScoped
public class PrivateMessageService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    public PrivateMessage savePrivateMessage(PrivateMessage privateMessage) {
        return privateMessageRepository.save(privateMessage);
    }

    public void deletePrivateMessage(PrivateMessage message) {
        privateMessageRepository.delete(message);
    }

    public PrivateMessage loadPrivateMessage(Long id) {
        return privateMessageRepository.findOne(id);
    }

    public Collection<PrivateMessage> getAllPrivateMessagesBySender(String usernameS, String usernameR) {
        return privateMessageRepository.getPrivateMessagesBySender(usernameS, usernameR);
    }

    public Collection<PrivateMessage> getAllPrivateMessagesBySender(String username) {
        return privateMessageRepository.getPrivateMessagesBySender(username);
    }


}