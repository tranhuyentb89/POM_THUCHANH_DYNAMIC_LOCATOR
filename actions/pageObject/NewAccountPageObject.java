package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.bankguru.customers.NewAccountPageUI;
import com.bankguru.customers.RegisterPageUI;

import commons.AbstractPages;

public class NewAccountPageObject extends AbstractPages{
	public NewAccountPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
	private WebDriver driver;
	public void inputEmailToTextBox(String emailInput) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_REGISTER);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_REGISTER, emailInput);
	}
	public void clickToRegisterButton() {
		waitForElementVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}
	public String getUserInfor() {
		waitForElementVisible(driver, RegisterPageUI.USER_INFOR);
		return getTextOfElement(driver, RegisterPageUI.USER_INFOR);
	}
	public String getPasswordInfor() {
		waitForElementVisible(driver, RegisterPageUI.PASS_INFOR);
		return getTextOfElement(driver, RegisterPageUI.PASS_INFOR);
	}
	public boolean isInvalidEmailErrorDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.ERR_INVALID_EMAIL);
		return isControlDisplayed(driver, RegisterPageUI.ERR_INVALID_EMAIL);
	}
	public boolean isErrorBlankEmailDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.ERR_EMAIL_BLANK);
		return isControlDisplayed(driver, RegisterPageUI.ERR_EMAIL_BLANK);
	}
	public void sendkeyTabToEmailID() {
		sendKeyboardToElement(driver, RegisterPageUI.EMAIL_REGISTER, Keys.TAB);
	}
	public void clearTextInEmailTextbox() {
		clearTextInElement(driver, RegisterPageUI.EMAIL_REGISTER);
	}
	public void selectAccountType(String valueToSelect) {
		selectItemInDropdown(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPBOX, valueToSelect);
	}

}
