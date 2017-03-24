package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.UserRole;
import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Repository for managing {@link User} entities.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Transactional
public interface UserRepository extends UserBaseRepository<User>, AbstractRepository<User,Long> {


    List<User> findByUsernameContaining(String username);

    @Query("SELECT u FROM User u WHERE CONCAT(u.firstName, ' ', u.lastName) = :wholeName")
    List<User> findByWholeNameConcat(@Param("wholeName") String wholeName);

    @Query("SELECT u FROM User u WHERE :role = u.userRole")
    List<User> findByRole(@Param("role") UserRole role);

}
