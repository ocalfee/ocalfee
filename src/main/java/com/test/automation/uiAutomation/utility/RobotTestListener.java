package com.test.automation.uiAutomation.utility;

import org.testng.ITestResult;

import pages.ListenerBase;

public class RobotTestListener extends ListenerBase {

	public RobotTestListener() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onTestStart(ITestResult result) {
	}
	@Override
	public void onTestSuccess(ITestResult result) {
	//	getLog().info(result.getName() + "\tPASS");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		getLog().info(result.getParameters()[0] + ":\t" + result.getName() + "\tFAIL");	
		}
	@Override
	public void onTestSkipped(ITestResult result) {
		getLog().info(result.getParameters()[0] + ":\t" + result.getName() + "\tSKIPPED");	
	}
}
