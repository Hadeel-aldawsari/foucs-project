package com.example.focus.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceDTO {
    private String name;
    private String type;
    private Double area;
    private String description;
    private Double price;
    private String status;
    private String image;
}