package com.github.melpis.domain;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @Column(name = "USER_ID")
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;


    public User() {

    }

    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

}
