package at.qe.sepm.c4f_app.ui.controllers;

import at.qe.sepm.c4f_app.models.child.Child;
import at.qe.sepm.c4f_app.models.nursery.Lunch;
import at.qe.sepm.c4f_app.services.ChildService;
import at.qe.sepm.c4f_app.services.LunchService;
import at.qe.sepm.c4f_app.ui.beans.SessionInfoBean;
import at.qe.sepm.c4f_app.ui.constraints.LunchConstraints;
import at.qe.sepm.c4f_app.utils.DateUtils;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas Aukenthaler on 22.05.2017.
 */
@Component
// @Scope("view")
@Scope("application")
public class LunchController {
	@Autowired
	private LunchService lunchService;
	@Autowired
	private ChildService childService;

	@Autowired
	private SessionInfoBean session;
	@Autowired
	private LunchConstraints lunchConstraints;

	private Lunch lunch;
	private Lunch lunchEdit;
	private List<Lunch> lunchAll;

	private Child child;
	private String childFirstname;
	private Long childId;
	private Date date;

	private List<Lunch> thisWeekLunch;
	private boolean thisWeekFlag;

	private List<Lunch> nextWeekLunch;
	private boolean nextWeekFlag;
	private boolean[] signUp = new boolean[5];

	
	@PostConstruct
	public void init(){
		lunchAll = lunchService.findAll();
	}
	/*
	 * public void processSignUp(){ Lunch l; for(int i = 0; i < 5; i++){
	 * if(thisWeekLunch.size() > i) { l = thisWeekLunch.get(i); if (signUp[i]) {
	 * l.addChild(child); lunchService.saveLunch(l); } } } }
	 */

	/**
	 * getCurrentWeek calculates dates for the current week (mon-fri) and tries
	 * to retrieve corresponding lunch objects from the DB if no lunch is found
	 * for any given date, it is filled with a default lunch object
	 *
	 * @return list of lunch for the current week
	 */
	public List<Lunch> getCurrentWeek() {
		List<Lunch> ret = null;
		if (!thisWeekFlag) {
			LocalDate now = LocalDate.now();

			Date[] dates = DateUtils.getWeek(0);

			for (Date s : dates) {
				if (ret == null) {
					ret = lunchService.getLunchByDate(s);
					if (ret == null || ret.size() == 0) {
						ret = new LinkedList<Lunch>();
						ret.add(new Lunch(s, "Geschlossen", 0));
					}
				} else {
					List<Lunch> tmp = lunchService.getLunchByDate(s);
					if (tmp == null || tmp.size() == 0) {
						ret.add(new Lunch(s, "Geschlossen", 0));
					} else {
						ret.addAll(tmp);
					}

				}
			}
			thisWeekLunch = ret;
			thisWeekFlag = true;
		}
		return thisWeekLunch;
	}

	/**
	 * getNextWeek calculates dates for the next week (mon-fri) and tries to
	 * retrieve corresponding lunch objects from the DB if no lunch is found for
	 * any given date, it is filled with a default lunch object
	 *
	 * @return list of lunch for the next week
	 */
	public List<Lunch> getNextWeek() {
		List<Lunch> ret = null;
		if (!nextWeekFlag) {
			LocalDate now = LocalDate.now();


			Date[] dates = DateUtils.getWeek(1);

			for (Date s : dates) {
				if (ret == null) {
					ret = lunchService.getLunchByDate(s);
					if (ret == null || ret.size() == 0) {
						ret = new LinkedList<Lunch>();
						ret.add(new Lunch(s, "Geschlossen", 0));
					}
				} else {
					List<Lunch> tmp = lunchService.getLunchByDate(s);
					if (tmp == null || tmp.size() == 0) {
						ret.add(new Lunch(s, "Geschlossen", 0));
					} else {
						ret.addAll(tmp);
					}

				}
			}
			nextWeekLunch = ret;
			nextWeekFlag = true;
		}
		return nextWeekLunch;
	}

