package PageFactory;
	
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

	public  class StackPage extends BasePage {			

		public  StackPage(WebDriver driver) {
			
		    super(driver);

		}

		@FindBy(xpath = "//a[@href='data-structures-introduction']")
		WebElement getStarted_dataStructure;
		
		@FindBy(xpath = "//a[@href='stack']")
		WebElement getStarted_stack;
		
		@FindBy(xpath="//a[@href='operations-in-stack']")
		WebElement operation_Lnk;
		
		@FindBy(xpath="//a[@href='implementation']")
		WebElement implemen_Lnk;
		
		@FindBy(xpath="//a[@href='stack-applications']")
		WebElement applic_Lnk;
		
		@FindBy(xpath="//a[@href='/stack/practice']")
		WebElement practiceQues_Lnk;
		
		
		
		//TryEditor
		@FindBy(xpath= "//a[@href='/tryEditor']")
		WebElement tryhereBtn;

		@FindBy(xpath="//button[contains(text(),'Run')]")
		WebElement run_btn;
		
		@FindBy(id="output")
		WebElement output;
		
		
		public void clickOnGetStarted() {
			getStarted_stack.click();
		}
		
		public  void TryhereBtn() {
			
			tryhereBtn.click();
		}
		public void clickRun() {
			 run_btn.click();
		}
		
		public String result() {
			return output.getText();
			
		}
		
		public void clickingLink(String linkName) throws Exception {
			
			switch(linkName) {
		
			case "Operations in Stack":
				
				operation_Lnk.click();
				
			   break;
			   
			case "Implementation":
				
				implemen_Lnk.click();
				
				break;
				
			case "Applications":
				
				applic_Lnk.click();
				
				break;
				
			case "Practice Questions":
				
				practiceQues_Lnk.click();
				
				break;
				
			default:
				throw new IllegalArgumentException("you hit the wrong link" + linkName);
			
			}		
		}	

}
