package pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerBase implements ITestListener {
	private static Logger log = null;
	private WebDriver driver = null;

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
	public void onTestStart(ITestResult result) {
		logInfo("onTestStart =======>" + result.getName());
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess  =======>" + result.getName());
	}
	public void onTestFailure(ITestResult result) {
		logInfo("onTestFailure ========>" + result.getName());
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
			FileUtils.copyFile(src, new File("screenshot" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void onTestSkipped(ITestResult result) {
		logInfo("onTestSkipped  ==========>" + result.getName());

	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	

	}
	public void onStart(ITestContext context) {
		

	}
	public void onFinish(ITestContext context) {
		System.out.println("Test completed =======>" + context.getName());

	}
}
