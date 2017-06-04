package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.MailService;
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
//@Scope("view")
@Scope("application")
public class ParentController {
    @Autowired
    private ParentService parentService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ChildService childService;
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

    public Collection<Child> getChildren(String username){
        return childService.getChildrenByParentUsername(username);
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
        mailService.sendEmail(parent.getEmail(), "ASN-App Registrierung",
                "Willkommen bei ASN-Application!\n\n" +
                        "Die Plattform der Kinderkrippe erreichen Sie via localhost:8080.\n\n" +
                        "Ihr Benutzername: "+ parent.getUsername() + "\n" +
                        "Ihr Passwort: passwd" +
                        "\n\nBitte ändern Sie nach dem ersten Login Ihr Password.\n" +
                        "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n" +
                        "Viel Spaß wünschen das Kinderkrippen Team!");
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
    public void setParent2(Parent parent) {
        this.parent = parent;
    }

    public void doChangePassword(String password){
       parentService.changePassword(password);
    }




}
