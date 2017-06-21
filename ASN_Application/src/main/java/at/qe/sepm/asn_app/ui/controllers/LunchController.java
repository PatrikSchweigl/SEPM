package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.ui.constraints.LunchConstraints;
import at.qe.sepm.asn_app.ui.constraints.RegistrationConstraints;
import at.qe.sepm.asn_app.utils.DateUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas Aukenthaler on 22.05.2017.
 */
@Component
@Scope("view")
public class LunchController {
    @Autowired
    private LunchService lunchService;

    @Autowired
    private LunchConstraints lunchConstraints;
    @Autowired
    private RegistrationConstraints registrationConstraints;

    private Lunch lunch;
    private Lunch lunchEdit;
    private List<Lunch> lunchAll;

    private Long childId;

    private List<Lunch> thisWeekLunch;
    private boolean thisWeekFlag;

    private List<Lunch> nextWeekLunch;
    private boolean nextWeekFlag;
    private boolean[] signUp = new boolean[5];


    @PostConstruct
    public void init() {
        lunchAll = lunchService.findAll();
    }
    /*
	 * public void processSignUp(){ Lunch l; for(int i = 0; i < 5; i++){
	 * if(thisWeekLunch.size() > i) { l = thisWeekLunch.get(i); if (signUp[i]) {
	 * l.addChild(child); lunchService.saveLunch(l); } } } }
	 */

    /**
     * getCurrentWeek calculates dates for the current week (mon-fri) and tries
     * to retrieve corresponding lunch objects from the DB if no lunch is found
     * for any given date, it is filled with a default lunch object
     *
     * @return list of lunch for the current week
     */
    public List<Lunch> getCurrentWeek() {
        List<Lunch> ret = null;
        if (!thisWeekFlag) {
            LocalDate now = LocalDate.now();

            Date[] dates = DateUtils.getWeek(0);

            for (Date s : dates) {
                if (ret == null) {
                    ret = lunchService.getLunchByDate(s);
                    if (ret == null || ret.size() == 0) {
                        ret = new LinkedList<Lunch>();
                        ret.add(new Lunch(s, "Geschlossen", 0));
                    }
                } else {
                    List<Lunch> tmp = lunchService.getLunchByDate(s);
                    if (tmp == null || tmp.size() == 0) {
                        ret.add(new Lunch(s, "Geschlossen", 0));
                    } else {
                        ret.addAll(tmp);
                    }

                }
            }
            thisWeekLunch = ret;
            thisWeekFlag = true;
        }
        return thisWeekLunch;
    }

    /**
     * getNextWeek calculates dates for the next week (mon-fri) and tries to
     * retrieve corresponding lunch objects from the DB if no lunch is found for
     * any given date, it is filled with a default lunch object
     *
     * @return list of lunch for the next week
     */
    public List<Lunch> getNextWeek() {
        List<Lunch> ret = null;
        if (!nextWeekFlag) {
            LocalDate now = LocalDate.now();


            Date[] dates = DateUtils.getWeek(1);

            for (Date s : dates) {
                if (ret == null) {
                    ret = lunchService.getLunchByDate(s);
                    if (ret == null || ret.size() == 0) {
                        ret = new LinkedList<Lunch>();
                        ret.add(new Lunch(s, "Geschlossen", 0));
                    }
                } else {
                    List<Lunch> tmp = lunchService.getLunchByDate(s);
                    if (tmp == null || tmp.size() == 0) {
                        ret.add(new Lunch(s, "Geschlossen", 0));
                    } else {
                        ret.addAll(tmp);
                    }

                }
            }
            nextWeekLunch = ret;
            nextWeekFlag = true;
        }
        return nextWeekLunch;
    }

    public void signUp(Date d) {
        if (d == null) {
            return;
        }
        List<Lunch> lunchs = lunchService.getLunchByDate(d);
        if (lunchs == null || lunchs.size() < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Es ist kein Mittagessen für diesen Tag eingetragen", null));
        } else if (!lunchConstraints.checkTimeConstraints(lunchs.get(0))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Sie haben den Anmeldezeitpunkt für diesen Termin überschritten", null));
        } else {


            if (lunchs.get(0).getChildrenIds().contains(childId)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Sie haben Ihr Kind schon zum Mittagessen eingetragen", null));
            } else if (!registrationConstraints.checkIfChildIsRegisteredOnDate(d, childId)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Ihr Kind ist an diesem Tag nicht für die Kinderkrippe angemeldet", null));
            } else {
                lunchs.get(0).addChild(childId);
                lunchService.saveLunch(lunchs.get(0));
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('eventDateDialog').hide()");
                context.execute("window.location.replace(window.location.href)");
            }
        }
    }

    public List<Lunch> findAll() {
        return lunchService.findAll();
    }

    public Lunch doSaveLunch() {
        Lunch lunchReturn = null;
        if (lunchConstraints.checkIfLunchExists(lunch)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "An diesem Tag existiert bereits ein Mittagessen", null));
        } else if (!lunchConstraints.checkIfNurseryInformationExists(lunch)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "An diesem Tag hat die Kinderkrippe geschlossen", null));
        } else if (lunch.getCost() < 0.5) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte geben Sie einen adäquaten Preis an!", null));
        } else if (lunch.getMeal().compareTo("") == 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte geben Sie ein Mittagessen an!", null));
        } else if (lunch.getCost() > 10.0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte geben Sie einen adäquaten Preis an!", null));
        } else {
            lunchReturn = lunchService.saveLunch(lunch);
            lunch = null;
            initNewLunch();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('lunchAddDialog').hide()");
        }
        lunchAll = findAll();
        return lunchReturn;
    }

    public void doSaveLunchEdit() {
        lunchEdit = lunchService.saveLunch(lunchEdit);
        // mailService.sendEmail("Patrik.Schweigl@student.uibk.ac.at", "Test",
        // "Hallo, ich bin es, das System!");
        lunchEdit = null;
        initNewLunchEdit();
    }

    @PostConstruct
    private void initNewLunch() {
        lunch = new Lunch();
    }

    @PostConstruct
    private void initNewLunchEdit() {
        lunch = new Lunch();
    }


    public void doDeleteLunch() {
        this.lunchService.deleteLunch(lunchEdit);
        lunchEdit = null;
    }

    public void doReloadLunchEdit() {
        lunchEdit = lunchService.loadLunch(lunchEdit.getId());
    }

    //getters, setters -------------------------------------------------------------------------------------------
    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Lunch getLunchEdit() {
        return lunchEdit;
    }

    public void setLunchEdit(Lunch lunchEdit) {
        this.lunchEdit = lunchEdit;
        // doReloadLunch();
    }

    public void setLunchEdit2(Lunch lunchEdit) {
        this.lunchEdit = lunchEdit;
    }

    public Lunch getLunch() {
        return lunch;
    }

    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
        // doReloadLunch();
    }

    public boolean[] getSignUp() {
        return signUp;
    }

    public void setSignUp(boolean[] signUp) {
        this.signUp = signUp;
    }

    public List<Lunch> getLunchAll() {
        return lunchAll;
    }

    public void setLunchAll(List<Lunch> lunchAll) {
        this.lunchAll = lunchAll;
    }
}
