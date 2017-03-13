package com.github.melpis.service;

import com.github.melpis.domain.User;
import com.github.melpis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByusername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Id가 일치하는 사용자가 없습니다");
        }

        return new UserDetailsImpl(user);
    }
}
