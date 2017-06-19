package at.qe.sepm.c4f_app.services;
import at.qe.sepm.c4f_app.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public class AssignmentService {
    @Autowired AssignmentRepository assignmentRepository;
}
