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
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.MyEvent;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.TaskService;
import at.qe.sepm.asn_app.services.UserService;

/**
 * Created by Auki on 02.05.2017.
 */

@Component
@ViewScoped
public class ScheduleView implements Serializable {

	private ScheduleModel eventModel;

	private ScheduleEvent event = new DefaultScheduleEvent();
	@Autowired
	private UserService userService;
	@Autowired
	private AuditLogRepository auditLogRepository;

	private boolean visible;
	private boolean important;

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserRepository userRepository;

	private Task task;
	private String reciever;
	private Collection<Task> tasks;

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();

	}

	public void test() {
		eventModel.clear();
		tasks = taskService.getAllTasksByReceiverAndImportance(getAuthenticatedUser().getId());
		for (Task t : tasks) {
			System.err.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			System.err.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			System.err.println(t.getImportant());
			System.err.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			System.err.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			System.err.println(t.getStyleClass());
			DefaultScheduleEvent ev;
			if (t.getImportant())
				ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate(), "important");
			else if (getAuthenticatedUser().getUserRole() == UserRole.EMPLOYEE
					&& t.getReceiver().getUserRole() == UserRole.PARENT
					&& (getAuthenticatedUser().getUsername().compareTo(t.getSender().getUsername())) == 0)
				ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate(), "employee");
			else
				ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate());

			eventModel.addEvent(ev);
			ev.setId(t.getStringId());
			System.err.println(t.getStringId());
			System.err.println(ev.getId());

		}
	}

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random
																	// day of
																	// month

		return date.getTime();
	}

	public Date getInitialDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar.getTime();
	}

	public ScheduleModel getEventModel() {
		if (eventModel == null) {
		} else {
		}
		return eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent() {

		if (event.getId() == null) {
			eventModel.addEvent(event);
			Task task;
			if (reciever == null) {
				task = new Task(event.getDescription(), event.getId(), getAuthenticatedUser(), getAuthenticatedUser(),
						event.getStartDate(), event.getEndDate());
				AuditLog log = new AuditLog(task.getReceiver().getUsername(), "TASK CREATED: "
						+ getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ",
						new Date());
				auditLogRepository.save(log);
			} else {
				UserData user = userService.loadUser(reciever);
				System.err.println(reciever);
				if (user != null) {
					task = new Task(event.getDescription(), event.getId(), getAuthenticatedUser(), user,
							event.getStartDate(), event.getEndDate());
				} else {
					task = new Task(event.getDescription(), event.getId(), getAuthenticatedUser(),
							getAuthenticatedUser(), event.getStartDate(), event.getEndDate());

				}
			}
			task.setImportant(important);
			taskService.saveTask(task);

		}

		else {
			Task task = taskService.getTaskByStringId(event.getId());
			if (task.getSender().getUsername().compareTo(getAuthenticatedUser().getUsername()) == 0) {
				Task temp;
				if (reciever == null) {
					temp = new Task(event.getDescription(), event.getId(), getAuthenticatedUser(),
							getAuthenticatedUser(), event.getStartDate(), event.getEndDate());
				} else {
					UserData user = userService.loadUser(reciever);
					temp = new Task(event.getDescription(), event.getId(), getAuthenticatedUser(), user,
							event.getStartDate(), event.getEndDate());

				}
				eventModel.updateEvent(event);
				event.setId(temp.getStringId());

				taskService.deleteTask(task);
				temp.setImportant(important);
				taskService.saveTask(temp);
				AuditLog log = new AuditLog(temp.getReceiver().getUsername(), "TASK CHANGED: "
						+ getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ",
						new Date());
				auditLogRepository.save(log);
			}
		}
		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent moveEvent) {
		event = moveEvent.getScheduleEvent();

		Task task = taskService.getTaskByStringId(event.getId());
		if (task.getSender().getUsername().compareTo(getAuthenticatedUser().getUsername()) == 0) {
		Task temp = new Task(task.getDescription(), event.getId(), getAuthenticatedUser(), getAuthenticatedUser(),
				event.getStartDate(), event.getEndDate());
		eventModel.updateEvent(event);
		event.setId(temp.getStringId());

		taskService.deleteTask(task);
		taskService.saveTask(temp);
		AuditLog log = new AuditLog(temp.getReceiver().getUsername(), "TASK MOVED: "
				+ getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ",
				new Date());
		auditLogRepository.save(log);
		}
	}

	public void onEventResize(ScheduleEntryResizeEvent resizeEvent) {
		event = resizeEvent.getScheduleEvent();

		Task task = taskService.getTaskByStringId(event.getId());
		if (task.getSender().getUsername().compareTo(getAuthenticatedUser().getUsername()) == 0) {
		Task temp = new Task(task.getDescription(), event.getId(), getAuthenticatedUser(), getAuthenticatedUser(),
				event.getStartDate(), event.getEndDate());
		eventModel.updateEvent(event);
		event.setId(temp.getStringId());

		taskService.deleteTask(task);
		taskService.saveTask(temp);
		AuditLog log = new AuditLog(temp.getReceiver().getUsername(), "TASK RESIZED: "
				+ getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ",
				new Date());
		auditLogRepository.save(log);
		}
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

	public boolean getImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
}