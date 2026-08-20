package com.example.apitesting.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidDriver {

    private static final String SELENOID_URL = "http://localhost:4444/wd/hub";

    // ThreadLocal для параллельного запуска
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private SelenoidDriver() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("browserName", "chrome");
            options.setCapability("browserVersion", "128.0");

            try {
                WebDriver webDriver = new RemoteWebDriver(new URL(SELENOID_URL), options);
                webDriver.manage().window().maximize();
                driver.set(webDriver);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Неверный URL Selenoid: " + SELENOID_URL, e);
            }
        }
        return driver.get();
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Удаляем из ThreadLocal
        }
    }
}