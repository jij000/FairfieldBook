package edu.mum.cs.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PostUpdateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User follower;//who following the author
    @ManyToOne
    private Post post;//the author's article

}
