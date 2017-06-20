package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.CaregiverRepository;
import at.qe.sepm.asn_app.repositories.ChildRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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
    private ChildRepository childRepository;

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

    public Collection<Caregiver> getAllCaregiversByEligibleTrue(){
        return caregiverRepository.getCaregiversByEligibleTrue();
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

    public Collection<Caregiver> getCaregiversForParent(String usrn){
        Collection<Caregiver> ret = new HashSet<Caregiver>();
        Collection<Child> children = childRepository.getChildrenByParentUsername(usrn);
        for(Child c : children){
            ret.addAll(caregiverRepository.getAllCaregiversByChildId(c.getId()));
        }
        return ret;
    }

	public Collection<Caregiver> getAllCaregiversByChildId(Long id) {
		return caregiverRepository.getAllCaregiversByChildId(id);
	}
}
