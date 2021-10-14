package testCase;

import org.testng.annotations.Test;

import webpage.HomePage;


public class TestCases extends BaseTest {
  @Test
public void OpenFreeVieoAndPlayTheVideo() throws Exception {
	  
	  HomePage aHomePage = new HomePage(driver);
	  aHomePage.GoToBongoSite("https://bongobd.com/");
	  aHomePage.ScrollDown();
	  aHomePage.ClickOnFreeContent(3);
	  aHomePage.PlayVideoFullScreenMode();
	  
  }
}
