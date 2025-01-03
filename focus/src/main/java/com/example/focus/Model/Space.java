package com.example.focus.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter space name")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;

    @NotEmpty(message = "Please enter space type")
    @Column(columnDefinition = "varchar(20) not null ")
    private String type;

    @NotNull(message = "Please enter space area")
    @Positive
    @Column(columnDefinition = "double not null ")
    private Double area;

    @NotEmpty(message = "Please enter space description")
    @Column(columnDefinition = "varchar(150) not null")
    private String description;

    @NotNull(message = "Please enter space price")
    @Positive
    @Column(columnDefinition = "double not null ")
    private Double price;

    @Column
    private String status;

    @NotEmpty(message = "Enter image URL")
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "Image URL must be a valid URL")
    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

//    @OneToMany(mappedBy = "space")
//    private Set<TimeSlot> timeSlots;


}
