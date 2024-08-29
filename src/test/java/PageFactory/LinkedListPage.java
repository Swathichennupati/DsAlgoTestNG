package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinkedListPage extends BasePage {

	public LinkedListPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Introduction']")
	private  WebElement Introductionlink;

	@FindBy(xpath = "//a[text()='Creating Linked LIst']")
	private  WebElement CreatingLinkedLIstlink;

	@FindBy(xpath = "//a[text()='Types of Linked List']")
	private  WebElement TypesofLinkedListlink;

	@FindBy(xpath = "//a[text()='Implement Linked List in Python']")
	private  WebElement ImplementLinkedListinPythonlink;

	@FindBy(xpath = "//a[text()='Traversal']")
	private  WebElement Traversallink;

	@FindBy(xpath = "//a[text()='Insertion']")
	private  WebElement Insertionlink;

	@FindBy(xpath = "//a[text()='Deletion']")
	private  WebElement Deletionlink;

	@FindBy(xpath = "//button[text()='Run']")
	private WebElement runButton;

	@FindBy(xpath = "//a[text()='Practice Questions']")
	private  WebElement practicelink;

	@FindBy(xpath="//a[contains(text(),'Try here')]")
	private  WebElement tryherebutton;
	
	@FindBy(xpath="//a[text()='Sign out']")
	private  WebElement signout;
	
	public void selectonlink(String nameoflink) {
		switch (nameoflink) {
		case "Introduction":
			Introductionlink.click();
			break;
		case "Creating Linked LIst":
			CreatingLinkedLIstlink.click();
			break;
		case "Types of Linked List":
			TypesofLinkedListlink.click();
			break;
		case "Implement Linked List in Python":
			ImplementLinkedListinPythonlink.click();
			break;
		case "Traversal":
			Traversallink.click();
			break;
		case "Insertion":
			Insertionlink.click();
			break;
		case "Deletion":
			Deletionlink.click();
			break;
		case "Practice Questions":
			practicelink.click();
			break;
		 case "Try Here":
         	tryherebutton.click();
         	break;
		 case "Sign out":
        	 signOutAndWaitForSignIn(signout);
	         	break;
		default:
			throw new IllegalArgumentException("No such link: " + nameoflink);
		}

	}
}
