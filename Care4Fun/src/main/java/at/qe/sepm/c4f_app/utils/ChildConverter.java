package at.qe.sepm.c4f_app.utils;

import at.qe.sepm.c4f_app.models.child.Child;
import at.qe.sepm.c4f_app.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 15.06.2017
 */
@Service
public class ChildConverter implements Converter{

    @Autowired
    private ChildService childService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.equals("")) {
            return null;
        }

        Child child = childService.loadChild(Long.parseLong(value));
        return child;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        if (obj instanceof Child) {
            return ((Child)obj).getId().toString();
        }
        else {
            return "";
        }
    }
}
