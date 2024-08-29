package com.sparta.round9.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;

    public Board(String title, String contents) {

        this.title = title;
        this.contents = contents;
    }

    public void updateTitle(String title) { this.title = title;
    }

    public void updateContents(String contents) { this.contents = contents;
    }
}

