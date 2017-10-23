package com.test.automation.uiAutomation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.ListenerBase;

public class TestBase {
	public static final String BROWSER_PROPERTIES = "src\\main\\java\\com\\test\\automation\\uiAutomation\\configuration\\browsers.properties";
	public static final String LOG4J_PROPERTIES = "src\\main\\java\\com\\test\\automation\\uiAutomation\\configuration\\log4j.properties";
	public static final String CHROME_DRIVER = "drivers\\ChromeDriver\\chromedriver_win32_2.30\\chromedriver.exe";
	public static final String IE_DRIVER = "drivers\\ChromeDriver\\IEDriverServer.exe";
	public static final String FIREFOX_DRIVER = "drivers\\geckodriver-v0.16.1-win64\\geckodriver.exe";
	public static final String  URL = "https://www.icloud.com/";
	
	public static final int EXPECTED_IMPLISIT_WAIT_TIME = 10;
	
	protected Logger log = null;
	protected WebDriver driver = null;
	private Properties properties;

	/*
	 * 
	 */
	protected Logger getLogger() {
		if (log == null) {
			PropertyConfigurator.configure(LOG4J_PROPERTIES);
			log = Logger.getLogger(this.getClass());
		}
		return log;
	}

	protected Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			FileInputStream fis;
			try {
				fis = new FileInputStream(BROWSER_PROPERTIES);
				properties.load(fis);
				fis.close();
			} catch (IOException e) {
				e.getStackTrace();
				System.exit(1); // exit with problem
			}
		}
		return properties;
	}
	protected WebDriver getDriver(){
		if(driver == null)
		{
			Properties prop = getProperties();
			if(prop.getProperty("driver").contains("Firefox")){
				driver = new FirefoxDriver();
			}
			else if(prop.getProperty("driver").contains("Chrome")){
				System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
				driver = new ChromeDriver();
			}
			else{
				System.setProperty("webdriver.IEDriver.driver", IE_DRIVER);
				driver = new InternetExplorerDriver();
			}
			driver.manage().timeouts().implicitlyWait(EXPECTED_IMPLISIT_WAIT_TIME, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		return driver;
	}

	@BeforeMethod
	public void setUp() {
		getDriver();
		ListenerBase.setLog(getLogger());
	}

	@AfterMethod
	public void tearDown() {
		ListenerBase.setLog(null);
		log = null;
		this.properties = null;	
		try{
			driver.close();
		}catch( org.openqa.selenium.NoSuchSessionException e){
			
		}
		finally {
			driver = null;
		}
	}

}
