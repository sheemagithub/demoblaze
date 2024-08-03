package com.qa.demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demoblaze.base.BaseTest;
import com.qa.demoblaze.constants.AppConstants;
import com.qa.demoblaze.utils.CSVUtil;

public class HomePageTest extends BaseTest {

	@Test(priority = 0)
	public void doSignUpTest() {
		String successText = homepage.doSignUp();
		Assert.assertEquals(successText, AppConstants.SIGN_UP_SUCCESS);
	}

	@Test(priority = 1)
	public void doLoginTest() {
		boolean flag = homepage.doLogIn();
		Assert.assertTrue(flag);
	}

	@Test(priority = 2)
	public void goToContactAndSendMessageTest() {
		String successText = homepage.goToContactAndSendMessage();
		Assert.assertEquals(successText, AppConstants.CONTACT_MESSAGE_SENT_SUCCESS);
	}

	@Test(dataProvider = "getDataFromCSV", priority = 3, dependsOnMethods = { "doSignUpTest", "doLoginTest",
			"goToContactAndSendMessageTest" })
	public void selectProductTest(String Category, String Product) {
		System.out.println("********running for**********" + Category + "*" + Product);
		productpage = homepage.selectProduct(Category, Product);
		Assert.assertTrue(productpage.getProductPageURL().contains(AppConstants.PRODUCT_PAGE_URL));
	}
}
