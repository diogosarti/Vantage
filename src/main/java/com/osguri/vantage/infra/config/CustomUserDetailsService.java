package com.osguri.vantage.infra.config;

import com.osguri.vantage.entities.User;
import com.osguri.vantage.entities.CustomUserDetails;
import com.osguri.vantage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userRepository.findFirstByUsername(username);
        if(user != null) {
            User authUser = new User(
                    null,
                    user.getName(),
                    user.getUsername(),
                    user.getPassword()
            );
            return new CustomUserDetails(authUser);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
