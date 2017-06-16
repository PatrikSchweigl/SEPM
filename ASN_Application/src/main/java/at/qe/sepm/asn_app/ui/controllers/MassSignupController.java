package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.services.*;
import at.qe.sepm.asn_app.ui.constraints.RegistrationConstraints;
import at.qe.sepm.asn_app.utils.DateUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Auki on 15.06.2017.
 */
@Component
@Scope("view")
public class MassSignupController {
    @Autowired
    private ChildService childService;
    @Autowired
    private LunchService lunchService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private RegistrationConstraints registrationConstraints;
    @Autowired
    private UserService userService;
    @Autowired
    private NurseryInformationService nurseryInformationService;

    private List<Boolean[]> signUps;
    private List<Child> children;
    private List<Date[]> dates;

    private String[] strings;
    private Long childId;
    private Date date[];


    @PostConstruct
    public void initLists(){
        signUps = new LinkedList<Boolean[]>();
        children = new LinkedList<Child>();
        dates = new LinkedList<Date[]>();
    }

    @PostConstruct
    public void initBools(){
        strings = new String[10];
    }

    public void test(){
        addBools();
        System.err.println("ID IS " +childId);
        addChild(childService.loadChild(childId));
        addDate(DateUtils.getWeek(1));
        for(int j = 0; j < signUps.size(); j++) {
            for (int i = 0; i <signUps.get(j).length; i++) {
                System.err.println("aghs " + signUps.get(j)[i]);
            }
        }

        process();
    }

    public void addBools(){
        Boolean[] bools = new Boolean[10];
        for(int i = 0; i < bools.length ; i++){
            bools[i] = false;
        }
        for(int i = 0; i < strings.length; i++){
            int offset = Character.getNumericValue(strings[i].charAt(0));
            int offset2 = Character.getNumericValue(strings[i].charAt(1));
            bools[offset*2 + offset2] = true;
            System.err.println("FFFF "+ (offset*2 + offset2));

        }
        signUps.add(bools);
    }
    public void addSignUp(Boolean[] booleans){
        signUps.add(booleans);
    }

    public void addChild(Child c){
        children.add(c);
    }

    public void addDate(Date[] d){
        dates.add(d);
    }

    public void addData(Child c, Date[] d, Boolean[] b){
        addChild(c);
        addDate(d);
        addSignUp(b);
    }

    public void addAndProcess(){
        addBools();
        process();
    }

    public void process(){
        if(signUps == null || children == null || dates == null || signUps.size() != children.size() || children.size() != dates.size()){
            System.err.println("SOMETHING WENT TERRIBLY WRONG");
            return;
        }

        for(int i = 0; i < signUps.size(); i++){

            Date[] d = dates.get(i);
            Boolean[] b = signUps.get(i);
            Child c = children.get(i);

            for(int j = 0; j < 5; j++){
                Lunch l;
                if(b[j*2 + 1]) {
                    l = lunchService.getLunchByDate(d[j]).get(0);
                    l.addChild(c);
                    lunchService.saveLunch(l);
                }
                if(b[j*2]){
                    //TODO: handle registration
                    addRegistration(d[j], c);

                }

            }
        }
    }
    public void addRegistration(Date d, Child childReg) {
        NurseryInformation nurseryInformation;
        try {
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(d);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal2.setTime(d);
            cal2.add(Calendar.HOUR_OF_DAY, 2);
            Registration reg = new Registration("Auto-Signup", childReg, cal.getTime(), cal2.getTime());

            if (d.compareTo(new Date()) <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Keine Anmeldung in der Vergangenheit möglich", null));
                System.err.println("VRG");
            } else if (registrationConstraints.registationExists(reg)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Sie haben für heute ihr Kind schon angemeldet", null));
                System.err.println("ISSCHO");
            } else if (!registrationConstraints.checkIfNurseryExists(reg)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sie können kein Kind eintragen", null));
                System.err.println("GETNIT");
            } else if((nurseryInformation = nurseryInformationService.nurseryInformationByOriginDate(cal.getTime())) == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Für diesen Tag gibt es keine Information", null));
                System.err.println("KOAINF");
            }else if(nurseryInformation.getCurrentOccupancy() == nurseryInformation.getMaxOccupancy()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Kein Platz mehr frei für diesen Tag", null));
                System.err.println("PLTZ");
            } else{
                registrationService.saveRegistration(reg);
                nurseryInformation.setCurrentOccupancy(nurseryInformation.getCurrentOccupancy()+1);
                nurseryInformationService.saveNurseryInformationEdit(nurseryInformation);
                AuditLog log = new AuditLog(reg.getChild().getFirstName() + " " + reg.getChild().getLastName(),
                        "REGISTRATION CREATED: " + userService.getAuthenticatedUser().getUsername() + " ["
                                + userService.getAuthenticatedUser().getUserRole() + "] ",
                        reg.getBringdate());
                auditLogRepository.save(log);
                RequestContext context = RequestContext.getCurrentInstance();
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
            ex.printStackTrace();
        }
    }
    public boolean hasChild(){
        return childId == null;
    }

    // getters,, setters ===========================================================================================


    public Date[] getDate() {
        return date;
    }

    public void setDate(Date[] date) {
        this.date = date;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public List<Boolean[]> getSignUps() {
        return signUps;
    }

    public void setSignUps(List<Boolean[]> signUps) {
        this.signUps = signUps;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<Date[]> getDates() {
        return dates;
    }

    public void setDates(List<Date[]> dates) {
        this.dates = dates;
    }
}
