package at.qe.sepm.c4f_app.services;

import at.qe.sepm.c4f_app.models.nursery.AuditLog;
import at.qe.sepm.c4f_app.models.nursery.Registration;
import at.qe.sepm.c4f_app.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public Collection<Registration> getAllRegistrationsByDate(Date date){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<Registration> reg = registrationRepository.findAll();
		Collection<Registration> ret = new LinkedList<>();
		for(Registration r : reg){
			if(r.getDate().getDay() == date.getDay() && r.getDate().getMonth() == date.getMonth() && r.getDate().getYear() == date.getYear())
				ret.add(r);
		}
        return ret;
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

    public List<Registration> getRegistrationInTimeWindowIE(Date start, Date end){
        return registrationRepository.getRegistrationInTimeWindowIE(start, end);
    }

    public List<Registration> getRegistrationInTimeWindowII(Date start, Date end){
        return registrationRepository.getRegistrationInTimeWindowII(start, end);
    }
}
