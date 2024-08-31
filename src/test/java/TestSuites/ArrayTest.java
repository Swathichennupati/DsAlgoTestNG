package TestSuites;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.dsAlgoWebDriverManager.DriverManager;
import PageFactory.NumpyNinjaPage;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;
import PageFactory.ArrayPage;

public class ArrayTest extends BaseTest {

	private Map<String, String> data;
	TestDataFromExcelSheet testDataFromExcelSheet=new TestDataFromExcelSheet();
	public ArrayTest() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		initializeDriver(browser);
		logintotheapplication();
		driver.get(prop.getProperty("arrayPage"));

	}

	@DataProvider(name = "hyperlinkNavigationForArrays")
	public Object[][] getHyperlinkNavigationForArrays() throws IOException {
		Object[][] data=null;
		try
		{
		 data= TestDataFromExcelSheet.getTestData("ArraysClickonLink");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
  return data;
	}

	@Test(priority = 1, dataProvider = "hyperlinkNavigationForArrays")
	public void testHyperlinkNavigation(String linkText, String expectedTitle,String Url, String pageTitle) {
		try
		{
		if (linkText.equalsIgnoreCase("Practice Questions")) {
			driver.get(prop.getProperty("arraysinPython"));
		}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
			try {
				arrayPage.clickingLink(linkText);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String actualTitle = arrayPage.getTitle();
			assertEquals(actualTitle, expectedTitle, "Titles don't match!");
			System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
		
	}

	@AfterMethod
	public void teardown() throws IOException {
		testDataFromExcelSheet.removeTestData();

		driver.quit();
	}


	@DataProvider(name = "tryHereNavigationDataFromArraysPages")
	public Object[][] getTryHereNavigationDataFromArraysPages() {
		Object[][] data=null;
		try
		{
		 data= TestDataFromExcelSheet.getTestData("ArraysClickonLink");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		 return data;
			
		}  

	@Test(priority = 2, dataProvider = "hyperlinkNavigationForArrays")
	public void testTryHereNavigation(String link, String expectedtitle, String Url, String pageTitle) throws Exception {
		driver.get(prop.getProperty(Url));
		arrayPage.clickonTryEditor();
		Assert.assertEquals(arrayPage.getTitle(), pageTitle);
		LoggerLoad.info("The user clicked on the " + Url + "and clicked on tryhere button" + "then navigated to"
				+ expectedtitle);

	}
	@Test(priority=3, dataProvider ="numberofLinks")
	public void numberOfLinksInPractiseQuestionsPage(String page,int Expectednumberoflinks)
	{
		int numberoflinks=arrayPage.getnumberoflinksinPracticeQuestionsPage();
		Assert.assertEquals(numberoflinks,Expectednumberoflinks );
		
	}
	@DataProvider(name = "numberofLinks")
	public Object[][] getnumberoflinksinArrayPage() {
		Object[][] data=null;
		try
		{
		 data= TestDataFromExcelSheet.getDataFromSheetRowwise("DsAlgo",1);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		 return data;
			
		} 
	
}
