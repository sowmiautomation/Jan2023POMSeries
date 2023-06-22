package com.qa.opencart.testpages;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pojo.Product;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetUp() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider(name= "productInfoDetails")
	public Object[][] getProductInfoDetails(){
		return new Object[][] {
			{new Product("MacBook","MacBook Pro", "Apple", "In Stock", "MacBook Pro", "$2,000.00")}
			
		};
	}

	
	@Test(dataProvider="productInfoDetails")
	public void productInfoTest(Product product) {
		resultPage= accPage.doSearchItem(product.getSearchKey());
		productInfoPage= resultPage.clickProduct(product.getSearchItem());
		Map<String, String> productInfoMap= productInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		//Brand=Apple, Availability=In Stock, productTitle=MacBook Pro, extaxprice= $2,000.00, 
		//Product Code=Product 18, Reward Points=800, productprice=$2,000.00
		softAssert.assertEquals(productInfoMap.get("Brand"), product.getBrand());
		softAssert.assertEquals(productInfoMap.get("Availability"), product.getAvailability());
		softAssert.assertEquals(productInfoMap.get("productTitle"), product.getProductTitle());
		softAssert.assertEquals(productInfoMap.get("productPrice"), product.getProductPrice());
		softAssert.assertAll();
		
	}
	
	@Test
	public void AddCartSuccessMsgTest() {
		resultPage= accPage.doSearchItem("MacBook");
		productInfoPage= resultPage.clickProduct("MacBook Pro");
		String actualResult= productInfoPage.verifyAddCartSuccessMsg("5", "MacBook Pro");
		Assert.assertTrue(actualResult.contains("MacBook Pro"));
	}

}
