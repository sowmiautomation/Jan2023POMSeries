package com.qa.opencart.testpages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataprovider.ProductDataProvider;
import com.qa.opencart.pojo.Product;

public class SearchDataTest extends BaseTest{
	
	@BeforeClass
	public void searchSetUp() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(dataProvider="productName", dataProviderClass= ProductDataProvider.class)
	public void resultPageSearchCountTest(Product product) {
		resultPage=	accPage.doSearchItem(product.getSearchKey());
		int actualCount= resultPage.getProductTResultCount();
		Assert.assertTrue(actualCount>0);
	}
	
	@Test(dataProvider= "productName", dataProviderClass= ProductDataProvider.class)
	public void resultPageTitleTest(Product product) {
		resultPage=	accPage.doSearchItem(product.getSearchItem());
		String actualTitle= resultPage.getResultPageTitle(product.getSearchItem());
		System.out.println("RearchPage title:"+actualTitle);
		Assert.assertEquals(actualTitle, "Search - "+ product.getSearchItem());
	}
	
	
	@Test(dataProvider= "productName", dataProviderClass= ProductDataProvider.class)
	public void productPageHeaderTest(Product product) {
		resultPage=	accPage.doSearchItem(product.getSearchKey());
		productInfoPage= resultPage.clickProduct(product.getSearchItem());
		String actualHeader= productInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, product.getSearchItem());
	}
	
	
	
	@Test(dataProvider= "productName", dataProviderClass= ProductDataProvider.class)
	public void productPageImagesTest(Product product) {
		resultPage=	accPage.doSearchItem(product.getSearchKey());
		productInfoPage= resultPage.clickProduct(product.getSearchItem());
		int actualImgCount= productInfoPage.getProductImageCount();
		System.out.println("Actal product Images Count:"+ actualImgCount);
		Assert.assertEquals(actualImgCount, product.getSearchCount());
	}
	
	

}



