package com.example.experiment.exceptions;

import java.util.List;

public class TaskValidatorException extends Exception {
    public TaskValidatorException(List<String> errors) {
        super(String.join(", ", errors));
    }
}
