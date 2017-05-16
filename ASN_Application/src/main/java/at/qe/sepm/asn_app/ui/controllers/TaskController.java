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

    private Task task;
    private Collection<Task> tasks;

    public Collection<Task> getTasks(){
        return tasks;
    }

    public Collection<Task> getTasksBySender(String id){
        return taskService.getAllTasksBySender(id);
    }
    public Collection<Task> getTasksByReceiver(String id){
        return taskService.getAllTasksByReceiver(id);
    }

    @PostConstruct
    public void initNewTask(){
        task = new Task();
    }

    @PostConstruct
    public void initList(){
        setTasks(taskService.getAllTasks());
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
        doReloadTask();
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    public void doSaveTask(){
        taskService.saveTask(task);
        task = null;
        initNewTask();
        initList();
    }

    public void doDeleteTask(){
        this.taskService.deleteTask(taskEdit);
    }

    public void doReloadTask(){
        task = taskService.loadTask(task.getId());
    }

    private Task taskEdit;

    public Task getTaskEdit() {
        return taskEdit;
    }

    public void setTaskEdit(Task task) {
        this.taskEdit = task;
        doReloadTaskEdit();
    }

    public void doSaveTaskEdit(Task task) {
        taskService.saveTask(task);
        initList();
    }

    public void doReloadTaskEdit(){
        taskEdit = taskService.loadTask(taskEdit.getId());
    }

}
