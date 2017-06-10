package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;


import javax.annotation.PostConstruct;
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


    private Lunch lunch;
    private Lunch lunchEdit;

    private Child child;
    private Date date;

    private List<Lunch> thisWeekLunch;
    private boolean thisWeekFlag;

    private List<Lunch> nextWeekLunch;
    private boolean nextWeekFlag;
    private boolean[] signUp = new boolean[5];

    /*
    public void processSignUp(){
        Lunch l;
        for(int i = 0; i < 5; i++){
            if(thisWeekLunch.size() > i) {
                l = thisWeekLunch.get(i);
                if (signUp[i]) {
                    l.addChild(child);
                    lunchService.saveLunch(l);
                }
            }
        }
    }
    */

    /** getCurrentWeek
     *  calculates dates for the current week (mon-fri) and tries to retrieve corresponding lunch objects from the DB
     *  if no lunch is found for any given date, it is filled with a default lunch object
     * @return list of lunch for the current week
     */
    public List<Lunch> getCurrentWeek(){
        List<Lunch> ret = null;
        if(!thisWeekFlag) {
            Date[] dates = DateUtils.getWeek(0);

            for (Date s : dates) {
                if (ret == null) {
                    ret = lunchService.getLunchByDate(s);
                    if(ret == null || ret.size() == 0){
                        ret = new LinkedList<Lunch>();
                        ret.add(new Lunch(s, "Geschlossen", 0));
                    }
                } else {
                    List<Lunch> tmp = lunchService.getLunchByDate(s);
                    if(tmp == null || tmp.size() == 0){
                        ret.add(new Lunch(s, "Geschlossen", 0));
                    }
                    else {
                        ret.addAll(tmp);
                    }

                }
            }
            thisWeekLunch = ret;
            thisWeekFlag = true;
        }
        return thisWeekLunch;
    }
    /** getNextWeek
     *  calculates dates for the next week (mon-fri) and tries to retrieve corresponding lunch objects from the DB
     *  if no lunch is found for any given date, it is filled with a default lunch object
     * @return list of lunch for the next week
     */
    public List<Lunch> getNextWeek(){
        List<Lunch> ret = null;
        if(!nextWeekFlag) {
            Date[] dates = DateUtils.getWeek(1);

            for (Date s : dates) {
                if (ret == null) {
                    ret = lunchService.getLunchByDate(s);
                    if(ret == null || ret.size() == 0){
                        ret = new LinkedList<Lunch>();
                        ret.add(new Lunch(s, "Geschlossen", 0));
                    }
                }  else {
                    List<Lunch> tmp = lunchService.getLunchByDate(s);
                    if(tmp == null || tmp.size() == 0){
                        ret.add(new Lunch(s, "Geschlossen", 0));
                    }
                    else {
                        ret.addAll(tmp);
                    }

                }
            }
            nextWeekLunch = ret;
            nextWeekFlag = true;
        }
        return nextWeekLunch;
    }
    public void signUp(Date d){
        if(d == null){
            System.err.println("WAT");
            return;
        }
        System.err.println("OY WAT IS DES");
        List<Lunch> lunchs = lunchService.getLunchByDate(d);
        if(lunchs == null || child == null){
            System.err.println("LUNCH" + lunchs + "CHILD" + child);
            return;
        }
        lunchs.get(0).addChild(child);
        System.err.println(child);
        lunchService.saveLunch(lunchs.get(0));
        System.err.println(lunchs);
    }
    public List<Lunch> findAll(){
        return lunchService.findAll();
    }
    public void doSaveLunch(){
        lunch = lunchService.saveLunch(lunch);
        //mailService.sendEmail("Patrik.Schweigl@student.uibk.ac.at", "Test", "Hallo,  ich bin es, das System!");
        lunch = null;
        initNewLunch();
    }
    public void doSaveLunchEdit(){
        lunchEdit = lunchService.saveLunch(lunchEdit);
        //mailService.sendEmail("Patrik.Schweigl@student.uibk.ac.at", "Test", "Hallo,  ich bin es, das System!");
        lunchEdit = null;
        initNewLunchEdit();
    }


    @PostConstruct
    private void initNewLunch(){
        lunch = new Lunch();
    }
    @PostConstruct
    private void initNewLunchEdit(){
        lunch = new Lunch();
    }
    @PostConstruct
    private void initNewChild() {child = new Child();}

    public void doDeleteLunch() {
        this.lunchService.deleteLunch(lunchEdit);
        lunchEdit = null;
    }
    public void doReloadLunch(){
        lunch = lunchService.loadLunch(lunch.getId());
    }
    public void doReloadLunchEdit(){
        lunchEdit = lunchService.loadLunch(lunchEdit.getId());
    }

    //getters, setters -------------------------------------------------------------------------------------------

    public Lunch getLunchEdit() {
        return lunchEdit;
    }

    public void setLunchEdit(Lunch lunchEdit) {
        this.lunchEdit = lunchEdit;
    }

    public Lunch getLunch() {
        return lunch;
    }

    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }


    public boolean[] getSignUp() {
        return signUp;
    }

    public void setSignUp(boolean[] signUp) {
        this.signUp = signUp;
    }

    public List<Lunch> getThisWeekLunch() {
        return thisWeekLunch;
    }

    public void setThisWeekLunch(List<Lunch> thisWeekLunch) {
        this.thisWeekLunch = thisWeekLunch;
    }
}
