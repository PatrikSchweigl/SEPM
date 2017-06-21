package at.qe.sepm.asn_app.ui.beans;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Picture;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.*;
import at.qe.sepm.asn_app.ui.controllers.ChildEditController;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Map;

@Component
@Scope("view")
public class FileBean {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChildEditController childEditController;
    @Autowired
    private ChildService childService;
    @Autowired
    private AuditLogService auditLogService;
    private String selectedPicture;
    @Autowired
    private CaregiverService caregiverService;

    public FileBean() {
    }


    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "PICTURE UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogService.saveAuditLog(log);


        Path folder = Paths.get("src/main/webapp/resources/pictures/gallery");
        String filename = FilenameUtils.getBaseName(file.getFileName());
        String extension = FilenameUtils.getExtension(file.getFileName());
        Path newFile = Files.createTempFile(folder, filename, "." + extension);
        pictureService.savePicture(new Picture(newFile.getFileName().toString(), getAuthenticatedUser(), new Date(), file.getFileName()));

        InputStream input = file.getInputstream();
        Files.copy(input, newFile, StandardCopyOption.REPLACE_EXISTING);


    }

    public void handleFileUploadProfilePicture(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "PROFILE_PICTURE UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
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


    }

    public void handleFileUploadProfilePictureChildren(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "PROFILE_PICTURE_CHILD UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogService.saveAuditLog(log);

        Path folder = Paths.get("src/main/webapp/resources/pictures/profile_pictures_children");
        String filename = FilenameUtils.getBaseName(file.getFileName());
        String extension = FilenameUtils.getExtension(file.getFileName());
        Path newFile = Files.createTempFile(folder, filename, "." + extension);
        Child child = childService.getChildrenByFirstnameAndParentUsername(getAuthenticatedUser().getUsername(), childEditController.getChildEdit().getFirstName());
        child.setImgName(newFile.getFileName().toString());
        childService.saveChild(child);
        InputStream input = file.getInputstream();
        Files.copy(input, newFile, StandardCopyOption.REPLACE_EXISTING);


    }

    public void handleFileUploadProfilePictureCaregiver(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "PROFILE_PICTURE_CAREGIVER UPLOADED: " + getAuthenticatedUser().getUsername() + " [" + getAuthenticatedUser().getUserRole() + "] ", new Date());
        auditLogService.saveAuditLog(log);

        Path folder = Paths.get("src/main/webapp/resources/pictures/profile_pictures_caregiver");
        String filename = FilenameUtils.getBaseName(file.getFileName());
        String extension = FilenameUtils.getExtension(file.getFileName());
        Path newFile = Files.createTempFile(folder, filename, "." + extension);
        Map<String, Object> requestAttributes = event.getComponent().getAttributes();
        Long personId = (Long) requestAttributes.get("caregiverId");
        Caregiver caregiver = caregiverService.loadCaregiver(personId);
        caregiver.setImgName(newFile.getFileName().toString());
        caregiverService.saveCaregiver(caregiver);
        InputStream input = file.getInputstream();
        Files.copy(input, newFile, StandardCopyOption.REPLACE_EXISTING);


    }

    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }


    public String getSelectedPicture() {
        return selectedPicture;
    }


    public void setSelectedPicture(String selectedPicture) {
        this.selectedPicture = selectedPicture;
    }


}