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
 * on 14.05.2017
 */
@Component
@Scope("view")
public class ParentController {
    @Autowired
    private ParentService parentService;
    @Autowired
    private UserRepository userRepository;
    private String password;
    private Parent parent;
    private Collection<Parent> parents;


    public Collection<Parent> getParents(){
        return parents;
    }

    public void setParents(Collection<Parent> parents) {
        this.parents = parents;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }


    @PostConstruct
    public void initList(){
        setParents(parentService.getAllParents());
    }

    @PostConstruct
    private void initNewParent(){
        parent = new Parent();
    }

    public void doReloadParent(){
        parent = parentService.loadParent(parent.getUsername());
    }


    public void doSaveParent(){
        parent = parentService.saveParent(parent);
        parent = null;
        initNewParent();
        initList();
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
        doReloadParent();
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



}
