package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ICloudLoginPage extends PageBase {


	/*
	 *  id for the login text-box on the login page
	 */
	@FindBy (id = "appleId")
	WebElement appleId;
	
	/*
	 * id for the password text-box on the login page
	 */
	@FindBy (id = "pwd")
	WebElement pwd;
	
	@FindBy (id = "sign-in")
	WebElement signInButton;
	
	@FindBy(css = "#auth-frame")
	WebElement iFrame;
	
	public ICloudLoginPage(WebDriver driver) {
		super(driver);
	}
	public void login(String userName, String password){
		driver.switchTo().defaultContent();

		driver.switchTo().frame(iFrame.getAttribute("id"));
		
		appleId.clear();
		appleId.sendKeys(userName);
		pwd.sendKeys(password);
		signInButton.click();
	}
	public boolean isLoginPage(){
		try{
			appleId.getText();
			return true;
		}
		catch(Exception e)
		{
			
		}
		return false;		
	}
	
	

}
