package com.bankguru.customer;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewCustomerPageObject;
import pageObject.RegisterPageObject;

public class Step03_NewCustomer extends AbstractTest {
	WebDriver driver;
	String emailInput, customerName, dob, address, city, state, pin, mobile, password, customerID;
	String customerNameCreated, dobCreated, genderCreated, addressCreated, cityCreated, stateCreated, pinCreated, mobileCreated, emailCreated;
	String loginPageUrl;
	String userInfor = "mngr508199";
	String passInfor = "hejAjAm";
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	NewCustomerPageObject newCustomerPage;
	HomePageObject homePage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManage.getLoginPage(driver);
		emailInput = "tranhuyentb" + randomNumber() + "@yopmail.com";
		customerName = "Tran Thi Huyen";
		dob = "22/10/2017";
		address = "123 Tay Ho Ha Noi";
		city = "Ha Noi";
		state = "Viet Nam";
		pin = "000000";
		mobile = "098727839";
		password = "12345678";

	}

	@Test
	public void TC_01_LoginToSystem() {
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.sendkeyToUserID(userInfor);
		loginPage.sendkeyToPassword(passInfor);
		loginPage.clicktoLoginButton();
		loginPage.openMultiplePage(driver, "Manager");
		homePage = PageFactoryManage.getHomePage(driver);
		Assert.assertEquals("Manger Id : " + userInfor, homePage.isWelcomeUserNameDisplayed());
		System.out.println(homePage.isWelcomeUserNameDisplayed());

	}

	@Test
	public void TC_02_CustomerNameCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToTextBox(driver, "name");
		String message = newCustomerPage.getTextOfErrMessage("Customer name must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Customer name must not be blank"));
	}

	@Test
	public void TC_03_CustomerNameCouldNotSpecialChar() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToDynamicTextbox(driver, emailInput, "name");
		String message = newCustomerPage.getTextOfErrMessage("Special characters are not allowed");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Special characters are not allowed"));
	}

	@Test
	public void TC_04_CustomerNameCouldNotNumberic() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToDynamicTextbox(driver, randomNumber() + "", "name");
		String message = newCustomerPage.getTextOfErrMessage("Numbers are not allowed");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Numbers are not allowed"));
	}

	@Test
	public void TC_05_CustomerNameCouldNotHaveSpace() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendSpaceToTextbox(driver, "name");
		String message = newCustomerPage.getTextOfErrMessage("First character can not have space");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"First character can not have space"));
	}

	@Test
	public void TC_06_DOBCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToTextBox(driver, "dob");
		String message = newCustomerPage.getTextOfErrMessage("Date Field must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Date Field must not be blank"));
	}

	@Test
	public void TC_07_AddressCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToAddressBox();
		String message = newCustomerPage.getTextOfErrMessage("Address Field must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Address Field must not be blank"));
	}

	@Test
	public void TC_08_AddressCouldNotBeSpecialChar() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToAddressbox(emailInput);
		String message = newCustomerPage.getTextOfErrMessage("Special characters are not allowed");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Special characters are not allowed"));
	}

	@Test
	public void TC_09_CityCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToTextBox(driver, "city");
		String message = newCustomerPage.getTextOfErrMessage("City Field must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"City Field must not be blank"));
	}

	@Test
	public void TC_10_CityCouldNotHaveSpecialChar() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToDynamicTextbox(driver, emailInput, "city");
		String message = newCustomerPage.getTextOfErrMessage("Special characters are not allowed");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Special characters are not allowed"));
	}

	@Test
	public void TC_11_CityCouldNotHaveFirtSpace() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendSpaceToTextbox(driver,"city");
		String message = newCustomerPage.getTextOfErrMessage("First character can not have space");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"First character can not have space"));
	}

	@Test
	public void TC_12_StateCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToTextBox(driver, "state");
		String message = newCustomerPage.getTextOfErrMessage("State must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"State must not be blank"));
	}
	
	@Test
	public void TC_13_StateCouldNotHaveSpecialChar() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToAddressbox(emailInput);
		String message = newCustomerPage.getTextOfErrMessage("Special characters are not allowed");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Special characters are not allowed"));
	}

	
	@Test
	public void TC_14_StateCouldNotNumeric() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToDynamicTextbox(driver, randomNumber()+"", "state");
		String message = newCustomerPage.getTextOfErrMessage("Numbers are not allowed");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Numbers are not allowed"));
	}

	
	@Test
	public void TC_15_PinCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToTextBox(driver, "pinno");
		String message = newCustomerPage.getTextOfErrMessage("PIN Code must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"PIN Code must not be blank"));
	}

	@Test
	public void TC_16_MobileCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToTextBox(driver, "telephoneno");
		String message = newCustomerPage.getTextOfErrMessage("Mobile no must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Mobile no must not be blank"));
	}

	@Test
	public void TC_17_EmailCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToTextBox(driver, "emailid");
		String message = newCustomerPage.getTextOfErrMessage("Email-ID must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver,"Email-ID must not be blank"));
	}

	@Test
	public void TC_18_PasswordCouldNotBeBlank() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.sendKeyTabToTextBox(driver, "password");
		String message = newCustomerPage.getTextOfErrMessage("Password must not be blank");
		System.out.println(message);
		Assert.assertTrue(newCustomerPage.isErrMsgTextboxDisplayed(driver, "Password must not be blank"));
	}
	
	@Test
	public void TC_19_CreadNewCustomerSuccess() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToDynamicTextbox(driver, customerName, "name");
		newCustomerPage.inputToDynamicTextbox(driver, dob, "dob");
		newCustomerPage.inputToAddressbox(address);
		newCustomerPage.inputToDynamicTextbox(driver, city, "city");
		newCustomerPage.inputToDynamicTextbox(driver, state, "name");
		newCustomerPage.inputToDynamicTextbox(driver, pin, "pinno");
		newCustomerPage.inputToDynamicTextbox(driver, mobile, "telephoneno");
		newCustomerPage.inputToDynamicTextbox(driver, emailInput, "emailid");
		newCustomerPage.inputToDynamicTextbox(driver, password, "password");
		Assert.assertTrue(newCustomerPage.isCustomerRegisterSuccessDisplayed());
		customerID = newCustomerPage.getTextOfSuccessFormCreateNewCus(driver, "Customer ID");
		Assert.assertEquals(customerName, newCustomerPage.getTextOfSuccessFormCreateNewCus(driver, "Address"));
		Assert.assertEquals(customerName, newCustomerPage.getTextOfSuccessFormCreateNewCus(driver,"City"));
		Assert.assertEquals(customerName, newCustomerPage.getTextOfSuccessFormCreateNewCus(driver,"State"));
		Assert.assertEquals(customerName, newCustomerPage.getTextOfSuccessFormCreateNewCus(driver,"Pin"));
		Assert.assertEquals(customerName, newCustomerPage.getTextOfSuccessFormCreateNewCus(driver,"Mobile No."));
		Assert.assertEquals(customerName, newCustomerPage.getTextOfSuccessFormCreateNewCus(driver,"Email"));
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
