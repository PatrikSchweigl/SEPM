package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.ownExceptions.SiblingConstraintException;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.ChildRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
//import java.util.Collection;
//import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@Component
@Scope("application")
public class ChildService {
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private UserRepository userRepository;
    private Long id;
    private Child child;


    public void setChild(Child child) {
        this.child = child;
    }

    public Child getChild() {
        return this.child;
    }


    public Collection<Child> getAllChildren(){
        return childRepository.findAll();
    }


    public Child saveChild(Child child) {
        return childRepository.save(child);
    }
    
    public String addCaregiver(Caregiver c){
    	System.err.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

    	System.err.println(id);
    	Child child = childRepository.findOne(this.id);
    	child.addCaregiver(c);
    	childRepository.save(child);
    	return child.getFirstName() + " " + child.getLastName();
    }


    public Child loadChild(Long id) {
        return childRepository.findOne(id);
    }

    public void deleteChild(Child child) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: " + child.getFirstName() + " " + child.getLastName(), new Date());
        auditLogRepository.save(log);
        childRepository.delete(child);
    }

    public Collection<Child> getChildrenByParentUsername(String usrn){ return childRepository.getChildrenByParentUsername(usrn);}

    private UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

	public Child getChildrenByFirstnameAndParentUsername(String username, String childFirstname) {
		return childRepository.getChildrenByFirstnameAndParentUsername(username, childFirstname);
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

}
