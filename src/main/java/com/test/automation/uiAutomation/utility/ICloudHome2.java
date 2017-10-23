package com.test.automation.uiAutomation.utility;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ICloudHome2 {
	// *********************Page Object Start**********************//

	/*****************************************
	 * Object identifier for emailButton()
	 * 
	 * Find elements that contains the word mail in it
	 * xpath = "//*[@href[contains(.,'mail')]]"
	 */
	@FindBy(css="*[href*='mail']")
	List<WebElement> elementsWithMail;

	// *********************Page Object End****************************//

	public static final String ICLOUD_HOME_PAGE = "https://www.icloud.com/";
	private WebDriver driver = null;
	private static Logger log = null;

	public ICloudHome2(WebDriver driver) {
		this.driver = driver;
	}
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger logArg) {
		log = logArg;
	}
	private static boolean logInfo(String message) {
		if (log != null) {
			log.info(message);
			return true;
		} else {
			System.out.println(message);
		}
		return false;
	}

	/******************************************************
	 * Find element with the word mail and it is a link
	 * 
	 * @return WebElement
	 */
	public WebElement emailButton() {
		logInfo("CSS Selector for list of elements containing the word mail ==> " + elementsWithMail.toString());
		List<WebElement> elements = elementsWithMail;
		for (WebElement element : elements) {
			if (element.getTagName().equals("a")) {
				logInfo("List of possible emails link ==> " + element.toString() + " , ");
				return element;
			}
		}
		if (elements.size() == 0) {
			throw new NoSuchElementException();
		}
		return elements.get(0); //if no anchor found return what's available
	}

	/***********************************************
	 * Click on the email Icon on the ICloud home page
	 */
	public void clickEmailButton() {
	
		driver.switchTo().defaultContent();
		WebElement element = emailButton();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
}
