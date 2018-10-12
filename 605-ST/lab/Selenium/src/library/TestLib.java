package library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestLib {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\atiqa\\Downloads\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		
		WebElement searchBox = driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("atiqs library");
		searchBox.submit();
		
		driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div/div/div/div[1]/a/h3")).click();
		
		WebElement element = driver.findElement(By.xpath("//*[@id='h.p_rBSA5YezxeRZ']/div/div/div/a/img"));
		new Actions(driver).moveToElement(element).perform();
		
		
		driver.findElement(By.xpath("//*[@id='h.p_rBSA5YezxeRZ']/div/div/div/a/img")).click();
		
		
	}
}
