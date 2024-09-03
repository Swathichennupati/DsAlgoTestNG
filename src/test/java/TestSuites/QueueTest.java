package TestSuites;

import static org.testng.Assert.assertEquals;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import PageFactory.QueuePage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

	public class QueueTest extends BaseTest {

	    private QueuePage queuePage;

	    TestDataFromExcelSheet testDataFromExcelSheet=new TestDataFromExcelSheet();

		public QueueTest() {

			super();

		}


		@Parameters("browser")
		@BeforeMethod
		public void setUp(String browser) throws InterruptedException {
			initializeDriver(browser);
			logintotheapplication();
			driver.get(prop.getProperty("queuePage"));
			queuePage = new QueuePage(driver);
		}
		

		@Test(priority = 1, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
	    @Parameters("sheetName1")
		public void testhyperlinkNavigation(String linkText, String expectedTitle, String Url, String pageTitle) throws Exception {
			
			try {
				if (linkText.equalsIgnoreCase("Practice Questions")) {
					driver.get(prop.getProperty("implQueueinPython"));
					queuePage = new QueuePage(driver);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				queuePage.clickingLink(linkText);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			//Thread.sleep(300);
			String actualTitle = queuePage.getTitle();
			
			assertEquals(actualTitle, expectedTitle, "Titles don't match!");
			System.out.println("Test passed: Navigated to " + expectedTitle + " page.");

		}


		@Test(priority = 2, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
	    @Parameters("sheetName1")
		public void testTryHereNavigation(String link, String expectedtitle, String Url, String pageTitle) throws Exception {
			
			queuePage = new QueuePage(driver);
			driver.get(prop.getProperty(Url));
			queuePage.TryhereBtn();
			Thread.sleep(300);
			Assert.assertEquals(queuePage.getTitle(), pageTitle);
			LoggerLoad.info("The user clicked on the " + Url + "and clicked on tryhere button" + "then navigated to"
					+ expectedtitle);

		}
		
		@AfterMethod
		public void teardown() throws IOException {
			testDataFromExcelSheet.removeTestData();
			driver.quit();
		}
	}


