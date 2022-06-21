package seleniumexprog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Listing_links {

	WebDriver driver = null;
	Properties properties = null;
	String browsername = null;

	@Test
	void getpropertyfile() throws IOException {
		String userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
				+ pathSeparator + "seleniumexprog" + pathSeparator + "Listing_links.Properties";

		FileInputStream inputStream = new FileInputStream(filePath);
		properties = new Properties();
		properties.load(inputStream);

		browsername = properties.getProperty("browsername");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(properties.getProperty("expectedurl"));
		try{
			  String expectedUrl=properties.getProperty("expectedurl") ;
			Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			  System.out.println("Navigated to correct webpage");
			}
			catch(Throwable pageNavigationError){
			  System.out.println("Didn't navigate to correct webpage");
			}
		driver.manage().window().maximize();
	}

	@Test
	void listLinks() throws IOException {

		List<WebElement> linksAvail = driver.findElements(By.tagName(properties.getProperty("tagName")));
		int linkcount = linksAvail.size();
		System.out.println(linkcount);
		
			String userWorkingDirectory = System.getProperty("user.dir");
			String pathSeparator = System.getProperty("file.separator");
			String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
					+ pathSeparator + "seleniumexprog" + pathSeparator + "listinglink.txt";
		      FileWriter w = new FileWriter(filePath); 
				 try (BufferedWriter out = new BufferedWriter(w)) {
					 for (int linkcounts=0; linkcounts<linksAvail.size(); linkcounts++) {
						 	String text = linksAvail.get(linkcounts).getText();
							String link = linksAvail.get(linkcounts).getAttribute("href");
							String newline = System.lineSeparator();
							
							System.out.print((linkcounts+1)+"."+" ");
							System.out.print(linksAvail.get(linkcounts).getText()+ "-");
							System.out.println(linksAvail.get(linkcounts).getAttribute("href"));
							System.out.println(newline+text);
					//w.append(text);
					 out.write(text+"-"+link+newline);
					 out.flush();
				}
		      
		}
		// if i need to click the link
		linksAvail.get(2).click();
		
		
	}

}