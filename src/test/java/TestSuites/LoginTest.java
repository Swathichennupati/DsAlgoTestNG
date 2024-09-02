package TestSuites;


	import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageFactory.NumpyNinjaPage;


import Utilities.TestDataFromExcelSheet;

import log4j.LoggerLoad;


public class LoginTest extends BaseTest {
	NumpyNinjaPage numpyninjapage;
	TestDataFromExcelSheet testDataFromExcelSheet;
     String expectedMessage;
     String actualMessage;


	public LoginTest() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException, IOException {
		initializeDriver(browser);	
		System.out.println("beforemethod");		
	}
	
	
    @Test(priority=1,dataProvider = "validexcel")
	public void testforCorrectUsernamePassword(String username,String password,String message) throws IOException, InterruptedException 
	{
    	loginPage.enterUsernamePassword(username,password);
    	//loginPage.enterusername(username);
		//loginPage.enterpassword(password);
    	expectedMessage =message;
		String field;
	
		NumpyNinjaPage numpyninjapage = loginPage.clickonloginbutton();
		try {
			if (expectedMessage.equals(numpyninjapage.getTitle())) {
				actualMessage = numpyninjapage.getTitle();

				Assert.assertTrue(actualMessage.contains(expectedMessage));
				LoggerLoad.info("User logged in sucessfully");

			} 
			else if(username==""||password=="")
			{
				if(username=="")
				{
					field="username";
				}
				else
				{
					field="password";
				}
				expectedMessage="Please fill out this field.";
				try {
				actualMessage = loginPage.getRequiredFieldErrorMessage(field);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				System.out.println();
				LoggerLoad.info("User did not enter fields " + field);
				System.out.println(actualMessage.trim());
				Assert.assertEquals(actualMessage, expectedMessage, "Error messsages did not match");

			}
			else {
				actualMessage = loginPage.getLoginErrorMessage();
				Assert.assertTrue(actualMessage.contains(expectedMessage));
				LoggerLoad.info("User entered username or password incorrectly");
			}
		} finally {
			LoggerLoad.info("Completed login attempt.");

		}		
	}
	
    @DataProvider (name = "validexcel")
	 public Object[][] getTestData() throws IOException
	 {
		Object data[][]=null;
			data = TestDataFromExcelSheet.getTestData("LoginUsernamePassword");
		return data;		
	 }
 
    
    @AfterMethod
	public void teardown() throws IOException {
		driver.quit();
	}

}

