package com.test.automation.uiAutomation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.automation.uiAutomation.utility.HSSFReadWrite;

import pages.ICloudHomePage;
import pages.ICloudLoginPage;

public class CredentialTest extends TestBase {

	SoftAssert sAssert = new SoftAssert();
	
	@DataProvider(name = "authentication")
	public static Object[][] credential() {
		return HSSFReadWrite.getCredential();
	}

	@Test(dataProvider = "authentication")
	public void loginTest(String userName, String password) {
		driver.get(URL);
		ICloudLoginPage iCloudLoginPage = new ICloudLoginPage(driver);
		ICloudHomePage iCloudHomePage = new ICloudHomePage(driver);
		iCloudLoginPage.login(userName, password);
		sAssert.assertTrue(iCloudHomePage.isHomePage());
	}

}
