package utility;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;
import pageFactory.HomePage;
import pageFactory.loginPageFactory;

public class TestContext {
	
	WebDriver driver;
	loginPageFactory loginPage;
	HomePage homePage;
	Scenario scenario;
	
	public Scenario getScenario() {
		return scenario;
	}
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public loginPageFactory getLoginPage() {
		return loginPage;
	}
	public HomePage getHomePage() {
		return homePage;
	}
	
	public void initializePageObject(WebDriver driver)
	{
		loginPage = new loginPageFactory(driver);
		homePage = new HomePage(driver);
	}
	

}
