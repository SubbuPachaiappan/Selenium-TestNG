package seleniumexprog;

import java.time.Duration;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazonwebsite {
	static WebDriver link= null;
	Scanner userinput= new Scanner(System.in);

	@Test(priority=1)
	void gotoamazon() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		//WebDriverManager.chromedriver().setup();
		link= new ChromeDriver();
		link.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		link.get("https://www.amazon.in/");
		link.manage().window().maximize();
		//search product		
		link.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles");
		link.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]")).click();
	}
	@Test(priority=2)
		void getBrand() {
		List<WebElement> products = link.findElements(By.xpath("//ul[@aria-labelledby=\"p_89-title\"]"));
		link.findElements(By.xpath("(//*[@class=\"a-expander-prompt\"])[2]"));
		 for (int i = 0; i < products.size(); i++) {
	    	   String name = products.get(i).getText();
	    	   System.out.println(name);
	    	   System.out.println("Enter the brand name");
	    	   String brandname=userinput.nextLine();
	    	   if(name.contains(brandname)) {
	     		   System.out.println("Brand is available");
	     		   
	    	   }else {
	    		   System.out.println("Brand is not available");
	    	   }
	}

}
}
