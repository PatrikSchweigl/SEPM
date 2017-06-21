package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.repositories.SiblingRepository;
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
public class SiblingService {
    @Autowired
    private SiblingRepository siblingRepository;

    public Collection<Sibling> getAllSiblings() {
        return siblingRepository.findAll();
    }


    public Sibling saveSibling(Sibling sibling) {
        return siblingRepository.save(sibling);
    }


    public void deleteSibling(Sibling sibling) {
        siblingRepository.delete(sibling);
    }


    public Sibling loadSibling(Long id) {
        return siblingRepository.findOne(id);
    }
}
