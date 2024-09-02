
package TestSuites;
	
import static Utilities.ExtentTestManager.startTest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageFactory.NumpyNinjaPage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;

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
		public void testtoselectfromdropdown(Method method, String option,String expectedtitle) {
			
			startTest(method.getName(), "testtoselectfromdropdown.");
			numpyninjapage.selectFromDropdown(option);
			actual = numpyninjapage.getTitle();
			expected = expectedtitle;
			Assert.assertEquals(actual, expected, "Title does not match");

		}
		@Test(priority = 2, dataProvider = "dropdownOptions",dataProviderClass = DataproviderUtilities.class)
		public void testtoselectfromgetstartedbutton(Method method, String option,String expectedtitle) {
			
			startTest(method.getName(), "testtoselectfromgetstartedbutton.");
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
	
