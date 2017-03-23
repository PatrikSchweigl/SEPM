package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@Transactional
public interface ParentRepository extends UserBaseRepository<Parent> {

    @Query("Select p.username, p.firstName, p.lastName, p.status From Parent p, User u where u.id = p.id")
    List<Parent> getAllParents();

}
