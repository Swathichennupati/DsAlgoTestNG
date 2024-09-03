package TestSuites;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.dsAlgoWebDriverManager.DriverManager;

import PageFactory.ArrayPage;
import PageFactory.NumpyNinjaPage;
import PageFactory.PracticeQuestionsPage;
import PageFactory.loginpage;
import Utilities.ExtentManager;
import Utilities.TestDataFromExcelSheet;


public class BaseTest {
	public static Properties prop;
	public static InputStream input;
	
	private static final Lock lock = new ReentrantLock();
	
	ExtentTest test;
	File sourcePath;
	TestDataFromExcelSheet TestDataFromExcelsheet = new TestDataFromExcelSheet();
	DriverManager drivermanager = new DriverManager();
	protected WebDriver driver;
    protected NumpyNinjaPage numpyninjapage;
    protected ArrayPage arrayPage;
    protected loginpage loginPage;
    protected PracticeQuestionsPage practiceQuestionsPage;

	public BaseTest()
	{
		prop = DriverManager.getproperties();
	}
	
	@BeforeSuite
	public void setUp() {
	    ExtentManager.createInstance("test-output/extent-report.html");
	}
	
	public void initializeDriver(String browser) throws InterruptedException {
        DriverManager.initilizedriver(browser);
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(240));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));
        driver.get(prop.getProperty("loginpage"));         
        loginPage = new loginpage(driver);
            }
	public void logintotheapplication() throws InterruptedException
	{
		
        loginPage.enterusername(prop.getProperty("username"));
        loginPage.enterpassword(prop.getProperty("password"));
        numpyninjapage = loginPage.clickonloginbutton();
	}
	
	public void driverquit() throws IOException {
		try {
			if (driver != null) {
				System.out.println("driver is quitting");
				DriverManager.quitDriver();
			}
		} finally {
			lock.unlock();
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
	
//	private void captureScreenshotOnFailure(Scenario scenario) throws IOException {
//	if (scenario.isFailed()) {
//
//		String screenshotName = scenario.getName().replaceAll(" ", "_");
//		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//		String destFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\"
//				+ screenshotName + "_" + timestamp + ".png";
//		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		File destFile = new File(destFilePath);
//
//		FileUtils.copyFile(sourcePath, destFile);
//
//		scenario.attach(FileUtils.readFileToByteArray(sourcePath), "image/png", screenshotName);
//
//		test.addScreenCaptureFromPath(destFilePath, "Screenshot on failure");
//
		}
	

