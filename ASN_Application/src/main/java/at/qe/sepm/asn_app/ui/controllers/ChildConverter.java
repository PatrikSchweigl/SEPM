package at.qe.sepm.asn_app.ui.controllers;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 12.06.2017
 */

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.ui.beans.SessionInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@FacesConverter("childConverter")
public class ChildConverter implements Converter {
    @Autowired
    private ChildService childService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.equals("")) {
            return null;
        }
        Child child = childService.loadChild(Long.parseLong(value));
        System.out.println(child.toString());
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