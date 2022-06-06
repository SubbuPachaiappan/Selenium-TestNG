package seleniumexprog;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class My_Store_Website {
		
		WebDriver testSite;
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	@Test(priority=1)
		void getsite() {
		WebDriverManager.chromedriver().setup();
		testSite= new ChromeDriver();
		testSite.get("https://demo.nopcommerce.com/");
		}
	@Test(priority=2)
	void searchElement() throws InterruptedException {
		WebElement searchBox=testSite.findElement(By.xpath("//*[@class=\"search-box-text ui-autocomplete-input\"]"));
		searchBox.sendKeys("Nokia");
		testSite.findElement(By.xpath("//*[@class=\"button-1 search-box-button\"]")).submit();
		JavascriptExecutor js = (JavascriptExecutor) testSite;
	       js.executeScript("window.scrollBy(0,550)", "");
	       
	       List<WebElement> products = testSite.findElements(By.cssSelector("h2.product-title"));
	       for (int i = 0; i < products.size(); i++) {
	    	   String name = products.get(i).getText();
	    	   if (name.contains("Nokia Lumia 1020")) {
	    		    testSite.findElements(By.xpath("//button[text()='Add to cart']")).get(i).click();
	    		    Thread.sleep(3000);
	    		    break;
			}
		}
	       TakesScreenshot screenshot= (TakesScreenshot) testSite;
	       File Sourcefile= screenshot.getScreenshotAs(OutputType.FILE);

	       String Filename = "MyStore";

	       File files =new File("C:\\Users\\subbulakshmi.pachaia\\Desktop\\Java" +Filename+".jpg");
	       try {
			org.openqa.selenium.io.FileHandler.copy(Sourcefile, files);
			Thread.sleep(3000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       testSite.quit();
	       
	       
	}
}
