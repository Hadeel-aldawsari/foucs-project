package com.example.focus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Please enter id of space")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer spaceId;

    @FutureOrPresent(message = "Start date and time must be now or in the future")
    @Column(columnDefinition = "TIMESTAMP not null")
    private LocalDateTime startDateTime;

    @Future(message = "End date and time must be in the future")
    @Column(columnDefinition = "TIMESTAMP not null")
    private LocalDateTime endDateTime;

    @Column(columnDefinition = "boolean ")
    private Boolean isBooked;

    @ManyToOne
    @JsonIgnore
    private Space space;

    @ManyToOne
    @JsonIgnore
    private BookSpace bookSpace;
}
