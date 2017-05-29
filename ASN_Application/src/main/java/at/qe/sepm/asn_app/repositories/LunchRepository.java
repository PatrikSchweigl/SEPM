package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.nursery.Lunch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public interface LunchRepository extends AbstractRepository<Lunch, Long>{
    @Query("SELECT u from Lunch u WHERE u.date = :date")
    List<Lunch> getLunchByDate(@Param("date") String date);
}
