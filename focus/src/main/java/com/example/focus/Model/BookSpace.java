package com.example.focus.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class BookSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Please enter start date")
    @Column(columnDefinition = "DATETIME not null ")
    private LocalDate startDate;
    @NotEmpty(message = "Please enter end date")
    @Column(columnDefinition = "DATETIME not null ")
    private LocalDate endDate;
    @Column(columnDefinition = "varchar(20)")
    private String status;
    @NotEmpty(message = "Please enter your notes")
    @Column(columnDefinition = "varchar(100) not null")
    private String note;

    @ManyToOne
    @JsonIgnore
    private Space space;

    @OneToMany(mappedBy = "rentalStudioRequest")
    private Set<TimeSlot> timeSlot;

}
