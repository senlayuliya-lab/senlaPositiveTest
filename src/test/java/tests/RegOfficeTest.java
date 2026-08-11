package tests;

import driver.BaseTest;
import io.qameta.allure.*;
import models.AdminRegistrationData;
import models.UserData;
import models.CitizenData;
import models.SpouseData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pages.*;

@Epic("E2E ЗАГС")
public class RegOfficeTest extends BaseTest {  // ← наследуем BaseTest

    UserData user = UserData.builder()
            .lastName("Иванов")
            .firstName("Иван")
            .middleName("Иванович")
            .phone("+375296667788")
            .passport("AB1234590")
            .address("г. Брест")
            .build();

    CitizenData citizen = CitizenData.builder()
            .lastName("Иванов")
            .firstName("Иван")
            .middleName("Иванович")
            .birthDate("01.01.1990")
            .passport("AB1234590")
            .gender("Мужской")
            .address("г. Брест")
            .build();

    SpouseData spouse = SpouseData.builder()
            .regDate("01.01.2024")
            .newLastName("Иванова")
            .lastName("Петрова")
            .firstName("Мария")
            .middleName("Петровна")
            .birthDate("05.05.1992")
            .passport("CD7654321")
            .build();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет создание успешной заявки О заключении брака")
    @Story("Заключение брака")
    public void marriageFlow() {
        Allure.step("Запуск теста: Заключение брака");
        logger.info("Запуск теста: marriageFlow");

        Allure.step("Шаг 1: Авторизация и заполнение данных пользователя");
        UserDataPage userDataPage = new UserDataPage(driver);
        userDataPage.login();
        userDataPage.fillForm(user);
        userDataPage.next();

        Allure.step("Шаг 2: Заполнение данных о браке");
        MarriagePage marriagePage = new MarriagePage(driver);
        marriagePage.openRegistration();
        marriagePage.fillCitizen(citizen);
        marriagePage.next();
        marriagePage.fillSpouse(spouse);
        marriagePage.finish();

        Allure.step("Шаг 3: Проверка успешного завершения");
        Assertions.assertTrue(new FinalPage(driver).isSuccess());

        logger.info("Тест marriageFlow успешно завершён");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет создание успешной заявки О рождении")
    @Story("Регистрация рождения")
    public void birthFlow() {
        Allure.step("Запуск теста: Регистрация рождения");
        logger.info("Запуск теста: birthFlow");

        Allure.step("Шаг 1: Авторизация");
        UserDataPage userDataPage = new UserDataPage(driver);
        userDataPage.login();
        userDataPage.fillForm("Иванов", "Иван", "Иванович",
                "+375296667788", "AB1234590", "г. Брест");
        userDataPage.next();

        Allure.step("Шаг 2: Выбор услуги рождения");
        new BirthPage(driver).selectService();

        Allure.step("Шаг 3: Заполнение данных гражданина");
        CitizenPage citizenPage = new CitizenPage(driver);
        citizenPage.fill("Иванов", "Иван", "Иванович",
                "1990-01-01", "AB1234590", "Мужской", "г. Брест");
        citizenPage.next();

        Allure.step("Шаг 4: Заполнение данных о рождении");
        BirthPage birthPage = new BirthPage(driver);
        birthPage.fill("г. Брест", "Мать", "Отец", "Бабушка", "Дедушка");
        birthPage.finish();

        Allure.step("Шаг 5: Проверка успешного завершения");
        Assertions.assertTrue(new FinalPage(driver).isSuccess());

        logger.info("Тест birthFlow успешно завершён");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет создание успешной заявки О кончине")
    @Story("Регистрация смерти")
    public void deathFlow() {
        Allure.step("Запуск теста: Регистрация смерти");
        logger.info("Запуск теста: deathFlow");

        Allure.step("Шаг 1: Авторизация");
        UserDataPage userDataPage = new UserDataPage(driver);
        userDataPage.login();
        userDataPage.fillForm("Иванов", "Иван", "Иванович",
                "+375296667788", "AB1234590", "г. Брест");
        userDataPage.next();

        Allure.step("Шаг 2: Заполнение данных о смерти");
        DeathPage deathPage = new DeathPage(driver);
        deathPage.openRegistration();
        deathPage.fillCitizen("Петров", "Пётр", "Петрович",
                "1950-01-01", "AB1234567", "Мужской", "г. Минск");
        deathPage.next();
        deathPage.fillService("2024-07-01", "г. Минск, больница №3");
        deathPage.finish();

        Allure.step("Шаг 3: Проверка успешного завершения");
        Assertions.assertTrue(new FinalPage(driver).isSuccess());

        logger.info("Тест deathFlow успешно завершён");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет успешный вход администратора с валидными данными")
    @Story("Администрирование")
    public void adminFlow() {
        Allure.step("Запуск теста: Администрирование");
        logger.info("Запуск теста: adminFlow");

        AdminRegistrationData data = AdminRegistrationData.builder()
                .lastName("Иванов")
                .firstName("Иван")
                .middleName("Иванович")
                .phone("+375296667788")
                .passport("AB1234590")
                .birthDate("1990-01-01")
                .build();

        Allure.step("Шаг 1: Вход как администратор");
        AdminUserDataPage adminPage = new AdminUserDataPage(driver);
        adminPage.loginAsAdmin(data);

        Allure.step("Шаг 2: Проверка загрузки страницы администрирования");
        Assertions.assertTrue(adminPage.isAdminPageLoaded(), "Страница администрирования не загрузилась");

        logger.info("Тест adminFlow успешно завершён");
    }
}