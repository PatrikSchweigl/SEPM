package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.nursery.Registration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 26.05.2017
 */
public interface RegistrationRepository extends AbstractRepository<Registration, Long> {

    @Query("SELECT r FROM Registration r WHERE r.child.parent1.username = :username OR r.child.parent2.username = :username")
    List<Registration> getRegistrationsByParent(@Param("username") String username);

    @Query("SELECT r FROM Registration r WHERE r.date = :date")
    List<Registration> getRegistrationsByDate(@Param("date") Date date);

    @Query("SELECT r from Registration r WHERE ((DATE(r.date) < DATE(:end)) AND (DATE(r.date) >= DATE(:start)))")
    List<Registration> getRegistrationInTimeWindowIE(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT r from Registration r WHERE ((DATE(r.date) <= DATE(:end)) AND (DATE(r.date) >= DATE(:start)))")
    List<Registration> getRegistrationInTimeWindowII(@Param("start") Date start, @Param("end") Date end);
}
