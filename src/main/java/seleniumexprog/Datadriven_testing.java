package datadriven_testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Datadriven_testing {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "LoginData")
	public void loginTest(String userName, String Password, String exp) throws InterruptedException {
		driver.get("https://admin-demo.nopcommerce.com/login");
		WebElement username = driver.findElement(By.id("Email"));
		username.clear();
		username.sendKeys(userName);

		WebElement password = driver.findElement(By.id("Password"));
		password.clear();
		password.sendKeys(Password);

		WebElement submitbutton = driver.findElement(By.xpath("//button[@type='submit']"));
		submitbutton.click();
	
		
		String exp_title ="Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		System.out.println(act_title);

		if (exp.equals("valid")) {
			
			if (exp_title.equals(act_title)) {
				
				driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} else if (exp.equals("invalid")) {
			if (exp_title.equals(act_title)) {
				driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}

		}

	}

	@DataProvider(name = "LoginData")
	public String[][] getData() {
		String loginData[][] = {
				{ "admin@yourstore.com", "admin", "valid" },
				{ "admin@yourstore.com", "adm", "invalid" },
				{ "adm@yourstore.com", "admin", "invalid" },
				{ "adm@yourstore.com", "Adm", "invalid" } };
		return loginData;
	}

	@AfterClass
	void tearDown() {

		driver.close();

	}

}
