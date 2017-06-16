package at.qe.sepm.asn_app.ui.beans;

import java.io.FileOutputStream;
import java.io.IOException;

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
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.ui.controllers.ChildController;

@Component
@Scope("request")
public class PDFBean {

	@Autowired
	private ChildController childController;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);

	public void createPDFChild() throws DocumentException, IOException {

		Child child = childController.getChild();
		Document document = new Document();
		String home = System.getProperty("user.home");
		final String DEST = home + "/Downloads/Stammblatt" + child.getFullname() + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(DEST));
		document.open();
		String imgString = child.getImgName();
		if(imgString == null || imgString == "")
			imgString = "emptypicture.png";
		else{
			String[] tmp = imgString.split(".");
			imgString = tmp[0];
		}
		Image img = Image.getInstance("src/main/webapp/resources/pictures/profile_pictures/" + imgString +".png");
		img.setAlignment(Image.ALIGN_RIGHT);
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		Paragraph childFirstname = new Paragraph(child.getFirstName(), redFont);
		Paragraph childSurname = new Paragraph(child.getLastName(), redFont);
		Paragraph childBirthday = new Paragraph(child.getBirthday(), redFont);
		Paragraph childGender = new Paragraph(child.getGender().toString(), redFont);
		Paragraph childParent1 = new Paragraph(child.getPrimaryParentFullName(), redFont);
		Paragraph childParent2 = new Paragraph(
				child.getParent2().getFirstName() + " " + child.getParent2().getLastName(), redFont);
		Paragraph childEmergencyNumber = new Paragraph(child.getEmergencyNumber(), redFont);
		Paragraph childReligion = new Paragraph(child.getReligion().toString(), redFont);
		Paragraph childIntolerances = new Paragraph(child.getFoodIntolerances(), redFont);
		Paragraph childAllergies = new Paragraph(child.getAllergies(), redFont);

		PdfPCell cell = new PdfPCell(childFirstname);
		//cell.setBorder(0);
		// cell.setFixedHeight(40f);
		//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Vorname", catFont));
		table.addCell(cell);
		PdfPCell cell2 = new PdfPCell(childSurname);
		//cell2.setBorder(0);
		// cell2.setFixedHeight(40f);
		//cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Familienname", catFont));
		table.addCell(cell2);
		PdfPCell cell3 = new PdfPCell(childBirthday);
		//cell3.setBorder(0);
		// cell3.setFixedHeight(40f);
		//cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Geburtsdatum", catFont));
		table.addCell(cell3);
		PdfPCell cell4 = new PdfPCell(childGender);
		//cell4.setBorder(0);
		// cell4.setFixedHeight(40f);
		//cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Geschlecht", catFont));
		table.addCell(cell4);
		PdfPCell cell9 = new PdfPCell(childReligion);
		//cell9.setBorder(0);
		// cell9.setFixedHeight(40f);
		//cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Religionsbekenntnis", catFont));
		table.addCell(cell9);
		PdfPCell cell5 = new PdfPCell(childParent1);
		//cell5.setBorder(0);
		// cell5.setFixedHeight(40f);
		//cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Primärer Elternteil", catFont));
		table.addCell(cell5);
		PdfPCell cell6 = new PdfPCell(childParent2);
		//cell6.setBorder(0);
		// cell6.setFixedHeight(40f);
		//cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Elternteil", catFont));
		table.addCell(cell6);
		PdfPCell cell7 = new PdfPCell(childEmergencyNumber);
		//cell7.setBorder(0);
		// cell7.setFixedHeight(40f);
		//cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Notfallkontakt", catFont));
		table.addCell(cell7);
		for (Sibling s : child.getSiblings()) {
			Paragraph childSiblings = new Paragraph(s.getFirstName() +  " " + s.getLastName(), redFont);

			PdfPCell cell8 = new PdfPCell(childSiblings);
			//cell8.setBorder(0);
			// cell8.setFixedHeight(40f);
			//cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(new Paragraph("Geschwister", catFont));
			table.addCell(cell8);
		}
		for (Caregiver c : child.getCaregivers()) {
			Paragraph childCaregivers = new Paragraph(c.getFullName(), redFont);
			PdfPCell cell10 = new PdfPCell(childCaregivers);
			//cell10.setBorder(0);
			// cell10.setFixedHeight(40f);
			//cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(new Paragraph("Bezugspersonen", catFont));
			table.addCell(cell10);
		}
		PdfPCell cell11 = new PdfPCell(childIntolerances);
		//cell11.setBorder(0);
		// cell11.setFixedHeight(40f);
		//cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Unverträglichkeiten", catFont));
		table.addCell(cell11);
		PdfPCell cell12 = new PdfPCell(childAllergies);
		//cell12.setBorder(0);
		// cell12.setFixedHeight(40f);
		//cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Paragraph("Allergien", catFont));
		table.addCell(cell12);
		document.add(img);
		document.add(table);
		document.close();
	}
}