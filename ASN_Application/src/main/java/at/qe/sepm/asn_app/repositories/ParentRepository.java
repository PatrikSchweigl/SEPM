package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public interface ParentRepository {

    @Query("SELECT username, firstName, lastName, status From Parent, User WHERE User.id = Parent.id")
    List<Parent> getAllParents();




}
