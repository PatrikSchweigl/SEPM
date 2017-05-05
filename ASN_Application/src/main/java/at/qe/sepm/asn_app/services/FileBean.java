package at.qe.sepm.asn_app.services;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;

@Component
@ApplicationScoped
public class FileBean {
	
	public FileBean(){}
	
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		System.out.println( file.getFileName());
	}
}