package materialDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestofMD {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\atiqa\\Downloads\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("material design");
		searchBox.submit();
		
		//search completed
		
		
		driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div[1]/div/div/div[1]/a[1]")).click();
		driver.findElement(By.xpath("/html/body/header/button[1]/i")).click();
		
		
		
		driver.findElement(By.xpath("/html/body/nav/ul[2]/li[3]/button")).click();
		driver.findElement(By.xpath("//a[@href ='./foundation-overview']")).click();
		
		
		JavascriptExecutor jsx = (JavascriptExecutor)driver;
		jsx.executeScript("window.scrollBy(0,450)", "");
		
		driver.findElement(By.xpath("/html/body/main/article/div[3]/div/div/a[1]/div")).click();
		
		WebElement vedio = driver.findElement(By.xpath("//*[@id='color-theme-creation']/div/div/div[3]/div[2]/div[2]/div[1]/section/div/figure/div/video"));
		new Actions(driver).moveToElement(vedio).perform();
		
		
		driver.findElement(By.xpath("//*[@id='color-theme-creation']/div/div/div[3]/div[2]/div[2]/div[1]/section/div/figure/div/video")).click();
		
		
		//driver.quit();

	}
	
}
