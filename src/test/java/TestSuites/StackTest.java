package TestSuites;
	
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import PageFactory.StackPage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

	public class StackTest extends BaseTest {

	    private StackPage stackPage;
	    TestDataFromExcelSheet testDataFromExcelSheet=new TestDataFromExcelSheet();

		public StackTest() {

			super();

		}


		@Parameters("browser")
		@BeforeMethod
		public void setUp(String browser) throws InterruptedException {
			initializeDriver(browser);
			logintotheapplication();
			driver.get(prop.getProperty("stackPage"));
			stackPage = new StackPage(driver);
		}

	   


		@Test(priority = 1, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
	    @Parameters("sheetName1")
		public void testhyperlinkNavigation(String linkText, String expectedTitle, String Url, String pageTitle) {
			
			try {
				if (linkText.equalsIgnoreCase("Practice Questions")) {
					driver.get(prop.getProperty("operationsStack"));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				stackPage.clickingLink(linkText);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			String actualTitle = stackPage.getTitle();
			assertEquals(actualTitle, expectedTitle, "Titles don't match!");
			System.out.println("Test passed: Navigated to " + expectedTitle + " page.");

		}


		@Test(priority = 2, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
	    @Parameters("sheetName1")
		public void testTryHereNavigation(String link, String expectedtitle, String Url, String pageTitle) throws Exception {
			
			driver.get(prop.getProperty(Url));
			stackPage.TryhereBtn();
			Assert.assertEquals(stackPage.getTitle(), pageTitle);
			LoggerLoad.info("The user clicked on the " + Url + "and clicked on tryhere button" + "then navigated to"
					+ expectedtitle);

		}
		
		@AfterMethod
		public void teardown() throws IOException {
			testDataFromExcelSheet.removeTestData();
			driver.quit();
		}

}
