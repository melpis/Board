package com.github.melpis.service;

import com.github.melpis.domain.User;
import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;

@Data
public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {

    public UserDetailsImpl(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
    }

}
