package com.example.experiment.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int taskId;
    private String category;
    private String description;
    private int priorityLevel;
    private int approximateDuration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
