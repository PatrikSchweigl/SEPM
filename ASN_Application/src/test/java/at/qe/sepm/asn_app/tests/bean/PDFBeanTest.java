package at.qe.sepm.asn_app.tests.bean;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.tests.controller.ContextMocker;
import at.qe.sepm.asn_app.tests.initialize.InitializeCaregiver;
import at.qe.sepm.asn_app.tests.initialize.InitializeChild;
import at.qe.sepm.asn_app.tests.initialize.InitializeEmployee;
import at.qe.sepm.asn_app.tests.initialize.InitializeParent;
import at.qe.sepm.asn_app.ui.beans.PDFBean;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.context.RequestContext;

import javax.faces.context.FacesContext;
import java.io.IOException;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 22.06.17 15:36 CEST.
 */
public class PDFBeanTest {

    private PDFBean pdfBean;
    private Caregiver caregiver;
    private Child child;
    private Employee employee;
    private Parent parent;


    @Before
    public void initialize() {
        pdfBean = new PDFBean();
        caregiver = InitializeCaregiver.initialize1();
        child = InitializeChild.initialize();
        employee = InitializeEmployee.initialize1();
        parent = InitializeParent.initialize1();
    }


    @Test
    public void testValid() throws IOException, DocumentException {
        FacesContext context = ContextMocker.mockFacesContext();
        RequestContext requestContext = ContextMocker.mockRequestContext();

        try {
            pdfBean.setCaregiverPrint(caregiver);
            pdfBean.setChildPrint(child);
            pdfBean.setEmployeePrint(employee);
            pdfBean.setParentPrint(parent);

            pdfBean.createPDFCaregiver();
            pdfBean.createPDFChild();
            pdfBean.createPDFEmployee();
            pdfBean.createPDFParent();
            pdfBean.getCaregiverPrint();
            pdfBean.getChildPrint();
            pdfBean.getEmployeePrint();
            pdfBean.getParentPrint();
        }
        finally {
            context.release();
            requestContext.release();
        }
    }


    @Test
    public void testInvalid() throws IOException, DocumentException {
        FacesContext context = ContextMocker.mockFacesContext();
        RequestContext requestContext = ContextMocker.mockRequestContext();

        try {
            pdfBean.createPDFCaregiver();
            pdfBean.createPDFChild();
            pdfBean.createPDFEmployee();
            pdfBean.createPDFParent();
            pdfBean.getCaregiverPrint();
            pdfBean.getChildPrint();
            pdfBean.getEmployeePrint();
            pdfBean.getParentPrint();
        }
        finally {
            context.release();
            requestContext.release();
        }
    }


    @After
    public void cleanUp() {
        pdfBean = null;
        caregiver = null;
        child = null;
        employee = null;
        parent = null;
    }
}