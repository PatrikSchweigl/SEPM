package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.nursery.PrivateMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrivateMessageRepository extends AbstractRepository<PrivateMessage, Long> {

    @Query("SELECT p FROM PrivateMessage p WHERE ((:usernameS = p.usernameSender and :usernameR = p.usernameReceiver) or (:usernameR = p.usernameSender and :usernameS = p.usernameReceiver))")
    List<PrivateMessage> getPrivateMessagesBySender(@Param("usernameS") String usernameS,
                                                    @Param("usernameR") String usernameR);

    @Query("SELECT p FROM PrivateMessage p WHERE :username = p.usernameReceiver or :username = p.usernameSender")
    List<PrivateMessage> getPrivateMessagesBySender(@Param("username") String id);
}
