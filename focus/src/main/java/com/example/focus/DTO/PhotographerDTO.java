package com.example.focus.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotographerDTO {
    private String username;
    private String name;

    private String city;

    private String email;
    private String phoneNumber;



}