package TestSuites;
	
	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import com.dsAlgoProject.Hooks.dsAlgoHooks; 
	 // Assuming this class handles login
	import com.dsAlgoWebDriverManager.DriverManager;

	import PageFactory.NumpyNinjaPage;
	import PageFactory.TreePage;

	public class TreeTest {

	    private WebDriver driver;
	    private TreePage treepage;
	    private NumpyNinjaPage numpyninjapage;
	    private dsAlgoHooks hooks = new dsAlgoHooks(); // Assuming this is for login

	    public TreeTest() {
	        this.driver = DriverManager.getDriver();
	        numpyninjapage = new NumpyNinjaPage(driver);
	        treepage = new TreePage(driver);
	        hooks.performLogin(); // Assuming this method logs in the user (adjust if needed)
	    }

	    // Data provider for tree navigation test
	    @DataProvider(name = "treeNavigationData")
	    public Object[][] getTreeNavigationData() {
	        return new Object[][] {
	                {"Traversal"}, // Link text and expected title
	                {"Operations"},  // You can add more data sets here
	        };
	    }

	    @Test(dataProvider = "treeNavigationData")
	    public void testTreeNavigation(String linkText, String expectedTitle) throws Exception {
	        treepage.clickonlink(linkText);
	        String actualTitle = treepage.getTitle();
	        Assert.assertEquals(actualTitle, expectedTitle, "Titles don't match!");
	        System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
	    }
	}


