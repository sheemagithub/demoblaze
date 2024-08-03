package com.qa.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demoblaze.constants.AppConstants;
import com.qa.demoblaze.utils.ElementUtil;

public class ProductPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// 1.By Locators
	By addToCartBtn = By.xpath("//div[@id='tbodyid']//div[@class='row']//a");
	By cart = By.xpath("//a[contains(text(),'Cart')]");

	// 2.Page Class Constructor
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// 3.Page Actions/Methods
	public String getProductPageURL() {
		return eleutil.getPageURL(AppConstants.PRODUCT_PAGE_URL, 5);
	}

	public String addToCart() {
		eleutil.waitForElementVisibility(addToCartBtn, 5);
		eleutil.doClick(addToCartBtn);
		String alertText = eleutil.getAlertText(5);
		System.out.println(alertText);
		eleutil.acceptAlert(5);
		return alertText;
	}

	public CartPage viewCart() {
		addToCart();
		eleutil.waitForElementVisibility(cart, 5);
		eleutil.doClick(cart);
		return new CartPage(driver);
	}

}
