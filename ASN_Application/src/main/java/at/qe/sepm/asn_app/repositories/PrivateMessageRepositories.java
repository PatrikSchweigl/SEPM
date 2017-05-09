package at.qe.sepm.asn_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.qe.sepm.asn_app.models.nursery.PrivateMessage;


public interface PrivateMessageRepositories extends AbstractRepository<PrivateMessage, Long>{
	
    @Query("SELECT p FROM privateMessage p WHERE :username = p.userenameSender")
    List<PrivateMessage> getPrivateMessagesBySender(@Param("username") String id);

    @Query("SELECT p FROM privateMessage p WHERE :username = p.usernameReceiver")
    List<PrivateMessage> getPrivateMessagesByReceiver(@Param("username") String id);
}
