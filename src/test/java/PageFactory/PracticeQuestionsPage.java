package PageFactory;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.dsAlgoWebDriverManager.DriverManager;
import PageFactory.NumpyNinjaPage;
import Utilities.DataproviderUtilities;
import Utilities.TestDataFromExcelSheet;
import log4j.LoggerLoad;
import PageFactory.ArrayPage;

public class PracticeQuestionsPage extends BasePage {

	private WebDriver driver;

	public  PracticeQuestionsPage(WebDriver driver) {
		
	    super(driver);
	}
	
	
	@FindBy(xpath ="//a[text()='Search the array']")
	WebElement SearchthearrayLink;
	
	@FindBy(xpath ="//a[text()='Max Consecutive Ones']")
	WebElement MaxConsecutiveOnesLink;
	
	@FindBy(xpath ="//a[text()='Find Numbers with Even Number of Digits']")
	WebElement FindNumberswithEvenNumberofDigitsLink;
	
	@FindBy(xpath ="//a[text()='Squares of  a Sorted Array']")
	WebElement SquaresofaSortedArrayLink;	
	
	@FindBy(xpath="//a[@class='list-group-item']")
	List<WebElement> listoflinksinPracticeQuestionsPage;
	//TryEditor
    @FindBy(xpath= "//a[@href='/tryEditor']")	
    WebElement TryEdt_btn;
					
	@FindBy(xpath="//button[contains(text(),'Run')]")
	WebElement run_btn;
			
	@FindBy(id="output")
	WebElement output;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submitButton;
	
	@FindBy(css="div:nth-child(1) > textarea")
	  WebElement textEditor;
	
	@FindBy(xpath="//pre[@id='output']")
	WebElement submissionMessage;
	
	@FindBy(xpath="//button[contains(text(),'Run')]")
	WebElement runButton;
	
	public void clickonrunButton()
	{
		runButton.click();
	}
	
	public void clickonTryEditor() {
		TryEdt_btn.click();
	}
	
	public void clickRun() {
		 run_btn.click();
	}
	
	public void clickingLink(String linkName) throws Exception {
		
		switch(linkName) {
	
		case "Search the array":
			
			clickElementUsingJS(SearchthearrayLink);			
		   break;
		   
		case "Max Consecutive Ones":
			
			clickElementUsingJS(MaxConsecutiveOnesLink);			
			break;
			
		case "Find Numbers with Even Number of Digits":
			
			clickElementUsingJS(FindNumberswithEvenNumberofDigitsLink);
			
			break;
			
        case "Squares of  a Sorted Array":
			
			clickElementUsingJS(SquaresofaSortedArrayLink);

			break;	
			
		default:
			throw new IllegalArgumentException("you hit the wrong link" + linkName);
		}
		
	}
	public int getnumberoflinksinPracticeQuestionsPage()
	{
		return listoflinksinPracticeQuestionsPage.size();
	}
	

	public void clickonsubmitButton() {
		submitButton.click();
	}
	public String getsubmissionMessage() throws InterruptedException
	{
		Thread.sleep(900);
		return submissionMessage.getText().trim();
	}
	public String gettextarea()
	{
		return textEditor.getText();
	}
	public void typepythoncodeintexteditor(String code)
	{
		code.trim();
		textEditor.sendKeys(code);
		textEditor.sendKeys(Keys.END);
		textEditor.sendKeys(Keys.BACK_SPACE);
	
		
	}

}
