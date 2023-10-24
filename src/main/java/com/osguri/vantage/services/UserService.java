package com.osguri.vantage.services;

import com.osguri.vantage.entities.User;
import com.osguri.vantage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public Optional<User> finfById(Long id){
        return repository.findById(id);
    }

    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    public User save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
