package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexception.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	public Properties prop;
	OptionManager optionManager;
	public static String highlightElement;

	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browserName= prop.getProperty("browser").trim();	
		//String browserName= System.getProperty("browser");
		System.out.println("browser name is : "+ browserName);

		highlightElement= prop.getProperty("highlight");

		optionManager= new OptionManager(prop);
		switch (browserName.toLowerCase()) {
		case "chrome":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			}else {
				// run it on local
				tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			}
			break;
			
		case "firefox":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			}else {
				// run it on local
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			}
			break;
			
		case "edge":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("edge");
			}else {
				// run it on local
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			}
			break;
			
		case "safari":
			tlDriver.set(new SafariDriver());
			break;	
		default:
			
			System.out.println("Plz pass the right browser : "+ browserName);
			throw new FrameworkException("NOBROWSERFOUNDEXCEPTION");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	private void init_remoteDriver(String browserName) {
		System.out.println("running tests on grid with browser: "+ browserName);

		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getEdgeOptions()));
				break;

			default:
				break;
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties intiProp() {
		prop= new Properties();
		FileInputStream ip=null;

		//mvn clean install -Denv="qa" command line, jenkins
		//mvn clean install 
		String envName= System.getProperty("env");
		System.out.println("Running testcases on env:" +envName);

		try {
			if(envName==null){
				ip= new FileInputStream("./src/main/resources/config/qa.config.properties");

			}else{
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip= new FileInputStream("./src/main/resources/config/qa.config.properties");
					break;
				case "dev":
					ip= new FileInputStream("./src/main/resources/config/dev.config.properties");
					break;
				case "stage":
					ip= new FileInputStream("./src/main/resources/config/stage.config.properties");
					break;
				case "uat":
					ip= new FileInputStream("./src/main/resources/config/uat.config.properties");
					break;
				case "prod":
					ip= new FileInputStream("./src/main/resources/config/config.properties");
					break;

				default:
					System.out.println("Plz pass the right env name :"+ envName);	
					throw new FrameworkException("NOTVALIDENVGIVEN");
				}
			}	


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop; 
	}

	/**
	 * take screenshot
	 */
	public synchronized static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

	/*public static String getScreenshot() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
