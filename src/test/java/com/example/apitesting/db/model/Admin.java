package com.example.apitesting.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "staff", schema = "reg_office")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffid")
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Column(name = "passportnumber", unique = true)
    private String passportNumber;

    @Column(name = "phonenumber")
    private String phoneNumber;
}