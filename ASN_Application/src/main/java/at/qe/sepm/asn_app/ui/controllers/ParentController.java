package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.ParentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 24.03.2017
 */
@Component
@Scope("view")
public class ParentController {
    @Autowired
    private ParentService parentService;

    private Collection<Parent> parents;
    private Parent parent;
    private Parent parentEdit;
    private String password;
    @Autowired
	private UserRepository userRepository;

    public Collection<Parent> getParents() {

        return parents;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
        doReloadParent();
    }
    public Parent getParent() {
        return parent;
    }
    public void setParents(Collection<Parent> parents) {
        this.parents = parents;
    }
    public void doReloadParent() {
        parent = parentService.loadParent(parent.getId());
    }

    @PostConstruct
    public void initNewParent() {
        parent = new Parent();
    }

    @PostConstruct
    public void initList(){
        setParents(parentService.getAllParents());
    }

    public void doSaveParent() {
        System.out.println("doSaveParentEdit ------------------" +parent.getFirstName() +"---------------------------------------");
        parent = parentService.saveParent(parent);
        parent = null;
        initNewParent();
        initList();
    }


    public Parent getParentEdit() {
        return parentEdit;
    }

    public void setParentEdit(Parent parent) {
        this.parentEdit = parent;
        doReloadParentEdit();
    }

    public void doReloadParentEdit() {
        parentEdit = parentService.loadParent(parentEdit.getId());
    }

    public void doSaveParentEdit(){
        System.out.println("doSaveParentEdit ------------------" +parentEdit.getFirstName() +"---------------------------------------");
        parentEdit = parentService.saveParent(parentEdit);
        initList();
    }

    public void doDeleteParentEdit() {
        this.parentService.deleteParent(parentEdit);
        parentEdit = null;
        initList();
    }
    
    public void changePassword(String password){
    	UserData user = getAuthenticatedUser();
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

	public UserData getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return userRepository.findFirstByUsername(auth.getName());
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}



}
