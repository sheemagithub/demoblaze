package com.qa.demoblaze.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	WebDriver driver;
	
	public WebDriver initDriver() {
		driver = new ChromeDriver();
		driver.get("https://www.demoblaze.com/");
		return driver;
	}
	
}
