package extentreports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import parallel.DriverFactory;

public class GetScreenShot {
	
	 public static String getScreenshot() throws IOException
	    {
	        TakesScreenshot ts = ((TakesScreenshot)DriverFactory.getInstance().getDriver());
	        
	        Date d = new Date();
			String screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
			
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String dest=System.getProperty("user.dir")+"/target/surefire-reports/"+screenshotName;

	        File destination = new File(dest);
	        FileUtils.copyFile(source, destination.getAbsoluteFile());        
	                     
	        return screenshotName;

    }

	
}
