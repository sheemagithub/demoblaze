package com.qa.demoblaze.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import com.qa.demoblaze.constants.AppConstants;
import com.qa.demoblaze.utils.CSVUtil;
import com.qa.demoblaze.utils.ElementUtil;
import com.qa.demoblaze.utils.TimeUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// Random UserName & Password Generator
	String newUserName = "user" + TimeUtil.getCurrentTime();
	String newPassword = "password" + TimeUtil.getCurrentTime();

	// 1.By Locators
	By home = By.xpath("//a[contains(text(),'Home')]");
	By contact = By.xpath("//a[contains(text(),'Contact')]");
	By aboutUs = By.xpath("//a[contains(text(),'About us')]");
	By cart = By.xpath("//a[contains(text(),'Cart')]");
	By logIn = By.xpath("//a[contains(text(),'Log in')]");
	By logOut = By.xpath("//a[contains(text(),'Log out') and @onclick='logOut()']");
	By signUp = By.xpath("//a[contains(text(),'Sign up')]");
	By signUpUserName = By.xpath("//div[@id='signInModal']//input[@id='sign-username']");
	By signUppassword = By.xpath("//div[@id='signInModal']//input[@id='sign-password']");
	By signUpButton = By.xpath("//div[@id='signInModal']//button[text()='Sign up']");
	By logUserName = By.xpath("//div[@id='logInModal']//input[@id='loginusername']");
	By logInPwd = By.xpath("//div[@id='logInModal']//input[@id='loginpassword']");
	By logInBtn = By.xpath("//div[@id='logInModal']//button[text()='Log in']");
	By contactEmail = By.xpath("//div[@id='exampleModal']//input[@id='recipient-email']");
	By contactName = By.xpath("//div[@id='exampleModal']//input[@id='recipient-name']");
	By message = By.xpath("//div[@id='exampleModal']//textarea[@id='message-text']");
	By sendMessageButton = By.xpath("//div[@id='exampleModal']//button[text()='Send message']");

	// 2.Page Class Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// 3.Page Actions/Methods
	public String doSignUp() {
		eleutil.doClick(signUp);
		eleutil.waitForElementVisibility(signUpUserName, 5);
		eleutil.doSendKeys(signUpUserName, newUserName);
		eleutil.doSendKeys(signUppassword, newPassword);
		eleutil.doClick(signUpButton);
		String alertText = eleutil.getAlertText(5);
		eleutil.acceptAlert(5);
		return alertText;
	}

	public boolean doLogIn() {
		eleutil.doClick(logIn);
		eleutil.waitForElementVisibility(logUserName, 5);
		eleutil.doSendKeys(logUserName, newUserName);
		eleutil.doSendKeys(logInPwd, newPassword);
		eleutil.doClick(logInBtn);
		eleutil.waitForElementVisibility(logOut, 5);
		return eleutil.isElementVisible(logOut);
	}

	public String goToContactAndSendMessage() {
		eleutil.doClick(contact);
		eleutil.waitForElementVisibility(contactEmail, 5);
		eleutil.doSendKeys(contactEmail, AppConstants.CONTACT_EMAIL);
		eleutil.doSendKeys(contactName, AppConstants.CONTACT_NAME);
		eleutil.doSendKeys(message, AppConstants.CONTACT_MESSAGE);
		eleutil.doClick(sendMessageButton);
		String alertText = eleutil.getAlertText(5);
		eleutil.acceptAlert(5);
		return alertText;
	}

	public ProductPage selectProduct(String Category, String Product) {
		eleutil.doClick(home);
		driver.findElement(By.xpath("//a[@id='cat']/following-sibling::a[text()='" + Category + "']")).click();
		eleutil.waitForElementVisibility(By.xpath("//a[text()='" + Product + "']/../../preceding-sibling::a/img"), 10);
		driver.findElement(By.xpath("//a[text()='" + Product + "']/../../preceding-sibling::a/img")).click();
		return new ProductPage(driver);
	}

}
