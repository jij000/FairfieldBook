package edu.mum.cs.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;
    @ManyToOne
    private User author;
    @Column(nullable = false)
    @Expose
    private String content;

    @Type(type="yes_no")
    @Column(nullable = false)
    @Expose
    private boolean isDisable;
}
