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
		WebElement searchBox = driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("material design");
		searchBox.submit();
		
		//search completed
		
		
		driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div[1]/div/div/div[1]/a[1]")).click();
		
		driver.findElement(By.xpath("/html/body/header/button[1]/i")).click();
		
	//	 /html/body/header/button[1]/i
		
		
		
		driver.findElement(By.xpath("/html/body/nav/ul[2]/li[3]/button")).click();
		driver.findElement(By.xpath("//a[@href ='./foundation-overview']")).click();
		
		//href="./layout/understanding-layout.html"
		
		
		//driver.findElement(By.xpath("//*[@id='_nav4']/ul/li[1]/a")).click();
		
		
		//*[@id="_nav6"]/ul/li[1]/a
		
		// /html/body/nav/ul[2]/li[5]/button
		
		
		
		
		
		
		//driver.quit();
		
		
	}
	
}
