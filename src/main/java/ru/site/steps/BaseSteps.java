package ru.site.steps;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.site.pages.BasePage;
import ru.site.utils.DriverManager;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    public static BasePage basePage;

    @Before
    public static void setUp(){
        WebDriver driver = DriverManager.getDriver();

        driver.manage().window().maximize();
        driver.get("http://www.ozon.ru/");
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @After
    public static void tearDown(){
        DriverManager.quitDriver();
    }
}
