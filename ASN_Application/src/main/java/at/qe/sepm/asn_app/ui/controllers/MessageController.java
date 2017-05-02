package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 02.05.2017
 */

@Component
@Scope("view")
public class MessageController {

    @Autowired
    private MessageService messageService;

    public Collection<Message> getMessages(){
        return messageService.getAllMessages();
    }
}
