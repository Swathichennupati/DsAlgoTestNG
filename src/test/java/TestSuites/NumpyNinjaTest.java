package TestSuites;
	
	import java.util.Properties;

	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;   


	import com.dsAlgoProject.Hooks.dsAlgoHooks;
	import com.dsAlgoWebDriverManager.DriverManager;

	import PageFactory.NumpyNinjaPage;
	import PageFactory.loginpage;
	import Utilities.TestDataFromExcelSheet;

	public class NumpyNinjaTest {

	    private WebDriver driver;
	    private NumpyNinjaPage numpyninjapage;
	    private loginpage loginPage;
	    private dsAlgoHooks hooks = new dsAlgoHooks();
	    private Properties prop;

	    public NumpyNinjaTest() {
	        this.driver = DriverManager.getDriver();
	        numpyninjapage = new NumpyNinjaPage(driver);
	        loginPage = new loginpage(driver);
	        hooks.performLogin(); // Assuming this method logs in the user
	    }

	    @DataProvider(name = "dropdownNavigationData")
	    public Object[][] getDropdownNavigationData() throws Exception {
	        TestDataFromExcelSheet excelreader = new TestDataFromExcelSheet();
	        return excelreader.getDataFromExcel("DropdownNavigation"); // Assuming "DropdownNavigation" is your sheet name
	    }

	    @Test(dataProvider = "dropdownNavigationData")
	    public void testDropdownNavigation(String option, String expectedTitle) {
	        numpyninjapage.selectFromDropdown(option);
	        String actualTitle = numpyninjapage.getTitle();
	        Assert.assertEquals(actualTitle, expectedTitle, "Titles don't match!");
	        System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
	    }
	}


