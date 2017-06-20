package at.qe.sepm.asn_app.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import at.qe.sepm.asn_app.models.referencePerson.Caregiver;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public interface CaregiverRepository extends AbstractRepository <Caregiver, Long>{

	@Query("SELECT c FROM Caregiver c WHERE c.child.id = :id")
	Collection<Caregiver> getAllCaregiversByChildId(@Param("id")Long id);
	
	@Query("SELECT c FROM Caregiver c WHERE c.eligible = FALSE")
	Collection<Caregiver> getCaregiversByEligibleFalse();
    /*
    List<Caregiver> findByUsernameContaining(String username);

    @Query("SELECT u FROM UserData u WHERE CONCAT(u.firstName, ' ', u.lastName) = :wholeName")
    List<Caregiver> findByWholeNameConcat(@Param("wholeName") String wholeName);

    @Query("SELECT u FROM UserData u WHERE :role = u.userRole")
    List<Caregiver> findByRole(@Param("role") UserRole role);

    @Query("SELECT u FROM UserData u WHERE u.userRole = 'ADMIN'")
    List<Caregiver> findAllAdmin();
    */
}
