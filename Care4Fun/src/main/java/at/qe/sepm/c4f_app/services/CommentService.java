package at.qe.sepm.c4f_app.services;
import at.qe.sepm.c4f_app.models.UserData;
import at.qe.sepm.c4f_app.models.general.Comment;
import at.qe.sepm.c4f_app.models.nursery.AuditLog;
import at.qe.sepm.c4f_app.repositories.AuditLogRepository;
import at.qe.sepm.c4f_app.repositories.CommentRepository;
import at.qe.sepm.c4f_app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Component
@Scope("application")
public class CommentService {
    /**
	 * 
	 */

	@Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    
    private String text;

    public Collection<Comment> getAllMessages(){
    	Collection<Comment> temp = commentRepository.findAll();
        Collections.reverse( (List<?>) temp );
        return temp;
    }
    
    public Collection<Comment> getAllCommentsByPicture(String pictureName){
    	Collection<Comment> temp = commentRepository.getAllCommentsByPicture(pictureName);
        Collections.reverse( (List<?>) temp );
        return temp;
    }
    
    
    public Comment saveMessage(Comment message){
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"COMMENT POSTED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogRepository.save(log);
        message.setDate(new Date());
        message.setUsername(getAuthenticatedUser().getUsername());
    	return commentRepository.save(message);
    }
    
    public void deleteMessage(Comment message){
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"COMMENT DELETED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogRepository.save(log);
        commentRepository.delete(message);
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


	public Comment loadMessage(long id) {
        return commentRepository.findOne(id);
	}

}
