package com.qa.opencart.testpages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataprovider.ProductDataProvider;

public class SearchPageTest extends BaseTest{
	
	@BeforeClass
	public void searchSetUp() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(dataProvider="productDataWithSearchKey", dataProviderClass= ProductDataProvider.class)
	public void resultPageSearchCountTest(String searchKey) {
		resultPage=	accPage.doSearchItem(searchKey);
		int actualCount= resultPage.getProductTResultCount();
		Assert.assertTrue(actualCount>0);
	}
	
	@Test(dataProvider= "productDataWithSearchKey", dataProviderClass= ProductDataProvider.class)
	public void resultPageTitleTest(String searchKey) {
		resultPage=	accPage.doSearchItem(searchKey);
		String actualTitle= resultPage.getResultPageTitle(searchKey);
		System.out.println("RearchPage title:"+actualTitle);
		Assert.assertEquals(actualTitle, "Search - "+ searchKey);
	}
	
	
	
	@Test(dataProvider= "productDataWithName", dataProviderClass= ProductDataProvider.class)
	public void productPageHeaderTest(String searchKey, String searchItem) {
		resultPage=	accPage.doSearchItem(searchKey);
		productInfoPage= resultPage.clickProduct(searchItem);
		String actualHeader= productInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, searchItem);
	}
	
	
	
	@Test(dataProvider="productDataWithCount", dataProviderClass= ProductDataProvider.class)
	public void productPageImagesTest(String searchKey, String searchItem, int Searchcount) {
		resultPage=	accPage.doSearchItem(searchKey);
		productInfoPage= resultPage.clickProduct(searchItem);
		int actualImgCount= productInfoPage.getProductImageCount();
		System.out.println("Actal product Images Count:"+ actualImgCount);
		Assert.assertEquals(actualImgCount, Searchcount);
	}
	
	

}
