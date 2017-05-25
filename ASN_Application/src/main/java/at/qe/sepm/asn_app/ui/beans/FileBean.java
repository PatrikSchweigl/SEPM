package at.qe.sepm.asn_app.ui.beans;

import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Picture;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.AuditLogService;
import at.qe.sepm.asn_app.services.PictureService;

@Component
public class FileBean {
	@Autowired
	private PictureService pictureService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLogService auditLogService;
	private Picture picture;
    private UploadedFile file;
	public FileBean(){}
	
	
    public Picture getPicture(){
    	return picture;
    }
    
    public void setPicture(Picture picture){
    	this.picture = picture;
    }
    
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
	
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"PICTURE UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogService.saveAuditLog(log);
       
        
        pictureService.savePicture(new Picture(file.getFileName(), getAuthenticatedUser(), new Date(), file.getFileName()));
		
	}
	
    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findFirstByUsername(auth.getName());
    }
}