package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.bankguru.customers.NewCustomerPageUI;

import commons.AbstractPageUI;
import commons.AbstractPages;


public class NewCustomerPageObject extends AbstractPages{

	WebDriver driver;
	public NewCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
	public String getTextOfErrMessage(String string) {
		return getTextOfElement(driver, AbstractPageUI.DYNAMIC_BLANK_ERR_MESSAGE_NEW_CUSTOMER, string);
	}
	public void sendKeyTabToAddressBox() {
		sendKeyboardToElement(driver, NewCustomerPageUI.ADDRESS_BOX, Keys.TAB);
	}
	public void inputToAddressbox(String valueInput) {
		sendKeyToElement(driver, NewCustomerPageUI.ADDRESS_BOX, valueInput);
	}
	public boolean isCustomerRegisterSuccessDisplayed() {
		return isControlDisplayed(driver, NewCustomerPageUI.REGIS_SUCCESS_MSG);
	}

}
