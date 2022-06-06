package seleniumexprog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToolQAWebsite {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	@Test
	void openWebsite() {
		
		WebDriverManager.chromedriver().setup();
		WebDriver testWebsite = new ChromeDriver();
		testWebsite.get("https://demoqa.com/automation-practice-form");
		testWebsite.manage().window().maximize();
		System.out.println(testWebsite.getTitle());
		testWebsite.findElement(By.xpath("//input[@id=\"firstName\"]")).sendKeys("Subbulakshmi");
		testWebsite.findElement(By.xpath("//input[@id=\"lastName\"]")).sendKeys("Pachaiappan");
		testWebsite.findElement(By.xpath("//*[@id=\"userEmail\"]")).sendKeys("1999.bks@gmail.com");
		
		//testWebsite.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();
		//testWebsite.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[7]/div[2]/div[1]/input)")).click();
		
		String expectedUrl = "https://demoqa.com/automation-practice-forms";
		String actualUrl= testWebsite.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "expectedUrl is not actualUrl");
		
		WebElement header = testWebsite.findElement(By.xpath("//*[@class=\"main-header\"]"));
		String expHeader ="Practice Form";
		String actHeader=header.getText();
		Assert.assertEquals(expHeader, actHeader, "not correct header");
	}

}
