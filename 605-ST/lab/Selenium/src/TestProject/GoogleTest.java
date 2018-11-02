package TestProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Atiq\\Downloads\\Compressed\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		System.err.println(driver.getTitle());
		
		WebElement searchBox = driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("Atiq Ahammed");
		
		
		WebElement searchButton = driver.findElement(By.xpath("//input[@jsaction='sf.chk']"));
		searchButton.click();
		
		driver.quit();
		
	}

}
