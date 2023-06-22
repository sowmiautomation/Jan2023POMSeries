package com.qa.opencart.testpages;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class LoginPageNegativeTest extends BaseTest{
//	
//	@DataProvider
//	public Object[][] getLoginPageData() {
//		return new Object[][]{
//			{"trick@gmail.com", "123"},
//			{"tst@gmail.com", "test123"},
//			{"$$$$$4", "768"}
//		};
//	}
	
	@DataProvider(name= "loginTestData")
	public Object[][] getExcelTestData() {
		Object loginData[][]= ExcelUtil.getTestData(AppConstants.LOGIN_SHEET_NAME);
		return loginData;
	}
	
	@Test(dataProvider= "loginTestData")
	public void loginErrorMsgTest(String emailId, String pwd) {
		boolean flag= loginPage.doLoginErrorMsg(emailId, pwd);
		Assert.assertTrue(flag);
	}

}
