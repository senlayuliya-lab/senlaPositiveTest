package driver;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        Allure.step("Настройка драйвера и открытие страницы");
        logger.info("Настройка драйвера");
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        logger.info("Драйвер настроен, страница открыта");
    }

    @AfterEach
    public void tearDown() {
        Allure.step("Завершение теста - закрытие драйвера");
        logger.info("Завершение теста");
        DriverSingleton.quit();
    }
}