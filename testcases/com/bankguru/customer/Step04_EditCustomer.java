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
import pageObject.DeleteCustomerPageObject;
import pageObject.EditCustomerPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.NewCustomerPageObject;
import pageObject.RegisterPageObject;

public class Step04_EditCustomer extends AbstractTest {
	WebDriver driver;
	String emailInput, customerName, dob, address, city, state, pin, mobile, password, customerID1, customerID2;
	String loginPageUrl;
	String userInfor = "mngr508199";
	String passInfor = "hejAjAm";
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;
	DeleteCustomerPageObject deleteCustomerPage;
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
		customerID1 = newCustomerPage.getTextOfSuccessFormCreateNewCus(driver,"Customer ID");
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
		newCustomerPage.inputToDynamicTextbox(driver, "t"+emailInput, "emailid");
		newCustomerPage.inputToDynamicTextbox(driver, password, "password");
		newCustomerPage.clickToSubmitButton(driver,"sub");
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
		editCustomerPage.acceptAlert(driver);
	}

	@Test
	public void TC_05_EnterCusIDNotBelongToUser() {
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPage.inputToDynamicTextbox(driver, pin, "cusid");
		editCustomerPage.clickToSubmitButton(driver, "AccSubmit");
		Assert.assertEquals("You are not authorize to edit this customer!!", editCustomerPage.getTextInAlert(driver));
		editCustomerPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_06_EnterValidCustomerID() {
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPage.inputToDynamicTextbox(driver, customerID1, "cusid");
		editCustomerPage.clickToSubmitButton(driver, "AccSubmit");
		Assert.assertTrue(editCustomerPage.isTitleFormDisplayed(driver, "Edit Customer"));
	}
	
	
	@Test
	public void TC_07_NoChangeWhenEditCustomer() {
		editCustomerPage.clickToSubmitButton(driver, "sub");
		Assert.assertEquals("No Changes made to Customer records", editCustomerPage.getTextInAlert(driver));
		editCustomerPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_08_DeleteCustomer_CusIDnotBeBlank() {
		editCustomerPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.sendKeyTabToTextBox(driver, "cusid");
		Assert.assertTrue(deleteCustomerPage.isErrMsgTextboxDisplayed(driver, "Customer ID is required"));
	}
	
	@Test
	public void TC_09_DeleteCustomer_CusIDnotHaveFirstSpace() {
		editCustomerPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.sendSpaceToTextbox(driver, "cusid");
		Assert.assertTrue(deleteCustomerPage.isErrMsgTextboxDisplayed(driver, "First character can not have space"));
	}
	
	@Test
	public void TC_10_DeleteCustomer_CusIDnotExist() {
		editCustomerPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.inputToDynamicTextbox(driver, mobile , "cusid" );
		deleteCustomerPage.clickToSubmitButton(driver, "AccSubmit");
		Assert.assertEquals("Do you really want to delete this Customer?", deleteCustomerPage.getTextInAlert(driver));
		deleteCustomerPage.acceptAlert(driver);
		Assert.assertEquals("Customer does not exist!!", deleteCustomerPage.getTextInAlert(driver));
		deleteCustomerPage.acceptAlert(driver);
	}

	@Test
	public void TC_11_DeleteCustomer_CusIDNotBelongToUser() {
		editCustomerPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.inputToDynamicTextbox(driver, pin +"", "cusid" );
		deleteCustomerPage.clickToSubmitButton(driver, "AccSubmit");
		Assert.assertEquals("Do you really want to delete this Customer?", deleteCustomerPage.getTextInAlert(driver));
		deleteCustomerPage.acceptAlert(driver);
		Assert.assertEquals("You are not authorize to delete this customer!!", deleteCustomerPage.getTextInAlert(driver));
		deleteCustomerPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_12_DeleteCustomer_Success() throws Exception {
		CreateNewCustomer();
		customerID2 = newCustomerPage.getTextOfSuccessFormCreateNewCus(driver, "Customer ID");
		editCustomerPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.inputToDynamicTextbox(driver, customerID2, "cusid" );
		deleteCustomerPage.clickToSubmitButton(driver, "AccSubmit");
		Thread.sleep(5000);
		Assert.assertEquals("Do you really want to delete this Customer?", deleteCustomerPage.getTextInAlert(driver));
		deleteCustomerPage.acceptAlert(driver);

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
