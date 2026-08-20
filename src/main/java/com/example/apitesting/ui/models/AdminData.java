package com.example.apitesting.ui.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String passport;
    private String birthDate;
}