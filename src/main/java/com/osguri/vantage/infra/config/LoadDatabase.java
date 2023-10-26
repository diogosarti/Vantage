package com.osguri.vantage.infra.config;

import com.osguri.vantage.entities.Role;
import com.osguri.vantage.entities.User;
import com.osguri.vantage.repositories.RoleRepository;
import com.osguri.vantage.repositories.UserRepository;
import com.osguri.vantage.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadDatabase implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ADMIN") == null) {
            roleRepository.save(new Role(null, "ADMIN", null));

        }
        if (roleRepository.findByName("USER") == null) {
            roleRepository.save(new Role(null, "USER", null));
        }

        User user = new User("user", "user@user.com", encoder.encode("user123"));
        user.getRoles().add(roleRepository.findByName("USER"));
        User admin = new User("admin", "admin@admin.com", encoder.encode("admin123"));
        admin.getRoles().add(roleRepository.findByName("ADMIN"));
        userRepository.saveAll(Arrays.asList(user, admin));
    }
}
