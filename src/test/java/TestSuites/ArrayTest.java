package TestSuites;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;
import PageFactory.ArrayPage;


public class ArrayTest extends BaseTest {
	
	private ArrayPage arrayPage;

	private Map<String, String> data;
	TestDataFromExcelSheet testDataFromExcelSheet=new TestDataFromExcelSheet();
	DataproviderUtilities dataproviderUtilities=new DataproviderUtilities();
	
	public ArrayTest() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		initializeDriver(browser);
		logintotheapplication();
		arrayPage = new ArrayPage(driver);
		driver.get(prop.getProperty("arrayPage"));
	

	}

	
	@Test(priority = 1, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
    @Parameters("sheetName1")
	public void testHyperlinkNavigation(String linkText, String expectedTitle,String Url, String pageTitle) throws InterruptedException {
		
			try {
				arrayPage.clickingLink(linkText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(300);
			String actualTitle = arrayPage.getTitle();
			assertEquals(actualTitle, expectedTitle, "Titles don't match!");
			System.out.println("Test passed: Navigated to " + expectedTitle + " page.");		
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


