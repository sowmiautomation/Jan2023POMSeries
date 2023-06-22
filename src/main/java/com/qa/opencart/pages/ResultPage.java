package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.ElementUtil;

public class ResultPage {
	private WebDriver driver;
	private ElementUtil eleUtils;
	public ResultPage(WebDriver driver) {
		this.driver= driver;
		eleUtils= new ElementUtil(driver);
	}
	
	//By locator
	
	By resultProduct= By.xpath("//div//div[@class='product-thumb']");
	
	
	public String getResultPageTitle(String searchKey) {
		return eleUtils.waitForTitleIsAndCapture(searchKey, 5);
	}
	
	public int getProductTResultCount() {
	 int resultCount= eleUtils.waitForElementsVisible(resultProduct, 5).size();
	 System.out.println("Product search result count==>"+resultCount);
		return resultCount;
	}
	
	public ProductInfoPage clickProduct(String productName) {
		By product= By.linkText(productName);
		eleUtils.doClick(product);
		return new ProductInfoPage(driver);
	}
	

}
