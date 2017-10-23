package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestPage {
	protected WebDriver driver = null;
	
	@FindBy (linkText = "Euro Disclosure")
	WebElement euroDisclosureLink;
	
	@FindBy (id = "btnNext")
	WebElement btnNext;
	/*
	 * If I had the source page I would make this a simple relative xpath or even better I will use cssSelector because it is much faster.
	 */
	@FindBy (xpath = "/html/body/div[2]/div/div/form/div/div[3]/div/div/div[2]/div[2]/table/tbody/tr[3]/td[2]/div[2]/input")
	WebElement unknownInput;
	
	@FindBy (css = "div.messageRequired>ul>li>a")
	WebElement messageRequiredLink;

	@FindBy (id = "announcementType")
	WebElement announcementTypeDropdown;
	String announcementTypeSelection ="Communication relating to …and turnover";
	
	@FindBy (id = "countryCode")
	WebElement countryCodeSelector;	
	String countryCodeSelection = "France";

	@FindBy (id = "entityCountryCode")
	WebElement entityCountryCode;
	String entityCountryCodeSelection = "France";

	@FindBy (id = "entityIsin")
	WebElement entityIsin;
	String entityIsinTxt = "FR0010263202";

	@FindBy (id = "releaseThemes")
	WebElement releaseThemesSelector;
	String releaseThemesSelection = "Sales";

	@FindBy (id = "update")
	WebElement updateButton;
	
	public TestPage(WebDriver driver){
	
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	

}
