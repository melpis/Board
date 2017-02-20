package com.github.melpis.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_ID")
    private Long seq;

    private String title;

    private String writer;

    private int hits;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String content;

    public Board() {
        hits = 0;
    }

    // getter setter

    public Long getSeq() {
        return seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // increase hit
    public void increHits() {
        hits += 1;
    }
}
