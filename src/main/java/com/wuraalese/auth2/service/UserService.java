package com.wuraalese.auth2.service;

import com.wuraalese.auth2.models.CustomUserDetails;
import com.wuraalese.auth2.models.User;
import com.wuraalese.auth2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired // we can also use constructor injection
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByName( name);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        CustomUserDetails userDetails = optionalUser
                .map(user -> {
                    return new CustomUserDetails(user);
                }).get();
        return userDetails;
    }
}
