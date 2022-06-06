package seleniumexprog;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Loginpage {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver link = new ChromeDriver();
		link.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		link.get("https://www.rahulshettyacademy.com/loginpagePractise/");
		link.findElement(By.cssSelector("#username")).sendKeys("subbulakshmi");
		link.findElement(By.cssSelector("#password")).sendKeys("subbu@4599");
		link.findElement(By.xpath("(//*[@class=\"checkmark\"])[2]")).click();
		Thread.sleep(2000);
		link.findElement(By.xpath("(//button[@class=\"btn btn-success\"])")).click();
		Select option = new Select(link.findElement(By.xpath("(//*[@class=\"form-control\"])[3]")));
		option.selectByValue("stud");
		link.findElement(By.xpath("(//*[@id=\"terms\"])")).click();
		Thread.sleep(2000);
		link.findElement(By.xpath("(//*[@id=\"signInBtn\"])")).click();
		//Thread.sleep(2000);
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the file Name:");
		String filename = input.nextLine();
		String getUserDirectory = System.getProperty("user.dir");
		String path = getUserDirectory + File.separator + "Picture" + File.separator + filename;
		File files = new File(path);
		TakesScreenshot snippet = (TakesScreenshot) link;
		File referfile = snippet.getScreenshotAs(OutputType.FILE);
		org.openqa.selenium.io.FileHandler.copy(referfile, files);
		System.out.println(link.findElement(By.xpath("//div[@class=\"alert alert-danger col-md-12\"]")).getText());
		
		
		
		
		//link.findElement(By.xpath("//*[@class=\"btn btn-secondary\"]")).click();
	}

}
