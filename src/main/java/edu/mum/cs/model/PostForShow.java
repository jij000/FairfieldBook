package edu.mum.cs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@AllArgsConstructor
public class PostForShow {
    private int id;
    private String authorName;
    private String content;
    private String isDisable;
}
