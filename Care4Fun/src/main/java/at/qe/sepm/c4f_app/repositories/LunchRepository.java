package at.qe.sepm.c4f_app.repositories;

import at.qe.sepm.c4f_app.models.nursery.Lunch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public interface LunchRepository extends AbstractRepository<Lunch, Long>{
    @Query("SELECT u from Lunch u WHERE DATE(u.date) = DATE(:date)")
    List<Lunch> getLunchByDate(@Param("date") Date date);

    /** getLunchInTimeWindowII
     *  returns all Lunches in the time window specified by the two parameters
     *  II stands for Inclusive Inclusive, meaning that all dates INCLUDING start and end will be searched
     * @param start date
     * @param end date
     * @return List of lunch in the time window
     */
    @Query("SELECT l from Lunch l WHERE ((DATE(l.date) <= DATE(:end)) AND (DATE(l.date) >= DATE(:start)))")
    List<Lunch> getLunchInTimeWindowII(@Param("start") Date start, @Param("end") Date end);
    /** getLunchInTimeWindowIE
     *  returns all Lunches in the time window specified by the two parameters
     *  IE stands for Inclusive Exclusive, meaning that all dates INCLUDING start and EXCLUDING end will be searched
     * @param start date
     * @param end date
     * @return List of lunch in the time window
     */
    @Query("SELECT l from Lunch l WHERE ((DATE(l.date) < DATE(:end)) AND (DATE(l.date) >= DATE(:start)))")
    List<Lunch> getLunchInTimeWindowIE(@Param("start") Date start, @Param("end") Date end);
}