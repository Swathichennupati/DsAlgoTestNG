package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DataStructurePage extends BasePage{
	String actual;
	String expected;
	
	public DataStructurePage(WebDriver driver)
	{	
		super(driver);
	}
	@FindBy(xpath="//a[text()='Time Complexity']")
	private WebElement TimeComplexitylink;
	
	@FindBy(xpath="//textarea[@tabindex='0']")
	private WebElement textEditor;
	
	@FindBy(xpath="//button[text()='Run']")
	private WebElement runButton;
	
	@FindBy(xpath="//a[text()='Practice Questions']")
	private  WebElement practicelink;
	
	@FindBy(xpath="//a[contains(text(),'Try here')]")
	private  WebElement tryherebutton;
	
	@FindBy(xpath="//a[text()='Sign out']")
	private  WebElement signout;
	
	@FindBy(xpath="//div[@class='alert alert-primary']")
	private   WebElement logoutmessage;
	
	public void getlogoutmessage()
	{
		logoutmessage.getText();
	}
	
	public void clickonsignout() {
		signout.click();
	}
	
	public void clickontryherebutton() throws InterruptedException
	{
		tryherebutton.click();
	}
	public void entertextintextEditor(String pythoncode) throws InterruptedException
	{
		textEditor.sendKeys(pythoncode);
	}
	
	public void clickonrunbutton()
	{
		runButton.click();
	}
    public String getgraphpageTitle() {
        return driver.getTitle();
    }
    
    public void selectonlink(String nameoflink) {
		switch (nameoflink) {
		case "Time Complexity":
			TimeComplexitylink.click();
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
    


