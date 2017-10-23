package com.test.automation.uiAutomation.utility;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//, ISuiteListener, IInvokedMethodListener
public class ICloudListener implements ITestListener {
	private static Logger log = null;

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
//		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(src, new File("c:\\test\\screenshot" + ".png"));

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
