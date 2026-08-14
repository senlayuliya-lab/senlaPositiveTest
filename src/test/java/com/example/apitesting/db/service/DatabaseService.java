package com.example.apitesting.db.service;

import com.example.apitesting.db.model.User;
import com.example.apitesting.db.model.Admin;
import com.example.apitesting.db.repository.AdminRepository;
import com.example.apitesting.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DatabaseService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;


    @Transactional
    public User createUser(User user) {
        User saved = userRepository.save(user);
        log.info("Создан заявитель: {}", saved);
        return saved;
    }


    public User findUserByPassport(String passportNumber) {
        return userRepository.findByPassportNumber(passportNumber).orElse(null);
    }


    @Transactional
    public void deleteUserByPassport(String passportNumber) {
        userRepository.deleteByPassportNumber(passportNumber);
        log.info("Удален заявитель с паспортом: {}", passportNumber);
    }


    @Transactional
    public Admin createAdmin(Admin admin) {
        Admin saved = adminRepository.save(admin);
        log.info("Создан сотрудник: {}", saved);
        return saved;
    }


    public Admin findAdminByPassport(String passportNumber) {
        return adminRepository.findByPassportNumber(passportNumber).orElse(null);
    }


    @Transactional
    public void deleteAdminByPassport(String passportNumber) {
        adminRepository.deleteByPassportNumber(passportNumber);
        log.info("Удален сотрудник с паспортом: {}", passportNumber);
    }
}