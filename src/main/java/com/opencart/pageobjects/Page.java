package com.opencart.pageobjects;

import javafx.scene.web.WebEngine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {


    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//i[@class='fa-solid fa-user']")
    protected WebElement accountIcon;
    @FindBy(xpath = "//a[normalize-space()='Register']")
    protected WebElement registerButton;

    public void navigateToRegistrationPageFromHeaderMenu() {
        accountIcon.click();
        System.out.println("The account icon has been clicked");

        registerButton.click();
        System.out.println("The register button has been clicked");

    }
}
