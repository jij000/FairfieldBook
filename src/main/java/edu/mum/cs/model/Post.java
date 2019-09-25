package edu.mum.cs.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;
    @ManyToOne
    @Expose
    private User author;
    @Expose
    private String content;

    @Expose
    private String picUrl;

    @Type(type="yes_no")
    @Column(nullable = false)
    @Expose
    private boolean isFromTwitter;

    @Type(type="yes_no")
    @Column(nullable = false)
    @Expose
    private boolean isDisable;
}
