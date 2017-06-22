package at.qe.sepm.asn_app.ui.beans;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@Scope("view")
public class PDFBean {

    private Child childPrint;
    private Employee employeePrint;
    private Parent parentPrint;
    private Caregiver caregiverPrint;

	/*
     * @PostConstruct public void init() { childPrint = new Child();
	 * 
	 * }
	 */

    public PDFBean() {
    }

    public void createPDFChild() throws DocumentException, IOException {
        Font catFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL);

        Child child = childPrint;
        if (child == null) {
            return;
        }
        String DEST = "src/main/webapp/resources/downloads/Stammblatt" + child.getFirstName() + child.getLastName()
                + ".pdf";
        File file = new File(DEST);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        String imgString = child.getImgName();

        if (imgString == null || imgString.compareTo("emptypicture.png") == 0 || imgString.compareTo("") == 0) {
            imgString = "empty_profile_pdf.png";
        }
        Image img = Image.getInstance("src/main/webapp/resources/pictures/profile_pictures_children/" + imgString);
        img.setAlignment(Image.ALIGN_RIGHT);
        img.scaleAbsolute(200f, 200f);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        Paragraph childFirstname = new Paragraph(child.getFirstName(), redFont);
        PdfPCell cell = new PdfPCell(childFirstname);
        cell.setMinimumHeight(35f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Vorname:", catFont));
        table.addCell(cell);
        Paragraph childSurname = new Paragraph(child.getLastName(), redFont);
        PdfPCell cell2 = new PdfPCell(childSurname);
        cell2.setMinimumHeight(35f);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Familienname:", catFont));
        table.addCell(cell2);
        Paragraph childBirthday = new Paragraph(child.getBirthday(), redFont);
        PdfPCell cell3 = new PdfPCell(childBirthday);
        cell3.setMinimumHeight(35f);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Geburtsdatum:", catFont));
        table.addCell(cell3);
        Paragraph childGender = new Paragraph(child.getGender().toString(), redFont);
        PdfPCell cell4 = new PdfPCell(childGender);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell4.setMinimumHeight(35f);
        table.addCell(new Paragraph("Geschlecht:", catFont));
        table.addCell(cell4);
        Paragraph childReligion;
        if (child.getReligion() == null)
            childReligion = new Paragraph("k.A.", redFont);
        else
            childReligion = new Paragraph(child.getReligion().toString(), redFont);

        PdfPCell cell9 = new PdfPCell(childReligion);
        cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell9.setMinimumHeight(35f);
        table.addCell(new Paragraph("Religionsbekenntnis:", catFont));
        table.addCell(cell9);
        Paragraph childParent1 = new Paragraph(child.getPrimaryParentFullName(), redFont);
        PdfPCell cell5 = new PdfPCell(childParent1);
        cell5.setMinimumHeight(35f);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Primärer Elternteil:", catFont));
        table.addCell(cell5);

        if (child.getParent2() != null) {
            Paragraph childParent2 = new Paragraph(
                    child.getParent2().getFirstName() + " " + child.getParent2().getLastName(), redFont);
            PdfPCell cell6 = new PdfPCell(childParent2);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(new Paragraph("Elternteil:", catFont));
            table.addCell(cell6);
        }
        Paragraph childEmergencyNumber = new Paragraph(child.getEmergencyNumber(), redFont);
        PdfPCell cell7 = new PdfPCell(childEmergencyNumber);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell7.setMinimumHeight(35f);
        table.addCell(new Paragraph("Notfallkontakt:", catFont));
        table.addCell(cell7);
        for (Sibling s : child.getSiblings()) {
            Paragraph childSiblings = new Paragraph(s.getFirstName() + " " + s.getLastName(), redFont);

            PdfPCell cell8 = new PdfPCell(childSiblings);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell8.setMinimumHeight(35f);
            table.addCell(new Paragraph("Geschwister:", catFont));
            table.addCell(cell8);
        }
        for (Caregiver c : child.getCaregivers()) {
            Paragraph childCaregivers = new Paragraph(c.getFullName(), redFont);
            PdfPCell cell10 = new PdfPCell(childCaregivers);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell10.setMinimumHeight(35f);
            table.addCell(new Paragraph("Bezugsperson:", catFont));
            table.addCell(cell10);
        }
        Paragraph childIntolerances;
        if (child.getFoodIntolerances() == null)
            childIntolerances = new Paragraph("k.A.", redFont);
        else
            childIntolerances = new Paragraph(child.getFoodIntolerances(), redFont);
        PdfPCell cell11 = new PdfPCell(childIntolerances);
        cell11.setMinimumHeight(35f);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Unverträglichkeiten:", catFont));
        table.addCell(cell11);
        Paragraph childAllergies;
        if (child.getAllergies() == null)
            childAllergies = new Paragraph("k.A.", redFont);
        else
            childAllergies = new Paragraph(child.getAllergies(), redFont);
        PdfPCell cell12 = new PdfPCell(childAllergies);
        cell12.setMinimumHeight(35f);
        cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Allergien:", catFont));
        table.addCell(cell12);
        document.add(img);
        document.add(table);
        document.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sie haben eine PDF Datei", null));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Stammblatt" + child.getFirstName() + child.getLastName() + ".pdf erstellt.", null));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sie befindet sich im Ordner Downloads.", null));
    }

    public void createPDFEmployee() throws DocumentException, IOException {
        Font catFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL);

        Employee employee = employeePrint;
        if (employee == null) {

            return;
        }
        String DEST = "src/main/webapp/resources/downloads/Stammblatt" + employee.getFirstName()
                + employee.getLastName() + ".pdf";
        File file = new File(DEST);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        String imgString = employee.getImgName();

        if (imgString == null || imgString.compareTo("emptypicture.png") == 0 || imgString.compareTo("") == 0) {
            imgString = "empty_profile_pdf.png";
        }
        Image img = Image.getInstance("src/main/webapp/resources/pictures/profile_pictures/" + imgString);
        img.setAlignment(Image.ALIGN_RIGHT);
        img.scaleAbsolute(200f, 200f);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        Paragraph employeeFirstname = new Paragraph(employee.getFirstName(), redFont);
        PdfPCell cell = new PdfPCell(employeeFirstname);
        cell.setMinimumHeight(35f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Vorname:", catFont));
        table.addCell(cell);
        Paragraph employeeSurname = new Paragraph(employee.getLastName(), redFont);
        PdfPCell cell2 = new PdfPCell(employeeSurname);
        cell2.setMinimumHeight(35f);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Familienname:", catFont));
        table.addCell(cell2);
        Paragraph employeeBirthday = new Paragraph(employee.getBirthday(), redFont);
        PdfPCell cell3 = new PdfPCell(employeeBirthday);
        cell3.setMinimumHeight(35f);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Geburtsdatum:", catFont));
        table.addCell(cell3);
        Paragraph employeeLocation = new Paragraph(employee.getLocation(), redFont);
        PdfPCell cell4 = new PdfPCell(employeeLocation);
        cell4.setMinimumHeight(35f);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Wohnort:", catFont));
        table.addCell(cell4);
        Paragraph employeeStreet = new Paragraph(employee.getStreetName(), redFont);
        PdfPCell cell5 = new PdfPCell(employeeStreet);
        cell5.setMinimumHeight(35f);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Straße:", catFont));
        table.addCell(cell5);
        Paragraph employeepostcode = new Paragraph(employee.getLocation(), redFont);
        PdfPCell cell13 = new PdfPCell(employeepostcode);
        cell13.setMinimumHeight(35f);
        cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Postleitzahl:", catFont));
        table.addCell(cell13);
        Paragraph employeeNumber = new Paragraph(employee.getPhoneNumber(), redFont);
        PdfPCell cell7 = new PdfPCell(employeeNumber);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Telefonnummer", catFont));
        table.addCell(cell7);
        Paragraph employeeEmail = new Paragraph(employee.getEmail(), redFont);
        PdfPCell cell11 = new PdfPCell(employeeEmail);
        cell7.setMinimumHeight(35f);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Emailadresse:", catFont));
        table.addCell(cell11);
        Paragraph employeeReligion;
        if (employee.getReligion() == null)
            employeeReligion = new Paragraph("k.A.", redFont);
        else
            employeeReligion = new Paragraph(employee.getReligion().toString(), redFont);
        PdfPCell cell9 = new PdfPCell(employeeReligion);
        cell9.setMinimumHeight(35f);
        cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Religionsbekenntnis:", catFont));
        table.addCell(cell9);
        Paragraph parentFamily;
        if (employee.getFamilyStatus() == null)
            parentFamily = new Paragraph("k.A.", redFont);
        else
            parentFamily = new Paragraph(employee.getFamilyStatus().toString(), redFont);
        PdfPCell cell20 = new PdfPCell(parentFamily);
        cell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell20.setMinimumHeight(35f);
        table.addCell(new Paragraph("Familienstatus", catFont));
        table.addCell(cell20);
        Paragraph employeeWorkingstate;
        if (employee.getWorkRole() == null)
            employeeWorkingstate = new Paragraph("k.A.", redFont);
        else
            employeeWorkingstate = new Paragraph(employee.getWorkRole().toString(), redFont);
        PdfPCell cell10 = new PdfPCell(employeeWorkingstate);
        cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell10.setMinimumHeight(35f);
        table.addCell(new Paragraph("Arbeitsstatus:", catFont));
        table.addCell(cell10);
        document.add(img);
        document.add(table);
        document.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sie haben eine PDF Datei", null));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Stammblatt" + employee.getFirstName() + employee.getLastName() + ".pdf erstellt.", null));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sie befindet sich im Ordner Downloads.", null));
    }

    public void createPDFParent() throws DocumentException, IOException {
        Font catFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL);

        Parent parent = parentPrint;
        if (parent == null) {
            return;
        }
        String DEST = "src/main/webapp/resources/downloads/Stammblatt" + parent.getFirstName()
                + parent.getLastName() + ".pdf";
        File file = new File(DEST);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        String imgString = parent.getImgName();

        if (imgString == null || imgString.compareTo("emptypicture.png") == 0 || imgString.compareTo("") == 0) {
            imgString = "empty_profile_pdf.png";
        }
        Image img = Image.getInstance("src/main/webapp/resources/pictures/profile_pictures/" + imgString);
        img.setAlignment(Image.ALIGN_RIGHT);
        img.scaleAbsolute(200f, 200f);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        Paragraph parentFirstname = new Paragraph(parent.getFirstName(), redFont);
        PdfPCell cell = new PdfPCell(parentFirstname);
        cell.setMinimumHeight(35f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Vorname:", catFont));
        table.addCell(cell);
        Paragraph parentSurname = new Paragraph(parent.getLastName(), redFont);
        PdfPCell cell2 = new PdfPCell(parentSurname);
        cell2.setMinimumHeight(35f);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Familienname:", catFont));
        table.addCell(cell2);
        Paragraph parentBirthday = new Paragraph(parent.getBirthday(), redFont);
        PdfPCell cell3 = new PdfPCell(parentBirthday);
        cell3.setMinimumHeight(35f);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Geburtsdatum:", catFont));
        table.addCell(cell3);
        Paragraph parentLocation = new Paragraph(parent.getLocation(), redFont);
        PdfPCell cell4 = new PdfPCell(parentLocation);
        cell4.setMinimumHeight(35f);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Wohnort:", catFont));
        table.addCell(cell4);
        Paragraph parentStreet = new Paragraph(parent.getStreetName(), redFont);
        PdfPCell cell5 = new PdfPCell(parentStreet);
        cell5.setMinimumHeight(35f);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Straße:", catFont));
        table.addCell(cell5);
        Paragraph parentpostcode = new Paragraph(parent.getLocation(), redFont);
        PdfPCell cell13 = new PdfPCell(parentpostcode);
        cell13.setMinimumHeight(35f);
        cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Postleitzahl:", catFont));
        table.addCell(cell13);
        Paragraph parentNumber = new Paragraph(parent.getPhoneNumber(), redFont);
        PdfPCell cell7 = new PdfPCell(parentNumber);
        cell7.setMinimumHeight(35f);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Telefonnummer:", catFont));
        table.addCell(cell7);
        Paragraph parentEmail = new Paragraph(parent.getEmail(), redFont);
        PdfPCell cell11 = new PdfPCell(parentEmail);
        cell11.setMinimumHeight(35f);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Emailadresse:", catFont));
        table.addCell(cell11);
        Paragraph parentReligion;
        if (parent.getReligion() == null)
            parentReligion = new Paragraph("k.A.", redFont);
        else
            parentReligion = new Paragraph(parent.getReligion().toString(), redFont);
        PdfPCell cell9 = new PdfPCell(parentReligion);
        cell9.setMinimumHeight(35f);
        cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Religionsbekenntnis:", catFont));
        table.addCell(cell9);
        Paragraph parentFamily;
        if (parent.getFamilyStatus() == null)
            parentFamily = new Paragraph("k.A.", redFont);
        else
            parentFamily = new Paragraph(parent.getFamilyStatus().toString(), redFont);
        PdfPCell cell10 = new PdfPCell(parentFamily);
        cell10.setMinimumHeight(35f);
        cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Familienstatus:", catFont));
        table.addCell(cell10);
        for (Child c : parent.getChildren()) {
            Paragraph parentChilds = new Paragraph(c.getFullname(), redFont);
            PdfPCell cell15 = new PdfPCell(parentChilds);
            cell15.setMinimumHeight(35f);
            cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(new Paragraph("Kind:", catFont));
            table.addCell(cell15);
        }
        document.add(img);
        document.add(table);
        document.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sie haben eine PDF Datei", null));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Stammblatt" + parent.getFirstName() + parent.getLastName() + ".pdf erstellt.", null));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sie befindet sich im Ordner Downloads.", null));
    }

    public void createPDFCaregiver() throws DocumentException, IOException {
        Font catFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL);

        Caregiver caregiver = caregiverPrint;
        if (caregiver == null) {
            return;
        }
        String DEST = "src/main/webapp/resources/downloads/Stammblatt" + caregiver.getFirstName()
                + caregiver.getLastName() + ".pdf";
        File file = new File(DEST);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        String imgString = caregiver.getImgName();

        if (imgString == null || imgString.compareTo("emptypicture.png") == 0 || imgString.compareTo("") == 0) {
            imgString = "empty_profile_pdf.png";
        }
        Image img = Image.getInstance("src/main/webapp/resources/pictures/profile_pictures_caregiver/" + imgString);
        img.setAlignment(Image.ALIGN_RIGHT);
        img.scaleAbsolute(200f, 200f);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        Paragraph careFirstname = new Paragraph(caregiver.getFirstName(), redFont);
        PdfPCell cell = new PdfPCell(careFirstname);
        cell.setMinimumHeight(35f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Vorname:", catFont));
        table.addCell(cell);
        Paragraph careSurname = new Paragraph(caregiver.getLastName(), redFont);
        PdfPCell cell2 = new PdfPCell(careSurname);
        cell2.setMinimumHeight(35f);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Familienname:", catFont));
        table.addCell(cell2);
        Paragraph carePhone = new Paragraph(caregiver.getPhoneNumber(), redFont);
        PdfPCell cell3 = new PdfPCell(carePhone);
        cell3.setMinimumHeight(35f);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Telefonnummer:", catFont));
        table.addCell(cell3);
        Paragraph careChild = new Paragraph(caregiver.getChild().getFullname(), redFont);
        PdfPCell cell4 = new PdfPCell(careChild);
        cell4.setMinimumHeight(35f);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Kind:", catFont));
        table.addCell(cell4);
        Paragraph careElig;
        if (caregiver.getEligible() == false)
            careElig = new Paragraph("Nein", redFont);
        else
            careElig = new Paragraph("Ja", redFont);
        PdfPCell cell5 = new PdfPCell(careElig);
        cell5.setMinimumHeight(35f);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(new Paragraph("Bestätigt:", catFont));
        table.addCell(cell5);
        document.add(img);
        document.add(table);
        document.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sie haben eine PDF Datei", null));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Stammblatt" + caregiver.getFirstName() + caregiver.getLastName() + ".pdf erstellt.", null));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sie befindet sich im Ordner Downloads.", null));
    }

    public Child getChildPrint() {
        return childPrint;
    }

    public void setChildPrint(Child childPrint) {
        this.childPrint = childPrint;
    }

    public Employee getEmployeePrint() {
        return employeePrint;
    }

    public void setEmployeePrint(Employee employeePrint) {
        this.employeePrint = employeePrint;
    }

    public Parent getParentPrint() {
        return parentPrint;
    }

    public void setParentPrint(Parent parentPrint) {
        this.parentPrint = parentPrint;
    }

    public Caregiver getCaregiverPrint() {
        return caregiverPrint;
    }

    public void setCaregiverPrint(Caregiver caregiverPrint) {
        this.caregiverPrint = caregiverPrint;
    }
}