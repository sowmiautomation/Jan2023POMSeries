package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtils;
	public RegisterPage(WebDriver driver) {
		this.driver= driver;
		eleUtils= new ElementUtil(this.driver);
	}
	
	// By Locatore
	private By pageHeader= By.xpath("//h1");
	private By firstName= By.id("input-firstname");
	private By lastName= By.id("input-lastname");
	private By email= By.id("input-email");
	private By phone= By.id("input-telephone");
	private By passwrd= By.id("input-password");
	private By confrmPasswrd= By.id("input-confirm");
	private By chckBox= By.name("agree");
	private By contBtn= By.xpath("//input[@type='submit']");
	//private By accNotCreate= By.cssSelector("#account-register > div.alert.alert-danger.alert-dismissible");
	private By accCreate= By.xpath("//div[@id='content']/h1");
	private By subscribeYes= By.xpath("//label[@class='radio-inline'][1]/input");
	private By subscribeNo= By.xpath("//label[@class='radio-inline'][2]/input");
	
	private By logoutLink= By.linkText("Logout");
	private By regLink= By.linkText("Register");

	

	public String verifyPageCurrentUrl() {
		return eleUtils.waitForURLContainsAndCapture("account/register", AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public String verifyPageTitle() {
		return eleUtils.waitForTitleIsAndCapture("Register Account", AppConstants.SHORT_DEFAULT_WAIT);	
	}
	
	public String verifyPageHeader() {
		return eleUtils.doGetElementText(pageHeader);
	}
	
	public String positiveAccCreate(String firstname, String lastname, String emailId, String phoneNo, String pwd, String subscribe) {
		eleUtils.doSendKeys(firstName, firstname);
		eleUtils.doSendKeys(lastName, lastname);
		eleUtils.doSendKeys(email, emailId);
		eleUtils.doSendKeys(phone, phoneNo);
		eleUtils.doSendKeys(passwrd, pwd);
		eleUtils.doSendKeys(confrmPasswrd, pwd);
		subscribeTheApp(subscribe);
		eleUtils.doClick(chckBox);
		eleUtils.waitForElementVisible(contBtn, AppConstants.DOUBLE_LONG_DEFAULT_WAIT);
		eleUtils.doClick(contBtn);	
	    String text= eleUtils.doGetElementText(accCreate);
	    doClickAccountLink();
	    return text;
		
	}
	
	private void subscribeTheApp(String subscribe) {
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtils.doClick(subscribeYes);
		}else {
			eleUtils.doClick(subscribeNo);
		}
	}
	
	public void doClickAccountLink()  {
		eleUtils.doClick(logoutLink);
		//Thread.sleep(5000);
		eleUtils.doClick(regLink);
		//Thread.sleep(5000);
		
	}
}
