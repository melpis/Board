package com.github.melpis.service;

import com.github.melpis.domain.User;
import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User{
    private User user;

    public LoginUserDetails(User user) {
        super(user.getId(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));

        this.user = user;
    }
}
