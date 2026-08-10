package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CitizenPage extends BasePage {

    private final By nextBtn = By.xpath("//button[contains(.,'Далее')]");

    public CitizenPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить данные гражданина:")
    public void fill(String lastName, String name, String middleName,
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
}