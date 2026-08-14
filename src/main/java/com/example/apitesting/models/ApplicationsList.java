package com.example.apitesting.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationsList {
    private int applicantid;
    private int applicationid;
    private int citizenid;
    private String dateofapplication;
    private String kindofapplication;
    private int statusofapplication;
    private int staffid;
}