package seleniumexprog;

import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestWebsite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver testWeb = new ChromeDriver();
		//System.setProperty("webdriver.gecko.driver","Downloads://Firefox Installer.exe");
		//WebDriver testWeb = new FirefoxDriver();
		testWeb.get("https://www.automationtestinginsider.com/2019/08/student-registration-form.html");
		System.out.println(testWeb.getTitle());
		testWeb.findElement(By.xpath("//*[@name=\"First_Name\"]")).sendKeys("Subbu");
		testWeb.findElement(By.xpath("//*[@name=\"Last_Name\"]")).sendKeys("Lakshmi");
		Select date= new Select(testWeb.findElement(By.xpath("//*[@id=\"Birthday_Day\"]")));
		date.selectByValue("4");
		Select month= new Select(testWeb.findElement(By.xpath("//*[@id=\"Birthday_Month\"]")));
		month.selectByValue("May");
		Select year= new Select(testWeb.findElement(By.xpath("//*[@id=\"Birthday_Year\"]")));
		year.selectByValue("1999");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[4]/td[2]/input")).sendKeys("psubbu1999@gmail.com");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[5]/td[2]/input")).sendKeys("6369879818");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[6]/td[2]/input[2]")).click();
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[12]/td[2]/input[1]")).click();
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[7]/td[2]/textarea")).sendKeys("10 DM, ulundurpet");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[8]/td[2]/input")).sendKeys("Ulundurpet");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[9]/td[2]/input")).sendKeys("606107");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[10]/td[2]/input")).sendKeys("Tamil Nadu");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[14]/td[2]/input[3]")).click();		
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[12]/td[2]/input[5]")).click();
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[12]/td[2]/input[6]")).sendKeys("Scrolling news feed");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[2]/td[3]/input")).sendKeys("State Board");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[2]/td[4]/input")).sendKeys("90");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[2]/td[5]/input")).sendKeys("2014");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[3]/td[3]/input")).sendKeys("State Board");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[3]/td[4]/input")).sendKeys("85");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[3]/td[5]/input")).sendKeys("2016");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[4]/td[3]/input")).sendKeys("Periyar");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[4]/td[4]/input")).sendKeys("72");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[4]/td[5]/input")).sendKeys("2019");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[5]/td[3]/input")).sendKeys("Periyar");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[5]/td[4]/input")).sendKeys("80");
		testWeb.findElement(By.xpath("//*[@id=\"post-body-7514611991416825350\"]/div[1]/table/tbody/tr[13]/td[2]/table/tbody/tr[5]/td[5]/input")).sendKeys("2021");
		
		//webTest.close();
	}

}
