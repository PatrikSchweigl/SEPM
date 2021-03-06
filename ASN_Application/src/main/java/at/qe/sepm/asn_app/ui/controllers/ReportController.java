package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.LunchReport;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.utils.DateUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.PostConstruct;

/**
 * Created by Auki on 13.06.2017.
 */

@Component
@Scope("view")
public class ReportController {
    @Autowired
    private LunchService lunchService;
    @Autowired
    private ChildService childService;
    
    private Collection<LunchReport> monthlyReport;
    private Collection<LunchReport> weeklyReport;
    private Collection<LunchReport> annualReport;
    private Collection<LunchReport> dateReport;


    
    @PostConstruct
    public void initList(){
        monthlyReport = getLunchReportThisMonth();
        weeklyReport = getLunchReportForWeek(0);
        annualReport = getLunchReportForYear(0);
    }


    public List<LunchReport> getLunchReportLastMonth(){
        return getLunchReportForMonth(-1);
    }
    public List<LunchReport> getLunchReportThisMonth(){

        return getLunchReportForMonth(0);
    }
    /** getLunchReportForWeek
     *  returns LR for week relative to current
     * @param i int specifying which week to get (0 = current, -1 = previous, etc)
     * @return LunchReport for specified week
     */
    public List<LunchReport> getLunchReportForWeek(int i){
        Date[] dates = DateUtils.getWeek(i);
        if(dates.length < 1){
            return null;
        }
        Date start = dates[0];
        Date end = dates[dates.length-1];
        return getLunchReportInTimeWindowII(start, end);

    }

    /** getLunchReportForYear
     *  returns LR for year relative to current
     * @param i int specifying which year to get (0 = current, -1 = previous, etc)
     * @return LunchReport for specified year
     */
    public List<LunchReport> getLunchReportForYear(int i){
        Date start = new Date();
        start.setDate(1);
        start.setMonth(0);
        start.setYear(start.getYear() + i);
        Date end = new Date();
        end.setDate(1);
        end.setMonth(0);
        end.setYear(end.getYear() + i + 1);
        return getLunchReportInTimeWindowIE(start, end);
    }

    public void printLunchReportForMonthToJSON(){
        try (Writer writer = new FileWriter("Output.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            gson.toJson(monthlyReport,writer);

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /** getLunchReportForMonth
     *  returns LR for month relative to current
     * @param i int specifying which month to get (0 = current, -1 = previous, etc)
     * @return LunchReport for specified month
     */
    public List<LunchReport> getLunchReportForMonth(int i){
        Date start = new Date();
        start.setDate(1);
        start.setMonth((start.getMonth() + i)%12);
        Date end = new Date();
        end.setDate(1);
        end.setMonth((end.getMonth() + i + 1)%12);
        if(end.getMonth() == 0){
            end.setYear(end.getYear() + 1);
        }
        return getLunchReportInTimeWindowIE(start, end);
    }

    /** getLunchReportInTimeWindowIE
     *  IE stands for including excluding
     * @param start including
     * @param end excluding
     * @return list of lunchs
     */
    public List<LunchReport> getLunchReportInTimeWindowIE(Date start, Date end){
        List<LunchReport> lunchReports = new LinkedList<>();
        List<Lunch> lunchs = lunchService.getLunchInTimeWindowIE(start, end);
        for(Lunch l : lunchs){
            Collection<Child> childs = childService.getChildrenByLunch(l);
            for(Child c : childs){
                lunchReports.add(new LunchReport(l, c));
            }
        }
        return lunchReports;
    }

    /** getLunchReportInTimeWindowII
     *  II stands for including including
     * @param start including
     * @param end including
     * @return list of lunchs
     */
    public List<LunchReport> getLunchReportInTimeWindowII(Date start, Date end){
        List<LunchReport> lunchReports = new LinkedList<>();
        List<Lunch> lunchs = lunchService.getLunchInTimeWindowII(start, end);
        for(Lunch l : lunchs){
            Collection<Child> childs = childService.getChildrenByLunch(l);
            for(Child c : childs){
                lunchReports.add(new LunchReport(l, c));
            }
        }
        return lunchReports;
    }
	public Collection<LunchReport> getMonthlyReport() {
		return monthlyReport;
	}
	public void setMonthlyReport(Collection<LunchReport> monthlyReport) {
		this.monthlyReport = monthlyReport;
	}

    public Collection<LunchReport> getWeeklyReport() {
        return weeklyReport;
    }

    public void setWeeklyReport(Collection<LunchReport> weeklyReport) {
        this.weeklyReport = weeklyReport;
    }

    public Collection<LunchReport> getAnnualReport() {
        return annualReport;
    }

    public void setAnnualReport(Collection<LunchReport> annualReport) {
        this.annualReport = annualReport;
    }

    public Collection<LunchReport> getDateReport() {
        return dateReport;
    }

    public void setDateReport(Collection<LunchReport> dateReport) {
        this.dateReport = dateReport;
    }

    public double getLunchCostByChildWeekly(Child child){
        double sum = 0.0;
        for(LunchReport lr : weeklyReport){
            if(lr.getChild().getId().equals(child.getId())){
                sum += lr.getLunch().getCost();
            }
        }
        return sum;
    }
    public double getLunchCostByChildMonthly(Child child){
        double sum = 0.0;
        for(LunchReport lr : monthlyReport){
            if(lr.getChild().getId().equals(child.getId())){
                sum += lr.getLunch().getCost();
            }
        }
        return sum;
    }
    public double getLunchCostByChildAnnually(Child child){
        double sum = 0.0;
        for(LunchReport lr : annualReport){
            if(lr.getChild().getId().equals(child.getId())){
                sum += lr.getLunch().getCost();
            }
        }
        return sum;
    }

    public List<LunchReport> getLunchReportByDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd k:mm:ss z yyyy");
        LinkedList<LunchReport> lunchReports = new LinkedList<>();
        Date d = new Date();
        try {
            d = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        List<Lunch> tmp = lunchService.getLunchByDate(d);
        if(tmp == null || tmp.size() < 1){
            return null;
        }
        Lunch lunch = tmp.get(0);
        Collection<Child> childs = childService.getChildrenByLunch(lunch);
        for(Child c : childs){
            lunchReports.add(new LunchReport(lunch, c));
        }
        return lunchReports;
    }
}
