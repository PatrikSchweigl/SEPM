package at.qe.sepm.asn_app.utils;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom scope implementation for spring to enable JSF2-like view-scoped beans.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
public class ViewScope implements Scope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object bean;

        try {
            // retrieve the view map of the faces context
            Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

            // get the bean by its name from the view map
            bean = viewMap.get(name);
            if (bean == null) {
                // if the bean has not been initialized, do so now and add it to the view map
                bean = objectFactory.getObject();
                viewMap.put(name, bean);
            }
        }
        //If the Viewmap could not be loaded, create the bean anyways (e.g. when testing)
        catch(Exception ex)
        {
            //System.out.println("YOU SHOULD SEE THIS MESSAGE ONLY WHEN TESTING");

            Map<String, Object> viewMap = new HashMap<String, Object>();

            // get the bean by its name from the view map
            bean=null;// = viewMap.get(name);
            if (bean == null) {
                // if the bean has not been initialized, do so now and add it to the view map
                bean = objectFactory.getObject();
                viewMap.put(name, bean);
            }
        }
        return bean;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // nothing to do
    }

    @Override
    public Object remove(String name) {
        // retrieve the view map of the faces context
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        // remove the object from the view map
        return viewMap.remove(name);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

}
