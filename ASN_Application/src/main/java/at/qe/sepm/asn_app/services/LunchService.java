package at.qe.sepm.asn_app.services;
import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.LunchRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */

@Component
//@Scope("view")
@Scope("application")
public class LunchService {
    @Autowired private LunchRepository lunchRepository;
    @Autowired private AuditLogRepository auditLogRepository;
    @Autowired private UserRepository userRepository;

    /*
    public List<Lunch> getLunchByDate(String date){
        return lunchRepository.getLunchByDate(date);
    } */
    public List<Lunch> getLunchByDate(Date date){
        return lunchRepository.getLunchByDate(date);
    }

    public List<Lunch> findAll(){
        return lunchRepository.findAll();
    }

    public Lunch saveLunch(Lunch lunch) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "CHANGED/CREATED Lunch: " + lunch.getMeal() + " " + lunch.getDate(), new Date());
        auditLogRepository.save(log);
        lunch = lunchRepository.save(lunch);
        return lunch;
    }

    public Lunch loadLunch(Long id){
        return lunchRepository.findOne(id);
    }

    public List<Lunch> getLunchInTimeWindowIE(Date start, Date end){
        return lunchRepository.getLunchInTimeWindowIE(start, end);
    }
    public List<Lunch> getLunchInTimeWindowII(Date start, Date end){
        return lunchRepository.getLunchInTimeWindowII(start, end);
    }

    public void deleteLunch(Lunch lunch) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED Lunch: " + lunch.getMeal() + " " + lunch.getDate(), new Date());
        auditLogRepository.save(log);
        lunchRepository.delete(lunch);
    }
    private UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

}
