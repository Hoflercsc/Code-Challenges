package page_google;

import static org.testng.Assert.assertTrue;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.naming.ldap.ExtendedRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import parallel.DriverFactory;

public class GooglePage {
	
	
	By searchField = By.xpath("//input[@title='Search']");
	By seachBtn = By.xpath("(//*[@type='submit'])[3]");
	By searchFieldTwo = By.xpath("//input[@aria-label='Search']");
	By officalDuckSite = By.xpath("//h3[contains(text(),'Official Anaheim Ducks Website | NHL.com')]");

	public void verifyGoogleSearchField() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getInstance().getDriver()).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(600))
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(searchField)));
	}
	
	public void verifySearchBtn() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getInstance().getDriver()).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(600))
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(seachBtn)));
	}
	
	public void verifyGoogleSearchFieldTwo() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getInstance().getDriver()).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(600))
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(searchFieldTwo)));
	}
	
	public void searchFieldSendKeys(String ducks) throws InterruptedException {
		DriverFactory.getInstance().getDriver().findElement(searchField).sendKeys(ducks);
		Thread.sleep(1000);
		DriverFactory.getInstance().getDriver().findElement(searchField).sendKeys(Keys.TAB);
	}
	
	public void clickSearchBtn() {
		DriverFactory.getInstance().getDriver().findElement(seachBtn).click();
	}

	public void verifyDucksInSearchField() {
		
		WebElement element = DriverFactory.getInstance().getDriver().findElement(searchFieldTwo);
		String value = element.getAttribute("value");
		Assert.assertTrue(value.contains("Ducks"));	
	}

	public void verifyOfficalDuckSiteHeadings() throws InterruptedException {
		String duck1 = "//h3[contains(text(),'Official Anaheim Ducks Website | NHL.com')]";
		String duck2 = "//h3[contains(text(),'Ducks Unlimited')]";
		Assert.assertTrue(DriverFactory.getInstance().getDriver().findElements(By.xpath(duck1)).size() > 0);
		Assert.assertTrue(DriverFactory.getInstance().getDriver().findElements(By.xpath(duck2)).size() > 0);
		System.out.println("------- Completed --> verifyOfficalDuckSiteHeadings -----------");
	}
	
	
	public void AdditionalDuckVerify() throws InterruptedException {
		String searchText = "Ducks";
		
		 WebElement page = DriverFactory.getInstance().getDriver().findElement(By.xpath("//html"));
		 List<WebElement> options = page.findElements(By.tagName("h3"));
	  	 List<String> h3List = options.stream().map(WebElement::getText).collect(Collectors.toList());
		 System.out.println(h3List);
		 System.out.println("Testing -----------");
		 
		 for (WebElement element : options) {
			 if (element.getText().contains(searchText)) {
				 System.out.println(element.getText());
				 //break;
			 }
			 
		 }
		 
	}
	
	public void searchForDucks(String ducks) throws InterruptedException {
		this.verifyGoogleSearchField();
		this.searchFieldSendKeys(ducks);
		this.verifySearchBtn();
		this.clickSearchBtn();
		System.out.println("------- Completed --> searchForDucks -----------");
	}
	
	
	public void verifyDataInSearchField() throws InterruptedException {
		this.verifyGoogleSearchFieldTwo();
		this.verifyDucksInSearchField();
		System.out.println("------- Completed --> verifySearchResults -----------");
	}
	
	public void additionVerification() throws InterruptedException {
		this.AdditionalDuckVerify();
		System.out.println("------- Completed --> additionVerification -----------");
	}
	
	
}
