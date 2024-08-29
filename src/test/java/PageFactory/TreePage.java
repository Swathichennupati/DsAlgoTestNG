package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TreePage extends BasePage {

	public TreePage(WebDriver driver)
	{
			super(driver);
		if (this.driver == null) {
            throw new IllegalArgumentException("hello WebDriver cannot be null");
        }

	}
	
	@FindBy(xpath="//a[text()='Overview of Trees']")
	private  WebElement OverviewofTrees;
	
	@FindBy(xpath="//a[text()='Terminologies']")
	private  WebElement Terminologies;	

	@FindBy(xpath="//a[text()='Types of Trees']")
	private  WebElement TypesofTrees;

	@FindBy(xpath="//a[text()='Tree Traversals']")
	private  WebElement TreeTraversals;
	
	@FindBy(xpath="//a[text()='Traversals-Illustration']")
	private  WebElement TraversalsIllustration;

	@FindBy(xpath="//a[text()='Binary Trees']")
	private  WebElement BinaryTrees;
	
	@FindBy(xpath="//a[text()='Types of Binary Trees']")
	private  WebElement TypesofBinaryTrees;

	
	@FindBy(xpath="//a[text()='Implementation in Python']")
	private  WebElement ImplementationinPython;

	@FindBy(xpath="//a[text()='Binary Tree Traversals']")
	private  WebElement BinaryTreeTraversals;

	@FindBy(xpath="//a[text()='Implementation of Binary Trees']")
	private  WebElement ImplementationofBinaryTrees;

	@FindBy(xpath="//a[text()='Applications of Binary trees']")
	private  WebElement ApplicationsofBinarytrees;

	@FindBy(xpath="//a[text()='Binary Search Trees']")
	private  WebElement BinarySearchTrees;

	@FindBy(xpath="//a[text()='Implementation Of BST']")
	private  WebElement ImplementationOfBST;

	@FindBy(xpath="//a[contains(text(),'Try here')]")
	private  WebElement tryherebutton;

	@FindBy(xpath="//a[@href='/tree/practice']")
	private  WebElement practicelink;
	

	public String gettreepageTitle() {
		System.out.println(driver.getTitle());
		return driver.getTitle();
		
	}
	
	@FindBy(xpath="//a[text()='Sign out']")
	private  WebElement signout;
	
	public void clickonlink(String nameoflink) {
        switch (nameoflink) {
            case "Overview of Trees":
            	OverviewofTrees.click();
                break;
            case "Terminologies":
            	Terminologies.click();
                break;
            case "Types of Trees":
            	TypesofTrees.click();
                break;
            case "Tree Traversals":
            	TreeTraversals.click();
                break;  
                case "Traversals-Illustration":
                	TraversalsIllustration.click();
                    break;
                case "Binary Trees":
                	BinaryTrees.click();
                    break;
                case "Types of Binary Trees":
                	TypesofBinaryTrees.click();
                    break;
                case "Implementation in Python":
                	ImplementationinPython.click();
                    break;
                case "Binary Tree Traversals":
                	BinaryTreeTraversals.click();
                    break;
                case "Implementation of Binary Trees":
                	ImplementationofBinaryTrees.click();
                    break;
                case "Applications of Binary trees":
                	ApplicationsofBinarytrees.click();
                    break;
                case "Binary Search Trees":
                	BinarySearchTrees.click();
                    break;
                case "Implementation Of BST":
                	ImplementationOfBST.click();
                    break;
                case "Try Here":
                	tryherebutton.click();
                	break;
                case "Practice Questions":
                practicelink.click();
                break;
                case "Sign out":
               	 signOutAndWaitForSignIn(signout);
                 	break;
               default:
                throw new IllegalArgumentException("No such link: " + nameoflink);
        }
	}
}
