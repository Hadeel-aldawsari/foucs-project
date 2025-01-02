package com.example.focus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Media type is required")
    @Pattern(regexp = "^(image|video)$", message = "type must be 'video' or 'image'")
    @Column(columnDefinition = "varchar(5) not null")
    private String mediaType;

    // @NotNull(message = "Upload date is required")
    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadDate;

    @AssertTrue(message = "visibility ")
    @Column(columnDefinition = "varchar(5) not null")
    private Boolean visibility;

    @NotEmpty(message = "Media URL is required")
    @URL(message = "Invalid media URL")
    @Column(columnDefinition = "varchar not null")
    private String mediaUrl;

    @ManyToOne
    private Editor editor;

    @ManyToOne
    private Photographer photographer;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;
}