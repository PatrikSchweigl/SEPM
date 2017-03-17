package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for managing {@link User} entities.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
public interface UserRepository extends AbstractRepository<User, Long> {

    User findFirstByUsername(String username);

    List<User> findByUsernameContaining(String username);

    @Query("SELECT u FROM User u WHERE CONCAT(u.firstName, ' ', u.lastName) = :wholeName")
    List<User> findByWholeNameConcat(@Param("wholeName") String wholeName);

    @Query("SELECT u FROM User u WHERE :role MEMBER OF u.roles")
    List<User> findByRole(@Param("role") UserRole role);

}
