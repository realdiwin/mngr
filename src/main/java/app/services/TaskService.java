package app.services;

import app.dao.TaskDAO;
import app.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class TaskService implements TaskServiceI {
    @Autowired
    private TaskDAO taskDAO;

    @Override
    public Collection<Task> findAllByUserId(int userId) {
        return taskDAO.findAllByUserId(userId);
    }

    @Override
    public Collection<Task> findAll(int projectId) {
        return taskDAO.findAll(projectId);
    }

    @Override
    public Task findById(int taskId) {
        return taskDAO.findById(taskId);
    }

    @Override
    public Task addTask(Task task) {
        return taskDAO.addTask(task);
    }

    @Override
    public Task updateTask(int taskId, Task task) {
        return taskDAO.updateTask(taskId, task);
    }

    @Override
    public Task deleteTask(int taskId) {
        return taskDAO.deleteTask(taskId);
    }

    @Override
    public int findInPlanTasks(int userId) {
        Collection<Task> tasks = findAllByUserId(userId);
        return (int) tasks.stream()
                .filter(task -> {
                   return task.getStatus() == 0;
                }).count();
    }

    @Override
    public int findInProgressTasks(int userId) {
        Collection<Task> tasks = findAllByUserId(userId);
        return (int) tasks.stream()
                .filter(task -> {
                    return task.getStatus() == 1;
                }).count();
    }

    @Override
    public int findDoneTasks(int userId) {
        Collection<Task> tasks = findAllByUserId(userId);
        return (int) tasks.stream()
                .filter(task -> {
                    return task.getStatus() == 2;
                }).count();
    }
}
