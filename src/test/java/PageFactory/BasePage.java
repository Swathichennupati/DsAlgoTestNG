package PageFactory;

import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dsAlgoWebDriverManager.DriverManager;

public class BasePage {
	
		
		public WebDriver driver;
		public Properties prop;
		public Properties testdata;
		public InputStream input;
	    protected WebDriverWait wait;
		  public void navigateTo(String pagename) {
				String urlName = prop.getProperty(pagename);
				driver.get(urlName);
			}
		

		public BasePage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        prop=DriverManager.get_Properties_from_configfile();
		}
		
		public void signOutAndWaitForSignIn(WebElement signout) {
	        WebElement signOutLink = wait.until(ExpectedConditions.elementToBeClickable(signout));
	        signOutLink.click();
	        wait.until(ExpectedConditions.urlContains("home"));
	    }
		
		public String getErrorMessage(By locator) {
	        WebElement errorMessageElement = driver.findElement(locator);
	        return errorMessageElement.getText();
	    }

	    public String getErrorMessage(WebElement errorMessageElement) {
	        return errorMessageElement.getText();
	    }
	
	    public String getSucessfulMessage(WebElement sucessMessageElement) {
	        return sucessMessageElement.getText();
	    }
	  
       public String getTitle()
       {
    	   return driver.getTitle();
       }
       
       public void waitfortheelement(WebElement element)
       {
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	   wait.until(ExpectedConditions.visibilityOf(element));
    	   
    			   }
		
	    

}
