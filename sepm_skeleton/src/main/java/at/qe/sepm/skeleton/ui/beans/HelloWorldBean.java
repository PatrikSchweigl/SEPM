package at.qe.sepm.skeleton.ui.beans;

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

    /**
     * Returns a hello-world-string.
     *
     * @return hello-world-string
     */
    public String getHello() {
        return "Hello from a JSF-managed bean!";
    }
}
