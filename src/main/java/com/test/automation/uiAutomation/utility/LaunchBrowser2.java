package com.test.automation.uiAutomation.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class LaunchBrowser2 {

	public static final String PATH_TO_BROWSER_PROPERTIES_FILE = "src\\main\\java\\com\\test\\automation\\uiAutomation\\configuration\\browsers.properties";
	public static final String PATH_TO_LOG4J_PROPERTIES_FILE = "src\\main\\java\\com\\test\\automation\\uiAutomation\\configuration\\log4j.properties";
	public static final String PATH_TO_CHROME_DRIVER = "drivers\\ChromeDriver\\chromedriver_win32_2.30\\chromedriver.exe";
	public static final String PATH_TO_IE_DRIVER = "drivers\\ChromeDriver\\IEDriverServer.exe";
	public static final String PATH_TO_FIREFOX_DRIVER = "drivers\\geckodriver-v0.16.1-win64\\geckodriver.exe";

	public WebDriver driver = null;
	public Properties prop = null;
	public FileInputStream fis = null;

	/**
	 * Load up the driver base on what is set on the properties file
	 */
	public LaunchBrowser2() {

		prop = new Properties();
		try {
			fis = new FileInputStream(PATH_TO_BROWSER_PROPERTIES_FILE);
			prop.load(fis);
		} catch (IOException e) {
			e.getStackTrace();
			System.exit(1); //abnormal termination
		}
		if (prop.getProperty("driver").contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
			driver = new ChromeDriver();
			this.launchHome();
		} else if (prop.getProperty("driver").contains("Firefox")) {
			System.setProperty("webdriver.gecko.driver", PATH_TO_FIREFOX_DRIVER);
			driver = new FirefoxDriver();
			this.launchHome();
		} else {
			System.setProperty("webdriver.IEDriver.driver", PATH_TO_IE_DRIVER);
			driver = new InternetExplorerDriver();
			this.launchHome();
		}
	}

	/**
	 * Start up a home page Tell the driver to wait for 10 second before
	 * throwing exception Maximize the window
	 */
	private void launchHome() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get(url);
		driver.manage().deleteAllCookies();
	}
}
