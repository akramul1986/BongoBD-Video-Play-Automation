package webpage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class HomePage {
	
	
	WebDriver driver;
	String URL;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@Test (priority=0)
	public void GoToBongoSite(String url) throws Exception {
		this.URL = url;
		driver.get(url);
		//driver.manage().window().maximize();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(0));
	    Thread.sleep(1000);
	    String title = driver.getTitle();
		System.out.println("Successfully Open the BongoBD site and The page title is : " +title);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}
	
	@Test (priority=1)
	public void ScrollDown() throws Exception {
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		System.out.println("Successfully Scroll down and show the content list Properly ");
	}
	
	@Test (priority=2)
	public void ClickOnFreeContent() throws Exception {
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
		List<WebElement> elent = driver.findElements(By.className("MuiGrid-root"));
		Thread.sleep(1000);
		
		Collections.shuffle(elent);
		elent.get(0).click();
		System.out.println("Successfully open the Video Link");
		if(elent != null) {
			 System.out.println("Successfully load and Play the video");
			 
			 }
		

	}
	
	@Test (priority=3)
	public void PlayVideoFullScreenMode() throws Exception {
		Thread.sleep(10000);
		driver.findElement(By.className("vjs-fullscreen-control")).click();
		System.out.println("Successfully Play the video with full screen");
		
	}

}
