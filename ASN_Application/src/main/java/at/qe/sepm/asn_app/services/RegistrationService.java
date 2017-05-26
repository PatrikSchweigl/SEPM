package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 26.05.2017
 */
@Component
@Scope("application")
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private UserService userService;

    public Collection<Registration> getAllRegistrations(){
        return registrationRepository.findAll();
    }

    public Collection<Registration> getAllRegistrationsByDate(){
        return registrationRepository.getRegistrationsByDate(new Date());   //new Date() just to achieve a successful building
    }

    public Collection<Registration> getAllRegistrationsByParent(){
        return registrationRepository.getRegistrationsByParent(userService.getAuthenticatedUser().getUsername());
    }

    public Registration saveRegistration(Registration registration){
        AuditLog auditLog = new AuditLog(userService.getAuthenticatedUser().getUsername(),
                "REGISTER: "+registration.getChild().getFirstName()+" "+registration.getChild().getLastName(),
                new Date());
        auditLogService.saveAuditLog(auditLog);
        return registrationRepository.save(registration);
    }

    public void deleteRegistration(Registration registration){
        AuditLog auditLog = new AuditLog(userService.getAuthenticatedUser().getUsername(),
                "DEREGISTER: "+registration.getChild().getFirstName()+" "+registration.getChild().getLastName(),
                new Date());
        auditLogService.saveAuditLog(auditLog);
        registrationRepository.delete(registration);
    }

    public Registration loadRegistration(Long id){
        return registrationRepository.findOne(id);
    }
}