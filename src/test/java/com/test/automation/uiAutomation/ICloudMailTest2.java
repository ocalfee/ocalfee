package com.test.automation.uiAutomation;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.automation.uiAutomation.utility.HSSFReadWrite;
import com.test.automation.uiAutomation.utility.ICloudEmail2;
import com.test.automation.uiAutomation.utility.ICloudHome2;
import com.test.automation.uiAutomation.utility.ICloudListener;
import com.test.automation.uiAutomation.utility.ICloudPage2;
import com.test.automation.uiAutomation.utility.LaunchBrowser2;

public class ICloudMailTest2 {
	public static final String SEND_TO_EMAILS = "ohcalfee@icloud.com";
	public static final String EMAIL_BODY = "I can send emails";

	public static final String PATH_TO_LOG4J_PROPERTIES_FILE = "src\\main\\java\\com\\test\\automation\\uiAutomation\\configuration\\log4j.properties";

	SoftAssert sAssert = new SoftAssert();
	LaunchBrowser2 browser = null;
	Logger log = null;

	@BeforeMethod (alwaysRun = true)
	public void setUp() {
		// System.setProperty("log4j.debug", "");
		browser = new LaunchBrowser2(ICloudEmail2.EMAIL_HOME_PAGE);

		PropertyConfigurator.configure(PATH_TO_LOG4J_PROPERTIES_FILE);
		log = Logger.getLogger(this.getClass());
		ICloudListener.setLog(log);

	}
	@AfterMethod
	public void tearDown() {
		ICloudListener.setLog(null);
	}

	@DataProvider(name = "authentication")
	public static Object[][] credential() {
		return HSSFReadWrite.getCredential();
	}

	@Test(groups = {"regression"}, dataProvider = "authentication")
	public void sendEmailTest(String userName, String password) {

		ICloudPage2 icloudPage2 = PageFactory.initElements(browser.driver, ICloudPage2.class);
		ICloudPage2.setLog(log);
		ICloudEmail2 iCloudEmail2 = PageFactory.initElements(browser.driver, ICloudEmail2.class);
		ICloudEmail2.setLog(log);
		ICloudHome2 iCloudHome2 = PageFactory.initElements(browser.driver, ICloudHome2.class);
		ICloudHome2.setLog(log);

		String subject = UUID.randomUUID().toString();
		log.info("The automatically generated email subject UUID ==> " + subject);
		// Login to ICloud
		if (icloudPage2.isLoginPage()) {
			log.info("userName and password ==> " + userName + " >> " + password);
			icloudPage2.login(userName, password);
		}
		if (icloudPage2.isHomePage()) // Check if this is a home page if so
										// click on the email icon
		{
			iCloudHome2.clickEmailButton();
		}
		/**
		 * Composing email and send it
		 */
		if (iCloudEmail2.isEmailPage()) {
			iCloudEmail2.composeMailAndSend(SEND_TO_EMAILS, subject, EMAIL_BODY);
		}

		if (iCloudEmail2.isEmailPage()) {
			// make sure DOM is updated
			iCloudEmail2.clickOnInbox();
			iCloudEmail2.clickOnSentBox();
			iCloudEmail2.clickOnInbox();
		}

		if (!iCloudEmail2.isInbox()) {
			log.info("Not in inbox");
		}
		//sAssert.assertTrue(iCloudEmail2.isEmailReceived(subject));
		iCloudEmail2.clickOnSentBox();
		if (!iCloudEmail2.isSentBox()) {
		log.info("Not in sentbox");	
		}
		
		sAssert.assertTrue(iCloudEmail2.isEmailReceived(subject));
		sAssert.assertAll();	
		browser.driver.close();
	}
	
	@Test(groups = {"smoke", "regression"}, dataProvider = "authentication", enabled =false)
	public void logOutICloudHomeTest(String userName, String password) {

		ICloudPage2 icloudPage2 = PageFactory.initElements(browser.driver, ICloudPage2.class);
		ICloudPage2.setLog(log);
		
		if (icloudPage2.isLoginPage()) {
	
			log.info("userName and password provided ==> " + userName + " >> " + password);
			icloudPage2.login(userName, password);
		}

		ICloudHome2 iCloudHome2 = PageFactory.initElements(browser.driver, ICloudHome2.class);
		ICloudHome2.setLog(log);

		if (icloudPage2.isHomePage()) {
			
			iCloudHome2.clickEmailButton();
		}

		ICloudEmail2 iCloudEmail2 = PageFactory.initElements(browser.driver, ICloudEmail2.class);
		ICloudEmail2.setLog(log);
		
		if (iCloudEmail2.isEmailPage()) {
			
			iCloudEmail2.logOut();
		}
		
		sAssert.assertTrue(icloudPage2.isHomePage());
		sAssert.assertAll();
		browser.driver.close();

	}

}
