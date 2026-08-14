package com.example.apitesting.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "applicants", schema = "reg_office")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicantid")
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "passportnumber", unique = true)
    private String passportNumber;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "registration_address")
    private String registrationAddress;

}