package framework.web_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverWrapper {
    public static WebDriver driver = null;
    private static String url = "https://www.darksky.com";

    public static WebDriver getDriver() {
        return driver;
    }

    public void initializeWebDriver() {
        //set chromedriver
        System.setProperty("webdriver.chrome.driver", "/usr/ramizahsalim/downloads/chromedriver");
        //Initialize webdriver interface
        driver = new ChromeDriver();
        //1. Goto Facebook homepage
        driver.navigate().to(url);
    }

    public void tearDown() {
        //quit the selenium driver
        driver.quit();
    }

}
