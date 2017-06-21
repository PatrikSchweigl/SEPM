package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.UserData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository for managing {@link UserData} entities.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Transactional
public interface UserRepository extends UserBaseRepository<UserData>, AbstractRepository<UserData, Long> {


    @Query("SELECT u FROM UserData u WHERE u.userRole = 'ADMIN'")
    List<UserData> findAllAdmin();

    @Query("SELECT u FROM UserData u WHERE u.notification = TRUE and u.userRole <> 'ADMIN'")
    List<UserData> findParentsByNotification();

    @Query("SELECT u FROM UserData u WHERE u.email = :email")
    UserData findFirstByEMail(@Param("email") String email);

}
