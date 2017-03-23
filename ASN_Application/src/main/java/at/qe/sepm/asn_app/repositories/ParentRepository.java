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
public interface ParentRepository extends UserBaseRepository<Parent>, AbstractRepository<Parent,Long> {

    @Query("select u from Parent u")
    List<Parent> getAllParents();

}
