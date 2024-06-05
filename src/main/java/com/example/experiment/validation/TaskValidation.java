package com.example.experiment.validation;

import com.example.experiment.exceptions.TaskValidatorException;
import com.example.experiment.model.TaskDTO;
import com.example.experiment.model.User;

import java.util.ArrayList;
import java.util.List;


public class TaskValidation {

    public static void validate(TaskDTO task, User user) throws TaskValidatorException{
        List<String> errors = new ArrayList<>();

        if(user == null)
        {
            errors.add("User does not exist");
        }

        if (task.getCategory().isEmpty())
            errors.add("The category field is empty!");

        if (task.getDescription().isEmpty())
            errors.add("The description field is empty!");

        if (task.getPriorityLevel() > 3 || task.getPriorityLevel() < 1)
            errors.add("The priority level must be between 1 and 3!");

        if (task.getApproximateDuration() < 0 || task.getApproximateDuration() > 300)
            errors.add("The approximate duration must be between 0 and 300 minutes=5 hours!");

        if (!errors.isEmpty())
            throw new TaskValidatorException(errors);

    }
}
