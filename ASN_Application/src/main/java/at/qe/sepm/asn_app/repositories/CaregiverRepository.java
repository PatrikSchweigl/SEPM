package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public interface CaregiverRepository extends AbstractRepository<Caregiver, Long> {

    @Query("SELECT c FROM Caregiver c WHERE c.child.id = :id")
    Collection<Caregiver> getAllCaregiversByChildId(@Param("id") Long id);

    @Query("SELECT c FROM Caregiver c WHERE c.eligible = FALSE")
    Collection<Caregiver> getCaregiversByEligibleFalse();

    @Query("SELECT c FROM Caregiver c WHERE c.eligible = TRUE")
    Collection<Caregiver> getCaregiversByEligibleTrue();

}
