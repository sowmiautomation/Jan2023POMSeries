package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtils;
	public ProductInfoPage(WebDriver driver) {
		this.driver= driver;
		eleUtils= new ElementUtil(driver);
	}
	
	//By Locator
	private By productHeader= By.cssSelector("div#content h1");
	private By productImage= By.cssSelector("div#content ul.thumbnails li");
	private By productMetaData= By.xpath("//div[@class='col-sm-4']//ul[@class='list-unstyled'][position()=1]/li");
	private By productPriceData= By.xpath("//div[@class='col-sm-4']//ul[@class='list-unstyled'][position()=2]/li");
	private By productQty= By.cssSelector("input#input-quantity");
	private By addCartBtn= By.id("button-cart");
	private By addCartSuccessMsg= By.cssSelector("div.alert-success ");
	
	private Map<String, String> productInfoMap;
	
	
	public String getProductHeader() {
		return eleUtils.doGetElementText(productHeader);
	}
	
	public int getProductImageCount(){
		return eleUtils.waitForElementsVisible(productImage, AppConstants.MEDIUM_DEFAULT_WAIT).size();
	}
	
	public Map<String, String> getProductInfo() {
		//productInfoMap= new HashMap<String, String>();
		//productInfoMap= new LinkedHashMap<String, String>();// to maintain order
		productInfoMap= new TreeMap<String, String>();// to maintain order
		getProductMetaData();
		getProductPriceData();
		productInfoMap.put("productTitle",  getProductHeader());
		return productInfoMap;
		
	}
	
	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: In Stock
	
	private void getProductMetaData() {
		List<WebElement>metaList= eleUtils.getElements(productMetaData);
		for(WebElement e: metaList) {
			String metaText= e.getText();
			String metaInfo[]=  metaText.split(":");
			String Key= metaInfo[0].trim();
			String value=  metaInfo[1].trim();
			productInfoMap.put(Key, value);
			
		}
	}
	
	//$2,000.00
	//Ex Tax: $2,000.00
	
	private void getProductPriceData() {
		List<WebElement>priceList= eleUtils.getElements(productPriceData);
		String priceValue= priceList.get(0).getText();//$2000.00
		String exTaxprice= priceList.get(1).getText();
		String exTax[]= exTaxprice.split(":");
		String exTaxValue= exTax[1].trim();
		productInfoMap.put("productPrice", priceValue);
		productInfoMap.put("extaxprice", exTaxValue);
	
	}
	
	public String verifyAddCartSuccessMsg(String productQty, String productName) {
		boolean flag= false;
		eleUtils.doSendKeys(this.productQty, productQty);
		//eleUtils.getElement(this.productQty).sendKeys();
		eleUtils.doClick(addCartBtn);
		String msg= eleUtils.doGetElementText(addCartSuccessMsg);
		System.out.println(msg);
		return msg;
		
		
	}
	
	
	
}
