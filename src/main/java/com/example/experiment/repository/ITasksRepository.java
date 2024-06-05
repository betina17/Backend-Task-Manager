package com.example.experiment.repository;

import com.example.experiment.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITasksRepository extends JpaRepository<Task,Integer> {
    Page<Task> findByOrderByPriorityLevelAsc(Pageable pageable);
}
