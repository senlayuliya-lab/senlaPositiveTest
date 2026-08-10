package pages;

import io.qameta.allure.Step;
import models.CitizenData;
import models.SpouseData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MarriagePage extends BasePage {

    private final By regBtn = By.xpath("//button[contains(.,'Регистрация брака')]");
    private final By nextBtn = By.xpath("//button[contains(.,'Далее')]");
    private final By finishBtn = By.xpath("//button[contains(.,'Завершить')]");

    public MarriagePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть регистрацию брака")
    public void openRegistration() {
        click(regBtn);
    }

    @Step("Заполнить данные гражданина для брака:")
    public void fillCitizen(String lastName, String name, String middleName,
                            String birthDate, String passport, String gender, String address) {
        type(field("Фамилия"), lastName);
        type(field("Имя"), name);
        type(field("Отчество"), middleName);
        type(field("Дата рождения"), birthDate);
        type(field("Номер паспорта"), passport);
        type(field("Пол"), gender);
        type(field("Адрес прописки"), address);
    }

    @Step("Нажать кнопку 'Далее' на странице брака")
    public void next() {
        click(nextBtn);
    }

    @Step("Заполнить данные супруга:")
    public void fillSpouse(String regDate, String newLastName,
                           String spouseLastName, String spouseName, String spouseMiddleName,
                           String spouseBirthDate, String spousePassport) {
        type(field("Дата регистрации"), regDate);
        type(field("Новая фамилия"), newLastName);
        type(field("Фамилия супруга"), spouseLastName);
        type(field("Имя супруга"), spouseName);
        type(field("Отчество супруга"), spouseMiddleName);
        type(field("Дата рождения супруга"), spouseBirthDate);
        type(field("Номер паспорта супруга"), spousePassport);
    }

    @Step("Заполнить данные гражданина из объекта CitizenData")
    public void fillCitizen(CitizenData data) {
        fillCitizen(
                data.getLastName(),
                data.getFirstName(),
                data.getMiddleName(),
                data.getBirthDate(),
                data.getPassport(),
                data.getGender(),
                data.getAddress()
        );
    }

    @Step("Заполнить данные супруга из объекта SpouseData")
    public void fillSpouse(SpouseData data) {
        fillSpouse(
                data.getRegDate(),
                data.getNewLastName(),
                data.getLastName(),
                data.getFirstName(),
                data.getMiddleName(),
                data.getBirthDate(),
                data.getPassport()
        );
    }

    @Step("Завершить регистрацию брака")
    public void finish() {
        click(finishBtn);
    }
}