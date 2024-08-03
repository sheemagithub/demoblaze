package com.qa.demoblaze.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.qa.demoblaze.constants.AppConstants;
import com.qa.demoblaze.factory.DriverFactory;
import com.qa.demoblaze.pages.CartPage;
import com.qa.demoblaze.pages.HomePage;
import com.qa.demoblaze.pages.ProductPage;
import com.qa.demoblaze.utils.CSVUtil;

public class BaseTest {

	WebDriver driver;

	// Maintain Page Reference variables in the BaseTest
	protected HomePage homepage;
	protected ProductPage productpage;
	protected CartPage cartpage;

	@BeforeTest
	public void setUp() {
		driver = new DriverFactory().initDriver();
		homepage = new HomePage(driver);

	}

	@DataProvider
	public Object[][] getDataFromCSV() {
		return CSVUtil.getCSVData(AppConstants.CSV_FILE_NAME);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
