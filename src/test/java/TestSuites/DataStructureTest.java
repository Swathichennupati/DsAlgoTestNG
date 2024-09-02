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

import PageFactory.DataStructurePage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

public class DataStructureTest extends BaseTest {

	private DataStructurePage dataStructurePage;

	TestDataFromExcelSheet testDataFromExcelSheet = new TestDataFromExcelSheet();

	public DataStructureTest() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {
		initializeDriver(browser);
		logintotheapplication();
		dataStructurePage = new DataStructurePage(driver);
		driver.get(prop.getProperty("datastructurepage"));
		
	}

	

	@Test(priority = 1, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
    @Parameters("sheetName1")
	public void testHyperlinkNavigation(Method method, String linkText, String expectedTitle,String Url, String pageTitle) {
		
		startTest(method.getName(), "testHyperlinkNavigation.");
		
		try
		{
		if (linkText.equalsIgnoreCase("Practice Questions")) {
			driver.get(prop.getProperty("TimeComplexitypage"));
		}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
			try {
				dataStructurePage.clickingLink(linkText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String actualTitle = dataStructurePage.getTitle();
			assertEquals(actualTitle, expectedTitle, "Titles don't match!");
			System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
		
	}

	

	@Test(priority = 2, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
    @Parameters("sheetName1")
	public void testTryHereNavigation(Method method, String link, String expectedtitle, String Url, String pageTitle) throws Exception {
		
		startTest(method.getName(), "testTryHereNavigation.");
		driver.get(prop.getProperty(Url));
		dataStructurePage.clickontryherebutton();
		Assert.assertEquals(dataStructurePage.getTitle(), pageTitle);
		LoggerLoad.info("The user clicked on the " + Url + "and clicked on tryhere button" + "then navigated to"
				+ expectedtitle);

	}
	
//	@Test(priority=3, dataProvider ="NumberOfLinksTestData",dataProviderClass = DataproviderUtilities.class)
//    @Parameters("sheetName2")
//	public void numberOfLinksInPractiseQuestionsPage(String page,int Expectednumberoflinks)
//	{
//		int numberoflinks=dataStructurePage.getnumberoflinksinPracticeQuestionsPage();
//		Assert.assertEquals(numberoflinks,Expectednumberoflinks );
//		
//	}
	

	@AfterMethod
	public void teardown() throws IOException {
		testDataFromExcelSheet.removeTestData();
		driver.quit();
	}
}
