package TestSuites;
	
	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import com.dsAlgoWebDriverManager.DriverManager;


	import PageFactory.RegisterPage;
	import PageFactory.loginpage;

	public class RegisterTest {

	    private WebDriver driver;
	    private RegisterPage registerpage;
	    private loginpage loginPage;

	    public RegisterTest() {
	        this.driver = DriverManager.getDriver();
	        registerpage = new RegisterPage(driver);
	        loginPage = new loginpage(driver);
	    }

	    // Data provider for username validation with different lengths
	    @DataProvider(name = "usernameValidationData")
	    public Object[][] getUsernameValidationData() {
	        return new Object[][] {
	                {"", "Username is required"}, // Empty username
	                {"ThisIsAReallyLongUsernameThatExceedsThe150CharacterLimit", "Username is too long"},  // Long username
	                {"username123", null}, // Valid username (assuming no other validation rules)
	        };
	    }

	    @Test(dataProvider = "usernameValidationData")
	    public void testUsernameValidation(String username, String expectedValidationMessage) {
	        registerpage.enterusername(username);
	        registerpage.clickonRegisterbutton();

	        String actualValidationMessage;
	        if (username.isEmpty()) {
	            actualValidationMessage = registerpage.getRequiredFieldErrorMessage("Username");
	        } else {
	            actualValidationMessage = registerpage.getalertmessage();
	        }

	        if (expectedValidationMessage == null) {
	            // No specific validation message expected, verify no error message is displayed
	            Assert.assertNull(actualValidationMessage, "Unexpected validation message displayed.");
	        } else {
	            Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation messages don't match!");
	        }
	    }

}
