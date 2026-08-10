package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminRegistrationData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String passport;
    private String birthDate;
}