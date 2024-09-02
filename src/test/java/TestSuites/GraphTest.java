package TestSuites;

import static Utilities.ExtentTestManager.startTest;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageFactory.GraphPage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

public class GraphTest extends BaseTest {

	private GraphPage graphpage;
	private Map<String, String> data;
	
	TestDataFromExcelSheet testDataFromExcelSheet=new TestDataFromExcelSheet();

	public GraphTest() {

		super();

	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {
		initializeDriver(browser);
		logintotheapplication();		
		graphpage = new GraphPage(driver);
		driver.get(prop.getProperty("graphpage"));
	}

	
	@Test(priority = 1, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
    @Parameters("sheetName1")
	public void testHyperlinkNavigation(Method method, String linkText, String expectedTitle,String Url, String pageTitle) {

		startTest(method.getName(), "testHyperlinkNavigation.");
		try {
			if (linkText.equalsIgnoreCase("Practice Questions")) {
				driver.get(prop.getProperty("graphgraphpage"));
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			graphpage.clickingLink(linkText);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String actualTitle = graphpage.getTitle();
		assertEquals(actualTitle, expectedTitle, "Titles don't match!");
		System.out.println("Test passed: Navigated to " + expectedTitle + " page.");

	}


	@Test(priority = 2, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
    @Parameters("sheetName1")
	public void testTryHereNavigation(Method method, String link, String expectedtitle, String Url, String pageTitle) throws Exception {
		
		startTest(method.getName(), "testTryHereNavigation.");
		driver.get(prop.getProperty(Url));
		graphpage.clickontryherebutton();
		Thread.sleep(300);
		Assert.assertEquals(graphpage.getTitle(), pageTitle);
		LoggerLoad.info("The user clicked on the " + Url + "and clicked on tryhere button" + "then navigated to"
				+ expectedtitle);

	}
	
	@AfterMethod
	public void teardown() throws IOException {
		testDataFromExcelSheet.removeTestData();
		driver.quit();
	}
}
