package com.example.experiment.service;

import com.example.experiment.exceptions.TaskValidatorException;
import com.example.experiment.model.Task;
import com.example.experiment.model.TaskDTO;
import org.springframework.data.domain.Page;

public interface ITasksService{
    public Task addTask(TaskDTO task) throws TaskValidatorException;
    public Page<Task> getAllTasks(int pageNumber, int pageSize, boolean sorted);
    public Task getTaskById(int taskId);
    public Task updateTask(int taskId, TaskDTO task) throws TaskValidatorException;
    public void deleteTask(int taskId);
}
