package PracticeSelenium;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class Sort_by_rating_10_6 {

	static WebDriver link = null;
	Properties properties = null;
	String browsername = null;

	@Test(priority = 0)
	void getpropertyfile() throws IOException {
		String userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
				+ pathSeparator + "PracticeSelenium" + pathSeparator + "Sort_rate_details.Properties";

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
	void searchProduct() {

		// search product
		WebElement searchBox = link.findElement(By.id(properties.getProperty("searchbox")));
		searchBox.sendKeys(properties.getProperty("product"));

		link.findElement(By.xpath(properties.getProperty("submitbutton"))).click();
	}
	@SuppressWarnings("rawtypes")
	@Test(priority=3)
	void getBrand() throws InterruptedException {
	List<WebElement> productsprice = link.findElements(By.cssSelector("[class='a-price-whole']"));
	 Map<WebElement, Integer> map = new HashMap<WebElement, Integer>();
	/* for (int i = 0; i < productsprice.size(); i++) {
		 // to get low and high price so we need to store this in a map
		if(productsprice.get(i).getText()!= " ") {
		 map.put(productsprice.get(i), Integer.parseInt(productsprice.get(i).getText().replace(",", " ")));
		 //need to update in integer
    	      }
	 }*/
	 for(int i=0;i<productsprice.size();i++){
			if(productsprice.get(i).getText() !="") {
				map.put(productsprice.get(i),Integer.parseInt(productsprice.get(i).getText().replaceAll(",", "")));
				
			}
			
		}
		/*
		 * for(Entry m :map.entrySet()) { System.out.println(m.getValue()); }
		 */
	List<Entry<WebElement,Integer>> es = new ArrayList<Map.Entry<WebElement,Integer>>(map.entrySet());
	
	es.sort(Entry.comparingByValue());
	
	for(Entry m :es) {
		System.out.println(m.getValue());
	}
	//es.get(es.size()-1).getKey().click();
	es.get(0).getKey().click();
	}
   	   
    	  
}

	
