package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ICloudHomePage extends PageBase {

	@FindBy(css = "a[href='https://www.icloud.com/#settings']")
	WebElement settings;

	public ICloudHomePage(WebDriver driver) {
		super(driver);

	}

	public boolean isHomePage() {
		try{
			settings.getText();
			return true;
		}catch (Exception e)
		{
			
		}
		return false;
	}

}
