package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.services.RegistrationService;
import at.qe.sepm.asn_app.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.spi.RegisterableService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 26.05.2017
 */
@Component
@Scope("view")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    private Registration registration;
    private Registration registrationEdit;
    private Collection<Registration> registrations;

    public Collection<Registration> getRegistrations(){
        return registrations;
    }
    
    public Collection<Registration> getAllRegistrationsYear(){
    	return getRegistrationReportForYear(0);
    }
    
    public Collection<Registration> getAllRegistrationsMonth(){
    	return getRegistrationReportForMonth(0);
    }
    
    public Collection<Registration> getAllRegistrationsWeek(){
    	return getRegistrationReportForWeek(0);
    }

    public Collection<Registration> getRegistrationsByParent(){
        return registrationService.getAllRegistrationsByParent();
    }

    public Collection<Registration> getRegistrationsByDate(String date){
    	SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd k:mm:ss z yyyy"); 
    	Date d = new Date();
		try {
			System.err.println("HEEEEERE I AM");
			System.err.println(date);

			d = formatter.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			System.err.println(d);
			d = cal.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return registrationService.getAllRegistrationsByDate(d);
    }
    
    
    public Collection<Registration> getRegistrationsByDateToday(){
        return registrationService.getAllRegistrationsByDate(new Date());
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
        doReloadRegistration();
    }

    public void setRegistrations(Collection<Registration> registrations) {
        this.registrations = registrations;
    }

    public void doReloadRegistration(){
        registrationService.loadRegistration(registration.getId());
    }

    public void doSaveRegistration(){
        registration = registrationService.saveRegistration(registration);
        registration = null;
        initNewRegistration();
        initList();
    }

    @PostConstruct
    public void initNewRegistration(){
        registration = new Registration();
    }

    @PostConstruct
    public void initList(){
        setRegistrations(registrationService.getAllRegistrations());
    }

    public Registration getRegistrationEdit() {
        return registrationEdit;
    }

    public void setRegistrationEdit(Registration registrationEdit) {
        this.registrationEdit = registrationEdit;
        doReloadRegistrationEdit();
    }

    public void doSaveRegistrationEdit(){
        registration = registrationService.saveRegistration(registration);
        initList();
    }

    public void doDeleteRegistration(){
        registrationService.deleteRegistration(registrationEdit);
        initList();
    }

    public void doReloadRegistrationEdit(){
        registrationService.loadRegistration(registrationEdit.getId());
    }





    public List<Registration> getRegistrationReportForMonth(int i){
        Date start = new Date();
        start.setDate(1);
        start.setMonth((start.getMonth() + i)%12);
        Date end = new Date();
        end.setDate(1);
        end.setMonth((end.getMonth() + i + 1)%12);
        if(end.getMonth() == 0){
            end.setYear(end.getYear() + 1);
        }
        return registrationService.getRegistrationInTimeWindowIE(start, end);
    }

    public List<Registration> getRegistrationReportForYear(int i){
        Date start = new Date();
        start.setDate(1);
        start.setMonth(0);
        start.setYear(start.getYear() + i);
        Date end = new Date();
        end.setDate(1);
        end.setMonth(0);
        end.setYear(end.getYear() + i + 1);
        return registrationService.getRegistrationInTimeWindowIE(start, end);
    }

    public List<Registration> getRegistrationReportForWeek(int i){
        Date[] dates = DateUtils.getWeek(i);
        if(dates.length < 1){
            return null;
        }
        Date start = dates[0];
        Date end = dates[dates.length-1];
        return registrationService.getRegistrationInTimeWindowII(start, end);

    }
}
