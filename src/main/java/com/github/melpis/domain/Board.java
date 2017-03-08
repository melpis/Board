package com.github.melpis.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int hits;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Board() {
        hits = 0;
    }

    public void increaseHits() {
        hits += 1;
    }

    public void addComment(Comment comment){
        comments.add(comment);

        if (comment.getBoard() != this) {
            comment.setBoard(this);
        }
    }

}
