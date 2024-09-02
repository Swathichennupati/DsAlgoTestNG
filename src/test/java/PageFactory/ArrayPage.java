package PageFactory;

import java.util.List;
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
	
	@FindBy(xpath ="//a[text()='Arrays Using List']")
	WebElement arraysUsingList_Link;
	
	@FindBy(xpath ="//a[text()='Basic Operations in Lists']")
	WebElement basicOperations_Link;
	
	@FindBy(xpath ="//a[text()='Applications of Array']")
	WebElement applicationsOfArray_Link;
	
	@FindBy(xpath ="//a[text()='Practice Questions']")
	WebElement practiceQuest_Link;
	
	@FindBy(xpath="//a[@class='list-group-item']")
	List<WebElement> listoflinksinPracticeQuestionsPage;
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
			System.out.println("i'm here");
			
			//clickElementUsingJS(arraysUsingList_Link);	
			arraysUsingList_Link.click();
			break;
			
		case "Basic Operations in Lists":
			
			//clickElementUsingJS(basicOperations_Link);
			basicOperations_Link.click();
			
			break;
			
        case "Applications of Array":
			
			//clickElementUsingJS(applicationsOfArray_Link);
			applicationsOfArray_Link.click();

			break;	
			
		case "Practice Questions":
			
			clickElementUsingJS(practiceQuest_Link);
			
			break;
			
		default:
			throw new IllegalArgumentException("you hit the wrong link" + linkName);
		}
		
	}
	public int getnumberoflinksinPracticeQuestionsPage()
	{
		return listoflinksinPracticeQuestionsPage.size();
	}
	
		
	
}
