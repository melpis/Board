package com.github.melpis.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String comment;

    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "COMMENT_ID")
    private Board board;

}
