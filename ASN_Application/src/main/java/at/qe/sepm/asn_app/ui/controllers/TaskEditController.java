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
public class TaskEditController {

    //Controller to handle task edit

    @Autowired
    private TaskService taskService;
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
        doReloadTask();
    }

    public void doSaveTask(Task task) {
        taskService.saveTask(task);
    }

    public void doDeleteTask(Task task){
        taskService.deleteTask(task);
    }

    public void doReloadTask(){
        task = taskService.loadTask(task.getId());
    }

}
