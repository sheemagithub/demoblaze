package com.qa.demoblaze.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.demoblaze.constants.AppConstants;
import com.qa.demoblaze.utils.ElementUtil;

public class CartPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// 1. By Locators
	By cart = By.xpath("//a[contains(text(),'Cart')]");
	By cartItems = By.xpath("//div[@id='page-wrapper']//tbody/tr");
	By totalAmount = By.xpath("//div[@class='panel panel-info']//h3[@id='totalp']");
	By itemPrice = By.xpath("//tbody[@id='tbodyid']/tr/td[3]");
	By placeOrderBtn = By.xpath("//button[@class='btn btn-success' and text()='Place Order']");
	By placeOrderName = By.xpath("//div[@id='orderModal']//form/div/input[@id='name']");
	By placeOrderCountry = By.xpath("//div[@id='orderModal']//form/div/input[@id='country']");
	By placeOrderCity = By.xpath("//div[@id='orderModal']//form/div/input[@id='city']");
	By placeOrderCreditCard = By.xpath("//div[@id='orderModal']//form/div/input[@id='card']");
	By placeOrderMonth = By.xpath("//div[@id='orderModal']//form/div/input[@id='month']");
	By placeOrderYear = By.xpath("//div[@id='orderModal']//form/div/input[@id='year']");
	By purchaseBtn = By.xpath("//div[@id='orderModal']//div[@class='modal-footer']/button[text()='Purchase']");
	By purchaseSuccessDialog = By.xpath("//div[@class='sweet-alert  showSweetAlert visible']/h2");
	By purchaseSucessOkBtn = By.xpath(
			"//div[@class='sweet-alert  showSweetAlert visible']/div/div[@class='sa-confirm-button-container']/button[text()='OK']");

	// 2. Page Class Constructor
	public CartPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// 3.Page Methods/Actions
	public String getCartPageURL() {
		return eleutil.getPageURL(AppConstants.PRODUCT_PAGE_URL, 5);
	}

	public int validateAmount() {
		eleutil.waitForElementVisibility(totalAmount, 5);
		int total = Integer.parseInt(eleutil.getElementText(totalAmount));
		List<WebElement> priceList = eleutil.getElements(itemPrice);
		int sum = 0;
		for (WebElement p : priceList) {
			sum = sum + Integer.parseInt(p.getText());
		}
		if (total == sum)
			return sum;
		else
			return 0;
	}

	public String placeOrder() {
		eleutil.waitForElementVisibility(placeOrderBtn, 5);

		eleutil.doClick(placeOrderBtn);

		eleutil.waitForElementVisibility(purchaseBtn, 5);
		eleutil.doSendKeys(placeOrderName, AppConstants.PLACE_ORDER_NAME);
		eleutil.doSendKeys(placeOrderCountry, AppConstants.PLACE_ORDER_COUNTRY);
		eleutil.doSendKeys(placeOrderCity, AppConstants.PLACE_ORDER_CITY);
		eleutil.doSendKeys(placeOrderCreditCard, AppConstants.PLACE_ORDER_CREDIT_CARD);
		eleutil.doSendKeys(placeOrderMonth, AppConstants.PLACE_ORDER_MONTH);
		eleutil.doSendKeys(placeOrderYear, AppConstants.PLACE_ORDER_YEAR);
		eleutil.doClick(purchaseBtn);
		eleutil.waitForElementVisibility(purchaseSuccessDialog, 5);
		String successMsg = eleutil.getElementText(purchaseSuccessDialog);
		eleutil.doClick(purchaseSucessOkBtn);
		return successMsg;
	}

	public boolean isCartEmpty() {
		eleutil.doClick(cart);
		return eleutil.getElements(cartItems).isEmpty();
	}

}
