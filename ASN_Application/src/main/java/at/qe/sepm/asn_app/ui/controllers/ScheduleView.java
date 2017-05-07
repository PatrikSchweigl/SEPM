package at.qe.sepm.asn_app.ui.controllers;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.TaskService;

/**
 * Created by Auki on 02.05.2017.
 */


@Component
@ViewScoped
public class ScheduleView implements Serializable {

    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();
    
    private boolean visible;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserRepository userRepository;

    private Task task;
    private Collection<Task> tasks;

    @PostConstruct
    public void init(){
        eventModel = new DefaultScheduleModel();

    }
    public void test(){
    	eventModel.clear();
    	tasks = taskService.getAllTasksBySender(getAuthenticatedUser().getId());
    	for( Task t : tasks ){

    		eventModel.addEvent(new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndDate()));
    	}
    }
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        if(eventModel == null) {
            System.err.println("Ahoy");
        }
        else{
            System.err.println("T");
        }
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date open(){
            Calendar t = (Calendar) today().clone(); //TODO: get opening hours

            return t.getTime();
    }

    private Date close(){
        Calendar t = (Calendar) today().clone(); //TODO: get closing hours

        return t.getTime();
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date today12Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.HOUR, 12);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }
    private Date nextDay12Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 12);

        return t.getTime();
    }
    private Date nextDay1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }
    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null){
            eventModel.addEvent(event);
        Task task = new Task(event.getDescription(),getAuthenticatedUser(), getAuthenticatedUser(), event.getStartDate(), event.getEndDate());
        taskService.saveTask(task);
        
        }
        else{
            eventModel.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        System.err.println("SelectEv");
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        System.err.println("SelectDt");
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        System.err.println("MoveEv");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        System.err.println("ResizeEv");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

    }


	
    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return userRepository.findFirstByUsername(auth.getName());
    }
	public boolean getVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}