package com.test.automation.uiAutomation.utility;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ICloudPage2 {

	// ********************Page Object Start***************************//
	/**
	 * Id for drop down menu of ICloud Setting and Logout on the home page When
	 * Two-Factor Authentication is off
	 */
	@FindBy(id = "sc2052-label")
	WebElement dropDownIcloudSettingAndLogoutHomePage;

	/**
	 * Sign out drop down on the home page When Two-Factor Authentication is off
	 */
	@FindBy(xpath = ".//*[text()[contains(.,'Sign Out')]]")
	WebElement signOut;

	/*********************** Login to ICloud ********************/
	/**
	 * Log into ICloud home page
	 */
	@FindBy(xpath = ".//iframe")
	WebElement iFrame;

	/**
	 * Id for the user ID text-box
	 */
	@FindBy(id = "appleId")
	WebElement userIdTextBox;

	/**
	 * Id for the password text-box
	 */
	@FindBy(id = "pwd")
	WebElement pwd;

	/**
	 * Id for the sign-in button
	 */
	@FindBy(id = "sign-in")
	WebElement signInButton;

	/**
	 * Check if the page is home page xpath for the Settings icon
	 */
	@FindBy(xpath = ".//*[@href='https://www.icloud.com/#settings']")
	WebElement settingIcon;

	/**
	 * Check if it is Two-Factor Authentication page xpath for the Two-Factor
	 * Authentication text right above the 6 text-box to enter 6 digits
	 * authentication number
	 */
	@FindBy(xpath = "//h1[@class='si-container-title tk-intro']")
	WebElement twoFactorAuthText;

	/************ Logout from ICloud *************************************/
	/**
	 * Id for drop down ICloud Setting and Log out menu When Two-Face
	 * Authentication is on
	 */
	@FindBy(id = "sc3420-label")
	WebElement dropDownIcloudSettingAndLogout;

	// ********************Page Object End******************************//

	private WebDriver driver = null;
	private static Logger log = null;

	public ICloudPage2(WebDriver driver) {

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
	public void logOut() {
		// Logout if Two-Face Authentication is on

		if (isAuthenticationPage()) {
			logInfo("xpath to validate if two-factor authentication page is on ==> " + twoFactorAuthText.toString());

			logInfo("ID for drop down Icloud Setting two-factor on to log out ==> " + dropDownIcloudSettingAndLogout.getAttribute("id"));
			dropDownIcloudSettingAndLogout.click();

			// Logout if Two-Face Authentication is off
		} else if (isHomePage()) {
			logInfo("xpath to validate the homepage. Two-Face Authentication is off ==> " + settingIcon.toString());

			logInfo("ID for drop down Icloud setting. Two-Face Authentication is off ==> "
					+ dropDownIcloudSettingAndLogoutHomePage.getAttribute("id"));
			dropDownIcloudSettingAndLogoutHomePage.click();

			logInfo("xpath for the sign out button. Two-Face Authentication is off ==> " + signOut.toString());
			signOut.click();
		}
	}

	public void login(String emailAddress, String password) {

		driver.switchTo().defaultContent();

		logInfo("iframe id is ==> " + iFrame.getAttribute("id"));
		driver.switchTo().frame(iFrame.getAttribute("id"));

		logInfo("ID for the userName text-box ==> " + userIdTextBox.getAttribute("id"));
		userIdTextBox.clear();
		userIdTextBox.sendKeys(emailAddress);

		logInfo("ID for the password tex-box ==> " + pwd.getAttribute("id"));
		pwd.clear();
		pwd.sendKeys(password);

		logInfo("ID for signIn button ==> " + signInButton.getAttribute("id"));
		signInButton.click();

	}

	public boolean isHomePage() {
		try {
			settingIcon.getText();
			return true;
		} catch (NoSuchElementException e) {

		}
		return false;
	}

	public boolean isAuthenticationPage() {
		try {
			twoFactorAuthText.getText();
			return true;
		} catch (NoSuchElementException e) {

		} catch (WebDriverException e) {

		}

		return false;
	}

	public boolean isLoginPage() {
		try {
			driver.switchTo().defaultContent();
			logInfo("ID of the iFrame switching to ==> " + iFrame.getAttribute("id"));
			driver.switchTo().frame(iFrame.getAttribute("id"));

			logInfo("ID to validate the login page ==> " + userIdTextBox.getAttribute("id") );
			userIdTextBox.getText();

			return true;
		} catch (NoSuchElementException e) {

		}
		return false;

	}

}
