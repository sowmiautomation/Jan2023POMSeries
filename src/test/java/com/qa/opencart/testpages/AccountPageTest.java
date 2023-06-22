package com.qa.opencart.testpages;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetUp() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void PageCurrentUrlTest() {
		String actualUrl= accPage.verifyPageCurrentUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE), "The URL is not matched");
	}
	
	@Test
	public void PageTitleTest() {
		String actualText= accPage.verifyPageTitle();
		Assert.assertEquals(actualText, AppConstants.ACCOUNT_PAGE_TITLE, "The Title is not matched");
	}
	
	@Test
	public void LogoutLinkExitTest() {
		boolean flag= accPage.isLogoutLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void accoutLinkExitTest() {
		boolean flag= accPage.isAccountLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void accPageHeaderCountTest() {
		List<String> headerList= accPage.getAccountHeaderList();
		Assert.assertEquals(headerList.size(), 4);
	}
	
	@Test
	public void accPageHeaderList() {
		List<String>actualHeaderList= accPage.getAccountHeaderList();
		Assert.assertEquals(actualHeaderList, AppConstants.EXP_ACCOUNT_HEADERS_LIST);
	}
	
	
	
}
