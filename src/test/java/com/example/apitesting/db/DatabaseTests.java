package com.example.apitesting.db;

import com.example.apitesting.db.model.Admin;
import com.example.apitesting.db.model.User;
import com.example.apitesting.db.service.DatabaseService;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

@Slf4j
@SpringBootTest
@Epic("JDBC Автотесты")
@Feature("Работа с БД через JDBC")
public class DatabaseTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private DatabaseService databaseService;

    private static final String TEST_USER_PASSPORT = "AB" + System.currentTimeMillis() % 1000000;
    private static final String TEST_ADMIN_PASSPORT = "CD" + System.currentTimeMillis() % 1000000;

  /*  @AfterMethod
    public void cleanup() {
        databaseService.deleteUserByPassport(TEST_USER_PASSPORT);
        databaseService.deleteAdminByPassport(TEST_ADMIN_PASSPORT);
        log.info("Очистка выполнена");
  }*/

    @Test(description = "Создание заявителя (пользователя)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Создание заявителей")
    public void testCreateUser() {
        Allure.step("1. Создание нового заявителя");

        log.info("Создаём пользователя с паспортом: {}", TEST_USER_PASSPORT);

        User user = User.builder()
                .surname("Петров")
                .name("Иван")
                .middlename("Сергеевич")
                .passportNumber(TEST_USER_PASSPORT)
                .phoneNumber("37529123456")
                .registrationAddress("г. Минск, ул. Ленина, 1")
                .build();

        User created = databaseService.createUser(user);

        assertNotNull(created.getId(), "ID не должен быть null");
        assertEquals(created.getPassportNumber(), TEST_USER_PASSPORT);

        User found = databaseService.findUserByPassport(TEST_USER_PASSPORT);
        assertNotNull(found, "Заявитель должен существовать в БД");

        log.info("Тест создания заявителя пройден");
    }

    @Test(description = "Создание сотрудника (администратора)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Создание сотрудников")
    public void testCreateAdmin() {
        Allure.step("1. Создание нового сотрудника");

        log.info("Создаём сотрудника с паспортом: {}", TEST_ADMIN_PASSPORT);

        Admin admin = Admin.builder()
                .surname("Иванов")
                .name("Петр")
                .middlename("Алексеевич")
                .passportNumber(TEST_ADMIN_PASSPORT)
                .phoneNumber("37529123450")
                .dateOfBirth(LocalDate.of(1985, 5, 15))
                .build();

        Admin created = databaseService.createAdmin(admin);

        assertNotNull(created.getId(), "ID не должен быть null");
        assertEquals(created.getPassportNumber(), TEST_ADMIN_PASSPORT);

        Admin found = databaseService.findAdminByPassport(TEST_ADMIN_PASSPORT);
        assertNotNull(found, "Сотрудник должен существовать в БД");

        log.info("Тест создания сотрудника пройден");
    }
}