package at.qe.sepm.c4f_app.ui.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

import at.qe.sepm.c4f_app.models.child.Child;
import at.qe.sepm.c4f_app.models.child.Sibling;
import at.qe.sepm.c4f_app.models.referencePerson.Caregiver;

@Component
@Scope("view")
public class PDFBean {

	private Child childPrint;

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
		String DEST = "src/main/webapp/resources/Downloads/Stammblatt" + child.getFirstName() + child.getLastName() + ".pdf";
		File file = new File(DEST);
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		String imgString = child.getImgName();
		System.err.println(imgString);
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
		if (imgString == null || imgString.compareTo("emptypicture.png") == 0 || imgString.compareTo("") == 0){
			System.err.println("HEJHEJAHEY");
			imgString = "empty_profile_pdf.png";
		}
		Image img =
		Image.getInstance("src/main/webapp/resources/pictures/profile_pictures_children/"
		+ imgString);
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
		if (child.getGender() != null) {
			Paragraph childGender = new Paragraph(child.getGender().toString(), redFont);
			PdfPCell cell4 = new PdfPCell(childGender);
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(new Paragraph("Geschlecht", catFont));
			table.addCell(cell4);
		}
		if (child.getReligion() != null) {
			Paragraph childReligion = new Paragraph(child.getReligion().toString(), redFont);
			PdfPCell cell9 = new PdfPCell(childReligion);
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(new Paragraph("Religionsbekenntnis", catFont));
			table.addCell(cell9);
		}
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
	}

	public Child getChildPrint() {
		return childPrint;
	}

	public void setChildPrint(Child childPrint) {
		this.childPrint = childPrint;
	}
}