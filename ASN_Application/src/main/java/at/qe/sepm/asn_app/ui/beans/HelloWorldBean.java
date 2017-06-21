package at.qe.sepm.asn_app.ui.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Basic request scoped bean to test bean initialization.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("request")
public class HelloWorldBean {

}
