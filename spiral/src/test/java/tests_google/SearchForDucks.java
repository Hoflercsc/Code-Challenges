package tests_google;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_google.GooglePage;
import static constants.ConstantsFile.*;

public class SearchForDucks extends BaseTestSpiral {

	@Test(priority = 1, description = "Search")
	public void Search() throws InterruptedException {
		test.assignCategory("Google >> Search-For-Ducks");
		
		objGoogle = new GooglePage();
		objGoogle.searchForDucks(DUCKS);
	}
	
	@Test(priority = 2, description = "verifyDataInSearchField")
	public void verifyDataInSearchField() throws InterruptedException {
		test.assignCategory("Google >> Search-For-Ducks");
		
		objGoogle = new GooglePage();
		objGoogle.verifyDataInSearchField();
	}
	
	@Test(priority = 3, description = "verifyOfficalDuckSiteHeadings")
	public void verifyOfficalDuckSiteHeadings() throws InterruptedException {
		test.assignCategory("Google >> Search-For-Ducks");
		
		objGoogle = new GooglePage();
		objGoogle.verifyOfficalDuckSiteHeadings();
	}
	
	@Test(priority = 4, description = "additionVerification")
	public void additionVerification() throws InterruptedException {
		test.assignCategory("Google >> Search-For-Ducks");
		
		objGoogle = new GooglePage();
		objGoogle.additionVerification();
	}
	
	
}