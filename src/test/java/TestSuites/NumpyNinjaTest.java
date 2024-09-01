package TestSuites;
	
	import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

	import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


	import com.dsAlgoWebDriverManager.DriverManager;

	import PageFactory.NumpyNinjaPage;
	import PageFactory.loginpage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

	public class NumpyNinjaTest extends BaseTest {

		private Map<String, String> data;
		TestDataFromExcelSheet testDataFromExcelSheet=new TestDataFromExcelSheet();
		DataproviderUtilities dataproviderUtilities=new DataproviderUtilities();
		String actual;
		String expected;
		public NumpyNinjaTest() {
			super();
		}

		@Parameters("browser")
		@BeforeMethod
		public void setUp(String browser) throws InterruptedException {
			initializeDriver(browser);
			logintotheapplication();
			driver.get(prop.getProperty("numpyninjapage"));
			numpyninjapage = new NumpyNinjaPage(driver);

		}
		
		@Test(priority = 1, dataProvider = "dropdownOptions",dataProviderClass = DataproviderUtilities.class)
		public void testtoselectfromdropdown(String option,String expectedtitle) {
			numpyninjapage.selectFromDropdown(option);
			actual = numpyninjapage.getTitle();
			expected = expectedtitle;
			Assert.assertEquals(actual, expected, "Title does not match");

		}
		@Test(priority = 2, dataProvider = "dropdownOptions",dataProviderClass = DataproviderUtilities.class)
		public void testtoselectfromgetstartedbutton(String option,String expectedtitle) {
			numpyninjapage.clickonthegetstartedbutton(option);
			actual = numpyninjapage.getTitle();
			expected = expectedtitle;
			Assert.assertEquals(actual, expected, "Title does not match");

		}

		@AfterMethod
		public void teardown() throws IOException {
			testDataFromExcelSheet.removeTestData();

			driver.quit();
		}


		
		}
	
