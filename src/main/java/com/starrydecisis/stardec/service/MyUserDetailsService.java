package com.starrydecisis.stardec.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // User is a userDetails object in spring security
        return new User("foo",
                "bar",
                new ArrayList<>()
        ); // TODO - REFACTOR HERE TO USE DATABASE
    }
}
