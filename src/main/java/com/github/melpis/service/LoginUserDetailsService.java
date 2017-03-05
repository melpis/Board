package com.github.melpis.service;

import com.github.melpis.domain.User;
import com.github.melpis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        return new LoginUserDetails(user);
    }
}
