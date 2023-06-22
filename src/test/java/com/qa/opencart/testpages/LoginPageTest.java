package com.qa.opencart.testpages;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Login Page Design")
@Story("US 101: Design login page for open cart app with title, url. forgot pwd links, user is able to login")
public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.MINOR)
	@Description(" checking login page title test...")
	@Feature("title test")
	@Test
	public void pageTitleTest() {	
		String actualPageTitle= loginPage.verifyPageTitle();
		Assert.assertEquals(actualPageTitle, AppConstants.LOGIN_PAGE_TITLE);	
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description(" checking login page url test...")
	@Feature("url test")
	@Test
	public void pageCurrentUrlTest() {
		String actualCurrentUrl= loginPage.verifyCurrentUrl();
		Assert.assertTrue(actualCurrentUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description(" checking login page logo test...")
	@Feature("logo test")
	@Test
	public void pageLogoTest() {
		boolean flag= loginPage.verifyPageLogo();
		Assert.assertTrue(flag);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description(" checking login page placeholder test...")
	@Feature("placeholder test")
	@Test
	public void searchPlaceholderTest() {
		String actualText= loginPage.verifySearchPlaceholder();
		Assert.assertEquals(actualText, "Search", "The search is not displayed");
	}
	

	@Severity(SeverityLevel.NORMAL)
	@Description(" checking login page btn text test...")
	@Feature("btn text test")
	@Test
	public void addCartBtnTextTest() {
		String actualText= loginPage.verifyaddCartBtnText();
		Assert.assertTrue(actualText.contains("0 item(s)"));
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description(" checking login page customer title test...")
	@Feature("customer text test")
	@Test
	public void newCustomerTitleTest() {
		String actualText= loginPage.verifyNewCustomerTitle();
		Assert.assertEquals(actualText, "New Customer", "The Text is not same as per document");
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description(" checking login page return customer title test...")
	@Feature("return customer text test")
	@Test
	public void ReturnCustomerTitleTest() {
		String actualText= loginPage.verifyReturnCustomerTitle();
		Assert.assertEquals(actualText, "Returning Customer", "The Text is not same as per document");
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description(" checking user is able to login with correct username/password..")
	@Feature("login test")
	@Test(priority=1)
	public void loginTest() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actualAccTitle= accPage.verifyPageTitle();
		Assert.assertEquals(actualAccTitle, "My Account");
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	
	
	
}
