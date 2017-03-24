package at.qe.sepm.asn_app.ui.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 24.03.2017
 */
@Component
@Scope("view")
public class EnumController {

    public Map<String,String> getFamilyStatus(){
        Map<String,String> map = new HashMap<String,String>();

        map.put("MARRIED","MARRIED");
        map.put("NOT_MARRIED","NOT_MARRIED");
        map.put("DIVORCED","DIVORCED");
        map.put("WIDOWED","WIDOWED");
        map.put("REGISTERED_PARTNERSHIP","REGISTERED_PARTNERSHIP");

        return map;
    }

    public Map<String,String> getReligions(){
        Map<String,String> map =  new HashMap<String, String>();

        map.put("ISLAM", "ISLAM");
        map.put("CHRISTIANITY","CHRISTIANITY");
        map.put("JUDAISM","JUDAISM");
        map.put("BUDDHISM","BUDDHISM");
        map.put("HINDUISM","HINDUISM");
        map.put("ATHEISM","ATHEISM");
        return map;
    }
}
