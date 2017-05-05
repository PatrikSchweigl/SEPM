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
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.models.UserData;
import org.springframework.stereotype.Component;
import org.primefaces.model.DefaultUploadedFile;
@Component
@Scope("view")
public class FileUploadView{
	@Autowired
	private PictureService pictureService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
	private Picture picture;
    private UploadedFile file;
 
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
     
    public void upload() {
        System.out.println("HEEEEEEEEEEEEEEEEE" + "     " + file.getSize());

        if(file != null) {
        	try{
                AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"PICTURE UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
                auditLogRepository.save(log);
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                System.out.println(file.getFileName());
                picture = new Picture(file.getFileName(), userRepository.findFirstByUsername(auth.getName()), new Date(), file.getFileName());
                pictureService.savePicture(picture);
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