package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegisterPage extends BasePage{
	StringBuilder stringBuilder = new StringBuilder();
	String expected;
	String actual;
  public  int lengthofusername=0;
	public RegisterPage(WebDriver driver) {

		super(driver);

	}
	
	@FindBy(xpath="//input[@name='username']")
	private WebElement username;	
	public void enterusername(String Username)
	{
		 String result;
		
	if(Username.equalsIgnoreCase("more than 150 characters"))
		{
	        for (int i = 0; i < 152; i++) {
	            stringBuilder.append('a');
	             result = stringBuilder.toString();
		            lengthofusername=result.length();
	     		username.sendKeys(result);

	        }
		}
	else
	{
        lengthofusername=Username.length();
		username.sendKeys(Username);
	}
	}
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement password;
	public void enterpassword(String Password)
	{
		password.sendKeys(Password);
	}
	
	@FindBy(xpath="//input[@name='password2']")
	private WebElement confirmpassword;
	public void enterconfirmpassword(String Password)
	{
		confirmpassword.sendKeys(Password);
	}
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement register;
	public void clickonregister()
	{
		register.click();
	}
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement alertmessage;
	public String getalertmessage()
	{
		return alertmessage.getText();
	}
	
	@FindBy(xpath="//a[text()='Login ']")
	private WebElement loginbutton;
	public void clickonloginbutton()
	{
		loginbutton.click();
	}
	
	
	public void sendcharacters(String characters)
	{
		String[]charactersarray= characters.split(",");
		String username="a".repeat(8);
		stringBuilder.append(username);
         for(int i=0;i<charactersarray.length;i++)
         {
        	 stringBuilder.append(charactersarray[i]);
         }
        String usernamestring=stringBuilder.toString();
         enterusername(usernamestring);
         
	}
	
	
	public String getvalueofusernamefield()
	{
	    String enteredValue = username.getAttribute("value");
	    return enteredValue;

	}
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement Registerbutton;
	public void clickonRegisterbutton()
	{
		Registerbutton.click();
	}
	
	public String getRequiredFieldErrorMessage(String fieldElement) {
		String messageStr="";
		if(fieldElement.contains("username"))
		{
			username.click();
			messageStr = username.getAttribute("validationMessage");
		}
			return messageStr;
	}
	public boolean enterusernamefield(String username,String validationmessage,String field)
	
	{
		boolean t= false;
		if(username==null)
		{
			username="";
		}
		enterusername(username);
		clickonRegisterbutton();
		if(lengthofusername==0)
		{
		expected=validationmessage;
		actual=getRequiredFieldErrorMessage(field);
		if(actual.equalsIgnoreCase(expected))
		{
			t=true;
		}
		}	
		else if(lengthofusername>=150)
		{
			String enteredValue=getvalueofusernamefield();
			int actuallength=enteredValue.length();
			int expextedlength=150;
			if(actuallength==expextedlength)
			{
				t=true;
			}
		}
		return t;
	}
	
	public void validatePassword(String specialChars, String password)
	{
		sendcharacters(specialChars);	
		enterpassword(password);
		enterconfirmpassword(password);
		clickonRegisterbutton();
		
	}
	public void validatepasswordmismatch(String password1, String password2)
	{
		enterusername(prop.getProperty("username"));
		enterpassword(password1);
		enterconfirmpassword(password2);
		clickonRegisterbutton();
	}
}
