package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Service for accessing and manipulating user data.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("application")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private MailService mailService;

	private String email;

	/**
	 * Returns a collection of all users.
	 *
	 * @return
	 */
	public Collection<UserData> getAllUsers() {
		return userRepository.findAll();
	}

	public Collection<UserData> getAllAdmin() {
		return userRepository.findAllAdmin();
	}

	public UserData findFirstUserByEMail(String email) {
		return userRepository.findFirstByEMail(email);
	}

	public Collection<UserData> getParentsByNotification() {
		try {
			return userRepository.findParentsByNotification();
		} catch (NullPointerException ex) {
			return null;
		}
	}

	/**
	 * Loads a single user identified by its username.
	 *
	 * @param username
	 *            the username to search for
	 * @return the user with the given username
	 */
	public UserData loadUser(String username) {
		return userRepository.findFirstByUsername(username);
	}

	public UserData saveUser(UserData userData) {
		AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),
				"SAVED: " + userData.getUsername() + " [" + userData.getUserRole() + "] ", new Date());
		auditLogService.saveAuditLog(log);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userData.setPassword(passwordEncoder.encode("passwd"));
		return userRepository.save(userData);
	}

	public void generatePassword(String email) {
		char[] chars = "ABCDEFGHIJKLMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		UserData userData = findFirstUserByEMail(email);
		if (userData == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Wir konnten keine Email an diese Adresse schicken", null));
			return;
		}
		mailService.sendEmail(userData.getEmail(), "Care4Fun-App - Passwortzuruecksetzung",
				"Guten Tag " + userData.getFirstName() + " " + userData.getLastName() + "!\n\n"
						+ "Soeben wurde Ihr Passwort zurueckgesetzt.\n\n" + "Das neue Passwort lautet:  " + sb + "\n"
						+ "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n"
						+ "Viel Spaß wünscht das Kinderkrippen-Team!");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userData.setPassword(passwordEncoder.encode(sb));
		userRepository.save(userData);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Ihr Passwort wurde geändert. Bitte überprüfen Sie Ihre Mails.", null));
	}

	public UserData changeData(UserData userData) {
		AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),
				"CHANGED: " + userData.getUsername() + " [" + userData.getUserRole() + "] ", new Date());
		auditLogService.saveAuditLog(log);
		return userRepository.save(userData);
	}

	/**
	 * Deletes the userData.
	 *
	 * @param userData
	 *            the userData to delete
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUser(UserData userData) {
		AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),
				"DELETED: " + userData.getUsername() + " [" + userData.getUserRole() + "]", new Date());
		auditLogService.saveAuditLog(log);
		userRepository.delete(userData);
	}

	public UserData getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findFirstByUsername(auth.getName());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
