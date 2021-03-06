package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.services.NurseryInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 30.05.2017
 */
@Component
@Scope("application")
public class NurseryConstraints {
    @Autowired
    private NurseryInformationService nurseryInformationService;

    public  boolean nurseryInfoExists(NurseryInformation nurseryInformation){
        Collection<NurseryInformation> list = nurseryInformationService.getAllInformation();    //throws null pointer exception
        Iterator<NurseryInformation> iterator = list.iterator();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nurseryInformation.getOriginDate());
        cal.add(Calendar.HOUR_OF_DAY, 2);
        while(iterator.hasNext()) {
        	Date date = iterator.next().getOriginDate();
        	System.out.println(date + "  vergleich mit  " + cal.getTime());
            if (date.compareTo(cal.getTime()) == 0) {
                return true;
            }
        }

        return false;
    }
    
    public boolean checkTimeConstraints(NurseryInformation nurseryInformation){
    	if(nurseryInformation.getBringStart().compareTo(nurseryInformation.getBringEnd()) >= 0)
    		return false;
    	if(nurseryInformation.getPickUpStart().compareTo(nurseryInformation.getPickUpEnd()) >= 0)
    		return false;
    	if(nurseryInformation.getBringEnd().compareTo(nurseryInformation.getPickUpStart()) >= 0)
    		return false;
    	
    	return true;
    	
    }

}
