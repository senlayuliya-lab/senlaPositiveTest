package com.example.apitesting.db.repository;

import com.example.apitesting.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPassportNumber(String passportNumber);

    void deleteByPassportNumber(String passportNumber);
}