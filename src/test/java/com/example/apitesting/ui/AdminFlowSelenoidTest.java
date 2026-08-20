package com.example.apitesting.ui;

import com.example.apitesting.ui.driver.SelenoidDriver;
import com.example.apitesting.ui.models.AdminData;
import com.example.apitesting.ui.pages.AdminPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AdminFlowSelenoidTest {

    @BeforeMethod
    public void setUp() {
        WebDriver driver = SelenoidDriver.getDriver();
        driver.get("https://user:senlatest@regoffice.senla.eu/");
    }

    @Test
    public void adminFlowTest1() {
        runAdminFlow("Тест 1");
    }

    @Test
    public void adminFlowTest2() {
        runAdminFlow("Тест 2");
    }

    @Test
    public void adminFlowTest3() {
        runAdminFlow("Тест 3");
    }

    private void runAdminFlow(String testName) {
        System.out.println("Запуск: " + testName);

        WebDriver driver = SelenoidDriver.getDriver();

        AdminData data = AdminData.builder()
                .lastName("Иванов")
                .firstName("Иван")
                .middleName("Иванович")
                .phone("+375296667788")
                .passport("AB1234590")
                .birthDate("1990-01-01")
                .build();

        AdminPage adminPage = new AdminPage(driver);
        adminPage.loginAsAdmin(data);

        Assert.assertTrue(adminPage.isAdminPageLoaded(),
                "Страница администрирования не загрузилась: " + testName);

        System.out.println("Успешно завершен: " + testName);
    }

    @AfterMethod
    public void tearDown() {
        SelenoidDriver.quit();
    }
}