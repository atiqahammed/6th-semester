package materialDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestofMD {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\atiqa\\Downloads\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		
		//System.out.println(driver.getTitle());

		WebElement searchBox = driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("material design");
		searchBox.submit();
		
		//driver.findElement(By.xpath("//a[@href ='/docs/configuration']")).click();
		
		driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div[1]/div/div/div[1]/a[1]")).click();
		
		//*[@id="rso"]/div[1]/div/div[1]/div/div/div[1]/a[1]
		
		
		
		
		
		//driver.quit();
		
		
	}
	
}
