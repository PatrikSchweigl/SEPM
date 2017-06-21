package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.nursery.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 06.05.2017
 */
public interface TaskRepository extends AbstractRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE :receiverID = t.receiver.username")
    List<Task> getTasksByReceiver(@Param("receiverID") String id);

    @Query("SELECT t FROM Task t WHERE :stringID = t.stringId")
    Task getTaskByStringId(@Param("stringID") String id);

    @Transactional
    @Modifying
    @Query("Delete FROM Task t WHERE :stringID = t.stringId")
    void DeleteTaskByStringId(@Param("stringID") String id);

    @Query("SELECT t FROM Task t WHERE :receiverID = t.receiver.username or :receiverID = t.sender.username or t.important = true")
    List<Task> getTasksByReceiverAndImportance(@Param("receiverID") String id);

    @Query("SELECT t FROM Task t WHERE t.important = :importance")
    List<Task> getTasksByImportance(@Param("importance") Boolean imp);
}
