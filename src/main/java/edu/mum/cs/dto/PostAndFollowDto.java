package edu.mum.cs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PostAndFollowDto {
    private int id;
    private int authorId;
    private String authorName;
    private String profilePhotoUrl;
    private String content;
    private String picUrl;
    private boolean isFromTwitter;
    private boolean isDisable;
    private String canFollow;
}
