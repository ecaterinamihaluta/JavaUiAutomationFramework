package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegistrationFlowTestSuite {
    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    static int counter = 0;

    @BeforeEach
    public void executeTheCodeBeforeEachTestFromThisClass() {
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The test number " + counter + " started.");
    }
    @Test
    @DisplayName("The url contains keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectUrl() throws InterruptedException{
        homePage.navigateToRegistrationPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(10, 20);


        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The URL " + driver.getCurrentUrl() + "contains success keyword";
        Assertions.assertTrue(urlContainsTheCorrectKeyWords, errorMessage);
    }
    @Test
    @DisplayName("The URL contains register keyword when privacy policy is not accepted")
    public void registerFlowIsNotAcceptedByPrivacyPolicyToggleThatIsNotAccepted() throws InterruptedException {

        homePage.navigateToRegistrationPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(10, 20);

        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
//        Do not enable thePrivacy Toggle
//        registerPage.switchOnThePrivacyToggle(driver);

        registerPage.clickOnContinueButton();
        Thread.sleep(500);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The URL " + driver.getCurrentUrl() + "does not contain success keyword";
        Assertions.assertFalse(urlContainsTheCorrectKeyWords, errorMessage);

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("route=account/register&language=en-gb");
        Assertions.assertTrue(urlContainsRegisterKeyword, "The URL belongs to register page");
    }
    @AfterEach
    public void executeThisMethodAfterEachTestCase() {
        DriverManager.getInstance().quitTheDriver();
        System.out.println("The test number " + counter + "finished!");
    }

}
