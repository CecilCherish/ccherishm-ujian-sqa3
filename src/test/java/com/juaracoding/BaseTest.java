package com.juaracoding;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.nio.file.Paths;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        String url = Paths.get("src/test/resources/locator_practice.html")
                .toAbsolutePath().toUri().toString();
        driver.get(url);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000); 
        if (driver != null) {
            driver.quit();
        }
    }
}