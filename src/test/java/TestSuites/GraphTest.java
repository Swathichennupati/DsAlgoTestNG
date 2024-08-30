package TestSuites;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dsAlgoProject.Hooks.dsAlgoHooks; 

import com.dsAlgoWebDriverManager.DriverManager;
import PageFactory.GraphPage;
import PageFactory.NumpyNinjaPage;
import PageFactory.loginpage;
import Utilities.TestDataFromExcelSheet;

public class GraphTest {

    private WebDriver driver;
    private GraphPage graphpage;
    private loginpage loginPage;
    private dsAlgoHooks hooks = new dsAlgoHooks();

    public GraphTest() {
        this.driver = DriverManager.getDriver();
        loginPage = new loginpage(driver);
        graphpage = new GraphPage(driver);
        hooks.performLogin(); // Assuming this method logs in the user
    }

    @DataProvider(name = "graphLinkData")
    public Object[][] getGraphLinkData() throws Exception {
        TestDataFromExcelSheet excelreader = new TestDataFromExcelSheet();
        return excelreader.getDataFromExcel("GraphLinks"); // Assuming "GraphLinks" is your sheet name
    }

    @Test(dataProvider = "graphLinkData")
    public void testClickGraphLinkAndLandOnPage(String link, String expectedPage) throws InterruptedException {
        graphpage.clickonlink(link);
        String actualTitle = graphpage.getTitle();
        Assert.assertEquals(actualTitle, expectedPage, "Titles don't match!");
        System.out.println("Assertion passed for " + expectedPage + " page.");
    }

    // Remaining test methods (user enters code, runs, verifies output) can be adapted similarly
}