	public void signUp(Date d) {
		if (d == null) {
			return;
		}
		List<Lunch> lunchs = lunchService.getLunchByDate(d);
		if (lunchs == null || lunchs.size() < 1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Es ist kein Mittagessen für diesen Tag eingetragen", null));
		} else if(!lunchConstraints.checkTimeConstraints(lunchs.get(0))){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Sie haben den Anmeldezeitpunkt für diesen Termin überschritten", null));
		} else {

			// Child c =
			// childService.getChildrenByFirstnameAndParentUsername(childFirstname,
			// session.getCurrentUserName()).get(0);
			// System.err.println("CHILD -" + c);
			if (lunchs.get(0).getChildrenIds().contains(childId)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Sie haben Ihr Kind schon zum Mittagessen eingetragen", null));
			} else {
				lunchs.get(0).addChild(childId);
				lunchService.saveLunch(lunchs.get(0));
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('eventDateDialog').hide()");
			}
		}
	}

	public boolean hasLunchToday() {
		return hasLunch(new Date());
	}

	public boolean hasLunch(Date d) {
		if (d == null) {
			return false;
		}
		List<Lunch> lunchs = lunchService.getLunchByDate(d);
		return !(lunchs == null || lunchs.size() < 1);
	}

	public List<Lunch> findAll() {
		return lunchService.findAll();
	}

	public Lunch doSaveLunch() {
		Lunch lunchReturn = null;
		if (lunchConstraints.checkIfLunchExists(lunch)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"An diesem Tag existiert bereits ein Mittagessen", null));
		} else if (!lunchConstraints.checkIfNurseryInformationExists(lunch)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"An diesem Tag hat die Kinderkrippe geschlossen", null));
		} else if (lunch.getCost() < 0.5) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte geben Sie einen adäquaten Preis an!", null));
		} else if (lunch.getMeal().compareTo("") == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte geben Sie ein Mittagessen an!", null));
		} else if (lunch.getCost() > 10.0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bitte geben Sie einen adäquaten Preis an!", null));
		}

		else {
			lunchReturn = lunchService.saveLunch(lunch);
			lunch = null;
			initNewLunch();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('lunchAddDialog').hide()");
		}
		lunchAll = findAll();
		return lunchReturn;
	}

	public void doSaveLunchEdit() {
		lunchEdit = lunchService.saveLunch(lunchEdit);
		// mailService.sendEmail("Patrik.Schweigl@student.uibk.ac.at", "Test",
		// "Hallo, ich bin es, das System!");
		lunchEdit = null;
		initNewLunchEdit();
	}

	@PostConstruct
	private void initNewLunch() {
		lunch = new Lunch();
	}

	@PostConstruct
	private void initNewLunchEdit() {
		lunch = new Lunch();
	}

	@PostConstruct
	private void initNewChild() {
		child = new Child();
	}

	public void doDeleteLunch() {
		this.lunchService.deleteLunch(lunchEdit);
		lunchEdit = null;
	}

	public void doReloadLunch() {
		lunch = lunchService.loadLunch(lunch.getId());
	}

	public void doReloadLunchEdit() {
		lunchEdit = lunchService.loadLunch(lunchEdit.getId());
	}

    public List<Lunch> getLunchReportInTimeWindow(Date start, Date end){
        List<Lunch> tmp = lunchService.getLunchInTimeWindowIE(start, end);
        System.err.println(tmp);
        return tmp;
    }

    //getters, setters -------------------------------------------------------------------------------------------
    public Long getChildId() {
        return childId;
    }

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public Lunch getLunchEdit() {
		return lunchEdit;
	}

	public void setLunchEdit(Lunch lunchEdit) {
		this.lunchEdit = lunchEdit;
		// doReloadLunch();
	}

	public void setLunchEdit2(Lunch lunchEdit) {
		this.lunchEdit = lunchEdit;
	}

	public Lunch getLunch() {
		return lunch;
	}

	public void setLunch(Lunch lunch) {
		this.lunch = lunch;
		// doReloadLunch();
	}

	public void setLunch2(Lunch lunch) {
		this.lunch = lunch;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public boolean[] getSignUp() {
		return signUp;
	}

	public void setSignUp(boolean[] signUp) {
		this.signUp = signUp;
	}

	public List<Lunch> getThisWeekLunch() {
		return thisWeekLunch;
	}

	public void setThisWeekLunch(List<Lunch> thisWeekLunch) {
		this.thisWeekLunch = thisWeekLunch;
	}

	public String getChildFirstname() {
		return childFirstname;
	}
	public void setChildFirstname(String childFirstname) {
		this.childFirstname = childFirstname;
	}

	public List<Lunch> getLunchAll() {
		return lunchAll;
	}

	public void setLunchAll(List<Lunch> lunchAll) {
		this.lunchAll = lunchAll;
	}
}
