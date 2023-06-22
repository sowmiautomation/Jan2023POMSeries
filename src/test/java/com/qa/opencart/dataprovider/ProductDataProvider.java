package com.qa.opencart.dataprovider;

import org.testng.annotations.DataProvider;

import com.qa.opencart.pojo.Product;

public class ProductDataProvider {
	
	
	
	// Pojo Class DataProvider
	@DataProvider(name="productName")
	public Object[][] getProductTestData() {
		return new Object[][] {
		       {new Product("MacBook", "MacBook Pro", 4)},
		       {new Product("Samsung", "Samsung Galaxy Tab 10.1",7)},
		       {new Product("IMac", "iMac",3)}		  
	};
	}
	
	//Separate Data provider
	
	@DataProvider(name="productDataWithSearchKey")
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook"},
			{"Samsung"},
			{"IMac"}
		};
	}
	
	@DataProvider(name= "productDataWithName")
	public Object[][] getProductDataItem() {
		return new Object[][] {
			{"MacBook", "MacBook Pro" },
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			{"IMac", "iMac"}
		};
	}
	
	@DataProvider(name= "productDataWithCount")
	public Object[][] getProductDataImgItems() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"Samsung", "Samsung Galaxy Tab 10.1",7},
			{"IMac", "iMac",3}
		};
	}
	
	
}
