package complainbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestocCB {

	public static void main(String[] args) {
		// this line may need to updaate
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\atiqa\\Downloads\\chromedriver.exe");

		
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://complainbox.000webhostapp.com/");
		
		driver.findElement(By.xpath("/html/body/nav/div/ul[2]/li/a")).click();
		
		// for log in 
		WebElement emailBox = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[1]/input"));
		emailBox.sendKeys("khayrul.atiq@gmail.com");
		WebElement passwordBox = driver.findElement(By.xpath("//*[@id='myInput']"));
		passwordBox.sendKeys("asdfghjkl");
		emailBox.submit();
	
		
		
		driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[2]/a")).click();
		
		
		
		driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[4]/a")).click();
		driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[5]/a")).click();
		driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[6]/a")).click();
		
		driver.findElement(By.xpath("//*[@id='content']/a")).click();
		
		
		

		driver.findElement(By.xpath("/html/body/nav/div/ul[2]/li/a")).click();
		
	}
}
