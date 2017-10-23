package com.test.automation.uiAutomation;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.test.automation.uiAutomation.utility.HSSFReadWrite;
import com.test.automation.uiAutomation.utility.ICloudHome2;
import com.test.automation.uiAutomation.utility.ICloudPage2;
import com.test.automation.uiAutomation.utility.LaunchBrowser2;
import com.test.automation.uiAutomation.utility.ICloudListener;

//@Listeners(value=ICloudListener.class)
public class ICloudAccountTest2 {

	Assertion hardAssert = new Assertion();
	LaunchBrowser2 browser = null;
	Logger log = null;

	@BeforeMethod (alwaysRun = true)
	public void setUp() {
		browser = new LaunchBrowser2(ICloudHome2.ICLOUD_HOME_PAGE);
		//initializing the log
		PropertyConfigurator.configure(ICloudMailTest2.PATH_TO_LOG4J_PROPERTIES_FILE);
		log = Logger.getLogger(this.getClass());
		//sharing the log with the listener
		ICloudListener.setLog(log);
	}
	@AfterMethod
	public void tearDown() {
		ICloudListener.setLog(null);
	}
	@DataProvider(name="authentication")
	public static Object[][] credential()
	{		
		return HSSFReadWrite.getCredential();
	}

	@Test(dataProvider = "authentication")
	public void loginTest(String userName, String password) {
		
		//Initializing the page object
		ICloudPage2 icloudPage2 = PageFactory.initElements(browser.driver, ICloudPage2.class);
		ICloudPage2.setLog(log);
		
		log.info("UserName and password provided==> " + userName + " >> " + password);
		icloudPage2.login(userName, password);
		
		hardAssert.assertTrue(icloudPage2.isAuthenticationPage() || icloudPage2.isHomePage());

		browser.driver.close();
	}

	@Test(groups={"smoke", "regression"}, dataProvider = "authentication")
	public void logOutFromEmailHomeTest(String userName, String password) {
			
		ICloudPage2 icloudPage2 = PageFactory.initElements(browser.driver, ICloudPage2.class);
		ICloudPage2.setLog(log);
		
		log.info("UserName provided ==> " + userName +" >> "+ password);
		icloudPage2.login(userName, password);
		
		icloudPage2.logOut();
		
		hardAssert.assertTrue(icloudPage2.isLoginPage());
		browser.driver.close();
	}

}
