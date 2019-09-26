package edu.mum.cs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdsDto {
    private int id;
    private String name;
    private String content;
    private String isActive;
}
