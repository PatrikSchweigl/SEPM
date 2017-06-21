package at.qe.sepm.asn_app.ui.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.services.NurseryInformationService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.RegistrationService;
import at.qe.sepm.asn_app.services.TaskService;
import at.qe.sepm.asn_app.services.UserService;
import at.qe.sepm.asn_app.ui.constraints.RegistrationConstraints;
import at.qe.sepm.asn_app.ui.constraints.ScheduleConstraints;

/**
 * Created by Auki on 02.05.2017.
 */

@Component
@Scope("view")
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
	private MailService mailService;
	@Autowired
	private RegistrationConstraints registrationConstraints;
	@Autowired
	private ScheduleConstraints scheduleConstraints;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private ChildService childService;
	@Autowired
	private ParentService parentService;
	@Autowired
	private UserService userService;
	@Autowired
	private LunchService lunchService;
	@Autowired
	private AuditLogRepository auditLogRepository;
	@Autowired
	private NurseryInformationService nurseryInformationService;
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
	private Collection<NurseryInformation> nurseryInfo;
	private Collection<Registration> registrations;
	private Collection<Lunch> lunchs;

	private Child childReg;
	private String description;
	private String childFirstname;
	private Date childBringDate;
	private String selectedDay;
	private Date today;
	String footer = "Das Kinderkrippen Team bedankt sich für Ihre Mitarbeit!";

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
				ev = new DefaultScheduleEvent("Aufgabe für: " + t.getReceiver().getFirstName() + " "
						+ t.getReceiver().getLastName() + "\n" + t.getDescription(), t.getBeginDate(),
						t.getEndingDate(), "employee");
			else if (getAuthenticatedUser().getUserRole() == UserRole.PARENT
					&& t.getSender().getUserRole() == UserRole.EMPLOYEE
					&& (t.getEndingDate().compareTo(new Date()) > 0))
				ev = new DefaultScheduleEvent("Aufgabe von: " + t.getSender().getFirstName() + " "
						+ t.getSender().getLastName() + "\n" + t.getDescription(), t.getBeginDate(), t.getEndingDate(),
						"employee");
			else if (t.getEndingDate().compareTo(new Date()) > 0)
				ev = new DefaultScheduleEvent(t.getDescription(), t.getBeginDate(), t.getEndingDate(), "normal-event");
			else
				break;
			eventModel.addEvent(ev);
			ev.setId(t.getStringId());

		}
		if (getAuthenticatedUser().getUserRole() == UserRole.PARENT) {
			nurseryInfo = nurseryInformationService.getAllInformation();
			for (NurseryInformation n : nurseryInfo) {
				if (n.getBringEnd().compareTo(new Date()) > 0) {
					DefaultScheduleEvent ev3;
					ev3 = new DefaultScheduleEvent(
							"   Bringzeit: " + n.getBringDurationNew() + "\n" + "Holzeit: " + n.getPickUpDurationNew(),
							n.getOriginDate(), n.getOriginDate(), "info");
					ev3.setAllDay(true);
					eventModel.addEvent(ev3);
				}
			}

			registrations = registrationService.getAllRegistrationsByParent();
			for (Registration r : registrations) {
				if (r.getBringdate().compareTo(new Date()) > 0) {
					DefaultScheduleEvent ev;

					ev = new DefaultScheduleEvent(
							"Anmeldung: " + r.getChild().getFirstName() + " " + r.getChild().getLastName() + " "
									+ r.getFormattedBringDate() + " " + "Uhr" + "\n" + r.getNote(),
							r.getDate(), r.getDate(), "registration");
					System.err.println(ev.getId());
					eventModel.addEvent(ev);
				}
			}

			lunchs = lunchService.findAll();
			Parent p = parentService.loadParent(getAuthenticatedUser().getUsername());
			Set<Child> arr = p.getChildren();
			for (Lunch l : lunchs) {
				if(l.getDate().compareTo(new Date()) > 0){
				for (Child c : arr) {
					if (l.getChildrenIds().contains(c.getId())) {
						DefaultScheduleEvent ev;
						l.getDate().setHours(2);
						ev = new DefaultScheduleEvent("Mittagessen: " + c.getFirstName() + " " + c.getLastName(),
								l.getDate(), l.getDate(), "meal");
						eventModel.addEvent(ev);
					}
				}
				}
			}
		}
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
		if (task.getSender().getUsername().compareTo(getAuthenticatedUser().getUsername()) == 0) {
			taskService.deleteTaskById(event.getId());
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('eventDialog').hide()");
			context.execute("window.location.replace(window.location.href)");

		}

		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Sie sind nicht berrechtigt, diesen Eintrag zu löschen", null));
		}
	}

	public void deleteEventParent() {
		String desc = event.getTitle();
		Calendar cal = Calendar.getInstance();
		cal.setTime(event.getStartDate());
		cal.add(Calendar.HOUR_OF_DAY, 2);
		Parent p = parentService.loadParent(getAuthenticatedUser().getUsername());
		Set<Child> c = p.getChildren();
		System.err.println("WWWWWWWWWWWWWWWWWWWW");
		System.err.println(desc);
		if (desc.contains("Anmeldung:")) {
			Child child = null;
			for (Child ch : c) {
				System.err.println("compare " + ch.getFirstName() + " MMMMMMMMIT " + desc + desc.contains(ch.getFirstName()));
				if (desc.contains(ch.getFirstName())) {
					System.err.println("PEACE");
					child = ch;
					System.err.println(ch.getFirstName());
					break;
				}
				else{
					System.err.println("BBBBBBBBBBBBBBBBB");
				}
			}
			System.err.println(child.getFirstName() + "Zeit  " + event.getStartDate());
			Collection<Registration> reg = registrationService.getAllRegistrationsByDate(event.getStartDate());
			if(reg == null)
				System.err.println("NOOOOOOOOOOOOOOOOOOOOO");
			for (Registration r : reg) {
				System.err.println(r.getChild().getFirstName() + " " + child.getFirstName());

				if (child.getId() == r.getChild().getId()) {
					NurseryInformation nursery = nurseryInformationService.nurseryInformationByOriginDate(event.getStartDate());
					if(nursery == null){
						Collection<NurseryInformation> infos = nurseryInformationService.getAllInformation();
						System.err.println(infos.iterator().next().getOriginDate());
						System.err.println("KUUUUUUUUUUUUUUUUUUUUUUUUUUU");
						System.err.println(event.getStartDate());
					}
					else{
					nursery.setCurrentOccupancy(nursery.getCurrentOccupancy() - 1);
					nurseryInformationService.saveNurseryInformation(nursery);
					}
					registrationService.deleteRegistration(r);
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('eventDialog').hide()");
					context.execute("window.location.replace(window.location.href)");
				}
			}
		} else if (desc.contains("Mittagessen:")) {
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			Child child = null;
			for (Child ch : c) {
				System.err.println("compare " + ch.getFirstName() + " MMMMMMMMIT " + desc );
				if (desc.contains(ch.getFullname())) {
					child = ch;
				}
			}
			Collection<Lunch> lun = lunchService.getLunchByDate(event.getStartDate());
			for (Lunch r : lun) {
				if (r.getChildrenIds().contains(child.getId())) {
					if (r.getDate().compareTo(cal2.getTime()) <= 0) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Die Abmeldefrist für das Essen ist verstrichen", null));
					} else {
						r.removeChild(child.getId());
						lunchService.saveLunch(r);
						RequestContext context = RequestContext.getCurrentInstance();
						context.execute("PF('eventDialog').hide()");
						context.execute("window.location.replace(window.location.href)");

					}
				}

			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Sie sind nicht berrechtigt, diesen Eintrag zu löschen", null));
		}
	}

	public void registerFullWeek() {
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal2.add(Calendar.WEEK_OF_YEAR, 1);
		cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal2.set(Calendar.HOUR_OF_DAY, 8);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		Collection<Child> childs = childService.getChildrenByParentUsername(getAuthenticatedUser().getUsername());
		for (int i = 0; i < 5; ++i) {
			NurseryInformation nurseryInformation  = nurseryInformationService.nurseryInformationByOriginDate(cal.getTime());
			for (Child c : childs) {
				Registration reg = new Registration("", c, cal.getTime(), cal2.getTime());
				if (registrationConstraints.registrationExists(reg))
					continue;
				if (!registrationConstraints.checkIfNurseryExists(reg))
					continue;
				registrationService.saveRegistration(reg);
				nurseryInformation.setCurrentOccupancy(nurseryInformation.getCurrentOccupancy()+1);
				nurseryInformationService.saveNurseryInformationEdit(nurseryInformation);
				AuditLog log = new AuditLog(reg.getChild().getFirstName() + " " + reg.getChild().getLastName(),
						"REGISTRATION CREATED: " + getAuthenticatedUser().getUsername() + " ["
								+ getAuthenticatedUser().getUserRole() + "] ",
						reg.getBringdate());
				auditLogRepository.save(log);
			}
			cal.add(Calendar.DAY_OF_WEEK, 1);
			cal2.add(Calendar.DAY_OF_WEEK, 1);
		}
	}

	public void registerFullWeekLunch() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 2);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		System.err.println("WOOOOOOOOOOOOOOOOO");
		Collection<Child> childs = childService.getChildrenByParentUsername(getAuthenticatedUser().getUsername());
		for (int i = 0; i < 5; ++i) {
			for (Child c : childs) {
				List<Lunch> lunchs = lunchService.getLunchByDate(cal.getTime());
				System.err.println(cal.getTime());
				System.err.println("WOOOOOOOOOOOOOOOOO");
				if (lunchs == null || lunchs.size() < 1) {
					System.err.println("WHAAAAAAAAAAAAAAAAAAAAAT");
					continue;
				}
				if (lunchs.get(0).getChildrenIds().contains(c.getId()))
					continue;
				lunchs.get(0).addChild(c);
				lunchService.saveLunch(lunchs.get(0));
				AuditLog log = new AuditLog(c.getFullname(), "REGISTRATION MEAL CREATED: "
						+ getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ",
						cal.getTime());
				auditLogRepository.save(log);
			}
			cal.add(Calendar.DAY_OF_WEEK, 1);
		}
	}

	public NurseryInformation getCurrentNurseryInformation(){
		//System.out.println(nurseryInformationService.nurseryInformationByOriginDate(event.getStartDate()).toString()+"-----------------------");
		Calendar cal = Calendar.getInstance();
		cal.setTime(event.getStartDate());
		cal.add(Calendar.HOUR_OF_DAY, 2);
		System.err.println("HERE I AM");
		System.out.println(cal.getTime());
		Collection<NurseryInformation> info = nurseryInformationService.getAllInformation();
		for(NurseryInformation i : info){
		System.err.println(i.getOriginDate());
		}
		return nurseryInformationService.nurseryInformationByOriginDate(cal.getTime());
	}

	public void addRegistration() {
		NurseryInformation nurseryInformation;
		try {
			childReg = childService.getChildrenByFirstnameAndParentUsername(getAuthenticatedUser().getUsername(),
					childFirstname);
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal.setTime(event.getStartDate());
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal2.setTime(event.getStartDate());
			cal2.add(Calendar.HOUR_OF_DAY, 2);
			Registration reg = new Registration(description, childReg, cal.getTime(), cal2.getTime());

			if (event.getStartDate().compareTo(new Date()) <= 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Keine Anmeldung in der Vergangenheit möglich", null));
			} else if (registrationConstraints.registrationExists(reg)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Sie haben für heute ihr Kind schon angemeldet", null));
			} else if (!registrationConstraints.checkIfNurseryExists(reg)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sie können kein Kind eintragen", null));
			} else if((nurseryInformation = nurseryInformationService.nurseryInformationByOriginDate(cal.getTime())) == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Für diesen Tag gibt es keine Information", null));
			}else if(nurseryInformation.getCurrentOccupancy() == nurseryInformation.getMaxOccupancy()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Kein Platz mehr frei für diesen Tag", null));
			}else if (registrationConstraints.checkTimeConstraints(reg)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Sie können kein Kind um diese Uhrzeit eintragen", null));
			} else{

				registrationService.saveRegistration(reg);
				nurseryInformation.setCurrentOccupancy(nurseryInformation.getCurrentOccupancy()+1);
				nurseryInformationService.saveNurseryInformationEdit(nurseryInformation);
				AuditLog log = new AuditLog(reg.getChild().getFirstName() + " " + reg.getChild().getLastName(),
						"REGISTRATION CREATED: " + getAuthenticatedUser().getUsername() + " ["
								+ getAuthenticatedUser().getUserRole() + "] ",
						reg.getBringdate());
				auditLogRepository.save(log);
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('eventDateDialog').hide()");
				context.execute("window.location.replace(window.location.href)");

			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
		}
	}

	public void addEvent() {
		if (event.getStartDate().compareTo(new Date()) < 0)
			return;
		System.err.println(event.getStartDate());
		System.err.println(event.getEndDate());
		if (event.getEndDate().compareTo(event.getStartDate()) < 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Endzeit muss nach der Startzeit liegen", null));
			return;
		}
		if (event.getDescription().compareTo("") == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sie müssen einen Titel angeben", null));
			return;
		}
		// if the Event is Select on Date event
		if (event.getId() == null) {
			eventModel.addEvent(event);

			Task task;
			if (reciever == null || !visible) {

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
					if (scheduleConstraints.checkIfTaskExistsForParent(task)) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Dieser Elternteil hat in diesem Zeitraum schon eine Aufgabe", null));
						return;
					}
					mailService.sendEmail(user.getEmail(), "Ihnen wurde eine neue Aufgabe zugeteilt",
							"Guten Tag " + user.getFirstName() + " " + user.getLastName()
									+ "!\n\nIhnen wurde soeben von der/dem Krippenmitarbeiter/in "
									+ getAuthenticatedUser().getUsername() + " eine neue Augabe zugeteilt:\n\n"
									+ event.getDescription() + "\t " + task.getFormattedDate(task.getBeginDate())
									+ " bis " + task.getFormattedDate(task.getEndingDate()) + "\n\n" + footer);
				} else {
					task = new Task(event.getDescription(), event.getId(), getAuthenticatedUser(),
							getAuthenticatedUser(), event.getStartDate(), event.getEndDate());

				}
			}
			task.setImportant(important);
			taskService.saveTask(task);

		}

		// if the Event is Select on Event event

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
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('eventDialog').hide()");
		context.execute("window.location.replace(window.location.href)");

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

	public Child getChildReg() {
		return childReg;
	}

	public void setChildReg(Child childReg) {
		this.childReg = childReg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChildFirstname() {
		return childFirstname;
	}

	public void setChildFirstname(String childFirstname) {
		this.childFirstname = childFirstname;
	}

	public Date getChildBringDate() {
		return childBringDate;
	}

	public void setChildBringDate(Date childBringDate) {
		this.childBringDate = childBringDate;
	}

	public String getFormattedDate(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		form.setTimeZone(TimeZone.getTimeZone("GMT+2"));
		try {
			Date temp = form.parse(date.toString());
			return temp.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getSelectedDay() {
		return selectedDay;
	}

	public void setSelectedDay(String selectedDay) {
		this.selectedDay = selectedDay;
	}

	public Date getToday() {
		today = new Date();
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}
}