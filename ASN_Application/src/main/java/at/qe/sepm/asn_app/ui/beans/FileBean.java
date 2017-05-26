package at.qe.sepm.asn_app.ui.beans;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Picture;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.AuditLogService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.PictureService;
import at.qe.sepm.asn_app.services.UserService;

@Component
public class FileBean {
	@Autowired
	private PictureService pictureService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
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
	
	public void handleFileUpload(FileUploadEvent event) throws IOException {
		UploadedFile file = event.getFile();
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"PICTURE UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogService.saveAuditLog(log);
       
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
		
        Path folder = Paths.get("src/main/webapp/resources/pictures/gallery");
        String filename = FilenameUtils.getBaseName(file.getFileName());
        String extension = FilenameUtils.getExtension(file.getFileName());
        Path newFile = Files.createTempFile(folder, filename, "." + extension);
        pictureService.savePicture(new Picture(newFile.getFileName().toString(), getAuthenticatedUser(), new Date(), file.getFileName()));

        InputStream input = file.getInputstream();
        Files.copy(input, newFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Uploaded file successfully saved in " + newFile);
        
	}
	
	public void handleFileUploadProfilePicture(FileUploadEvent event) throws IOException {
		UploadedFile file = event.getFile();
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"PROFILE_PICTURE UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogService.saveAuditLog(log);

        Path folder = Paths.get("src/main/webapp/resources/pictures/profile_pictures");
        String filename = FilenameUtils.getBaseName(file.getFileName());
        String extension = FilenameUtils.getExtension(file.getFileName());
        Path newFile = Files.createTempFile(folder, filename, "." + extension);
        UserData user = userService.loadUser(getAuthenticatedUser().getUsername());
        user.setImgName(newFile.getFileName().toString());
        userService.saveUser(user);
        InputStream input = file.getInputstream();
        Files.copy(input, newFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Uploaded file successfully saved in " + newFile);
        
	}
	
    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findFirstByUsername(auth.getName());
    }
}