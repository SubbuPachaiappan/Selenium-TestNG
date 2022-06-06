package seleniumexprog;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Webtable {
	WebDriver link;
	int size;
@Test(priority=0)
 void goToLink() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		link = new ChromeDriver();
		link.get("https://www.techlistic.com/p/demo-selenium-practice.html");

		JavascriptExecutor js = (JavascriptExecutor) link;
	       js.executeScript("window.scrollBy(0,980)", "");
	       
}
@Test(priority=1)
void getdata() {
	       WebElement table =link.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]"));
	      List<WebElement> headers = table.findElements(By.xpath("//th[@scope=\"col\"]"));
	      int colsize= headers.size();
	      System.out.println("Column size"+" "+colsize);
	      for (WebElement header : headers) {
			String headtext=header.getText();
			System.out.println(headtext);
		}
	      List<WebElement> allrows = table.findElements(By.xpath("//th[@scope=\"row\"]"));
	     allrows.remove(0);
	      size= allrows.size();
	      System.out.println("No of Rows"+ size);
	      System.out.println("Structure Size"+"-"+size);
				if (size==4) {
					System.out.println("It has 4 structure only");
				}else 
					{System.out.println("Incorrect structure");
			}
				
				for (int r = 1; r <=size; r++) {
					for (int c = 1; c <colsize; c++) {
						String data= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr["+r+"]/td["+c+"]")).getText();
						System.out.println(data);
						
					}
					
				}
}
@Test(priority=2)
void burjkhalifa() {
		WebElement table =link.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]"));
				for(int i=1; i<size;i++) {
					String Structure= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr["+i+"]/th")).getText();
					if(Structure.equals("Burj Khalifa")) {
						String Height= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr[1]/td[3]")).getText();
						System.out.println(Structure+" "+Height);
					}
				}
}
@Test(priority=3)
void clocktowerheight() {
	WebElement table =link.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]"));
	for(int i=1; i<size;i++) {
		String Structure= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr["+i+"]/th")).getText();
		if(Structure.equals("Clock Tower Hotel")) {
			String Height= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr[2]/td[3]")).getText();
			System.out.println(Structure+" "+Height);
		}
	}
}
@Test(priority=4)
void taipei101() {
	WebElement table =link.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]"));
	for(int i=1; i<size;i++) {
		String Structure= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr["+i+"]/th")).getText();
		if(Structure.equals("Taipei 101")) {
			String Height= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr[3]/td[3]")).getText();
			System.out.println(Structure+" "+Height);
		}
	}
}
@Test(priority=5)
void financialcenterheight() {
	WebElement table =link.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]"));
	for(int i=1; i<=size;i++) {
		String Structure= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr["+i+"]/th")).getText();
		if(Structure.equals("Financial Center")) {
			String Height= table.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]//tbody/tr[4]/td[3]")).getText();
			System.out.println(Structure+" "+Height);
		}
	}
}
@Test(priority=6)
void footersize() {
	
				WebElement table =link.findElement(By.xpath("//table[@class=\"tsc_table_s13\"]"));
				List<WebElement> footertext=table.findElements(By.xpath("//table[@class=\"tsc_table_s13\"]/tfoot//tr"));
				int sizecol = footertext.size();
				System.out.println("It has "+sizecol+ "  column");
	}
		
}
