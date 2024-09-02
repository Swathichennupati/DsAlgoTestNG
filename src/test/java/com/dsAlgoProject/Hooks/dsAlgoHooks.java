package com.dsAlgoProject.Hooks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import
 java.util.Date;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver; 

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
    public ExtentTest test;
    File sourcePath;
    TestDataFromExcelSheet TestDataFromExcelsheet = new TestDataFromExcelSheet();
    DriverManager drivermanager = new DriverManager();

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() throws Throwable {
        // One-time setup before all tests in the suite (optional)
        prop = DriverManager.getproperties();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Throwable {
        // One-time setup before all test methods in the class
        lock.lock();
        try {
            String browser = ConfigReader.getBrowserType();
            DriverManager.initilizedriver(browser); 
        } finally {
            lock.unlock();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Throwable {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(240));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));
        driver.get(prop.getProperty("loginpage"));
        if (driver != null) {
            System.out.println("driver in initilization is not null");

        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws IOException {
        // One-time cleanup after all test methods in the class
        lock.lock();
        try {
            if (driver != null) {
                System.out.println("driver is quitting");
                DriverManager.quitDriver();
                TestDataFromExcelsheet.removeTestData();
            }
        } finally {
            lock.unlock();
        }
    }
}


