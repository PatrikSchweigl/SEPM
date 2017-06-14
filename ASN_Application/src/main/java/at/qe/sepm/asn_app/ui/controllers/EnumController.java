package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.employee.WorkRole;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 24.03.2017
 */

@Component
//@Scope("view")
@Scope("request")
public class EnumController {

    public Map<String,String> getFamilyStatus(){
        Map<String,String> familystatus = new HashMap<String,String>();

        for(FamilyStatus fm: FamilyStatus.values()){
            familystatus.put(fm.name(),fm.name());
        }

        return familystatus;
    }

    public Map<String,String> getReligions(){
        Map<String,String> religions =  new HashMap<String, String>();

        for(Religion r : Religion.values()){
            religions.put(r.name(),r.name());
        }
        return religions;
    }

    public Map<String,String> getWorkRoles(){
        Map<String,String> roles =  new HashMap<String, String>();

        for(WorkRole r: WorkRole.values()){
            roles.put(r.name(),r.name());
        }
        return roles;
    }

    public Map<String,String> getGender(){
        Map<String,String> gender = new HashMap<String, String>();

        for(Gender g: Gender.values()){
            gender.put(g.name(),g.name());
        }

        return gender;

    }
}
