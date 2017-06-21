package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 06.05.2017
 */

@Component
@Scope("view")
public class TaskController {

    //Controller to provide tasks and create tasks

    @Autowired
    private TaskService taskService;

    public Collection<Task> getNonImportantTasks() {
        Collection<Task> nonImpTasks = taskService.getTasksByImportance(false);
        return nonImpTasks;
    }


    @PostConstruct
    public void initList() {
        setTasks(taskService.getAllTasks());
    }

    public void setTasks(Collection<Task> tasks) {
    }

}
