package com.bankguru.customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObject.DeleteCustomerPageObject;
import pageObject.EditCustomerPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewAccountPageObject;
import pageObject.NewCustomerPageObject;
import pageObject.RegisterPageObject;

public class Step05_Account extends AbstractTest {
	WebDriver driver;
	String emailInput, customerName, dob, address, city, state, pin, mobile, password, customerID1, AccountID,
			DateOpening;
	String loginPageUrl;
	String userInfor = "mngr508199";
	String passInfor = "hejAjAm";
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;
	DeleteCustomerPageObject deleteCustomerPage;
	NewAccountPageObject newAccountPage;

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
	public void TC_02_CreateNewCustomer() {
		CreateNewCustomer();
		Assert.assertTrue(newCustomerPage.isCustomerRegisterSuccessDisplayed());
		customerID1 = newCustomerPage.getTextOfSuccessFormCreateNewCus(driver, "Customer ID");
	}

	public void CreateNewCustomer() {
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToDynamicTextbox(driver, customerName, "name");
		newCustomerPage.inputToDynamicTextbox(driver, dob, "dob");
		newCustomerPage.inputToAddressbox(address);
		newCustomerPage.inputToDynamicTextbox(driver, city, "city");
		newCustomerPage.inputToDynamicTextbox(driver, state, "state");
		newCustomerPage.inputToDynamicTextbox(driver, pin, "pinno");
		newCustomerPage.inputToDynamicTextbox(driver, mobile, "telephoneno");
		newCustomerPage.inputToDynamicTextbox(driver, emailInput, "emailid");
		newCustomerPage.inputToDynamicTextbox(driver, password, "password");
		newCustomerPage.clickToSubmitButton(driver, "sub");
	}

	@Test
	public void TC_03_NewAccount_BlankCusID() {
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.sendKeyTabToTextBox(driver, "cusid");
		Assert.assertTrue(newAccountPage.isErrMsgTextboxDisplayed(driver, "Customer ID is required"));
	}

	@Test
	public void TC_04_NewAccount_CusIDNotChar() {
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.inputToDynamicTextbox(driver, city, "cusid");
		Assert.assertTrue(newAccountPage.isErrMsgTextboxDisplayed(driver, "Characters are not allowed"));
	}

	@Test
	public void TC_05_NewAccount_CusIDNotFirstSpace() {
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.sendSpaceToTextbox(driver, "cusid");
		Assert.assertTrue(newAccountPage.isErrMsgTextboxDisplayed(driver, "First character can not have space"));
	}

	@Test
	public void TC_06_NewAccount_BlankInitialDeposit() {
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.sendKeyTabToTextBox(driver, "inideposit");
		Assert.assertTrue(newAccountPage.isErrMsgTextboxDisplayed(driver, "Initial Deposit must not be blank"));
	}

	@Test
	public void TC_07_NewAccount_InitialDepositNotFirstSpace() {
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.sendSpaceToTextbox(driver, "inideposit");
		Assert.assertTrue(newAccountPage.isErrMsgTextboxDisplayed(driver, "First character can not have space"));
	}

	@Test
	public void TC_08_NewAccount_CustomerNotExist() {
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.inputToDynamicTextbox(driver, randomNumber() + "", "cusid");
		newAccountPage.selectAccountType("Current");
		newAccountPage.inputToDynamicTextbox(driver, randomNumber() + "", "inideposit");
		newAccountPage.clickToSubmitButton(driver, "button2");
		Assert.assertEquals("Customer does not exist!!", newAccountPage.getTextInAlert(driver));
		newAccountPage.acceptAlert(driver);
	}

	@Test
	public void TC_09_NewAccount_CustomerNotBelongToUser() {
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.inputToDynamicTextbox(driver, pin, "cusid");
		newAccountPage.selectAccountType("Current");
		newAccountPage.inputToDynamicTextbox(driver, randomNumber() + "", "inideposit");
		newAccountPage.clickToSubmitButton(driver, "button2");
		Assert.assertEquals("You are not authorize to Add Account for this Customer",
				newAccountPage.getTextInAlert(driver));
		newAccountPage.acceptAlert(driver);
	}

	@Test
	public void TC_09_NewAccount_Success() {
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.inputToDynamicTextbox(driver, customerID1, "cusid");
		newAccountPage.selectAccountType("Current");
		newAccountPage.inputToDynamicTextbox(driver, randomNumber() + "", "inideposit");
		newAccountPage.clickToSubmitButton(driver, "button2");
		newAccountPage.isTitleFormDisplayed(driver, "Account Generated Successfully!!!");
		AccountID = newAccountPage.getTextOfSuccessFormCreateNewCus(driver, "Account ID");
		System.out.println(AccountID);
		DateOpening = newAccountPage.getTextOfSuccessFormCreateNewCus(driver, "Date of Opening");
		Assert.assertEquals(DateOpening, systemDate());
	}

	public String systemDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
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
