package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionManager(Properties prop) {
		this.prop= prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co= new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
			co.setBrowserVersion(prop.getProperty("browserversion"));
			//co.setCapability("enableVNC", true);
		}
		
		
		
//		co.addArguments("--window-size=1920,1080");
//		co.addArguments("--no-sandbox");
//		co.addArguments("--disable-gpu");
//		co.addArguments("--disable-crash-reporter");
//		co.addArguments("--disable-extensions");
//		co.addArguments("--disable-in-process-stack-traces");
//		co.addArguments("--disable-logging");
//		co.addArguments("--disable-dev-shm-usage");
//		co.addArguments("--log-level=3");
//		co.addArguments("--output=/dev/null");
		
	
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo= new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
			fo.setBrowserVersion(prop.getProperty("browserversion"));
			//fo.setCapability("enableVNC", true);
		}
		
//		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
//			co.setCapability("browserName", "firefox");
//		}
		
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo= new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			eo.addArguments("--incognito");
		}
		
//		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
//			co.setCapability("browserName", "edge");
//		}
		
		return eo;
	}

	

}
