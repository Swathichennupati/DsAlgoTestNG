package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

	public class GraphPage extends BasePage{
	
		public GraphPage(WebDriver driver)
		{		
			super(driver);
		}
		@FindBy(xpath="//a[text()='Graph Representations']")
		private  WebElement graphrepresentationslink;

		@FindBy(css="div:nth-child(1) > textarea")
		private WebElement textEditor;
		
		@FindBy(xpath="//button[text()='Run']")
		private WebElement runButton;
		
		@FindBy(xpath="//span[@class='cm-string']")
		private WebElement expectedValue;
		
		@FindBy(xpath="//*[@id='output']")
		private WebElement actualValue;
		
		@FindBy(xpath="//*[@href='graph']")
		private  WebElement graphlink;
		
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
	    public String getexpectedvalue()
	    {
	    	return expectedValue.getText();
	    }
	    public String getactualvalue()
	    {
	    	return actualValue.getText();
	    }
	    public String getgraphpageTitle() {
	        return driver.getTitle();
	    }
		
	    public void clickonlink(String nameoflink) {
	        switch (nameoflink) {
	            case "Graphgraph":
	            	graphlink.click();
	                break;
	            case "Graph representations":
	            	graphrepresentationslink.click();
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
