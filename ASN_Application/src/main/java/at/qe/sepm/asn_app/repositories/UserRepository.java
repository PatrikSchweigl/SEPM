package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Repository for managing {@link UserData} entities.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Transactional
public interface UserRepository extends UserBaseRepository<UserData>, AbstractRepository<UserData,Long> {


    List<UserData> findByUsernameContaining(String username);

    @Query("SELECT u FROM UserData u WHERE CONCAT(u.firstName, ' ', u.lastName) = :wholeName")
    List<UserData> findByWholeNameConcat(@Param("wholeName") String wholeName);

    @Query("SELECT u FROM UserData u WHERE :role = u.userRole")
    List<UserData> findByRole(@Param("role") UserRole role);

    @Query("SELECT u FROM UserData u WHERE u.userRole = 'ADMIN'")
    List<UserData> findAllAdmin();

    @Query("SELECT u FROM UserData u WHERE u.notification = TRUE")
    List<UserData> findParentsByNotification();

}
