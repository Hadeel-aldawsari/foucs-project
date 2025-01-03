package com.example.focus.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudioDTOIn {
    @NotEmpty(message = "Please enter studio name")
    private String name;

    @NotEmpty(message = "Please enter username")
    private String username;

    @NotEmpty(message = "Please enter your email")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Please enter password")
    private String password;

    @NotEmpty(message = "Please enter your phone number")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and have 10 digits")
    private String phoneNumber;

    @NotEmpty(message = "commercialRecord")
    @Pattern(regexp = "^7\\d{9}$", message = "commercialRecord must start with 7 and be followed by 9 digits")
    private String commercialRecord;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Please enter studio Address")
    private String address;


}
