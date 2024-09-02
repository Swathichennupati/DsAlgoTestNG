package PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dsAlgoProject.Hooks.dsAlgoHooks;



public class QueuePage extends BasePage {
	
	
	public QueuePage(WebDriver driver) {
		
		super(driver);
	} 
	
	@FindBy(xpath = "//a[@href=\"queue\"]")
	WebElement getStartedQueueBtn;
	
	@FindBy(xpath = "//a[@href=\"implementation-lists\"]")
	WebElement implQueuePython_Link;
	
	@FindBy(xpath = "//a[@href=\"implementation-collections\"]")
	WebElement implCollectionDeque_Link;
	
	@FindBy(xpath = "//a[@href=\"Implementation-array\"]")
	WebElement implUsingArray_Link;
	
	@FindBy(xpath = "//a[@href=\"QueueOp\"]")
	WebElement queueOper_Link;
	
	@FindBy(xpath = "//a[@href=\"/queue/practice\"]")
	WebElement practiceQues_Link;
	
	
	//Try Editor
	@FindBy(xpath = "//a[@href=\"/tryEditor\"]")
	WebElement tryhereBtn;
	
	@FindBy(xpath = "//button[@type=\"button\"]")
	WebElement runBtn;
	
	@FindBy(xpath = "//pre[@id=\"output\"]")
	WebElement output;	
	
	public void getStartedQueue() {
		
		System.out.println("im in this method ;) ;) :)");
		getStartedQueueBtn.click();
		
	}

	public  void TryhereBtn() {
		
		tryhereBtn.click();
	}
	

	public void run() {
		
		runBtn.click();
	}
	
	public String result() {
		return output.getText();
		
	}
	
	public void clickingLink(String linkName) throws Exception {
		
		switch(linkName) {
		
		case "Implementation of Queue in Python":
			
			implQueuePython_Link.click();
		    break;
		case "Implementation using collections.deque":
			
			implCollectionDeque_Link.click();
			break;
		case "Implementation using array":
			
			implUsingArray_Link.click();

			break;
			
		case "Queue Operations":
			
			queueOper_Link.click();
			
			break;
			
		case "Practice Questions":
			
			practiceQues_Link.click();
			
			break;
			
		default:
			throw new IllegalArgumentException ("you triggered wrong link" + linkName);
					
		}
		
	}

}
