package TestSuites;
	
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dsAlgoWebDriverManager.DriverManager;


import PageFactory.DataStructurePage;
import PageFactory.NumpyNinjaPage;

public class DataStructureTest {

    private WebDriver driver;
    private DataStructurePage datastructurepage;

    public DataStructureTest() {
        this.driver = DriverManager.getDriver();
        datastructurepage = new DataStructurePage(driver);
    }

    // Data provider for data structure navigation test
    @DataProvider(name = "dataStructureNavigationData")
    public Object[][] getDataStructureNavigationData() { 
        return new Object[][] {
                {"Stack"},  // Link text and expected title
                {"Queue"},   // You can add more data sets here
        };
    }

    @Test(dataProvider = "dataStructureNavigationData")
    public void testNavigateToDataStructure(String linkText, String expectedTitle) throws Exception {
        datastructurepage.selectonlink(linkText);
        String actualTitle = datastructurepage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Titles don't match!");
        System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
    }
}

