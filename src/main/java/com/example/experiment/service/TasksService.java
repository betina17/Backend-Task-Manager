package com.example.experiment.service;

import com.example.experiment.exceptions.TaskValidatorException;
import com.example.experiment.model.Task;
import com.example.experiment.model.TaskDTO;
import com.example.experiment.model.User;
import com.example.experiment.repository.ITasksRepository;
import com.example.experiment.repository.IUsersRepository;
import com.example.experiment.validation.TaskValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TasksService  implements ITasksService{

    @Autowired
    private ITasksRepository tasksRepository;

    @Autowired
    private IUsersRepository usersRepository;

    @Override
    public Task addTask(TaskDTO task) throws TaskValidatorException {
        User user = usersRepository.findByEmail(task.getUserEmail());
        TaskValidation.validate(task, user);
        Task newTask = new Task();
        newTask.setCategory(task.getCategory());
        newTask.setDescription(task.getDescription());
        newTask.setPriorityLevel(task.getPriorityLevel());
        newTask.setApproximateDuration(task.getApproximateDuration());
        newTask.setUser(user);
        return tasksRepository.save(newTask);
    }

    @Override
    public Page<Task> getAllTasks(int pageNumber, int pageSize, boolean sorted)
    {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Task> result;
        if(!sorted)
        {
            result = tasksRepository.findAll(pageRequest);
        }
        else
        {
            result = tasksRepository.findByOrderByPriorityLevelAsc(pageRequest);
        }
        return result;

    }

    @Override
    public Task getTaskById(int taskId) {
        return tasksRepository.findById(taskId).get();
    }

    @Override
    public Task updateTask(int taskId, TaskDTO task) throws TaskValidatorException{
        User user = usersRepository.findByEmail(task.getUserEmail());

        TaskValidation.validate(task, user);
        Optional<Task> taskToBeUpdated = tasksRepository.findById(taskId); //use Optional because obj might not be present in db
        Task updatedTask=taskToBeUpdated.get();
        updatedTask.setCategory(task.getCategory());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setPriorityLevel(task.getPriorityLevel());
        updatedTask.setApproximateDuration(task.getApproximateDuration());
        updatedTask.setUser(user);
        tasksRepository.save(updatedTask);
        return updatedTask;
    }

    @Override
    public void deleteTask(int taskId) {
        tasksRepository.deleteById(taskId);
    }
}
