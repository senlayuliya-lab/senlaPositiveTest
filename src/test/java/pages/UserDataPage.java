package pages;

import io.qameta.allure.Step;
import models.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDataPage extends BasePage {

    private final By loginBtn = By.xpath("//button[contains(.,'Войти')]");
    private final By nextBtn = By.xpath("//button[contains(.,'Далее')]");

    public UserDataPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку 'Войти'")
    public void login() {
        click(loginBtn);
    }

    @Step("Заполнить данные пользователя:")
    public void fillForm(String lastName, String name, String middleName,
                         String phone, String passport, String address) {
        type(field("Фамилия"), lastName);
        type(field("Имя"), name);
        type(field("Отчество"), middleName);
        type(field("Телефон"), phone);
        type(field("Номер паспорта"), passport);
        type(field("Адрес прописки"), address);
    }

    @Step("Заполнить данные пользователя из объекта UserData")
    public void fillForm(UserData data) {
        fillForm(
                data.getLastName(),
                data.getFirstName(),
                data.getMiddleName(),
                data.getPhone(),
                data.getPassport(),
                data.getAddress()
        );
    }

    @Step("Нажать кнопку 'Далее' на странице пользователя")
    public void next() {
        click(nextBtn);
    }
}