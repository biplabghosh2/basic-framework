package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPageFactory {
	
	WebDriver driver;
	
	public loginPageFactory(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[contains(text(),'UserID')]/parent::tr/td[2]/input")
	WebElement userName;
	
	@FindBy(xpath="//td[contains(text(),'Password')]/parent::tr/td[2]/input")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submit;
	
	public void enterUsername(String username)
	{
		userName.sendKeys(username);
	}
	
	public void enterPassword(String password1)
	{
		password.sendKeys(password1);
	}
	
	public void clickSubmit()
	{
		submit.click();
	}
	
	public String getLoginError()
	{
		String error = driver.switchTo().alert().getText();
		return error;
	}

}
