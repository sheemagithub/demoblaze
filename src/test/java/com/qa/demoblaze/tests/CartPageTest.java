package com.qa.demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.demoblaze.base.BaseTest;
import com.qa.demoblaze.constants.AppConstants;

public class CartPageTest extends BaseTest {

	@BeforeClass
	public void cartPageSetUp() {
		homepage.doSignUp();
		homepage.doLogIn();
		homepage.goToContactAndSendMessage();
	}

	@Test(priority = 0, dataProvider = "getDataFromCSV")
	public void cartWithProductsListTest(String Category, String Product) {
		System.out.println("********running for**********" + Category + "*" + Product);
		
		productpage = homepage.selectProduct(Category, Product);
		cartpage = productpage.viewCart();
	}

	@Test(priority = 1)
	public void validateAmountTest() {
		int total = cartpage.validateAmount();
		Assert.assertEquals(total, AppConstants.TOTAL_AMOUNT);
	}

	@Test(priority = 2)
	public void placeOrderTest() {
		String successMsg = cartpage.placeOrder();
		Assert.assertEquals(successMsg, AppConstants.PLACE_ORDER_SUCCESS_MESSAGE);
	}

	@Test(priority = 3)
	public void isCartEmptyTest() {
		Assert.assertTrue(cartpage.isCartEmpty());
	}

}
