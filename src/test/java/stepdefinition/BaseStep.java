package stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.TestContext;
import utility.Utilities;

public class BaseStep extends TestContext{
	
	TestContext context;
	
	public BaseStep(TestContext context)
	{
		this.context = context;
	}
	
	@Before
	public void BrowserSetup(Scenario scenario)
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		context.setDriver(driver);
		context.initializePageObject(driver);
		
		context.setScenario(scenario);
	}
	
	@After
	public void tearDown(Scenario scenario) {
		try {
			Utilities.takeSnapShot(context.getDriver(), scenario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.getDriver().close();
	}

}
