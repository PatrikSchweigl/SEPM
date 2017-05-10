package at.qe.sepm.asn_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.qe.sepm.asn_app.models.nursery.PrivateMessage;


public interface PrivateMessageRepository extends AbstractRepository<PrivateMessage, Long>{
	
    @Query("SELECT p FROM PrivateMessage p WHERE :usernameS = p.usernameSender and :usernameR = p.usernameReceiver")
    List<PrivateMessage> getPrivateMessagesBySender(@Param("usernameS") String usernameS,
    												@Param("usernameR") String usernameR);

    @Query("SELECT p FROM PrivateMessage p WHERE :username = p.usernameReceiver")
    List<PrivateMessage> getPrivateMessagesByReceiver(@Param("username") String id);
}
