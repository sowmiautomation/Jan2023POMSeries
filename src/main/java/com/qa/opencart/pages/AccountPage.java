package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleUtils;
	
	public AccountPage(WebDriver driver) {
		this.driver= driver;
		eleUtils= new ElementUtil(this.driver);
	}
	
	private By logout= By.linkText("Logout");
	private By myAccount= By.linkText("My Account");
	private By accHeaders= By.cssSelector("#content h2");
	private By search= By.name("search");
	private By searchBtn= By.cssSelector("button.btn-default");
	
	public String verifyPageCurrentUrl() {
		return eleUtils.waitForURLContainsAndCapture(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE, AppConstants.SHORT_DEFAULT_WAIT);	
	}
	
	public String verifyPageTitle() {
		return eleUtils.waitForFullTitleAndCapture(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtils.checkElementIsDisplayed(logout);
	}
	
	public boolean isAccountLinkExist() {
		return eleUtils.checkElementIsDisplayed(myAccount);
	}
	
	public List<String> getAccountHeaderList() {
		//List<WebElement>headersList= driver.findElements(accHeaders);
		List<WebElement>headersList= eleUtils.getElements(accHeaders);
		List<String>headerText= new ArrayList<String>();
		for(WebElement e: headersList) {
			String text= e.getText();
			headerText.add(text);
		}
		return headerText;
	}
	
	public ResultPage doSearchItem(String searchItem) {
		eleUtils.doSendKeys(search, searchItem);
		eleUtils.doClick(searchBtn);
		return new ResultPage(driver);
	}

}
