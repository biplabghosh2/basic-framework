package stepdefinition;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.HomePage;
import utility.TestContext;
import utility.Utilities;

public class HomeStep {

	TestContext context;
	String accTy;

	public HomeStep(TestContext context) {
		this.context = context;
	}

	@When("Create a new account with default account type")
	public void create_a_new_account_with_default_account_type() {
		//context.getHomePage().createAccount("2089", "1000");
		String name = context.getScenario().getName();
		String custId = Utilities.readExcel(name, "CustID");
		String amount = Utilities.readExcel(name, "Amount");
		HomePage homepage = context.getHomePage();
		accTy = homepage.createAccount(custId, amount);
	}

	@Then("Account should created with account type Savings")
	public void account_should_created_with_account_type_savings() {
		Assert.assertEquals(accTy, "Savings");
	}

}
