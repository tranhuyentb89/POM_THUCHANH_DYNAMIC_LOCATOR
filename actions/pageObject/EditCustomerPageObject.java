package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;

public class EditCustomerPageObject extends AbstractPages{
	public EditCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	private WebDriver driver;

	public String isCustomerNotExistDisplayed() {
		return getTextInAlert(driver);
	}
}
