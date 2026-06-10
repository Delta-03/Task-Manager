package com.unikl.taskmanager.repository;

import com.unikl.taskmanager.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
    UserTable findByUsername(String username);
}