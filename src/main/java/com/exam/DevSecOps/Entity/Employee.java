package com.exam.DevSecOps.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.name.notblank}")
    @Size(min = 2, max = 50, message = "{validation.name.size}")
    private String name;

    @Email(message = "{validation.email.format}")
    @NotBlank(message = "{validation.email.notblank}")
    @Column(unique = true)
    private String email;

    // Getters and Setters
}
