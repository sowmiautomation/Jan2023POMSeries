package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtils;
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		eleUtils= new ElementUtil(this.driver);
	}
	
	//By locators
	private By returnCustomerTxt= By.xpath("//h2[contains(text(),'Return')]");
	private By email= By.name("email");
	private By password= By.name("password");
	private By loginBtn= By.xpath("//input[@type='submit']");
	private By warnMsg= By.cssSelector(".alert");
	private By logo= By.xpath("//img[contains(@src,'opencart')]");
	private By searchBar= By.name("search");
	private By searchBtn= By.xpath("//button/i[@class='fa fa-search']");
	private By addCartBtn= By.xpath("//button//span[@id='cart-total']");
	private By newCustomerTxt= By.xpath("//h2[contains(text(),'New')]");
	private By continueBtn= By.linkText("Continue");
	private By rightList= By.id("column-right");
	private By footerLink= By.xpath("//footer//div[@class='row']");
	private By loginErrorMsg= By.cssSelector("div.alert.alert-danger.alert-dismissible");



	//Actions
	@Step("getting login page title")
	public String verifyPageTitle() {
		return eleUtils.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
	}

	@Step("getting login page url")
	public String verifyCurrentUrl() {
		return eleUtils.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE,AppConstants.SHORT_DEFAULT_WAIT);
	}

	@Step("check logo is displayed")
	public boolean verifyPageLogo(){	
		return eleUtils.checkElementIsDisplayed(logo);	
	}
	
	@Step("Check placeholder is present")
	public String verifySearchPlaceholder() {
		return eleUtils.doGetAttributeValue(searchBar, "placeholder");
	}
	
	@Step("Checking addcart btn text")
	public String verifyaddCartBtnText() {
		return eleUtils.doGetElementText(addCartBtn);
	}
	
	@Step("getting customer title")
	public String verifyNewCustomerTitle() {
		return eleUtils.doGetElementText(newCustomerTxt);
	}
	
	@Step("getting return customer title")
	public String verifyReturnCustomerTitle() {
		return eleUtils.doGetElementText(returnCustomerTxt);
	}
	
	@Step("Check rightside link{0}")
	public List<String> verifyRightSidelinkList(String linkName) {
		
		List<WebElement>rightLinkList= eleUtils.waitForElementsVisible(rightList, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String>rightLinks= new ArrayList<String>();
		for(WebElement e:rightLinkList) {
			String text= e.getText();
			rightLinks.add(text);
		}	
		return rightLinks;
	}
	
	@Step("Check footerlink{0}")
	public List<String> verifyFooterLinks(String linkName) {
		List<WebElement> footerLinkList= eleUtils.waitForElementsVisible(footerLink, AppConstants.MEDIUM_DEFAULT_WAIT );
		List<String>footerLinks= new ArrayList<String>();
		for(WebElement e: footerLinkList) {
			String text= e.getText();
			footerLinks.add(text);
		}
		return footerLinks;
	}
	
	@Step("redirect to registerpage")
	public RegisterPage docreateNewUser() {
		eleUtils.doClick(continueBtn);
		return new RegisterPage(driver);
	}
	
	@Step("login with username{0} and pwd{1} ")
	public AccountPage doLogin(String emailId, String pwd) {
		System.out.println("Correct creds are:"+ emailId + ":" + pwd);
		eleUtils.waitForElementVisible(email, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(emailId);
		eleUtils.doSendKeys(password, pwd);
		eleUtils.doClick(loginBtn);
		return new AccountPage(driver);	
	}
	
	@Step("login with wrong usernamer{0} and password{1}")
	public boolean doLoginErrorMsg(String emailId, String pwd) {
		eleUtils.waitForElementVisible(email, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(emailId);
		eleUtils.doSendKeys(password, pwd);
		eleUtils.doClick(loginBtn);
		String errorMsg= eleUtils.doGetElementText(loginErrorMsg);
		if(errorMsg.equals(AppConstants.LOGIN_ERROR_MSG)) {
			return true;
		}
		return false;
		
	}





}
