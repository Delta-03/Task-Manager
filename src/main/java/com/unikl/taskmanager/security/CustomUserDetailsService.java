package com.unikl.taskmanager.security;

import com.unikl.taskmanager.entity.UserTable;
import com.unikl.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Find the user in our custom database table
        UserTable user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found in database.");
        }

        // 2. Translate our database user into a Spring Security user
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().name()) // Passes your ROLE_USER authority
                .build();
    }
}