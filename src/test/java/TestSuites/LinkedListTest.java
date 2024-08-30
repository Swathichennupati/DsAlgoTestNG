package TestSuites;
	
	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import com.dsAlgoProject.Hooks.dsAlgoHooks;

	import com.dsAlgoWebDriverManager.DriverManager;

	import PageFactory.LinkedListPage;
	import PageFactory.NumpyNinjaPage;
	import PageFactory.loginpage;
	import Utilities.TestDataFromExcelSheet;

	public class LinkedListTest {

	    private WebDriver driver;
	    private LinkedListPage linkedlistpage;
	    private loginpage loginPage;
	    private dsAlgoHooks hooks = new dsAlgoHooks();

	    public LinkedListTest() {
	        this.driver = DriverManager.getDriver();
	        loginPage = new loginpage(driver);
	        linkedlistpage = new LinkedListPage(driver);
	        hooks.performLogin(); // Assuming this method logs in the user
	    }

	    @DataProvider(name = "linkedListLinkData")
	    public Object[][] getLinkedListLinkData() throws Exception {
	        TestDataFromExcelSheet excelreader = new TestDataFromExcelSheet();
	        return excelreader.getDataFromExcel("LinkedListLinks"); // Assuming "LinkedListLinks" is your sheet name
	    }

	    @Test(dataProvider = "linkedListLinkData")
	    public void testClickLinkedListLinkAndLandOnPage(String link, String expectedPage) throws InterruptedException {
	        linkedlistpage.selectonlink(link);
	        String actualTitle = linkedlistpage.getTitle();
	        Assert.assertEquals(actualTitle, expectedPage, "Titles don't match!");
	        System.out.println("Assertion passed for " + expectedPage + " page.");
	    }
	}


