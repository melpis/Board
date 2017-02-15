package com.github.melpis.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hongsung-won on 2017. 2. 15..
 */
@Entity
@Getter
@Setter
public class Board  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Date registDate;

    private Date updatedDate;

    private Long readCount;

    @PrePersist
    public void onPrePersist() {
        if (this.registDate == null) {
            this.registDate = new Date();
        }

        if (this.updatedDate == null) {
            this.updatedDate = this.updatedDate;
        }
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedDate = new Date();
    }
}
