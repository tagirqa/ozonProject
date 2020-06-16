package ru.site.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class DriverManager {
    private static Properties properties = TestProperties.getInstance().getProperties();
    private static WebDriver driver = null;

    public static WebDriver getDriver(){
        if (driver == null){
            driver = createDriver();
        }
        return driver;
    }

    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver createDriver() {

        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver");
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }
}
