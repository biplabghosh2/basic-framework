package stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageFactory.HomePage;
import pageFactory.loginPageFactory;
import utility.TestContext;
import utility.Utilities;

public class loginStep extends TestContext{
	
	loginPageFactory loginPage;
	TestContext context;
	
	public loginStep(TestContext context)
	{
		this.context = context;
	}

	@Given("User is on login page")
	public void user_is_on_login_page() {
		String url = Utilities.readProperties("url");
		context.getDriver().get(url);
	}

	@When("User enters {string} and {string}")
	public void user_enters_valid_and(String username, String password) {
		loginPage = context.getLoginPage();
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}
	
//	@When("User enters valid credential")
//	public void user_enters_valid_and() {
//		loginPage = context.getLoginPage();
//		String userid = Utilities.readProperties("userid");
//		String pass = Utilities.readProperties("password");
//		loginPage.enterUsername(userid);
//		loginPage.enterPassword(pass);
//	}
	
	@And("click on login button")
	public void click_on_login_button()
	{
		loginPage.clickSubmit();
	}

	@Then("User is navigated to Home Page")
	public void user_is_navigated_to_home_page() {
		HomePage home = context.getHomePage();
	    String title = home.title();
	    Assert.assertEquals(title, "Guru99 Bank Manager HomePage");
	}
	
	@Then("User received error message")
	public void User_received_error_message() {
		String error = loginPage.getLoginError();
		context.getDriver().switchTo().alert().accept();
		Assert.assertEquals(error, "User or Password is not valid");
	}



}
