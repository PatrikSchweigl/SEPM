package at.qe.sepm.c4f_app.services;

import at.qe.sepm.c4f_app.models.nursery.AuditLog;
import at.qe.sepm.c4f_app.models.referencePerson.Caregiver;
import at.qe.sepm.c4f_app.repositories.AuditLogRepository;
import at.qe.sepm.c4f_app.repositories.CaregiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@Component
@Scope("application")
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;


    public Caregiver saveCaregiver(Caregiver caregiver) {
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            AuditLog log = new AuditLog(getAuthenticatedUserName(), "SAVED CAREGIVER: " + caregiver.getFullName(), new Date());
            auditLogRepository.save(log);
        }
        return caregiverRepository.save(caregiver);
    }
    
    public Collection<Caregiver> getAllCaregiversByEligibleFalse(){
    	return caregiverRepository.getCaregiversByEligibleFalse();
    }

    public void deleteCaregiver(Caregiver caregiver){
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            AuditLog log = new AuditLog(getAuthenticatedUserName(), "DELETED CAREGIVER: " + caregiver.getFullName(), new Date());
            auditLogRepository.save(log);
        }
        caregiverRepository.delete(caregiver);
    }
    public Caregiver loadCaregiver(Long id) {
        return caregiverRepository.findOne(id);
    }

    public Collection<Caregiver> getAllCaregivers() {
        return caregiverRepository.findAll();
    }

    private String getAuthenticatedUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

	public Collection<Caregiver> getAllCaregiversByChildId(Long id) {
		return caregiverRepository.getAllCaregiversByChildId(id);
	}
}