package com.dsAlgoProject.Hooks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.dsAlgoWebDriverManager.DriverManager;

import Utilities.ConfigReader;
import Utilities.ExtentReportManager;
import Utilities.RetryforFailedScenarios;
import Utilities.TestDataFromExcelSheet;


public class dsAlgoHooks {

	public static Properties prop;
	public WebDriver driver;
	public static InputStream input;
	private static final Lock lock = new ReentrantLock();
	ExtentTest test;
	File sourcePath;
	TestDataFromExcelSheet TestDataFromExcelsheet = new TestDataFromExcelSheet();
	DriverManager drivermanager = new DriverManager();

	@BeforeMethod
	public void setUp() throws Throwable {

		//ExtentTest test = ExtentReportManager.getExtentReports().createTest(scenario.getName());
		//ExtentReportManager.setTest(test);
		prop = DriverManager.getproperties();
		lock.lock();
		try {
			String browser = ConfigReader.getBrowserType();
			DriverManager.initilizedriver(browser);
		} finally {
			lock.unlock();
		}
		driver = DriverManager.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(240));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));
		driver.get(prop.getProperty("loginpage"));
		if (driver != null) {
			System.out.println("driver in initilization is not null");

		}

	}

//	@AfterMethod
//	public void afterStep() throws IOException {
//		test = ExtentReportManager.getTest();
//
//		if (scenario.isFailed()) {
//			RetryforFailedScenarios.retry(() -> {
//				System.out.println("Retrying failed scenario: " + scenario.getName());
//			});
//		}
//		captureScreenshotOnFailure(scenario);
//	}

	@AfterMethod
	public void tearDown() throws IOException {

	//	test = ExtentReportManager.getTest();

//		if (scenario.isFailed()) {
//			captureScreenshotOnFailure(scenario);
//		}
//
//		else {
//			test.pass("Test passed" + scenario.getName());
//		}
		lock.lock();
		try {
			if (driver != null) {
				System.out.println("driver is quitting");
				DriverManager.quitDriver();
				TestDataFromExcelsheet.removeTestData();
//				ExtentReportManager.flushReports();

			}

		} finally {
			lock.unlock();
		}
	}

//	private void captureScreenshotOnFailure(Scenario scenario) throws IOException {
//		if (scenario.isFailed()) {
//
//			String screenshotName = scenario.getName().replaceAll(" ", "_");
//			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//			String destFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\"
//					+ screenshotName + "_" + timestamp + ".png";
//			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			File destFile = new File(destFilePath);
//
//			FileUtils.copyFile(sourcePath, destFile);
//
//			scenario.attach(FileUtils.readFileToByteArray(sourcePath), "image/png", screenshotName);
//
//			test.addScreenCaptureFromPath(destFilePath, "Screenshot on failure");
//			test.fail("Test failed: " + scenario.getName());
//		}
//	}
}
