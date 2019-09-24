package edu.mum.cs.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User author;
    @Column(nullable = false)
    private String content;

    @Type(type="yes_no")
    @Column(nullable = false)
    private boolean isDisable;
}
