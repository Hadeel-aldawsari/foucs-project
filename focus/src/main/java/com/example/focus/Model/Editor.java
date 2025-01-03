package com.example.focus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Editor {
    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "Please enter your city")
    @Column(columnDefinition = "varchar(40) not null")
    private String city;

    @NotEmpty(message = "Please enter your phone number")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and have 10 digits")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;


//
//    @OneToOne(mappedBy = "photographer", cascade = CascadeType.ALL)
//    private ProfileEditor profile;

}