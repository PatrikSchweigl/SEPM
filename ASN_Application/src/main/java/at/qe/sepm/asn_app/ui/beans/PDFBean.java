package at.qe.sepm.asn_app.ui.beans;


import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PDFBean {
    public static final String DEST = "results/objects/chapter_title.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PDFBean().createPdf(DEST);
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        Chunk chunk = new Chunk("This is the title", chapterFont);
        Chapter chapter = new Chapter(new Paragraph(chunk), 1);
        chapter.setNumberDepth(0);
        chapter.add(new Paragraph("This is the pdsfsdfdsfsdfsdfsdfsfwsdfaragraph", paragraphFont));
        document.add(chapter);
        document.close();
    }
}