package at.qe.sepm.asn_app.ui.beans;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Session information bean to retrieve session-specific parameters.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("session")
//@Scope("application")
public class SessionInfoBean {

	@Autowired
	private UserService userService;
	@Autowired
	private ParentService parentService;

	/**
	 * Attribute to cache the current user.
	 */
	private UserData currentUserData;
	
	private String phoneNumber;
	private String streetName;
	private String location;
	private boolean notification;
	private String email;
	private String postcode;

	/**
	 * Returns the currently logged on user, null if no user is authenticated
	 * for this session.
	 *
	 * @return
	 */
	public UserData getCurrentUserData() {
		String currentUserName = getCurrentUserName();
		if (currentUserName.isEmpty()) {
			return null;
		}
		currentUserData = userService.loadUser(currentUserName);
		phoneNumber = currentUserData.getPhoneNumber();
		streetName = currentUserData.getStreetName();
		location = currentUserData.getLocation();
		notification = currentUserData.isNotification();
		email = currentUserData.getEmail();
		postcode = currentUserData.getPostcode();
		return currentUserData;
	}

	public void doSaveUser() {
		currentUserData.setEmail(email);
		currentUserData.setLocation(location);
		currentUserData.setNotification(notification);
		currentUserData.setPhoneNumber(phoneNumber);
		currentUserData.setStreetName(streetName);
		currentUserData.setPostcode(postcode);
		currentUserData = this.userService.changeData(currentUserData);
	}

	/**
	 * Returns the username of the user for this session, empty string if no
	 * user has been authenticated for this session.
	 *
	 * @return
	 */
	public String getCurrentUserName() {
		if (!isLoggedIn()) {
			return "";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		return name;
	}

	/**
	 * Returns the roles of the user for this session as space-separated list,
	 * empty string if no user has been authenticated for this session-
	 *
	 * @return
	 */
	public String getCurrentUserRoles() {
		if (!isLoggedIn()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority role : auth.getAuthorities()) {
			sb.append(role.getAuthority());
			sb.append(' ');
		}
		return sb.toString().trim();
	}

	/**
	 * Checks if a user is authenticated for this session.
	 *
	 * @return true if a non-anonymous user has been authenticated, false
	 *         otherwise
	 */
	public boolean isLoggedIn() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return auth.isAuthenticated() && !auth.getName().equals("anonymousUser");
		} else {
			return false;
		}
	}

	/**
	 * Checks if the user for this session has the given role (c.f.
	 * {@link UserRole}).
	 *
	 * @param role
	 *            the role to check for as string
	 * @return true if a user is authenticated and the current user has the
	 *         given role, false otherwise
	 */
	public boolean hasRole(String role) {
		if (role == null || role.isEmpty() || !isLoggedIn()) {
			return false;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority ga : auth.getAuthorities()) {
			if (role.equals(ga.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasDefaultPasswd() {
		UserData user = getCurrentUserData();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches("passwd", user.getPassword()))
        	return true;
        else
        	return false;
	}

	public boolean isInactiveParent() {
		if(parentService.loadParent(getCurrentUserName()) != null) {
			return parentService.loadParent(getCurrentUserName()).isStatus() == false;
		} else {
			return false;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}
