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

public class Step01_RegisterToSystem extends AbstractTest {

	WebDriver driver;

	String emailInput = "tranhuyentb" + randomNumber() + "@yopmail.com";
	String loginPageUrl;
	String userInfor;
	String passInfor;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManage.getLoginPage(driver);
	}

	@Test
	public void TC_01_registerToSystem_EmailIDNotBlank() {
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		registerPage = loginPage.clickToHereLink();
		registerPage.sendkeyTabToEmailID();
		Assert.assertTrue(registerPage.isErrorBlankEmailDisplayed());
	}

	@Test
	public void TC_02_registerToSystem_InputValidEmail() {
		registerPage.inputEmailToTextBox(randomNumber() + "");
		Assert.assertTrue(registerPage.isInvalidEmailErrorDisplayed());

	}

	@Test
	public void TC_03_registerToSystem_Success() {
		registerPage.clearTextInEmailTextbox();
		registerPage.inputEmailToTextBox(emailInput);
		registerPage.clickToRegisterButton();

		userInfor = registerPage.getUserInfor();
		passInfor = registerPage.getPasswordInfor();
		System.out.println(userInfor + " --- " + passInfor);
		Step02_LoginToSystem login = new Step02_LoginToSystem();
	}

	// @Test
	public void TC_02_loginToSystem() {
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
