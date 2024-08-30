package TestSuites;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dsAlgoWebDriverManager.DriverManager; 


import PageFactory.NumpyNinjaPage;
import PageFactory.ArrayPage;

public class ArrayTest {

    private WebDriver driver;
    private ArrayPage arrayPage;
    private NumpyNinjaPage numpyninjapage;

    public ArrayTest() {
        this.driver = DriverManager.getDriver();
        numpyninjapage = new NumpyNinjaPage(driver);
        arrayPage = new ArrayPage(driver);
    }

    // Data provider for hyperlink navigation test
    @DataProvider(name = "hyperlinkNavigationData")
    public Object[][] getHyperlinkNavigationData() {
        return new Object[][] {
                {"Operations"}, // Link text and expected title
                {"Creation"},  // You can add more data sets here
        };
    }

    @Test(dataProvider = "hyperlinkNavigationData")
    public void testHyperlinkNavigation(String linkText, String expectedTitle) throws Exception {
        arrayPage.clickingLink(linkText);
        String actualTitle = arrayPage.getTitle();
        assertEquals(actualTitle, expectedTitle, "Titles don't match!");
        System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
    }
    
    @Test
    public void testHyperlinkNavigationArrayPage() throws Exception {
    	
        arrayPage.clickingLink(linkText);
        String actualTitle = arrayPage.getTitle();
        assertEquals(actualTitle, expectedTitle, "Titles don't match!");
        System.out.println("Test passed: Navigated to " + expectedTitle + " page.");
    }

    // Data provider for "Try Here" button navigation (optional)
    @DataProvider(name = "tryHereNavigationData") // Uncomment if you have data for different "Try Here" scenarios
    public Object[][] getTryHereNavigationData() {
        return new Object[][] {
                // {/* "Try Here" button text */, /* expected title */}, // Fill in data
        };
    }

    @Test(dataProvider = "tryHereNavigationData") // Uncomment if you have data
    public void testTryHereNavigation(String tryHereText, String expectedTitle) throws Exception {
        // Implement logic to click "Try Here" button and verify title
    }

}