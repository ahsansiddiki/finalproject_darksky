
public class DriverWrapper {

    private static WebDriver driver = null;
    private static String url = "www.darksky.net";

    public static WebDriver getDriver() {return driver;}

    public void initializeWebDriver(){
        System.setProperty("webdriver.chrome.driver", "/Users/ramizahsalim/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to(url);
    }
    public void tearDown(){
        driver.quit();
    }
}
