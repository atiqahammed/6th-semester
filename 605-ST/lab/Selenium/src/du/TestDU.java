package du;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestDU {

	public static void main(String[] args) {
		// this line may need to updaate
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\atiqa\\Downloads\\chromedriver.exe");

		
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.du.ac.bd/");
		
		
		
		driver.findElement(By.xpath("//*[@id='header']/nav/div/div[2]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='header']/nav/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id='header']/nav/div/div[2]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='header']/nav/div/div[2]/ul/li[3]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id='header']/nav/div/div[2]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='header']/nav/div/div[2]/ul/li[3]/ul/li[11]/a")).click();
		driver.findElement(By.xpath("//*[@id='middle']/div/div/div[1]/div/ul/li[8]/a")).click();
		driver.findElement(By.xpath("//*[@id='middle']/div/div/div[1]/div[1]/ul/li[1]/a")).click();
	
		
	}
}
