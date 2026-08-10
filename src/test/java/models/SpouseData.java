package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpouseData {
    private String regDate;
    private String newLastName;
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String passport;
}