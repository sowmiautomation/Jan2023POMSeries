package com.qa.opencart.testpages;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetUp() {
		regPage= loginPage.docreateNewUser();
	}

	@Test
	public void pageCurrentUrlTest() {	
		String actualUrl= regPage.verifyPageCurrentUrl();
		Assert.assertTrue(actualUrl.contains("account/register" ));
	}

	@Test
	public void pageTitleTest() {
		String actualText= regPage.verifyPageTitle();
		Assert.assertEquals(actualText, "Register Account");
	}

	public String randomGenEmailId() {
		//String emailId= "testautomation"+ System.currentTimeMillis()+ "@gmail.com";
		String emailId = "testautomation"+ UUID.randomUUID()+ "@gmail.com";
		return emailId;
	}

	//	@DataProvider
	//	public Object[][] getAccountData() {
	//		return new Object[][] {
	//			{"Sowmiya", "Chinnasamy",randomGenEmailId(),"345667","8989","yes"},
	//			{"Manoj","Sound",randomGenEmailId(),"989898","76765","no"},
	//			{"Yaaj","Manoj",randomGenEmailId(),"7878898","6565","yes"}
	//		};
	//	}

	@DataProvider(name= "regExcelData")
	public Object[][] getExcelTestData() {
		Object regData[][]= ExcelUtil.getTestData(AppConstants.REG_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider= "regExcelData")
	public void accCreateTest(String firstName, String lastName, String phone, String pwd, String subscribe)  {

		String actualTxt= regPage.positiveAccCreate(firstName, lastName, randomGenEmailId() ,phone, pwd, subscribe);
		Assert.assertTrue(actualTxt.contains("Has Been Created!"));	
	}

}
