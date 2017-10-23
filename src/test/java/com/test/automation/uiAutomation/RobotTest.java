package com.test.automation.uiAutomation;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.automation.uiAutomation.utility.HSSFReadWrite;

import org.testng.asserts.Assertion;
import com.test.automation.uiAutomation.utility.RobotTestListener;

@Listeners(value=RobotTestListener.class)
public class RobotTest extends TestBase {

	static final String robotFile = "robots.txt";
	static final String sitemapFile = "sitemap.xml";
	static final String yahoo = "https://search.yahoo.com/";
	static final String google = "https://www.google.com/";
	
	public RobotTest() {
	}
	
	@DataProvider(name = "url-list")
	public static Object[] urlList() {
		return new Object [] {yahoo,google};
	}
	
	@Test(dataProvider = "url-list")
	public void sitemapTest(String url) {
		String sitemapText = getFileText(url, sitemapFile);

		new Assertion().assertFalse(sitemapText.isEmpty());
		log.info(url + ":\tsitemap.xml - PASS");
	}
	
	@Test(dataProvider = "url-list")
	public void robotTest( String url ) {
		String robotText = getFileText(url, robotFile);
		new Assertion().assertFalse(robotText.isEmpty());
		log.info(url + ":\tRobots.txt - PASS");
	}
	
	@Test(dataProvider = "url-list")
	public void nonZeroPageTitle(String url)
	{
		driver.get(url);
		new Assertion().assertFalse(driver.getTitle().isEmpty());
		log.info(url + ":\tNonzero page title - PASS");
	}
	
	@Test(dataProvider = "url-list")
	public void nonZeroMetaKeywords(String url)
	{
		driver.get(url);
		WebElement metaKeywordsEle = driver.findElement(By.cssSelector("meta[name=\"keywords\"]"));
		String metaKeywordsContent = metaKeywordsEle.getAttribute("content");
		new Assertion().assertFalse(metaKeywordsContent.isEmpty());
		log.info(url + ":\tNonzero meta keywords - PASS");
	}
	
	@Test(dataProvider = "url-list")
	public void nonZeroMetaDescription(String url)
	{
		driver.get(url);
		WebElement metaDescriptionEle = driver.findElement(By.cssSelector("meta[name=\"description\"]"));
		String metaDescriptionContent = metaDescriptionEle.getAttribute("content");
		new Assertion().assertFalse(metaDescriptionContent.isEmpty());
		log.info(url + ":\tNonzero meta description - PASS");
	}
	
	@Test(dataProvider = "url-list")
	public void allImagesOnPageHaveNonZeroAltTags(String url){
		driver.get(url);
		List<WebElement> imageTagList = driver.findElements(By.cssSelector("img"));
		
		for(WebElement imageTag: imageTagList)
		{
			String altContent = imageTag.getAttribute("alt");
			new SoftAssert().assertFalse(altContent.isEmpty());
		}
		new SoftAssert().assertAll();
		log.info(url + ":\tAll images on page have nonzero alt tags - PASS");
	}
	
	public String getFileText(String url, String fileName) {
		String fileContents="";
		try {
			driver.get(url + fileName);
			WebElement body = driver.findElement(By.cssSelector("body"));
			fileContents = body.getText();
		} catch (org.openqa.selenium.NoSuchSessionException e) {
		} catch (org.openqa.selenium.NoSuchElementException e) {
		}
		return fileContents;
	}
}
