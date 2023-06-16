package pageObject;

import org.openqa.selenium.WebDriver;

import com.bankguru.customers.HomePageUI;

import commons.AbstractPages;

public class HomePageObject extends AbstractPages{
	
	private WebDriver driver;
	public HomePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
	public String isWelcomeUserNameDisplayed() {
		waitForElementVisible(driver, HomePageUI.USER_ID_HOME);
		return getTextOfElement(driver, HomePageUI.USER_ID_HOME);
	}
	
}
