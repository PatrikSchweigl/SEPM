package at.qe.sepm.asn_app.ui.controllers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;

	private ScheduleEvent event = new DefaultScheduleEvent();
	private ScheduleEvent editEvent = new DefaultScheduleEvent();
	private ScheduleEvent editViewEvent = new DefaultScheduleEvent();
	@Autowired
	private UserService userService;
	@Autowired
	private AuditLogRepository auditLogRepository;
	private boolean visible;
	private boolean important;
	private boolean child;
	private boolean meal;
	private boolean info;
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserRepository userRepository;
	private String reciever;
	private String sender;
	private Collection<Task> tasks;

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();

	}

	public void test() {
		eventModel.clear();
		tasks = taskService.getAllTasksByReceiverAndImportance(getAuthenticatedUser().getId());
		for (Task t : tasks) {
			DefaultScheduleEvent ev;
			if (t.getImportant())
				ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate(), "important");
			else if (getAuthenticatedUser().getUserRole() == UserRole.EMPLOYEE
					&& t.getReceiver().getUserRole() == UserRole.PARENT)
				ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate(), "employee");
			else if (getAuthenticatedUser().getUserRole() == UserRole.PARENT
					&& t.getSender().getUserRole() == UserRole.EMPLOYEE && (t.getEndingDate().compareTo(new Date()) > 0))
				ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate(), "employee");
			else if( t.getEndingDate().compareTo(new Date()) > 0)
				ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate(), "normal-event");
			else
				break;
			eventModel.addEvent(ev);
			ev.setId(t.getStringId());
			System.err.println(ev.getId());
			System.err.println(t.getDescription());

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

	public void deleteEvent() {
		Task task = taskService.getTaskByStringId(event.getId());
		if (task.getSender().getUsername().compareTo(getAuthenticatedUser().getUsername()) == 0)
			taskService.deleteTaskById(event.getId());
		else
			FacesContext.getCurrentInstance().addMessage("scheduleForm",
					new FacesMessage("Sie sind nicht berechtigt, den Eintrag zu löschen."));
	}

	public void addEvent() {
		if (event.getStartDate().compareTo(new Date()) < 0)
			return;

		if (event.getId() == null) {
			eventModel.addEvent(event);

			Task task;
			if (reciever == null || !visible) {
				System.err.println(event.getDescription());

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
	
	public void addChangeEvent() {
		if (editEvent.getStartDate().compareTo(new Date()) < 0)
			return;

		if (editEvent.getId() == null) {
			eventModel.addEvent(editEvent);

			Task task;
			if (reciever == null || !visible) {
				System.err.println(editEvent.getDescription());

				task = new Task(editEvent.getDescription(), editEvent.getId(), getAuthenticatedUser(), getAuthenticatedUser(),
						editEvent.getStartDate(), editEvent.getEndDate());
				AuditLog log = new AuditLog(task.getReceiver().getUsername(), "TASK CREATED: "
						+ getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ",
						new Date());
				auditLogRepository.save(log);
			} else {

				UserData user = userService.loadUser(reciever);
				System.err.println(reciever);
				if (user != null) {
					task = new Task(editEvent.getDescription(), editEvent.getId(), getAuthenticatedUser(), user,
							editEvent.getStartDate(), editEvent.getEndDate());
				} else {
					task = new Task(editEvent.getDescription(), editEvent.getId(), getAuthenticatedUser(),
							getAuthenticatedUser(), editEvent.getStartDate(), editEvent.getEndDate());

				}
			}
			task.setImportant(important);
			taskService.deleteTaskById(editEvent.getId());
			taskService.saveTask(task);

		}

		else {
			Task task = taskService.getTaskByStringId(editEvent.getId());
			if (task.getSender().getUsername().compareTo(getAuthenticatedUser().getUsername()) == 0) {
				Task temp;
				if (reciever == null) {
					temp = new Task(editEvent.getDescription(), editEvent.getId(), getAuthenticatedUser(),
							getAuthenticatedUser(), editEvent.getStartDate(), editEvent.getEndDate());
				} else {
					UserData user = userService.loadUser(reciever);
					temp = new Task(editEvent.getDescription(), editEvent.getId(), getAuthenticatedUser(), user,
							editEvent.getStartDate(), editEvent.getEndDate());

				}
				eventModel.updateEvent(editEvent);
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
		editEvent = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		editEvent = (ScheduleEvent) selectEvent.getObject();
		event = editEvent;
		editViewEvent = event;
		System.err.println(event.getId());
		sender = taskService.getTaskByStringId(event.getId()).getSender().getUsername();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent moveEvent) {
		event = moveEvent.getScheduleEvent();
		Task task = taskService.getTaskByStringId(event.getId());
		if (task.getSender().getUsername().compareTo(getAuthenticatedUser().getUsername()) == 0) {
			Task temp = new Task(task.getDescription(), event.getId(), getAuthenticatedUser(), task.getReceiver(),
					event.getStartDate(), event.getEndDate());
			temp.setImportant(task.getImportant());
			temp.setStyleClass(task.getStyleClass());
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
			Task temp = new Task(task.getDescription(), event.getId(), getAuthenticatedUser(), task.getReceiver(),
					event.getStartDate(), event.getEndDate());
			temp.setImportant(task.getImportant());
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

	public ScheduleEvent getEditEvent() {
		return editEvent;
	}

	public void setEditEvent(ScheduleEvent editEvent) {
		this.editEvent = editEvent;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public ScheduleEvent getEditViewEvent() {
		return editViewEvent;
	}

	public void setEditViewEvent(ScheduleEvent editViewEvent) {
		this.editViewEvent = editViewEvent;
	}

	public boolean getInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public boolean getMeal() {
		return meal;
	}

	public void setMeal(boolean meal) {
		this.meal = meal;
	}

	public boolean getChild() {
		return child;
	}

	public void setChild(boolean child) {
		this.child = child;
	}
}