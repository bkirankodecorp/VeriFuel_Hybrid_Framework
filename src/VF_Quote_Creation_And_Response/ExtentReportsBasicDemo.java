package VF_Quote_Creation_And_Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportsBasicDemo {
	
	private static WebDriver driver=null;

	public static void main(String[] args) throws Exception {

		
		ExtentHtmlReporter htmlReporter= new ExtentHtmlReporter("extentreports.html");
		
		 // create ExtentReports and attach reporter(s)
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        // creates a toggle for the given test, adds all log events under it    
        ExtentTest test1 = extent.createTest("OPen D1 Fuel Connect", "Validating dev site");
        
        System.setProperty("webdriver.chrome.driver",
				"../Veri-Fuel_Hybrid_Framework/Resources/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("chrome.switches","--disable-extensions");
		options.addArguments("--disable-web-security");
		options.addArguments("--start-maximized");
		options.addArguments("--no-proxy-server");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("disable-infobars");
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		driver = new ChromeDriver(options);
        
		test1.log(Status.INFO, "Navigating to dev site");
        driver.get("http://dev.veri-fuel.com/qa");
        Thread.sleep(2000);
        test1.pass("Navigated to dev site");
        
        driver.findElement(By.name("Username")).sendKeys("plakkam@kodecorp.com");
        test1.pass("UserName Entered");

        
        driver.findElement(By.name("Password")).sendKeys("ultralow");
        test1.pass("Password is entered");
        
        driver.findElement(By.id("loginbtn")).click();
        test1.pass("Login Button Clicked");
        
        driver.close();
        test1.info("test case completed");
     // calling flush writes everything to the log file
        extent.flush();
        
        
        
	}

}
