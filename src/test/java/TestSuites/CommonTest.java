package TestSuites;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test; 
import com.dsAlgoWebDriverManager.DriverManager;

import PageFactory.BasePage;
import PageFactory.NumpyNinjaPage;
import PageFactory.loginpage;

public class CommonTest {

    private WebDriver driver;
    private Properties prop;
    private BasePage basepage;
    private loginpage loginPage;
    private NumpyNinjaPage numpyninjapage;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
        prop = DriverManager.get_Properties_from_configfile();
        basepage = new BasePage(driver);
    }

    // Data provider for navigation test (optional)
    @DataProvider(name = "navigationData")
    public Object[][] getNavigationData() {
        return new Object[][] {
                {"Stack"},  // Page name
                {"Tree"},    // You can add more page names here
                // ...
        };
    }

    @Test(dataProvider = "navigationData")
    public void testNavigateToPage(String pageName) throws InterruptedException {
        loginPage = new loginpage(driver);
        loginPage.enterusername(prop.getProperty("username"));
        loginPage.enterpassword(prop.getProperty("password"));

        numpyninjapage = loginPage.clickonloginbutton();

        basepage.navigateTo(pageName);
        LoggerLoad.info("User is on " + pageName + " page");

        // Add additional assertions or actions for the specific page here
    }

    // Separate test method for login if needed (without data provider)
    @Test
    public void testLogin() throws InterruptedException {
        loginPage = new loginpage(driver);
        loginPage.enterusername(prop.getProperty("username"));
        loginPage.enterpassword(prop.getProperty("password"));

        numpyninjapage = loginPage.clickonloginbutton();
        LoggerLoad.info("User logged in successfully");
    }
}

