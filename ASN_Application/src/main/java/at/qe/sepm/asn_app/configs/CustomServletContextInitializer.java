package at.qe.sepm.asn_app.configs;


import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Spring configuration for servlet context.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */

public class CustomServletContextInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        sc.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        //sc.setInitParameter("primefaces.THEME", "afterdark");
    }

}
