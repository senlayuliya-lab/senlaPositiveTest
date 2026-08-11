package pages;

import driver.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeathPage extends BasePage {

    private final By regBtn = By.xpath("//button[contains(.,'Регистрация смерти')]");
    private final By nextBtn = By.xpath("//button[contains(.,'Далее')]");
    private final By finishBtn = By.xpath("//button[contains(.,'Завершить')]");

    public DeathPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть регистрацию смерти")
    public void openRegistration() {
        click(regBtn);
    }

    @Step("Заполнить данные гражданина для смерти:")
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

    @Step("Нажать кнопку 'Далее'")
    public void next() {
        click(nextBtn);
    }

    @Step("Заполнить данные о смерти:")
    public void fillService(String deathDate, String deathPlace) {
        type(field("Дата смерти"), deathDate);
        type(field("Место смерти"), deathPlace);
    }

    @Step("Завершить регистрацию смерти")
    public void finish() {
        click(finishBtn);
    }
}