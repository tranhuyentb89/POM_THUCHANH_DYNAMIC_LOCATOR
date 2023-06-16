package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.bankguru.customers.LoginPageUI;

import commons.AbstractPages;
import commons.PageFactoryManage;

public class LoginPageObject extends AbstractPages{
	private WebDriver driver;
	
	public LoginPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public RegisterPageObject clickToHereLink() {
		waitForElementVisible(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return  PageFactoryManage.getRegisterPage(driver);
	}

	public boolean isLoginFormDisplayed() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);
	}

	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}

	public void sendkeyTabToUserID() {
		sendKeyboardToElement(driver, LoginPageUI.USER_LOGIN, Keys.TAB);
	}

	public boolean isErrorBlankUserIDDisplayed() {
		waitForElementVisible(driver, LoginPageUI.ERR_USERID_BLANK);
		return isControlDisplayed(driver, LoginPageUI.ERR_USERID_BLANK);
	}

	public void sendkeyTabToPassword() {
		sendKeyboardToElement(driver, LoginPageUI.PASS_LOGIN, Keys.TAB);		
	}

	public boolean isErrorBlankPasswordDisplayed() {
		waitForElementVisible(driver, LoginPageUI.ERR_PASSWORD_BLANK);
		return isControlDisplayed(driver, LoginPageUI.ERR_PASSWORD_BLANK);
	}

	public void sendkeyToUserID(String values) {
		sendKeyToElement(driver, LoginPageUI.USER_LOGIN, values);
	}

	public void sendkeyToPassword(String passInfor) {
		sendKeyToElement(driver, LoginPageUI.PASS_LOGIN, passInfor);
	}

	public void clicktoLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BTN);
	}


}
