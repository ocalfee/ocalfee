package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
	protected WebDriver driver = null;
	
	public PageBase(WebDriver driver){
	
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	

}
