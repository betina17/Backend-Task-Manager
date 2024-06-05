package com.example.experiment.controller;

import com.example.experiment.exceptions.TaskValidatorException;
import com.example.experiment.model.Task;
import com.example.experiment.model.TaskDTO;
import com.example.experiment.service.ITasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TasksController {
    @Autowired
    private ITasksService tasksService;

    @PostMapping("/add")
    public String addTask(@RequestBody TaskDTO task){
        try {

            tasksService.addTask(task);
            System.out.println("Received task: " + task);
            return "Task added";
        }
        catch (TaskValidatorException e){
            return e.getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTasks(@RequestParam(defaultValue = "0") int pageNumber,
                                         @RequestParam(defaultValue = "3") int pageSize,
                                         @RequestParam(defaultValue = "false") Boolean sort){
        try{
            Page<Task> paginatedTasks = tasksService.getAllTasks(pageNumber, pageSize, sort);
            return ResponseEntity.ok(paginatedTasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not get tasks: " + e.getMessage());
        }
    }

    @GetMapping("/get/{taskId}")
    public Task getTaskById(@PathVariable int taskId){
        return tasksService.getTaskById(taskId);
    }

    @PutMapping("/update/{taskId}")
    public String updateTask(@PathVariable int taskId, @RequestBody TaskDTO task){
        try{
        tasksService.updateTask(taskId, task);
        return "Task updated";}
        catch (TaskValidatorException e){
            return e.getMessage();
        }
    }


    @DeleteMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId){
        tasksService.deleteTask(taskId);
        return "Task deleted";
    }
}
