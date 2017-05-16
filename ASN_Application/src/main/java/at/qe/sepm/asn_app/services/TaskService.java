package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 06.05.2017
 */
@Component
@Scope("application")
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Collection<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTask(Task task){
        taskRepository.delete(task);
    }

    public Task loadTask(Long id){
        return taskRepository.findOne(id);
    }
    @Modifying
    public void deleteTaskById(String id){
    	taskRepository.DeleteTaskByStringId(id);
    }

    public Collection<Task> getAllTasksBySender(String id){
        return taskRepository.getTasksBySender(id);
    }

    public Collection<Task> getAllTasksByReceiver(String id){
        return taskRepository.getTasksByReceiver(id);
    }
    
    public Collection<Task> getAllTasksByReceiverAndImportance(String id){
        return taskRepository.getTasksByReceiverAndImportance(id);
    }
    
    public Task getTaskByStringId(String id){
        return taskRepository.getTaskByStringId(id);
    }

}
