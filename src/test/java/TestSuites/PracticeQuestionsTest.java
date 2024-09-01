package TestSuites;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageFactory.PracticeQuestionsPage;
import Utilities.DataproviderUtilities;
import Utilities.Retry;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

public class PracticeQuestionsTest extends BaseTest {

	private Map<String, String> data;
	TestDataFromExcelSheet testDataFromExcelSheet = new TestDataFromExcelSheet();
	DataproviderUtilities dataproviderUtilities = new DataproviderUtilities();
	PracticeQuestionsPage practiceQuestionsPage;
	String actualMessage;

	public PracticeQuestionsTest() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		initializeDriver(browser);
		logintotheapplication();
		driver.get(prop.getProperty("arrayPractice"));
		practiceQuestionsPage = new PracticeQuestionsPage(driver);
	}

//	@Test(priority = 1, dataProvider = "TitleValidationTestData",dataProviderClass = DataproviderUtilities.class)
//    @Parameters("sheetName1")
//	public void testtonavigateAssessmentPageFromPracticeQuestionspage(String link,String expectedTitle, String code, String url,String expectedMessage,String failedMessageF) throws Exception {
//		practiceQuestionsPage.clickingLink(link);
//		String actualTitle = practiceQuestionsPage.getTitle();
//		Assert.assertEquals(actualTitle.trim(), expectedTitle.trim(), "Titles don't match!");
//		System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
//		
//	}

	@Test(priority = 2, dataProvider = "TitleValidationTestData", dataProviderClass = DataproviderUtilities.class,retryAnalyzer = Retry.class)

	@Parameters("sheetName1")
	public void testToEnterPythonCodeInAssessmentPage(String link, String expectedTitle, String code, String url,
	    String expectedMessage, String failedMessage) throws Exception {
		driver.get(prop.getProperty(url));
		practiceQuestionsPage.typepythoncodeintexteditor(code);
		practiceQuestionsPage.clickonrunButton();
		Alert alert = driver.switchTo().alert();
        System.out.println("Alert is present with text: " + alert.getText());
        alert.accept();
        try {
		practiceQuestionsPage.clickonsubmitButton();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		actualMessage = practiceQuestionsPage.getsubmissionMessage();
		System.out.println(actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage.trim(), "Messages don't match!");
	}

	@AfterMethod
	public void teardown() throws IOException {
		testDataFromExcelSheet.removeTestData();

		driver.quit();
	}

}
