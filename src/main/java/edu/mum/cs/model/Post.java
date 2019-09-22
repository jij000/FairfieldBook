package edu.mum.cs.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String content;

    private boolean isDisable;
    @ManyToOne
    private User author;
}
