package com.unikl.taskmanager.repository;

import com.unikl.taskmanager.entity.TaskTable;
import com.unikl.taskmanager.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskTable, Long> {
    List<TaskTable> findByOwner(UserTable owner);
}