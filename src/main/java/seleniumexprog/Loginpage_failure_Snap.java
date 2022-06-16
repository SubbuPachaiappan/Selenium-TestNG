package seleniumexprog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Loginpage_failure_Snap {

	WebDriver driver = null;
	Properties properties = null;
	String browsername = null;

	@Test(priority = 0)
	void getpropertyfile() throws IOException {
		String userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
				+ pathSeparator + "seleniumexprog" + pathSeparator + "Loginpage.Properties";

		FileInputStream inputStream = new FileInputStream(filePath);
		properties = new Properties();
		properties.load(inputStream);
	}

	@Test(priority = 1)
	void gotowebsite() throws IOException {

		browsername = properties.getProperty("browsername");

		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(properties.getProperty("url"));
			driver.manage().window().maximize();
		} else if (browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(properties.getProperty("url"));
			driver.manage().window().maximize();

		}
	}

	@Test(priority = 2)
	void loginsubmit() throws InterruptedException, IOException {
		driver.findElement(By.cssSelector(properties.getProperty("usernamecss"))).sendKeys(properties.getProperty("name"));
		driver.findElement(By.cssSelector(properties.getProperty("passwordcss"))).sendKeys(properties.getProperty("password"));
		driver.findElement(By.xpath(properties.getProperty("usercheckbox"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(properties.getProperty("dropdownxpath"))).click();
		Select option = new Select(driver.findElement(By.xpath(properties.getProperty("selectxpath"))));
		option.selectByValue(properties.getProperty("studentvalue"));
		driver.findElement(By.xpath(properties.getProperty("agreeboxxpath"))).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		WebElement elements= null;
		elements = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(properties.getProperty("signinbuttonxpath")))));
		elements.click();
		elements = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(properties.getProperty("alertxpath")))));
		elements.click();
		String getUserDirectory = System.getProperty("user.dir");
		String path = getUserDirectory + File.separator + "Picture" + File.separator + "Error.png";
		File files = new File(path);
		TakesScreenshot snippet = (TakesScreenshot) driver;
		File referfile = snippet.getScreenshotAs(OutputType.FILE);
		org.openqa.selenium.io.FileHandler.copy(referfile, files);
		System.out.println("Snap taken successfully");
		driver.quit();

	}

}
