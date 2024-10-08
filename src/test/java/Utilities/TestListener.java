package Utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TestSuites.BaseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import log4j.LoggerLoad;

import java.util.Objects;

import static Utilities.ExtentTestManager.getTest;

public class TestListener extends BaseTest implements ITestListener {
	
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

	@Override
	public void onStart(ITestContext iTestContext) {
		LoggerLoad.info("I am in onStart method " + iTestContext.getName());
		
	}
	 

    @Override
    public void onFinish(ITestContext iTestContext) {
        LoggerLoad.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extent.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTest test = ExtentManager.extent.createTest(getTestMethodName(iTestResult));
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    	LoggerLoad.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
    	LoggerLoad.info(getTestMethodName(iTestResult) + " test is failed.");

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    	LoggerLoad.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    	LoggerLoad.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}