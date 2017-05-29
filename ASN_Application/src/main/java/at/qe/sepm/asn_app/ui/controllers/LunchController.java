package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
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
    private Lunch monday;
    private Lunch tuesday;
    private Lunch wednesday;
    private Lunch thursday;
    private Lunch friday;

    public List<Lunch> getCurrentWeek(){
        String[] dates = DateUtils.getWeekToString(0);
        List<Lunch> ret = null;
        for(String s : dates){
            if(ret == null){
                ret = lunchService.getLunchByDate(s);
            }
            else {
                ret.addAll(lunchService.getLunchByDate(s));
            }
        }
        return ret;
    }

    public List<Lunch> getNextWeek(){
        String[] dates = DateUtils.getWeekToString(1);
        List<Lunch> ret = null;
        for(String s : dates){
            if(ret == null){
                ret = lunchService.getLunchByDate(s);
            }
            else {
                ret.addAll(lunchService.getLunchByDate(s));
            }
        }
        return ret;
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

    @PostConstruct
    private void initNewLunch(){
        lunch = new Lunch();
    }

    //getters, setters -------------------------------------------------------------------------------------------
    public Lunch getLunch() {
        return lunch;
    }

    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
    }

    public Lunch getMonday() {
        return monday;
    }

    public void setMonday(Lunch monday) {
        this.monday = monday;
    }

    public Lunch getTuesday() {
        return tuesday;
    }

    public void setTuesday(Lunch tuesday) {
        this.tuesday = tuesday;
    }

    public Lunch getWednesday() {
        return wednesday;
    }

    public void setWednesday(Lunch wednesday) {
        this.wednesday = wednesday;
    }

    public Lunch getThursday() {
        return thursday;
    }

    public void setThursday(Lunch thursday) {
        this.thursday = thursday;
    }

    public Lunch getFriday() {
        return friday;
    }

    public void setFriday(Lunch friday) {
        this.friday = friday;
    }
}
