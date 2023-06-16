package com.opencart;


import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException{
            //DriverManager driverManager = DriverManager.getInstance();
            //WebDriver driver2 = driverManager.getDriver();

            // Define a driver object that will be used for future action
            WebDriver driver = DriverManager.getInstance().getDriver();

            driver.get("https://www.youtube.com");

            String currentWindowName = driver.getWindowHandle();

            driver.switchTo().newWindow(WindowType.TAB);

            driver.get("https://www.youtube.com/watch?v=hN_q-_nGv4U");
            Thread.sleep(3000);

            driver.close();

            driver.switchTo().window(currentWindowName);
            Thread.sleep(1000);

            driver.get("https://ludovicoeinaudi.com");

            driver.quit();

            System.out.println("The execution was finished!");
        }
}