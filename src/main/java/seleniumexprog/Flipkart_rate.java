package PracticeSelenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart_rate {

	static WebDriver link = null;
	Properties properties = null;
	String browsername = null;
	String totalRate = null;

	@Test(priority = 0)
	void getpropertyfile() throws IOException {
		String userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
				+ pathSeparator + "PracticeSelenium" + pathSeparator + "rating_flipkart.Properties";

		FileInputStream inputStream = new FileInputStream(filePath);
		properties = new Properties();
		properties.load(inputStream);
	}

	@Test(priority = 1)
	void gotowebsite() throws IOException {

		browsername = properties.getProperty("browsername");

		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			link = new ChromeDriver();
			link.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			link.get(properties.getProperty("url"));
			link.manage().window().maximize();
		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.edgedriver().setup();
			link = new EdgeDriver();
			link.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			link.get(properties.getProperty("url"));
			link.manage().window().maximize();

		}
	}

	@Test(priority = 2)
	void getrating() {
		link.findElement(By.xpath(properties.getProperty("cancel"))).click();
		link.findElement(By.xpath(properties.getProperty("search"))).sendKeys("y21g");
		link.findElement(By.xpath(properties.getProperty("submit"))).click();
		String ratingString = link.findElement(By.xpath(properties.getProperty("rating"))).getText();
		System.out.println("Rating is : " + ratingString);

		List<WebElement> lists = link.findElements(By.xpath(properties.getProperty("totalProduct")));
		List<String> ratingList = new ArrayList<String>();

		for (WebElement webElement : lists) {
			totalRate = webElement.getText();
			System.out.println("Total product Rating is : " + totalRate);
			ratingList.add(totalRate);
		}

		TreeSet<Double> overallRating = new TreeSet<Double>();
		for (String string : ratingList) {
			double totalRating = Double.parseDouble(string);
			overallRating.add(totalRating);

		}
		System.out.println("Highest rating is : " + overallRating.last());
		System.out.println("Lowest rating is : " + overallRating.first());
	}

}
