package at.qe.sepm.c4f_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.qe.sepm.c4f_app.models.nursery.PrivateMessage;

public interface PrivateMessageRepository extends AbstractRepository<PrivateMessage, Long> {

	@Query("SELECT p FROM PrivateMessage p WHERE ((:usernameS = p.usernameSender and :usernameR = p.usernameReceiver) or (:usernameR = p.usernameSender and :usernameS = p.usernameReceiver))")
	List<PrivateMessage> getPrivateMessagesBySender(@Param("usernameS") String usernameS,
			@Param("usernameR") String usernameR);

	@Query("SELECT p FROM PrivateMessage p WHERE :username = p.usernameReceiver or :username = p.usernameSender")
	List<PrivateMessage> getPrivateMessagesBySender(@Param("username") String id);
}
