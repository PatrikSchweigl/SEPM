package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.nursery.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 06.05.2017
 */
public interface TaskRepository  extends AbstractRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE :senderID = t.sender.id")
    List<Task> getTasksBySender(@Param("senderID") Long id);

    @Query("SELECT t FROM Task t WHERE :receiverID = t.receiver.id")
    List<Task> getTasksByReceiver(@Param("receiverID") Long id);
}
