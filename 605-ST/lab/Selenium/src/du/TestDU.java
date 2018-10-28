package du;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestDU {

	public static void main(String[] args) {
		// this line may need to updaate
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\atiqa\\Downloads\\chromedriver.exe");

		
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.btv.gov.bd/");
		
		
		
		driver.findElement(By.xpath("//*[@id='dawgdrops']/ul/li[2]")).click();
		driver.findElement(By.xpath("//*[@id='dawgdrops']/ul/li[3]")).click();
		
		
		WebElement event = driver.findElement(By.xpath("//*[@id='right-content']/div[5]/table/tbody/tr[3]/td/a/span/span"));
		new Actions(driver).moveToElement(event).perform();
		event.click();
		
		driver.findElement(By.xpath("//*[@id='lang_form']/button")).click();
		
		
	}
}
