package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.repositories.NurseryInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        return nurseryInformationRepository.save(nurseryInformation);
    }

    public NurseryInformation loadNurseryInformation(Long id){
        return nurseryInformationRepository.findOne(id);
    }

    public void deleteNurseryInformation(NurseryInformation nurseryInformation){
        nurseryInformationRepository.delete(nurseryInformation);
    }
}
