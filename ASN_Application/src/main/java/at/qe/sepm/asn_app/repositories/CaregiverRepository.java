package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.referencePerson.Caregiver;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public interface CaregiverRepository extends AbstractRepository <Caregiver, Long>{
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
