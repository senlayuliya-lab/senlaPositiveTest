package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegOfficeTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void openSitePositive() {
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        driver.findElement(By.xpath("//button[contains(text(),'Войти как пользователь')]")).click();
// Фамилия
        WebElement firstInput = driver.findElement(By.xpath("(//input)[1]"));
        firstInput.click();
        firstInput.sendKeys("Иванов");
// Имя
        WebElement secondInput = driver.findElement(By.xpath("(//input)[2]"));
        secondInput.click();
        secondInput.sendKeys("Иван");
// Отчество
        WebElement thirdInput = driver.findElement(By.xpath("(//input)[3]"));
        thirdInput.click();
        thirdInput.sendKeys("Иванович");
// Телефон
        WebElement telInput = driver.findElement(By.xpath("(//input)[4]"));
        telInput.click();
        telInput.sendKeys("+375296667788");
// Паспорт
        WebElement passNum = driver.findElement(By.xpath("(//input)[5]"));
        passNum.click();
        passNum.sendKeys("AB1234590");
// Адрес
        WebElement addRes = driver.findElement(By.xpath("(//input)[6]"));
        addRes.click();
        addRes.sendKeys("г. Брест ул. Кирова");
// Кнопка Далее
        driver.findElement(By.xpath("//button[contains(text(),'Далее')]")).click();
// Кнопка Регистрация брака
        driver.findElement(By.xpath("//button[contains(text(),'Регистрация брака')]")).click();


// Фамилия
        WebElement lastNameUser = driver.findElement(By.xpath("//label[contains(text(),'Фамилия')]/following::input[1]"));
        lastNameUser.sendKeys("Иванов");
// Имя
        WebElement firstNameUser = driver.findElement(By.xpath("//label[contains(text(),'Имя')]/following::input[1]"));
        firstNameUser.sendKeys("Иван");
// Отчество
        WebElement middleNameUser = driver.findElement(By.xpath("//label[contains(text(),'Отчество')]/following::input[1]"));
        middleNameUser.sendKeys("Иванович");
// Дата рождения
        WebElement dateOfBirthday = driver.findElement(By.xpath("//label[contains(text(),'Дата рождения')]/following::input[1]"));
        dateOfBirthday.sendKeys("12.09.1990");
// Паспорт
        WebElement passNumUser = driver.findElement(By.xpath("//label[contains(text(),'Номер паспорта')]/following::input[1]"));
        passNumUser.sendKeys("AB1234590");
// Пол
        WebElement gender = driver.findElement(By.xpath("//label[contains(text(),'Пол')]/following::input[1]"));
        gender.sendKeys("male");
// Адрес прописки
        WebElement addResUser = driver.findElement(By.xpath("//label[contains(text(),'Адрес прописки')]/following::input[1]"));
        addResUser.sendKeys("город Брест ул Скрипникова 1");
// Кнопка Далее
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(),'Далее')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
        nextButton.click();


// Дата регистрации
        WebElement dateOfReg = driver.findElement(By.xpath("//label[contains(text(),'Дата регистрации')]/following::input[1]"));
        dateOfReg.sendKeys("31.07.2026");
// Новая фамилия
        WebElement lastNameNew = driver.findElement(By.xpath("//label[contains(text(),'Новая фамилия')]/following::input[1]"));
        lastNameNew.sendKeys("Иванова");
// Фамилия супруга/и *
        WebElement husbandName = driver.findElement(By.xpath("//label[contains(text(),'Фамилия')]/following::input[1]"));
        husbandName.sendKeys("Иванов");
// Имя супруга/и *
        WebElement firstNameNew = driver.findElement(By.xpath("//label[contains(text(),'Имя')]/following::input[1]"));
        firstNameNew.sendKeys("Евгения");
// Отчество супруга/и *
        WebElement husbandSurname = driver.findElement(
                By.xpath("//label[contains(text(),'Отчество супруга')]/following::input[1]")
        );
        husbandSurname.sendKeys("Иванович");

// Скролл вниз
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 600);");

// Дата рождения супруга/и — ВАЖНО: правильный лейбл!
        WebElement dateOfBirthSpouse = driver.findElement(
                By.xpath("//label[contains(text(),'Дата рождения супруга')]/following::input[1]")
        );
        dateOfBirthSpouse.clear();
        dateOfBirthSpouse.sendKeys("1987-12-02");
// Номер паспорта супруга/и *
        WebElement numOfPass = driver.findElement(
                By.xpath("//label[contains(text(),'Номер паспорта супруга')]/following::input[1]")
        );
        numOfPass.clear();
        numOfPass.sendKeys("AB543338");

// Кнопка Завершить
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Завершить')]"))
        );
        finishButton.click();

// Ждём появления блока
        WebElement finalBlock = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(.,'Спасибо за обращение')]")
                )
        );
// Проверка текста
        assert driver.findElement(By.xpath("//span[contains(text(),'Спасибо за обращение')]")).isDisplayed();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
