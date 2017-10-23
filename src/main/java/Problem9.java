import org.openqa.selenium.WebDriver;

public class Problem9 {

	public Problem9() {
		// TODO Auto-generated constructor stub
	}

	@Test
	@Parameters("tier")
	public void testAMF(@Optional String tier) throws Exception {
	
		/*
		 * Removing the try catch block because it is necessary for the exception to be thrown.
		 */
		LoginUtil.login(driver, tier);
	
		TestPage page = new TestPage(driver);
		page.euroDisclosureLink.click();
	
		page.btnNext.click();
	
		log.info("Successfully Added French Dislosure circuit");
	
		page.euroDisclosureLink.click();
	
		page.unknownInput.click();
	
		page.messageRequiredLink.click();
	
		new Select(page.announcementTypeDropdown).selectByVisibleText(page.announcementTypeSelection);	
		new Select(page.countryCodeSelector).selectByVisibleText(page.countryCodeSelection);	
		new Select(page.entityCountryCode).selectByVisibleText(page.entityCountryCodeSelection);

		page.entityIsin.sendKeys(page.entityIsinTxt);
	
		new Select(page.releaseThemesSelector).selectByVisibleText(page.releaseThemesSelection);
	
		page.updateButton.click();
	
		log.info("Successfully Completed French Disslosure Form");
	}

}
