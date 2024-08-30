package TestSuites;
	
	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import com.dsAlgoProject.Hooks.dsAlgoHooks; 
	 // Assuming this class handles login
	import com.dsAlgoWebDriverManager.DriverManager;

	import PageFactory.NumpyNinjaPage;
	import PageFactory.StackPage;

	public class StackTest {

	    private WebDriver driver;
	    private StackPage stackPage;
	    private NumpyNinjaPage numpyninjapage;
	    private dsAlgoHooks hooks = new dsAlgoHooks(); // Assuming this is for login

	    public StackTest() {
	        this.driver = DriverManager.getDriver();
	        numpyninjapage = new NumpyNinjaPage(driver);
	        stackPage = new StackPage(driver);
	        hooks.performLogin(); // Assuming this method logs in the user (adjust if needed)
	    }

	    // Data provider for hyperlink navigation test
	    @DataProvider(name = "hyperlinkNavigationData")
	    public Object[][] getHyperlinkNavigationData() {
	        return new Object[][] {
	                {"Operations"}, // Link name and expected title
	                {"Applications"},   // You can add more data sets here
	        };
	    }

	    @Test(dataProvider = "hyperlinkNavigationData")
	    public void testHyperlinkNavigation(String linkName, String expectedTitle) throws Exception {
	        stackPage.clickingLink(linkName);
	        String actualTitle = stackPage.getTitle();
	        assertEquals(actualTitle, expectedTitle, "Titles don't match!");
	        System.out.println("Test passed: Navigated to " + expectedTitle + " page."); 
	    }

	    // Data provider for "Try Editor" button navigation (optional)
	    @DataProvider(name = "tryEditorNavigationData") // Uncomment if you have data for different "Try Editor" scenarios
	    public Object[][] getTryEditorNavigationData() {
	        return new Object[][] {
	                // {/* "Try Editor" button text */, /* expected title */}, // Fill in data
	        };
	    }

	    @Test(dataProvider = "tryEditorNavigationData") // Uncomment if you have data
	    public void testTryEditorNavigation(String tryEditorText, String expectedTitle) throws Exception {
	        // Implement logic to click "Try Editor" button and verify title
	    }

}
