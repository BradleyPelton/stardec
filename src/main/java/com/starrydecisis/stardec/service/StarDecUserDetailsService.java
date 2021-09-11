package com.starrydecisis.stardec.service;

import com.starrydecisis.stardec.model.securitymodels.StarDecUser;
import com.starrydecisis.stardec.model.securitymodels.StarDecUserDetails;
import com.starrydecisis.stardec.repository.StarDecUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StarDecUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(StarDecUserDetailsService.class);

    @Autowired
    StarDecUserRepository starDecUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("StarDecUserDetailsService loadUserByUsername with username = " + username);
        Optional<StarDecUser> user = starDecUserRepository.findByUserName(username);

        if (!user.isPresent()) {
            logger.info("StarDecUserDetailsService Unable to find username = " + username);
            throw new UsernameNotFoundException("StarDecUserDetailsService Unable to find username = " + username);
        } else {
            logger.info("StarDecUserDetailsService User found, returning calling Constructor");

            return new StarDecUserDetails(user.get());
        }
    }
}
