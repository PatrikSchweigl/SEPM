package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.PrivateMessage;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;


@Component
@Scope("application")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService messageService;
    @Autowired
    private UserRepository userRepository;

    private String privateMessage;

    private String username;

    @PostConstruct
    public void init() {
        privateMessage = new String();
    }

    public void savePrivateMessage() {
        PrivateMessage privateMessages = new PrivateMessage();
        privateMessages.setDate(new Date());
        privateMessages.setMessage(privateMessage);
        privateMessages.setUsernameSender(getAuthenticatedUser().getUsername());
        privateMessages.setUsernameReceiver(username);
        messageService.savePrivateMessage(privateMessages);
        privateMessage = "";
    }

    public Collection<PrivateMessage> getPrivateMessagesBySender(String usernameS, String usernameR) {

        return messageService.getAllPrivateMessagesBySender(usernameS, usernameR);
    }

    public Collection<PrivateMessage> getPrivateMessagesBySender(String username) {

        return messageService.getAllPrivateMessagesBySender(username);
    }


    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findFirstByUsername(auth.getName());
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
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
