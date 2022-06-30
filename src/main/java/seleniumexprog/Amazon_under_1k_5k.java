package PracticeSelenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.opencsv.CSVWriter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon_under_1k_5k {
	WebDriver link = null;
	Properties properties = null;
	String browsername = null;

	@Test(priority = 0)
	void getpropertyfile() throws IOException {
		String userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
				+ pathSeparator + "PracticeSelenium" + pathSeparator + "Amazon_under_1k_5k.Properties";

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
		} else if (browsername.equalsIgnoreCase("edge")) {
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

	@Test(priority = 3)
	void filterProduct() throws IOException {
		String productsname = null;
		String productprice=null;
		int startPrice = 0;
		int endPrice = 0;
		List<String> productPriceList = null;
		WebDriverWait wait = new WebDriverWait(link, Duration.ofSeconds(20));

		WebElement elements = wait.until(ExpectedConditions
				.elementToBeClickable(link.findElement(By.xpath(properties.getProperty("pricerange")))));
		elements.click();

		List<WebElement> productnames = link.findElements(By.xpath(properties.getProperty("productnames")));
		
		System.out.println("Count of product" + " " + productnames.size());
		
		List<String> productList = new ArrayList<String>();
		for (int productindex = 0; productindex < productnames.size(); productindex++) {

			productsname = productnames.get(productindex).getText();
			productList.add(productsname);
			// System.out.println(productsname);
		}
		if (productsname.contains("Camera")) {
			System.out.println("Product is related to searched");
			
			List<WebElement> productsprice = link.findElements(By.xpath(properties.getProperty("productprices")));
			productPriceList = new ArrayList<String>();
			
			Map<WebElement, Integer> map = new HashMap<WebElement, Integer>();
			System.out.println("count of product price" + " " + productsprice.size());
			for (int i = 0; i < productsprice.size(); i++) {  
				// this is to remove the empty price
				productprice = productsprice.get(i).getText();
				productPriceList.add(productprice);
				if (productsprice.get(i).getText() != "") {
					map.put(productsprice.get(i), Integer.parseInt(productsprice.get(i).getText().replaceAll(",", "")));
					
				}

			}

			List<Entry<WebElement, Integer>> priceOrder = new ArrayList<Map.Entry<WebElement, Integer>>(map.entrySet());

			priceOrder.sort(Entry.comparingByValue());
			
			for (Entry pricelist : priceOrder) { 
				// System.out.println("Price List" + " - " + pricelist.getValue());
				// System.out.println("--------------------------------------------");
				startPrice = priceOrder.get(0).getValue();
				endPrice = priceOrder.get(priceOrder.size() - 1).getValue();
			}

			if (startPrice > 1000 && endPrice < 5000) {
				System.out.println("Product comes under the selected range");
			} else {
				System.err.println("Incorrect range");
			}

		} else {
			System.out.println("no product");
		}
	FileOutputStream fos = new FileOutputStream("C:\\Users\\subbulakshmi.pachaia\\eclipse-workspace\\SeleniumEx_Testing_Website\\src\\main\\java\\PracticeSelenium\\sample.csv");
	OutputStreamWriter osw = new OutputStreamWriter(fos);
	
	
	//String[] header= {"Product name", "Product price"};
	String[] strings1 = productList.toArray(new String[0]);
	
	String[] strings2 = productPriceList.toArray(new String[0]);
	
	
	
	List<String[]> content = new ArrayList<String[]>();
	//content.add(header);
	content.add(strings1);
	content.add(strings2);
	
	try (CSVWriter csv = new CSVWriter(osw)) {
		
		csv.writeAll(content);
		
		
		csv.flush();
	}
	System.out.println("file is created");
	
	
	
	}
	

}
