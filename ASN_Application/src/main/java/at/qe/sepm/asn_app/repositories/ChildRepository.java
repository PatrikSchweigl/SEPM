package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Parent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Emanuel Striednig <emanuel.striednig@student.uibk.ac.at>
 * on 20.03.2017.
 */
public interface ChildRepository extends AbstractRepository<Child, Long> {
    /*
    @Query("SELECT u from Child u WHERE (u.parent1.username = :parent.username OR u.parent2.username = :parent.username)")
    List<Child> getChildrenByParent(@Param("parent") Parent parent);
    */
    @Query("SELECT u from Child u WHERE (u.parent1.username = :username OR u.parent2.username = :username)")
    List<Child> getChildrenByParentUsername(@Param("username") String usrn);

    @Query("SELECT u from Child u WHERE (u.firstName = :childFirstname AND (u.parent1.username = :username OR u.parent2.username = :username))")
	Child getChildrenByFirstnameAndParentUsername(@Param("username") String username,@Param("childFirstname") String childFirstname);

    @Query("SELECT u from Child u WHERE u.id = :id")
	Child getChildById(@Param("id") long id);
    /*
    List<Child> getChildrenByParent(Parent p){return null;}
    */

}
