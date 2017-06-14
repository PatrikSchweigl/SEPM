package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.LunchReport;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
     *  IE stands for including including
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

	public double getLunchCostByChild(Child child){
        double sum = 0.0;
        for(LunchReport lr : monthlyReport){
            if(lr.getChild().getId() == child.getId()){
                sum += lr.getLunch().getCost();
            }
        }
        return sum;
    }
}
