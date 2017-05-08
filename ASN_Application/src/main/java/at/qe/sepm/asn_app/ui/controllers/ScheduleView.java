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
import at.qe.sepm.asn_app.models.nursery.MyEvent;
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
    		DefaultScheduleEvent ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate());
    		eventModel.addEvent(ev);
    		ev.setId(t.getStringId());
            System.err.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            System.err.println(t.getStringId());
            System.err.println(ev.getId());

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



    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null){
            eventModel.addEvent(event);
            Task task = new Task(event.getDescription(), event.getId(), getAuthenticatedUser(), getAuthenticatedUser(), event.getStartDate(), event.getEndDate());
            taskService.saveTask(task);
            System.err.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            System.err.println(event.getId());

        }
        else{
        	Task task = taskService.getTaskByStringId(event.getId());
        	Task temp = new Task(event.getDescription(), event.getId(), getAuthenticatedUser(), getAuthenticatedUser(), event.getStartDate(), event.getEndDate());
            eventModel.updateEvent(event);
        	event.setId(temp.getStringId());

            taskService.deleteTask(task);
            taskService.saveTask(temp);
        }
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        System.err.println("SelectEv");
        event = (ScheduleEvent) selectEvent.getObject();
        System.err.println(event.getDescription());
        System.err.println(event.getId());


        
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