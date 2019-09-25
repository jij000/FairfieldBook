package edu.mum.cs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String profilePhoto;
    private String isActive;
}
