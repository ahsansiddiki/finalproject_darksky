package framework.web_pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomePageDarksky extends BasePage {
//    private static By searchField = By.xpath("//input[@type='text']");
//    private static By searchButton = By.xpath("//a[@class='searchButton']//img[@width='16']");
    private static By temperature = By.xpath("//span[@class='summary swap']");
//    private static By checkRange = By.xpath("//span[@class='summary-high-low']");

    private static By todayMinTemp = By.xpath("//*[@id=\"week\"]/a[1]/span[2]/span[1]");
    private static By todayMaxTemp = By.xpath("//*[@id=\"week\"]/a[1]/span[2]/span[3]");
    private static By expantTodayTemp = By.xpath("//*[@id=\"week\"]/a[1]/span[3]/span[1]/svg/circle");


    //    public static void getTime() throws ParseException {
//        getData();
//    }
//
//    public void findRange()
//    {
//        getTemperatureRange();
//    }

//    public static void clickSearchField() throws InterruptedException {
//        clearContent(searchField);
//    }
//
//    public static void setSearchField() throws InterruptedException {
//        setValue(searchField,"260 Broadway, New York City, NY");
//        Thread.sleep(5000);
//    }
//
//    public static void clickSearch() throws InterruptedException {
//        Thread.sleep(1000);
//        clickOn(searchButton);
//    }

//    public static void findTemperature() {
//        getTemperature(temperature);
//    }

//    public static void checkTempratureRange() {
//        checkRange(checkRange);
//
//    }

//    public static void clickOnPlus() throws InterruptedException {
//        Thread.sleep(1000);
//        clickOn(expandTemperature);
//    }

    public static void getTemperatureRange() {

        int numberFound=1;
        String element = DriverWrapper.getDriver().findElement(By.xpath("//*[@id='week']/a[1]/span[2]")).getText(); /**Avoid hard coding of locators, rather pass as field variable*/
        String[] parts = element.split("˚");
        String temp1 = parts[0];
        String temp2 = parts[1];

        temp2=temp2.replaceAll("\\s","");

        List<WebElement> myElements = DriverWrapper.getDriver().findElements(By.xpath("//span[@class='temp']"));
        for(WebElement e : myElements) {
            if (numberFound==3) {
                break;
            }
            else {
                String  tempFound = e.getText();
                tempFound=tempFound.replace("˚","");
                tempFound=tempFound.replaceAll("\\s","");
                System.out.print("Found  " + numberFound + " temperature at "+tempFound+"\t");
                numberFound+=1;
                //		String tempLength=e.getText();
                //		System.out.println("String length " +tempLength.length() );

                if (tempFound.contains(temp1)) {
                    System.out.println("Lowest temperature of " + temp1 + " is correct.");
                }
                if (tempFound.contains(temp2)) {
                    System.out.println("Highest temperature of " + temp2 + " is correct.");
                }
            }
        }
    }
    public void verifyTimeline() throws InterruptedException {
        getHours();
    }
    public void getHours() throws InterruptedException {
        String timePlusTwo = "", hour = "";
        List<String> plusTwo= new ArrayList<>();
        List<String> timeline= new ArrayList<>();
        Calendar time = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ha");
        String currentTime = sdf.format(time.getTime()).toLowerCase(); /** I dont see any use of this line*/

        for (int t = 2; t < 24; t += 2) {
            Calendar newTime = Calendar.getInstance();
            newTime.add(Calendar.HOUR, t);
            timePlusTwo = sdf.format(newTime.getTime()).toLowerCase();

            //add current time plus 2 hours values to array list
            plusTwo.add(timePlusTwo);

        }

        Thread.sleep(2000);
        for (int i = 3; i < 25; i += 2) {
            hour = getDriver().findElement(By.xpath("//div[@id='timeline']//div[@class='hours']//span[" + i + "]")).getText();

            //add time plus 2 hours values to array list
            timeline.add(hour);

        }
        /** You shoul use Assertion for verification*/
        if (plusTwo.equals(timeline)) {
            System.out.println("TRUE - Timeline is displayed with two hours incremented \nCurrent+2: " + plusTwo + "\nTimeLine: " + timeline);
        } else {
            System.out.println("FALSE - Timeline is NOT displayed with two hours incremented \nCurrent+2: " + plusTwo + "\nTimeLine: " + timeline);
        }
    }
}
