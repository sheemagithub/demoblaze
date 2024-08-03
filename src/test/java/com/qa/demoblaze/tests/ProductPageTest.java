package com.qa.demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.demoblaze.base.BaseTest;
import com.qa.demoblaze.constants.AppConstants;

public class ProductPageTest extends BaseTest {

	@Test(priority = 0, dataProvider = "getDataFromCSV")
	public void addToCartTest(String Category, String Product) {
		productpage = homepage.selectProduct(Category, Product);
		String alertText = productpage.addToCart();
		Assert.assertEquals(alertText, AppConstants.PRODUCT_ADDED_SUCCESS);
	}

	@Test(priority = 1)
	public void viewCartTest() {
		cartpage = productpage.viewCart();
		Assert.assertEquals(cartpage.getCartPageURL(), AppConstants.CART_PAGE_URL);
	}
}
