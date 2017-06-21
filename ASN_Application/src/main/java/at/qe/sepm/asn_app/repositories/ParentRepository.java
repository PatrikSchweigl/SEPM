package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 14.05.2017
 */
public interface ParentRepository extends UserBaseRepository<Parent>, AbstractRepository<Parent, Long> {


}
