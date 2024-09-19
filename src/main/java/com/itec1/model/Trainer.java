package com.itec1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    @NotNull
    @NotBlank
    private String dni;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastname;

    @Column(unique = true)
    @Email
    private String email;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String speciality;

    @Column(name = "birth_date")
    @Past
    private LocalDate birthDate;

    @Column(name = "created_at")
    private LocalDate createdAt;

    private boolean enabled;

    @PrePersist
    private void prePersist() {
        createdAt = LocalDate.now();
    }

}
