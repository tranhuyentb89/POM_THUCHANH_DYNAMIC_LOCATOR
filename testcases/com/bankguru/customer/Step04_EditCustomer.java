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
import pageObject.EditCustomerPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewCustomerPageObject;
import pageObject.RegisterPageObject;

public class Step04_EditCustomer extends AbstractTest {
	WebDriver driver;
	String emailInput, customerName, dob, address, city, state, pin, mobile, password, customerID;
	String loginPageUrl;
	String userInfor = "mngr508199";
	String passInfor = "hejAjAm";
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;
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
		newCustomerPage.clickToSubmitButton(driver,"sub");
		Assert.assertTrue(newCustomerPage.isCustomerRegisterSuccessDisplayed());
		customerID = newCustomerPage.getTextOfSuccessFormCreateNewCus("Customer ID");
	}
	
	@Test
	public void TC_03_EditCustomerWithBlank() {
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		Assert.assertTrue(editCustomerPage.isTitleFormDisplayed(driver, "Edit Customer Form"));
		editCustomerPage.sendKeyTabToTextBox(driver, "cusid");
		Assert.assertTrue(editCustomerPage.isErrMsgTextboxDisplayed(driver, "Customer ID is required"));
	}
	
	@Test
	public void TC_03_EditCustomerWithSpace() {
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPage.sendSpaceToTextbox(driver, "cusid");
		Assert.assertTrue(editCustomerPage.isErrMsgTextboxDisplayed(driver, "First character can not have space"));
	}
	
	@Test
	public void TC_04_EnterInvalidCustomerID() {
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPage.inputToDynamicTextbox(driver, randomNumber()+"", "cusid");
		editCustomerPage.clickToSubmitButton(driver, "AccSubmit");
		Assert.assertEquals("Customer does not exist!!", editCustomerPage.getTextInAlert(driver));
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
