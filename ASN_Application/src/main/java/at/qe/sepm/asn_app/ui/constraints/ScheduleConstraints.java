package at.qe.sepm.asn_app.ui.constraints;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.services.TaskService;

@Component
@Scope("application")
public class ScheduleConstraints {

    @Autowired
    private TaskService taskService;

    public boolean checkIfTaskExistsForParent(Task t) {
        Collection<Task> tasks = taskService.getAllTasksByReceiver(t.getReceiver().getUsername());
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getReceiver().getUsername().compareTo(t.getReceiver().getUsername()) == 0)
                if (t.getBeginDate().after(task.getBeginDate()) && t.getBeginDate().before(task.getBeginDate())
                        || t.getEndingDate().after(task.getBeginDate())
                        && t.getEndingDate().before(task.getEndingDate()))
                    return true;
        }
        return false;
    }
}
