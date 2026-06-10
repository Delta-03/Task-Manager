package com.unikl.taskmanager.controller;

import com.unikl.taskmanager.entity.TaskTable;
import com.unikl.taskmanager.entity.UserTable;
import com.unikl.taskmanager.repository.TaskRepository;
import com.unikl.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired private TaskRepository taskRepository;
    @Autowired private UserRepository userRepository;

    @GetMapping
    public String viewTasks(Model model, Principal principal) {
        UserTable currentUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("tasks", taskRepository.findByOwner(currentUser));
        model.addAttribute("username", currentUser.getUsername());
        return "tasks";
    }

    @PostMapping("/create")
    public String createTask(@RequestParam String title, @RequestParam String description, Principal principal) {
        UserTable currentUser = userRepository.findByUsername(principal.getName());
        TaskTable task = new TaskTable();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus("PENDING");
        task.setOwner(currentUser);
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/view/{id}")
    @ResponseBody
    public TaskTable getTaskSecurely(@PathVariable Long id, Principal principal) {
        TaskTable task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource entry missing."));

        // CRITICAL BOLA/IDOR MITIGATION CHECK (OWASP ASVS V4)
        // Checks resource ownership context before executing delivery
        if (!task.getOwner().getUsername().equals(principal.getName())) {
            throw new SecurityException("Access Denied: Object-level authentication check verification failed.");
        }
        return task;
    }
}