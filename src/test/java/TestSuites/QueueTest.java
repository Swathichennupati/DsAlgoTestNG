package TestSuites;

	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import com.dsAlgoWebDriverManager.DriverManager;


	import PageFactory.NumpyNinjaPage;
	import PageFactory.QueuePage;

	public class QueueTest {

	    private WebDriver driver;
	    private QueuePage queuePage;
	    private NumpyNinjaPage numpyninjapage;

	    public QueueTest() {
	        this.driver = DriverManager.getDriver();
	        numpyninjapage = new NumpyNinjaPage(driver);
	        queuePage = new QueuePage(driver);
	    }

	    // Data provider for hyperlink navigation test
	    @DataProvider(name = "hyperlinkNavigationData")
	    public Object[][] getHyperlinkNavigationData() {
	        return new Object[][] {
	                {"Enqueue Operations"}, // Link name and expected title
	                {"Dequeue Operations"},   // You can add more data sets here
	        };
	    }

	    @Test(dataProvider = "hyperlinkNavigationData")
	    public void testHyperlinkNavigation(String linkName, String expectedTitle) throws Exception {
	        // Assuming user is already on the Queue overview page
	        queuePage.clickingHyperLink(linkName);
	        String actualTitle = queuePage.getTitle();
	        assertEquals(actualTitle, expectedTitle, "Titles don't match!");
	        System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
	    }

	    // Data provider for "Try Here" button navigation test (optional)
	    @DataProvider(name = "tryHereNavigationData") // Uncomment if you have data for different "Try Here" scenarios
	    public Object[][] getTryHereNavigationData() {
	        return new Object[][] {
	                // {/* "Try Here" button text  */, /* expected title */}, // Fill in data
	        };
	    }

	    @Test(dataProvider = "tryHereNavigationData") // Uncomment if you have data
	    public void testTryHereNavigation(String tryHereText, String expectedTitle) throws Exception {
	        // Implement logic to click "Try Here" button and verify title
	    }

	    // Similar data providers and tests can be added for other functionalities
	}


