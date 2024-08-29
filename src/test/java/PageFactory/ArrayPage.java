package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArrayPage extends BasePage {
	
	private WebDriver driver;

	public  ArrayPage(WebDriver driver) {
		
	    super(driver);

	}

	
	@FindBy(xpath = "//a[@href=\"arrays-in-python\"]")
	WebElement arrayPython_Link;
	
	@FindBy(xpath = "//a[@href=\"/array/arrays-using-list/\"]")
	WebElement arraysUsingList_Link;
	
	@FindBy(xpath="//a[@href=\"/array/basic-operations-in-lists/\"]")
	WebElement basicOperations_Link;
	
	@FindBy(xpath = "//a[@href=\"/array/applications-of-array/\"]")
	WebElement applicationsOfArray_Link;
	
	@FindBy(xpath = "//a[@href=\"/array/practice\"]")
	WebElement practiceQuest_Link;
	
	
	//TryEditor
    @FindBy(xpath= "//a[@href='/tryEditor']")	
    WebElement TryEdt_btn;
		
			
	@FindBy(xpath="//button[contains(text(),'Run')]")
	WebElement run_btn;
			
	@FindBy(id="output")
	WebElement output;
	
	
	public void clickonTryEditor() {
		TryEdt_btn.click();
	}
	
	public void clickRun() {
		 run_btn.click();
	}

	
	public void clickingLink(String linkName) throws Exception {
		
		switch(linkName) {
	
		case "Arrays in Python":
			
			arrayPython_Link.click();
			
		   break;
		   
		case "Arrays Using List":
			
			arraysUsingList_Link.click();
			
			break;
			
		case "Basic Operations in Lists":
			
			basicOperations_Link.click();
			
			break;
			
        case "Applications of Array":
			
			applicationsOfArray_Link.click();
			
			break;	
			
		case "Practice Questions":
			
			practiceQuest_Link.click();
			
			break;
			
		default:
			throw new IllegalArgumentException("you hit the wrong link" + linkName);
		}
		
	}
		
	
}
