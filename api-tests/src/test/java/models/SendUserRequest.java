package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendUserRequest {
    private String mode;

    private String personalLastName;
    private String personalFirstName;
    private String personalMiddleName;
    private String personalPhoneNumber;
    private String personalNumberOfPassport;
    private String personalAddress;

    private String citizenBirthDate;
    private String citizenLastName;
    private String citizenFirstName;
    private String citizenMiddleName;
    private String citizenNumberOfPassport;
    private String citizenGender;
    private String citizenAddress;

    private String dateOfMarriage;
    private String newLastName;

    private String anotherPersonLastName;
    private String anotherPersonFirstName;
    private String anotherPersonMiddleName;
    private String anotherPersonPassport;
    private String birth_of_anotoherPerson;

    private String birth_place;
    private String birth_mother;
    private String birth_father;
    private String birth_grandma;
    private String birth_grandpa;

    private String death_dateOfDeath;
    private String death_placeOfDeath;
}