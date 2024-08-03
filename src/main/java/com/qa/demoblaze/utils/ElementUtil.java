package com.qa.demoblaze.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public boolean isElementVisible(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public String getPageURL(String URLFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.urlContains(URLFraction));
			return driver.getCurrentUrl();
		}
		catch(Exception e) {
			System.out.println("url is not found within: "+timeOut);
		}
		return driver.getCurrentUrl();
		
	}
	
	public String getElementText(By locator) {
		return getElement(locator).getText();
	}
	
	public WebElement waitForElementVisibility(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
	}
	
	//******** Alerts *********
	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText(int timeOut) {
		return waitForJSAlert(timeOut).getText();
	}
	
	public void acceptAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
	}
	
	public void dismissAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}
	
	
}
