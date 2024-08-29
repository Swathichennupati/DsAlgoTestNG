package TestSuites;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dsAlgoProject.Hooks.dsAlgoHooks;
import com.dsAlgoWebDriverManager.DriverManager;

import PageFactory.GraphPage;
import PageFactory.NumpyNinjaPage;
import PageFactory.loginpage;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;

public class GraphTest {
	
	
	TestDataFromExcelSheet excelreader = new TestDataFromExcelSheet();
	loginpage loginPage;
	dsAlgoHooks hooks = new dsAlgoHooks();
	private WebDriver driver;
	NumpyNinjaPage numpyninjapage;
	GraphPage graphpage;
	String actual;
	String expected;

	public GraphTest() {
		this.driver = DriverManager.getDriver();
		numpyninjapage = new NumpyNinjaPage(driver);
		graphpage = new GraphPage(driver);

	}
	
  @Test(priority=1)
  public void firstTest() {
	  
		loginPage = new loginpage(driver);
		loginPage.enterusername(prop.getProperty("username"));
		loginPage.enterpassword(prop.getProperty("password"));
	    numpyninjapage =loginPage.clickonloginbutton();
	}
		//LoggerLoad.info("User landed on login page");
		
  }
  
   @DataProvider(priority=2)
   public void secondTest() {
	 
 

}
