package com.github.melpis.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonIgnore
    private Board board;

    public void setBoard(Board board) {
        this.board = board;

        if (!board.getComments().contains(this)) {
            board.getComments().add(this);
        }
    }
}
