
package TestSuites;
	
	import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

	import com.dsAlgoWebDriverManager.DriverManager;


	import PageFactory.RegisterPage;
	import PageFactory.loginpage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

	public class RegisterTest extends BaseTest {
		private Map<String, String> data;
		TestDataFromExcelSheet testDataFromExcelSheet=new TestDataFromExcelSheet();
		DataproviderUtilities dataproviderUtilities=new DataproviderUtilities();
		 RegisterPage registerpage;
		 String actual;
		 String expected;

		public RegisterTest() {
			super();
		}

		@Parameters("browser")
		@BeforeMethod
		public void setUp(String browser) throws InterruptedException {

			  DriverManager.initilizedriver(browser);
		        driver = DriverManager.getDriver();
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(240));
		        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));
			driver.get(prop.getProperty("Registrationspage"));
			registerpage=new RegisterPage(driver);

		}
		@Test(priority = 1, dataProvider = "UsernameData",dataProviderClass = DataproviderUtilities.class)
		public void the_user_enters_in_the_usernamefield_and_submits_the_form(String username,String validationmessage,String field) {
			boolean usernamelengthvalidation=registerpage.enterusernamefield(username,validationmessage,field);
			Assert.assertTrue(usernamelengthvalidation);
		}
		
		@Test(priority = 2, dataProvider = "PasswordValidationData",dataProviderClass = DataproviderUtilities.class)
		public void passwordValidationwithincorrecttestdata(String specialChars,String password,String validationmessage) {
			registerpage.validatePassword(specialChars,password);
			expected=validationmessage;
			actual=registerpage.getalertmessage();
			Assert.assertEquals(actual, expected, "They are not matching");
		}
		
		@Test(priority = 3, dataProvider = "PasswordMismatch",dataProviderClass = DataproviderUtilities.class)
		public void PasswordMismatchValidation(String password,String retypepassword,String validationmessage) {
			registerpage.validatepasswordmismatch(password,retypepassword);
			expected=validationmessage;
			actual=registerpage.getalertmessage();
			Assert.assertEquals(actual, expected, "They are not matching");		
		}
		
		@Test(priority = 4)
		public void PasswordMismatchValidation() {
			registerpage.clickonloginbutton();
			actual=registerpage.getTitle();
			expected="Login";
			Assert.assertEquals(actual, expected, "They are not matching");	
		}

		@AfterMethod
		public void teardown() throws IOException {
			testDataFromExcelSheet.removeTestData();

			driver.quit();
		}


		@Test(priority = 2, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
	    @Parameters("sheetName1")
		public void testTryHereNavigation(String link, String expectedtitle, String Url, String pageTitle) throws Exception {
			driver.get(prop.getProperty(Url));
			arrayPage.clickonTryEditor();
			Assert.assertEquals(arrayPage.getTitle(), pageTitle);
			LoggerLoad.info("The user clicked on the " + Url + "and clicked on tryhere button" + "then navigated to"
					+ expectedtitle);

		}
		@Test(priority=3, dataProvider ="NumberOfLinksTestData",dataProviderClass = DataproviderUtilities.class)
	    @Parameters("sheetName2")
		public void numberOfLinksInPractiseQuestionsPage(String page,int Expectednumberoflinks)
		{
			int numberoflinks=arrayPage.getnumberoflinksinPracticeQuestionsPage();
			Assert.assertEquals(numberoflinks,Expectednumberoflinks );
			
		}


}
