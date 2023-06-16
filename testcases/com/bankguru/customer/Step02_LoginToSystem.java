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
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;

public class Step02_LoginToSystem extends AbstractTest {
	WebDriver driver;
	String loginPageUrl;
	String userInfor = "mngr508199";
	String passInfor = "hejAjAm";
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManage.getLoginPage(driver);
	}

	@Test
	public void TC_01_LoginToSystemWithBlankUserID() {
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.sendkeyTabToUserID();
		Assert.assertTrue(loginPage.isErrorBlankUserIDDisplayed());
	}

	@Test
	public void TC_02_LoginToSystemWithBlankPassword() {
		loginPage.sendkeyTabToPassword();
		Assert.assertTrue(loginPage.isErrorBlankPasswordDisplayed());
	}

	@Test
	public void TC_03_LoginToSystemWittInvalidEmail() {
		loginPage.sendkeyToUserID(userInfor + "");
		loginPage.sendkeyToPassword(passInfor);
		loginPage.clicktoLoginButton();
		// loginPage.isErrorAlertDisplayed();
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
