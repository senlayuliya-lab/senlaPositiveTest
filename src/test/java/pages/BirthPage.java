package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BirthPage extends BasePage {

    private final By finishBtn = By.xpath("//button[contains(.,'Завершить')]");

    public BirthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Выбрать услугу 'Регистрация рождения'")
    public void selectService() {
        click(By.xpath("//button[contains(.,'Регистрация рождения')]"));
    }

    @Step("Заполнить данные о рождении: место {0}, мать {1}, отец {2}")
    public void fill(String place, String mother, String father,
                     String grandMother, String grandFather) {
        type(field("Место рождения"), place);
        type(field("Мать"), mother);
        type(field("Отец"), father);
        type(field("Бабушка"), grandMother);
        type(field("Дедушка"), grandFather);
    }

    @Step("Нажать кнопку 'Завершить'")
    public void finish() {
        click(finishBtn);
    }
}