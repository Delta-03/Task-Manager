package com.unikl.taskmanager.controller;

import com.unikl.taskmanager.entity.UserTable;
import com.unikl.taskmanager.entity.RoleEnumeration;
import com.unikl.taskmanager.entity.AuditLog;
import com.unikl.taskmanager.repository.UserRepository;
import com.unikl.taskmanager.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private AuditLogRepository auditLogRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping("/register")
    public String registerPage() { return "register"; }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                               @RequestParam String email, 
                               @RequestParam String password,
                               HttpServletRequest request) {
        
        // Input Validation Rule: Prevent processing blank or weak variables (OWASP ASVS V5)
        if (username.isBlank() || password.length() < 8) {
            throw new IllegalArgumentException("Input validation failed: Weak credentials.");
        }

        UserTable user = new UserTable();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Safe storage conversion via BCrypt
        user.setRole(RoleEnumeration.ROLE_USER);
        userRepository.save(user);

        // Security Audit Logging Execution (OWASP ASVS V7)
        AuditLog log = new AuditLog();
        log.setUsername(username);
        log.setAction("REGISTRATION_SUCCESS");
        log.setIpAddress(request.getRemoteAddr());
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);

        return "redirect:/login";
    }
}