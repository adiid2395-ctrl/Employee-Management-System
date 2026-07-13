package com.empmanagement.config;

import com.empmanagement.model.User;
import com.empmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            initDefaultUsers();
        };
    }

    private void initDefaultUsers() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFullName("System Administrator");
            admin.setEmail("admin@empmanagement.com");
            admin.getRoles().add(User.Role.ADMIN);
            admin.getRoles().add(User.Role.MANAGER);
            userRepository.save(admin);
        }

        if (!userRepository.existsByUsername("manager")) {
            User manager = new User();
            manager.setUsername("manager");
            manager.setPassword(passwordEncoder.encode("manager"));
            manager.setFullName("Department Manager");
            manager.setEmail("manager@empmanagement.com");
            manager.getRoles().add(User.Role.MANAGER);
            userRepository.save(manager);
        }

        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setFullName("Regular User");
            user.setEmail("user@empmanagement.com");
            user.getRoles().add(User.Role.USER);
            userRepository.save(user);
        }
    }
}
