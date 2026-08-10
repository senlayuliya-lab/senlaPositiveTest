package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitizenData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String passport;
    private String gender;
    private String address;
}