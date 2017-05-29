package at.qe.sepm.asn_app.services;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.repositories.LunchRepository;
import at.qe.sepm.asn_app.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */

@Component
@Scope("view")
public class LunchService {
    @Autowired LunchRepository lunchRepository;

    public List<Lunch> getLunchByDate(String date){
        return lunchRepository.getLunchByDate(date);
    }

    public List<Lunch> findAll(){
        return lunchRepository.findAll();
    }

    public Lunch saveLunch(Lunch lunch) {
        return lunchRepository.save(lunch);
    }

}
