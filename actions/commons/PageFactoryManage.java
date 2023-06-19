package commons;

import org.openqa.selenium.WebDriver;

import pageObject.DeleteCustomerPageObject;
import pageObject.EditCustomerPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewAccountPageObject;
import pageObject.NewCustomerPageObject;
import pageObject.RegisterPageObject;

public class PageFactoryManage {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}

	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}

	public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
		return new DeleteCustomerPageObject(driver);
	}

	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}
}
