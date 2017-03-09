package com.github.melpis.domain;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name = "BOARD_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

}
