package com.example.focus.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudioDTO {
    private String name;
    private String username;
    private String phoneNumber;
    private String email;
    private String city;
    private String Address;
    private String commercialRecord;
    private String status;
}
