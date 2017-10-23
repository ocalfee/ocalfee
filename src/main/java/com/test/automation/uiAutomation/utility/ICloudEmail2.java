package com.test.automation.uiAutomation.utility;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ICloudEmail2 
{
	//********************Object Page Start***************************/
	
	/*********************************************
	 * Identifying WebElement for composeMailAndSend()
	 */
	@FindBy (xpath = ".//*[@id='subject-field']/div[2]/input")
	WebElement emailSubjectLine;
	
	@FindBy (xpath = ".//*[@id='sc3425-label']")
	WebElement emailSendButton;
	
	/**********************************************
	 * Identifying WebElement for logOut()
	 */
	  @FindBy (id = "sc3286-label")
	  WebElement iCloudSettingsAndLogoutDropDown;
	  
	  @FindBy (xpath = ".//*[text()[contains(.,'Sign Out')]]")
	  WebElement signOutMenuChoice;
	  
	  /********************************************
	   * Identifying WebElement for isEmailPage()
	   */
	  @FindBy (xpath = ".//*[@title='Inbox']")
	  WebElement inboxLink;
	  
	  /********************************************
	   * Identifying WebElement for isInbox()
	   */
	  @FindBy (xpath = ".//*[@id='search-key-field']/*/div[contains(text(),'Inbox')]")
	  WebElement inboxSearch;
	  
	  /*******************************************
	   * Identifying WebElement for clickOnSentBox()
	   */
	  @FindBy (xpath = ".//*[@id='sc2701-3']/div/label")
	  WebElement sentBoxLink;
	  
	  /*******************************************
	   * Identifying WebElement for isSentBox()
	   */
	  @FindBy (xpath = ".//*[@id='search-key-field']/*/div[contains(text(),'Sent')]")
	  WebElement sentSearchBox;
	  
	  //*******************Page Object End********************************************//

    public static final String EMAIL_HOME_PAGE = "https://www.icloud.com/#mail";
    private WebDriver driver = null;
    private static Logger log = null;
    
	public ICloudEmail2(WebDriver driver)
	{
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
	public void composeMailAndSend(String sendToEmails, String subject, String emailBody)
	{
		driver.switchTo().defaultContent();
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		//Open up compose pop-up while it still a window
		while(driver.getWindowHandles().size() < 2)
		{
			String composeEmailKeyPress = Keys.chord(Keys.ALT, Keys.SHIFT, "n");
			Actions act = new Actions(driver);
			act.click().sendKeys(composeEmailKeyPress).perform(); 		
		}
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles())
		{
			if(!winHandle.equals(winHandleBefore))
			{
				driver.switchTo().window(winHandle);
			}
		}		
		//Find and type into the email text-box
		Actions act1 = new Actions(driver);
		act1.sendKeys(sendToEmails).perform();
		logInfo("The sendTo email address == >> " + sendToEmails);
		
		//Click on the email subject text-box and set subject with unique identifier
		emailSubjectLine.click();	
		act1.sendKeys(subject).perform();
		
		//Tab to the email body and input the email body
		act1.sendKeys(Keys.TAB).perform();
		act1.sendKeys(emailBody).perform();
		
		emailSendButton.click();	
		driver.switchTo().window(winHandleBefore);
	}
	public void logOut()
	{
		logInfo("xpath for ICloud Setting and Logout dropdown list ==> " + iCloudSettingsAndLogoutDropDown.toString());
		iCloudSettingsAndLogoutDropDown.click();
		
		logInfo("xpath for the sign out menu option ==> " + signOutMenuChoice.toString());
		signOutMenuChoice.click();
	}
	public boolean isEmailPage()
	{		
		try
		{
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			
			logInfo("xpath for the inbox ==> " + inboxLink.toString());
			inboxLink.getText();
			return true;
		}
		catch(NoSuchElementException e)
		{
			
		}
		return false;
	}
	public void clickOnInbox()
	{
		inboxLink.click();
	}
	public boolean isInbox()
	{
		try
		{
			inboxSearch.getText();
			return true;
		}
		catch (Exception e)
		{
			
		}
		return false;
	}
	public void clickOnSentBox()
	{
		sentBoxLink.click();
	}
	public boolean isSentBox()
	{
		try
		{
			sentSearchBox.getText();
			return true;
		}
		catch (Exception e)
		{
			
		}
		return false;
	}
	public boolean isEmailReceived(String subject)
	{
		String subjectXPATH = ".//*[text()='" + subject +"']";
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(subjectXPATH)));
			return true;
		}
		catch (Exception e)
		{

		}
		return false;
	}
}
