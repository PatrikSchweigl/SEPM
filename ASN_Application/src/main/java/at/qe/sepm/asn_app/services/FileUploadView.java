package at.qe.sepm.asn_app.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.Serializable;
import at.qe.sepm.asn_app.models.nursery.Picture;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import java.util.Date;

 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.models.UserData;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.primefaces.model.DefaultUploadedFile;
@Component
@ViewScoped
public class FileUploadView{
	@Autowired
	private PictureService pictureService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
	private Picture picture;
    private UploadedFile file;
 
    @PostConstruct
    public void init(){
    	file = new DefaultUploadedFile();
    }
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"PICTURE UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogRepository.save(log);
        System.out.println("HEEEEEEEEEEEEE");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        picture = new Picture("hi", userRepository.findFirstByUsername(auth.getName()), new Date(), "hi");
        pictureService.savePicture(picture);
        if(file != null) {
        	try{
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            String filename = FilenameUtils.getName(file.getFileName());
            InputStream input = file.getInputstream();
            OutputStream output = new FileOutputStream(new File( filename));
            try {
                IOUtils.copy(input, output);
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }
        	}catch(Exception e)
        	{
        		
        	}
        }
    }
    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }
}