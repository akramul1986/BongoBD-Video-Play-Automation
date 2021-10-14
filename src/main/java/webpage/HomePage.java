package webpage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class HomePage {

	WebDriver driver;
	String URL;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Navigate the BongoBD site and print the Page Title.
	@Test(priority = 0)
	public void GoToBongoSite(String url) throws Exception {
		this.URL = url;
		driver.get(url);
		// driver.manage().window().maximize();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(0));
		Thread.sleep(1000);
		String title = driver.getTitle();
		System.out.println("Successfully Open the BongoBD site and The page title is : " + title);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	// Scroll down the page to find out the video list.
	@Test(priority = 1)
	public void ScrollDown() throws Exception {
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		System.out.println("Successfully Scroll down and show the content list Properly ");
	}

	// Fetches the list of video links and play the first 3 unique videos.
	@Test(priority = 2)
	public void ClickOnFreeContent(int count) throws Exception {

		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		List<WebElement> elent = driver.findElements(By.tagName("a"));
		List<String> links = new ArrayList();
		Set<String> uniqueLinks = new HashSet();//To Check Unique video links in O(1) complexity.
		//Filter only the links having /watch/ 
		for (WebElement webElement : elent) {
			String link = webElement.getAttribute("href");
			if (link != null && link.contains("/watch/") && !uniqueLinks.contains(link)) {
				uniqueLinks.add(link);
				links.add(link);
			}
		}
		//Switch to the second tab.
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		//Use count if it is less than links size otherwise use link size.
		int videoCount = count < links.size() ? count : links.size();
		//Play {videoCount} number of videos for 5 seconds.
		for (int i = 0; i < videoCount; i++) {
			String link = links.get(i);
			driver.get(link);
			WebElement playbutton = driver.findElement(By.className("vjs-big-play-button"));
			if (playbutton != null) {
				playbutton.click();
				Thread.sleep(5000);
			}
		}
		Thread.sleep(1000);
		System.out.println("Successfully open the Video Link");
		if (elent != null) {
			System.out.println("Successfully load and Play the Free video");
		}
	}

	// Play the video as Full Screen mode and the video will be played for 10
	// seconds.
	@Test(priority = 3)
	public void PlayVideoFullScreenMode() throws Exception {
		Thread.sleep(10000);
		driver.findElement(By.className("vjs-fullscreen-control")).click();
		System.out.println("Successfully Play the video with full screen");

	}

}
