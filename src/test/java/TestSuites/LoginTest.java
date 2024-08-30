package TestSuites;
	
	import java.io.IOException;
	import java.util.Map;

	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.DataProvider; 

	import org.testng.annotations.Test;

	import com.dsAlgoProject.Hooks.dsAlgoHooks; 

	import com.dsAlgoWebDriverManager.DriverManager;

	import PageFactory.NumpyNinjaPage;
	import PageFactory.loginpage;
	import Utilities.TestDataFromExcelSheet;

	public class LoginTest {

	    private WebDriver driver;
	    private loginpage loginPage;
	    private NumpyNinjaPage numpyninjapage;
	    private dsAlgoHooks hooks = new dsAlgoHooks();

	    public LoginTest() {
	        this.driver = DriverManager.getDriver();
	        loginPage = new loginpage(driver);
	        numpyninjapage = new NumpyNinjaPage(driver);
	        hooks.performLogin(); // Assuming this method logs in the user (remove if not needed)
	    }

	    @DataProvider(name = "invalidLoginData")
	    public Object[][] getInvalidLoginData() throws IOException {
	        TestDataFromExcelSheet excelreader = new TestDataFromExcelSheet();
	        return excelreader.getDataFromExcel("InvalidLogin"); // Assuming "InvalidLogin" is your sheet name
	    }

	    @Test(dataProvider = "invalidLoginData")
	    public void testInvalidLogin(String username, String password, String expectedMessage) throws InterruptedException {
	        loginPage.enterusername(username);
	        loginPage.enterpassword(password);
	        loginPage.clickonloginbutton();

	        String  actualMessage = loginPage.getLoginErrorMessage();
	        Assert.assertTrue(actualMessage.contains(expectedMessage), "Error message mismatch!");
	        System.out.println("Test passed: Expected error message displayed for invalid credentials.");
	    }

	    // Remaining LoginSteps methods can be converted similarly:
	    // - Use separate data providers for different login scenarios (valid credentials, missing fields)
	    // - Update test methods based on specific error message retrieval methods in your PageFactory classes
	}

}
