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
import log4j.LoggerLoad;
import PageFactory.ArrayPage;

public class ArrayTest extends BaseTest {

	private Map<String, String> data;

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
	public Object[][] getHyperlinkNavigationForArrays() {
		return new Object[][] {

				{ "Arrays Using List", "Arrays Using List" }, { "Arrays in Python", "Arrays in Python" },
				{ "Basic Operations in Lists", "Basic Operations in Lists" },
				{ "Applications of Array", "Applications of Array" }, { "Practice Questions", "Practice Questions" } };
	}

	@Test(priority = 1, dataProvider = "hyperlinkNavigationForArrays")
	public void testHyperlinkNavigation(String linkText, String expectedTitle) throws Exception {
		if (linkText.equalsIgnoreCase("Practice Questions")) {
			driver.get(prop.getProperty("arraysinPython"));
		}	
			arrayPage.clickingLink(linkText);
			String actualTitle = arrayPage.getTitle();
			assertEquals(actualTitle, expectedTitle, "Titles don't match!");
			System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
		
	}

	@AfterMethod
	public void teardown() throws IOException {
		driver.quit();
	}


	@DataProvider(name = "tryHereNavigationDataFromArraysPages")
	public Object[][] getTryHereNavigationDataFromArraysPages() {
		return new Object[][] { { "arraysinPython", "Assessment" }, { "arrayUsingList", "Assessment" },
				{ "basicOperList", "Assessment" }, { "AppOfArray", "Assessment" }

		};
	}

	@Test(priority = 2, dataProvider = "tryHereNavigationDataFromArraysPages")
	public void testTryHereNavigation(String page, String expectedtitle) throws Exception {
		driver.get(prop.getProperty(page));
		arrayPage.clickonTryEditor();
		Assert.assertEquals(arrayPage.getTitle(), expectedtitle);
		LoggerLoad.info("The user clicked on the " + page + "and clicked on tryhere button" + "then navigated to"
				+ expectedtitle);

	}
}