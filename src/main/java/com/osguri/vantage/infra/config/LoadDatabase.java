package com.osguri.vantage.infra.config;

import com.osguri.vantage.entities.Curso;
import com.osguri.vantage.entities.Role;
import com.osguri.vantage.entities.User;
import com.osguri.vantage.repositories.CursoRepository;
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

    @Autowired
    private CursoRepository cursoRepository;

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
        User diogo = new User("admin", "diogo@diogo.com", encoder.encode("diogo123"));
        diogo.getRoles().add(roleRepository.findByName("ADMIN"));
        userRepository.saveAll(Arrays.asList(user, admin, diogo));

        Curso curso1 = new Curso("Curso de java", "Meu curso de java", admin);
        Curso curso2 = new Curso("Curso de php KKKKKKKKKK", "Meu curso da bosta do PHP KAKAKAKKA", admin);
        Curso curso3 = new Curso("Curso de MYSQL", "Meu curso de sql", admin);
        Curso curso4 = new Curso("Curso do Dioguinho", "Meu curso de sql", diogo);
        cursoRepository.saveAll(Arrays.asList(curso1, curso2, curso3, curso4));
    }
}
