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
    
    
    @PostConstruct
    public void initList(){
        monthlyReport = getLunchReportThisMonth();
    }


    public List<LunchReport> getLunchReportThisMonth(){

        Date start = new Date();
        start.setDate(1);
        Date end = new Date();
        end.setDate(DateUtils.getLastDay(end.getMonth(), end.getYear()));
        return getLunchReportInTimeWindow(start, end);
    }
    public List<LunchReport> getLunchReportInTimeWindow(Date start, Date end){
        List<LunchReport> lunchReports = new LinkedList<>();
        List<Lunch> lunchs = lunchService.getLunchInTimeWindow(start, end);
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
