package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utilities;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//a[contains(.,'New Account')]")
	WebElement newAccount;
	
	@FindBy(name="cusid")
	WebElement custID;
	
	@FindBy(name="inideposit")
	WebElement iniDeposit;
	
	@FindBy(name="button2")
	WebElement submit;
	
	@FindBy(xpath="//td[contains(text(),'Account Type')]//parent::tr/td[2]")
	WebElement acctype;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String title()
	{
		String titl = driver.getTitle();
		return titl;
	}
	
	public String createAccount(String custoID, String amount)
	{
		newAccount.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(custID));
		
		custID.sendKeys(custoID);
		iniDeposit.sendKeys(amount);
		submit.click();
		String accTyp = acctype.getText();
		//String acctId = acctID.getText();
		//Utilities.writeExcel(acctId,"account");
		return accTyp;
	}

}
