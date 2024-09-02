package TestSuites;
	
	import static Utilities.ExtentTestManager.startTest;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageFactory.LinkedListPage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

	public class LinkedListTest extends BaseTest {

		private LinkedListPage linkedListPage;
		
		
		TestDataFromExcelSheet testDataFromExcelSheet=new TestDataFromExcelSheet();

		public LinkedListTest() {

			super();

		}

		@Parameters("browser")
		@BeforeMethod
		public void setUp(String browser) throws InterruptedException {
			initializeDriver(browser);
			logintotheapplication();
			driver.get(prop.getProperty("linkedlistpage"));
			linkedListPage = new LinkedListPage(driver);
		}

		@Test(priority = 1, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
	    @Parameters("sheetName1")
		public void testhyperlinkNavigation(Method method, String linkText, String expectedTitle, String Url, String pageTitle) {
			
			startTest(method.getName(), "testhyperlinkNavigation.");
			try {
				if (linkText.equalsIgnoreCase("Practice Questions")) {
					driver.get(prop.getProperty("Introductionpage"));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				linkedListPage.clickingLink(linkText);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			String actualTitle = linkedListPage.getTitle();
			assertEquals(actualTitle, expectedTitle, "Titles don't match!");
			System.out.println("Test passed: Navigated to " + expectedTitle + " page.");

		}


		@Test(priority = 2, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
	    @Parameters("sheetName1")
		public void testTryHereNavigation(Method method, String link, String expectedtitle, String Url, String pageTitle) throws Exception {
			
			startTest(method.getName(), "testTryHereNavigation.");
			driver.get(prop.getProperty(Url));
			linkedListPage.clickontryhere();
			Thread.sleep(500);
			Assert.assertEquals(linkedListPage.getTitle(), pageTitle);
			LoggerLoad.info("The user clicked on the " + Url + "and clicked on tryhere button" + "then navigated to"
					+ expectedtitle);

		}
		
		@AfterMethod
		public void teardown() throws IOException {
			testDataFromExcelSheet.removeTestData();
			driver.quit();
		}
	}


