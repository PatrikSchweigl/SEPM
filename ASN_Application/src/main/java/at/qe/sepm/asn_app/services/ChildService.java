package at.qe.sepm.asn_app.services;
import at.qe.sepm.asn_app.repositories.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public class ChildService {
    @Autowired ChildRepository childRepository;
}
