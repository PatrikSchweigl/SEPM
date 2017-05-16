package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.repositories.NurseryInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@Component
@Scope("application")
public class NurseryInformationService {
    @Autowired
    private NurseryInformationRepository nurseryInformationRepository;

    public Collection<NurseryInformation> getAllInformation(){
        return nurseryInformationRepository.findAll();
    }

    public NurseryInformation saveNurseryInformation(NurseryInformation nurseryInformation){
        nurseryInformation.setCurrentDate(new Date());
        Date date;

        date = calculateFromToTimes(nurseryInformation.getBringStart(), nurseryInformation.getOriginDate());
        nurseryInformation.setBringStart(date);
        date = calculateFromToTimes(nurseryInformation.getBringEnd(), nurseryInformation.getOriginDate());
        nurseryInformation.setBringEnd(date);
        date = calculateFromToTimes(nurseryInformation.getPickUpStart(), nurseryInformation.getOriginDate());
        nurseryInformation.setPickUpStart(date);
        date = calculateFromToTimes(nurseryInformation.getPickUpEnd(), nurseryInformation.getOriginDate());
        nurseryInformation.setPickUpEnd(date);

        return nurseryInformationRepository.save(nurseryInformation);
    }

    public NurseryInformation loadNurseryInformation(Long id){
        return nurseryInformationRepository.findOne(id);
    }

    public void deleteNurseryInformation(NurseryInformation nurseryInformation){
        nurseryInformationRepository.delete(nurseryInformation);
    }

    public Date calculateFromToTimes(Date timestamp, Date originTime){
        int hours, mins;
        long milliDate;
        Date date = new Date();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        cal.setTime(timestamp);
        hours = cal.get(Calendar.HOUR_OF_DAY);
        mins = cal.get(Calendar.MINUTE);

        hours = hours*3600*1000;
        mins = mins*60*1000;


        milliDate = originTime.getTime();
        milliDate = milliDate + hours + mins;
        date.setTime(milliDate);

        return date;
    }
}
