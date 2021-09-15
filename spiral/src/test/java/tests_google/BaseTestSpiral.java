package tests_google;

//import static constants.ConstantsFile.HOME_PAGE;
import static constants.ConstantsFile.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import extentreports.ExtentManager;
import extentreports.GetScreenShot;
import page_google.GooglePage;
import parallel.BrowserFactory;
import parallel.DriverFactory;
import parallel.PropertiesOperations;

public class BaseTestSpiral {
	// -------Reference Variables-------------
	GooglePage objGoogle;
	// -----------------------------
	protected ExtentTest test;// --parent test
	ExtentReports report;

	BrowserFactory bf = new BrowserFactory();
	@BeforeClass
	public void setUp() throws Exception {
		report = ExtentManager.getInstance();
		
	    String browser = PropertiesOperations.getPropertyValueByKey("browser");

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));		
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(HOME_PAGE);
	}
	

	@BeforeMethod
	public void register(Method method) {
		String testName = method.getName();
		test = report.createTest(testName);
	}

	@AfterMethod
	public void catureStatus(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Method named as : " + result.getName() + " is passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String temp=GetScreenShot.getScreenshot();
			test.log(Status.FAIL,"Test failure : " + (result.getThrowable().getMessage()), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.PASS, "Test Method named as : " + result.getName() + " is skipped");
		}
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		DriverFactory.getInstance().closeBrowser();
		report.flush();
	}
}
