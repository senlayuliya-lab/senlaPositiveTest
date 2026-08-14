package com.example.apitesting.data;

import com.example.apitesting.models.SendUserRequest;

public class TestDataFactory {

    public static SendUserRequest createBirthRequest() {
        return SendUserRequest.builder()
                .mode("birth")
                .personalLastName("Ivanov")
                .personalFirstName("Ivan")
                .personalMiddleName("Ivanovich")
                .personalPhoneNumber("80298833883")
                .personalNumberOfPassport("LK553355")
                .personalAddress("Minsk")
                .citizenBirthDate("2029-11-11")
                .citizenFirstName("Daria")
                .citizenLastName("Ivanova")
                .citizenMiddleName("Ivanovna")
                .citizenNumberOfPassport("GH566663")
                .citizenGender("female")
                .citizenAddress("Minsk")
                .birth_place("Minsk")
                .birth_mother("Test Mother")
                .birth_father("Test Father")
                .birth_grandma("")
                .birth_grandpa("")
                .build();
    }

    public static SendUserRequest createMarriageRequest() {
        return SendUserRequest.builder()
                .mode("wedding")
                .personalLastName("Ivanov")
                .personalFirstName("Ivan")
                .personalMiddleName("Ivanovoch")
                .personalPhoneNumber("80298887766")
                .personalNumberOfPassport("HG12345")
                .personalAddress("Minsk")
                .citizenLastName("Petrova")
                .citizenFirstName("Anna")
                .citizenMiddleName("Igorevna")
                .citizenBirthDate("1111-11-11")
                .citizenNumberOfPassport("KJ876544")
                .citizenGender("female")
                .citizenAddress("Minsk")
                .dateOfMarriage("2027-07-20")
                .newLastName("Ivanova")
                .anotherPersonLastName("Michailov")
                .anotherPersonFirstName("Maxim")
                .anotherPersonMiddleName("Sidorovich")
                .anotherPersonPassport("LK567889")
                .birth_of_anotoherPerson("1987-11-11")
                .birth_place("")
                .birth_mother("")
                .birth_father("")
                .birth_grandma("")
                .birth_grandpa("")
                .death_dateOfDeath("")
                .death_placeOfDeath("")
                .build();
    }

    public static SendUserRequest createDeathRequest() {
        return SendUserRequest.builder()
                .mode("death")
                .personalLastName("Ivanov")
                .personalFirstName("Petr")
                .personalMiddleName("Petrovich")
                .personalPhoneNumber("80296665544")
                .personalNumberOfPassport("HJ876544")
                .personalAddress("Minsk")
                .citizenBirthDate("1966-11-11")
                .citizenFirstName("Filip")
                .citizenLastName("Sidorov")
                .citizenMiddleName("Filipovich")
                .citizenNumberOfPassport("HH77778")
                .citizenGender("male")
                .citizenAddress("Minsk")
                .death_dateOfDeath("2022-02-11")
                .death_placeOfDeath("Minsk")
                .birth_grandma("asdefdv")
                .birth_grandpa("advadfv")
                .build();
    }


    public static SendUserRequest createRequestWithoutLastName() {
        return SendUserRequest.builder()
                .mode("birth")
                .personalFirstName("John")
                .personalMiddleName("Doevich")
                .personalPhoneNumber("80291234567")
                .personalNumberOfPassport("AB1234567")
                .personalAddress("Minsk")
                .citizenBirthDate("1990-01-01")
                .citizenFirstName("John")
                .citizenLastName("Doe")
                .citizenMiddleName("Doevich")
                .citizenNumberOfPassport("CD9876543")
                .citizenGender("male")
                .citizenAddress("Minsk")
                .birth_place("Minsk")
                .birth_mother("Test Mother")
                .birth_father("Test Father")
                .birth_grandma("")
                .birth_grandpa("")
                .build();
    }

    public static SendUserRequest createRequestWithInvalidPhone() {
        return SendUserRequest.builder()
                .mode("birth")
                .personalLastName("Petrov")
                .personalFirstName("Ivan")
                .personalMiddleName("Ivanovich")
                .personalPhoneNumber("invalid-phone")
                .personalNumberOfPassport("AB1234567")
                .personalAddress("Minsk")
                .citizenBirthDate("1990-01-01")
                .citizenFirstName("Ivan")
                .citizenLastName("Petrov")
                .citizenMiddleName("Ivanovich")
                .citizenNumberOfPassport("CD9876543")
                .citizenGender("male")
                .citizenAddress("Minsk")
                .birth_place("Minsk")
                .birth_mother("Test Mother")
                .birth_father("Test Father")
                .birth_grandma("")
                .birth_grandpa("")
                .build();
    }
}