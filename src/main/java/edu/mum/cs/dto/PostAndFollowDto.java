package edu.mum.cs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAndFollowDto {
    private int id;
    private String authorId;
    private String authorName;
    private String profilePhotoUrl;
    private String content;
    private String picUrl;
    private boolean isFromTwitter;
    private boolean isDisable;
    private boolean canFollow;
}
