package edu.mum.cs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private int id;
    private String authorName;
    private String content;
    private String isDisable;
}
