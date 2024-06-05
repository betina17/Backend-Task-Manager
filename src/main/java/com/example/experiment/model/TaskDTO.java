package com.example.experiment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private String category;
    private String description;
    private int priorityLevel;
    private int approximateDuration;

    private String userEmail;
}
