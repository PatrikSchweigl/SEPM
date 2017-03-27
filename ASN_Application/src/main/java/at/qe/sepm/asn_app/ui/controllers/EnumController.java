package at.qe.sepm.asn_app.ui.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stefa on 24.03.2017.
 */

@Component
@Scope("view")
public class EnumController {

    public Map<String,String> getFamilyStatus(){
        Map<String,String> familystatus = new HashMap<String,String>();

        familystatus.put("MARRIED","MARRIED");
        familystatus.put("NOT_MARRIED","NOT_MARRIED");
        familystatus.put("DIVORCED","DIVORCED");
        familystatus.put("WIDOWED","WIDOWED");
        familystatus.put("REGISTERED_PARTNERSHIP","REGISTERED_PARTNERSHIP");

        return familystatus;
    }

    public Map<String,String> getReligions(){
        Map<String,String> religions =  new HashMap<String, String>();

        religions.put("ISLAM", "ISLAM");
        religions.put("CHRISTIANITY","CHRISTIANITY");
        religions.put("JUDAISM","JUDAISM");
        religions.put("BUDDHISM","BUDDHISM");
        religions.put("HINDUISM","HINDUISM");
        religions.put("ATHEISM","ATHEISM");
        return religions;
    }

    public Map<String,String> getWorkRoles(){
        Map<String,String> roles =  new HashMap<String, String>();

        roles.put("PEDAGOGUE", "PEDAGOGUE");
        roles.put("TRAINEE","TRAINEE");
        return roles;
    }
}
