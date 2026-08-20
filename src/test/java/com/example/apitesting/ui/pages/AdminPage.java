package com.example.apitesting.ui.pages;

import com.example.apitesting.ui.models.AdminData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By loginBtn = By.xpath("//button[contains(.,'Войти как администратор')]");
    private final By nextBtn = By.xpath("//button[contains(.,'Далее')]");
    private final By adminTitle = By.xpath("//span[contains(.,'Администрирование заявок')]");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Нажать кнопку 'Войти как администратор'")
    public AdminPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    @Step("Заполнить форму администратора")
    public AdminPage fillAdminForm(AdminData data) {
        typeByLabel("Фамилия", data.getLastName());
        typeByLabel("Имя", data.getFirstName());
        typeByLabel("Отчество", data.getMiddleName());
        typeByLabel("Телефон", data.getPhone());
        typeByLabel("Номер паспорта", data.getPassport());
        typeByLabel("Дата рождения", data.getBirthDate());
        return this;
    }

    @Step("Нажать кнопку 'Далее'")
    public AdminPage clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
        return this;
    }

    @Step("Проверить загрузку страницы администрирования")
    public boolean isAdminPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(adminTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Выполнить полный вход как администратор")
    public AdminPage loginAsAdmin(AdminData data) {
        return clickLoginButton()
                .fillAdminForm(data)
                .clickNextButton();
    }

    // Вспомогательный метод для поиска поля по label
    private void typeByLabel(String label, String value) {
        By fieldLocator = By.xpath("//label[contains(.,'" + label + "')]/following-sibling::input | " +
                "//input[@placeholder='" + label + "'] | " +
                "//input[contains(@name,'" + label + "')] | " +
                "//label[contains(.,'" + label + "')]/following::input[1]");
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        field.clear();
        field.sendKeys(value);
    }
}