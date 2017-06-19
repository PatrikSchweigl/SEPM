package at.qe.sepm.asn_app.ui.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;

@Component
@Scope("view")
public class PDFBean {

	private Child childPrint;
	private Employee employeePrint;
	private Parent parentPrint;

	/*
	 * @PostConstruct public void init() { childPrint = new Child();
	 * 
	 * }
	 */

	public PDFBean() {
	};

	public void createPDFChild() throws DocumentException, IOException {
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
		Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);

		Child child = childPrint;
		if (child == null) {
			System.err.println("KANN NICHT SIENNIENIEINEI");
			return;
		}
		String DEST = "src/main/webapp/resources/Downloads/Stammblatt" + child.getFirstName() + child.getLastName()
				+ ".pdf";
		File file = new File(DEST);
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		String imgString = child.getImgName();
		System.err.println(imgString);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		if (imgString == null || imgString.compareTo("emptypicture.png") == 0 || imgString.compareTo("") == 0) {
			System.err.println("HEJHEJAHEY");
			imgString = "empty_profile_pdf.png";
		}
		Image img = Image.getInstance("src/main/webapp/resources/pictures/profile_pictures_children/" + imgString);
		img.setAlignment(Image.ALIGN_RIGHT);
		img.scaleAbsolute(200f, 200f);
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		Paragraph childFirstname = new Paragraph(child.getFirstName(), redFont);
		PdfPCell cell = new PdfPCell(childFirstname);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Vorname", catFont));
		table.addCell(cell);
		Paragraph childSurname = new Paragraph(child.getLastName(), redFont);
		PdfPCell cell2 = new PdfPCell(childSurname);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Familienname", catFont));
		table.addCell(cell2);
		Paragraph childBirthday = new Paragraph(child.getBirthday(), redFont);
		PdfPCell cell3 = new PdfPCell(childBirthday);
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Geburtsdatum", catFont));
		table.addCell(cell3);
		Paragraph childGender = new Paragraph(child.getGender().toString(), redFont);
		PdfPCell cell4 = new PdfPCell(childGender);
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Geschlecht", catFont));
		table.addCell(cell4);
		Paragraph childReligion;
		if(child.getReligion() == null)
			childReligion = new Paragraph("k.A.", redFont);
		else
			childReligion = new Paragraph(child.getReligion().toString(), redFont);
		
		PdfPCell cell9 = new PdfPCell(childReligion);
		cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Religionsbekenntnis", catFont));
		table.addCell(cell9);
		Paragraph childParent1 = new Paragraph(child.getPrimaryParentFullName(), redFont);
		PdfPCell cell5 = new PdfPCell(childParent1);

		cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Primärer Elternteil", catFont));
		table.addCell(cell5);

