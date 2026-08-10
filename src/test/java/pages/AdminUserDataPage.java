package pages;

import io.qameta.allure.Step;
import models.AdminRegistrationData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminUserDataPage extends BasePage {

    private final By loginBtn = By.xpath("//button[contains(.,'Войти как администратор')]");
    private final By nextBtn = By.xpath("//button[contains(.,'Далее')]");
    private final By adminTitle = By.xpath("//span[contains(.,'Администрирование заявок')]");

    public AdminUserDataPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку 'Войти как администратор'")
    public AdminUserDataPage login() {
        click(loginBtn);
        return this;
    }

    @Step("Заполнить форму администратора")
    public AdminUserDataPage fillForm(AdminRegistrationData data) {
        type(field("Фамилия"), data.getLastName());
        type(field("Имя"), data.getFirstName());
        type(field("Отчество"), data.getMiddleName());
        type(field("Телефон"), data.getPhone());
        type(field("Номер паспорта"), data.getPassport());
        type(field("Дата рождения"), data.getBirthDate());
        return this;
    }

    @Step("Нажать кнопку 'Далее'")
    public AdminUserDataPage next() {
        click(nextBtn);
        return this;
    }

    @Step("Проверить загрузку страницы администрирования")
    public boolean isAdminPageLoaded() {
        try {
            waitVisible(adminTitle);
            return true;
        } catch (Exception e) {
            System.out.println("Текущий URL: " + driver.getCurrentUrl());
            return false;
        }
    }

    @Step("Выполнить вход как администратор")
    public AdminUserDataPage loginAsAdmin(AdminRegistrationData data) {
        return login()
                .fillForm(data)
                .next();
    }
}