		if (child.getParent2() != null) {
			Paragraph childParent2 = new Paragraph(
					child.getParent2().getFirstName() + " " + child.getParent2().getLastName(), redFont);
			PdfPCell cell6 = new PdfPCell(childParent2);
			cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(new Paragraph("Elternteil", catFont));
			table.addCell(cell6);
		}
		Paragraph childEmergencyNumber = new Paragraph(child.getEmergencyNumber(), redFont);
		PdfPCell cell7 = new PdfPCell(childEmergencyNumber);
		cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Notfallkontakt", catFont));
		table.addCell(cell7);
		for (Sibling s : child.getSiblings()) {
			Paragraph childSiblings = new Paragraph(s.getFirstName() + " " + s.getLastName(), redFont);

			PdfPCell cell8 = new PdfPCell(childSiblings);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(new Paragraph("Geschwister", catFont));
			table.addCell(cell8);
		}
		for (Caregiver c : child.getCaregivers()) {
			Paragraph childCaregivers = new Paragraph(c.getFullName(), redFont);
			PdfPCell cell10 = new PdfPCell(childCaregivers);
			cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(new Paragraph("Bezugspersonen", catFont));
			table.addCell(cell10);
		}
		Paragraph childIntolerances = new Paragraph(child.getFoodIntolerances(), redFont);
		PdfPCell cell11 = new PdfPCell(childIntolerances);
		cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Unverträglichkeiten", catFont));
		table.addCell(cell11);
		Paragraph childAllergies = new Paragraph(child.getAllergies(), redFont);
		PdfPCell cell12 = new PdfPCell(childAllergies);
		cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Allergien", catFont));
		table.addCell(cell12);
		document.add(img);
		document.add(table);
		document.close();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sie haben eine PDF Datei im Ordner Downloads erstellt.", null));
	}

	public void createPDFEmployee() throws DocumentException, IOException {
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
		Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);

		Employee employee = employeePrint;
		if (employee == null) {
			System.err.println("KANN NICHT SIENNIENIEINEI");
			return;
		}
		String DEST = "src/main/webapp/resources/Downloads/Stammblatt" + employee.getFirstName()
				+ employee.getLastName() + ".pdf";
		File file = new File(DEST);
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		String imgString = employee.getImgName();
		System.err.println(imgString);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		if (imgString == null || imgString.compareTo("emptypicture.png") == 0 || imgString.compareTo("") == 0) {
			System.err.println("HEJHEJAHEY");
			imgString = "empty_profile_pdf.png";
		}
		Image img = Image.getInstance("src/main/webapp/resources/pictures/profile_pictures/" + imgString);
		img.setAlignment(Image.ALIGN_RIGHT);
		img.scaleAbsolute(200f, 200f);
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		Paragraph employeeFirstname = new Paragraph(employee.getFirstName(), redFont);
		PdfPCell cell = new PdfPCell(employeeFirstname);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Vorname", catFont));
		table.addCell(cell);
		Paragraph employeeSurname = new Paragraph(employee.getLastName(), redFont);
		PdfPCell cell2 = new PdfPCell(employeeSurname);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Familienname", catFont));
		table.addCell(cell2);
		Paragraph employeeBirthday = new Paragraph(employee.getBirthday(), redFont);
		PdfPCell cell3 = new PdfPCell(employeeBirthday);
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Geburtsdatum", catFont));
		table.addCell(cell3);
		Paragraph employeeLocation = new Paragraph(employee.getLocation(), redFont);
		PdfPCell cell4 = new PdfPCell(employeeLocation);
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Wohnort", catFont));
		table.addCell(cell4);
		Paragraph employeeStreet = new Paragraph(employee.getStreetName(), redFont);
		PdfPCell cell5 = new PdfPCell(employeeStreet);
		cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Straße", catFont));
		table.addCell(cell5);
		Paragraph employeepostcode = new Paragraph(employee.getLocation(), redFont);
		PdfPCell cell13 = new PdfPCell(employeepostcode);
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Postleitzahl", catFont));
		table.addCell(cell13);
		Paragraph employeeNumber = new Paragraph(employee.getPhoneNumber(), redFont);
		PdfPCell cell7 = new PdfPCell(employeeNumber);
		cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Telefonnummer", catFont));
		table.addCell(cell7);
		Paragraph employeeEmail = new Paragraph(employee.getEmail(), redFont);
		PdfPCell cell11 = new PdfPCell(employeeEmail);
		cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Emailadresse", catFont));
		table.addCell(cell11);
		Paragraph employeeReligion;
		if(employee.getReligion() == null)
			employeeReligion = new Paragraph("k.A.", redFont);
		else
			employeeReligion = new Paragraph(employee.getReligion().toString(), redFont);
		PdfPCell cell9 = new PdfPCell(employeeReligion);
		cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Religionsbekenntnis", catFont));
		table.addCell(cell9);
		Paragraph employeeWorkingstate;
		if(employee.getWorkRole() == null)
			employeeWorkingstate = new Paragraph("k.A.", redFont);
		else
			employeeWorkingstate = new Paragraph(employee.getWorkRole().toString(), redFont);
		PdfPCell cell10 = new PdfPCell(employeeWorkingstate);
		cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Arbeitsstatus", catFont));
		table.addCell(cell10);
		document.add(img);
		document.add(table);
		document.close();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sie haben eine PDF Datei im Ordner Downloads erstellt.", null));
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
}