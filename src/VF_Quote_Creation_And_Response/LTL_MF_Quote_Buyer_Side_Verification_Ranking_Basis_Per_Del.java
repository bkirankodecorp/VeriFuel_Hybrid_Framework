package VF_Quote_Creation_And_Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.Constant;
import Utility.ExcelUtils;
public class LTL_MF_Quote_Buyer_Side_Verification_Ranking_Basis_Per_Del {

	private static WebDriver driver;
	private static int colnum=4;


	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeSuite
	public void setUp(){

		htmlReporter= new ExtentHtmlReporter("../Veri-Fuel_Hybrid_Framework/LTL Mobile Fueling/LTL_Quote_Buyer_Side_Calculations_Verification_$_Per_Del.html");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setTheme(Theme.DARK);//Default Theme of Report


	}

	@BeforeTest
	public void setUptest(){

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

	}

	@Test(priority =0)
	public void Browser() throws Exception {


		ExtentTest test1 = extent.createTest("Open D1 Fuel Connect", "Validating dev site");

		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"VF_LTL_MF_Seller_Response");

		String Url = ExcelUtils.getCellData(4, colnum);

		driver.get(Url);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		test1.log(Status.INFO, "Navigating to dev site");
		//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		try {

			if (driver.findElement(By.name("Username")).isDisplayed()) {

				System.out.print("Page is navigated to \n" + Url);
				test1.pass("Page is navigated to \n" + Url);


			} else {

				System.out.print("Page is not navigated to \n" + Url);
				test1.fail("Page is not navigated to \n" + Url);

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}



	@Test(priority =1)
	public  void Buyer_Login() throws Exception{

		ExtentTest test1 = extent.createTest("Buyer Login", "Login with Valid Credentials");
		test1.log(Status.INFO, "Enter valid Username and Password");

		try {

			WebElement UserName =driver.findElement(By.name("Username"));
			UserName.sendKeys(ExcelUtils.getCellData(90, colnum));
			test1.pass(ExcelUtils.getCellData(90, colnum)+ " as Username is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement Password =driver.findElement(By.name("Password"));
			Password.sendKeys(ExcelUtils.getCellData(91, colnum));
			test1.pass(ExcelUtils.getCellData(91, colnum) + " as Password is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement Login = driver.findElement(By.id("loginbtn"));
			Login.click();
			test1.pass("Login Button is clicked");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}

	@Test(priority =2)
	public  void verify_buyer_login() throws Exception{

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
		
		ExtentTest test1 = extent.createTest("verify_buyer_login", "Verify Buyer is logged in dev site");
		test1.log(Status.INFO, "Verify Buyer logged in");

		try {
			
			

			WebElement  newquote = driver.findElement(By.id("dashboardnewBuyerClick"));

			if(newquote.isDisplayed()){

				System.out.println("\n"+ ExcelUtils.getCellData(90, colnum) + " Buyer logged in successfully");
				test1.pass(ExcelUtils.getCellData(90, colnum) + " Buyer Logged in Succesfully");


			}
			else{

				System.out.println(ExcelUtils.getCellData(90, colnum) + " Buyer Loggedin is not Succesfully");
				test1.fail(ExcelUtils.getCellData(90, colnum) + " Buyer Loggedin is not Succesfully");


			}

			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}
		
		
	}

	@Test(priority =3)
	public  void Navigate_to_Create_Or_View_Page() throws Exception{

		ExtentTest test1 = extent.createTest("Navigate_to_Create_Or_View_Page", "Verify Buyer is navigtaed to create page");
		test1.log(Status.INFO, "Verify Buyer is navigtaed to create page");

		try {

			WebElement element=driver.findElement(By.id("26"));
			WebElement element1=driver.findElement(By.id("28"));

			Actions action=new Actions(driver);
			action.moveToElement(element).build().perform();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			action.moveToElement(element1).build().perform();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			element1.click();
			Thread.sleep(3000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			WebElement create=driver.findElement(By.linkText("Create"));
			if(create.isDisplayed()){
				System.out.println("Page is navigated to Create OR View Quote");
				test1.pass("Page is navigated to Create OR View Quote");


			}

			else{
				System.out.println("Page is not navigated to Create OR View Quote");
				test1.fail("Page is not navigated to Create OR View Quote");

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}

	}

	@Test(priority =4)
	public  void Open_Quote_In_Active_Tab() throws Exception{

		Thread.sleep(3000);

		ExtentTest test1 = extent.createTest("Open_Quote_In_Active_Tab", "Navigate to Active Tab");
		test1.log(Status.INFO, "Navigate to Active Tab");

		try {

			WebElement activequotes=driver.findElement(By.xpath("//span[contains(text(),'Active')]"));
			activequotes.click();
			Thread.sleep(3000);
			test1.info("Click Active Quote Tab");

			WebElement activesearch = driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div[2]/div/div[2]/div/div/div/div[1]/div/div[1]/div/a/span/i"));
			activesearch.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.pass("Click Active Search Icon");

			WebElement enteronsearch = driver.findElement(By.id("txtSearch"));
			enteronsearch.sendKeys(ExcelUtils.getCellData(12, colnum));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.pass("Query on Searchbox with " + ExcelUtils.getCellData(12, colnum));

			WebElement openactivequote=driver.findElement(By.xpath("//*[@id='ReviewRFPGrid']/div[3]/table/tbody/tr/td[2]/a"));

			System.out.println("Active Quote Number is " + openactivequote.getText());
			test1.info("Active Quote Number is " + openactivequote.getText());
			openactivequote.click();

		} catch (Exception e) {

			test1.error(e.getMessage());

		}




	}

	@Test(priority =5)
	public  void Verify_Page_Navigated_To_Delivery_Location() throws Exception{

		WebDriverWait wait0 = new WebDriverWait(driver, 20);
		wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

		ExtentTest test1 = extent.createTest("Verify_Page_Navigated_To_Delivery_Location", "Verify Page is Navigated to Delivery Location");
		test1.log(Status.INFO, "Verify Page is Navigated to Delivery Location");

		try {

			WebElement deliverylocationclick=driver.findElement(By.xpath("//*[@id='ReviewRFPDeliveryLocationrGrid']/div[3]/table/tbody/tr/td[2]/a"));
			System.out.println("Delivery Location is " + deliverylocationclick.getText());
			test1.info("Delivery Location is " + deliverylocationclick.getText());

			if (deliverylocationclick.isDisplayed()) {



				System.out.println("Page is Navigated to Delivery Location List");
				test1.pass("Page is Navigated to Delivery Location List");
			}
			else{


				System.out.println("Page is not Navigated to Delivery Location List");
				test1.fail("Page is not Navigated to Delivery Location List");
			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}



	}


	

	@Test(priority =6)
	public  void Open_View_in_TandC() throws Exception{

		ExtentTest test1 = extent.createTest("Open_View_in_TandC", "Open View in TandC");
		test1.log(Status.INFO, "Open View in TandC");

		try {
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("createRFP_RFPEndDate")));

			
			WebElement tandcview=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[39]/a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", tandcview);
			test1.info("View TandC in Manage Rounds is Opened");


		} catch (Exception e) {


			test1.error(e.getMessage());
		}

	}	


	@Test(priority =7)
	public  void Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_TandC() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_TandC", "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in TandC");
		test1.log(Status.INFO, "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in TandC");

		try {

			WebDriverWait wait = new WebDriverWait(driver, 150);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement benchmark=driver.findElement(By.id("Terms_FreightPerDelivery"));

			String expectedbenchmark=ExcelUtils.getCellData(16, colnum);
			String actualbenchmarkvalue=benchmark.getAttribute("value");

			if(actualbenchmarkvalue.equalsIgnoreCase(expectedbenchmark)){

				System.out.println("Delivery Flat Fee value is validated in TandC as " + benchmark.getAttribute("value"));
				test1.pass("Delivery Flat Fee value is validated in TandC as " + benchmark.getAttribute("value"));	

			}

			else{

				System.out.println("Delivery Flat Fee value is not validated in TandC as " + benchmark.getAttribute("value"));
				test1.fail("Delivery Flat Fee value is not validated in TandC as " + benchmark.getAttribute("value"));

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}	

	@Test(priority =8)
	public  void Verify_Freight_And_Margin_$_Per_Gallon_for_Seller_Freight_Rates_In_TandC() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Freight_And_Margin_$_Per_Gallon_in_Seller_Freight_Rates_In_TandC", "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in TandC");
		test1.log(Status.INFO, "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in TandC");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("Terms_FreightPerDelivery")));

		try {
			
			for(int i=1,j=17,k=24; i<=2&&j<=18&&k<=25; i++,j++,k++){

				WebElement frgtpergalvalue=driver.findElement(By.xpath("//*[@id='ProductsSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[4]"));
				String expectedfrgtpergalvalue=ExcelUtils.getCellData(j, colnum);
				String actualfrghtpergal=frgtpergalvalue.getText();
				if(actualfrghtpergal.equalsIgnoreCase(expectedfrgtpergalvalue)){

					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in TandC as " + frgtpergalvalue.getText());
					test1.pass("Freight $/Gal for  " +ExcelUtils.getCellData(k, colnum)+" Category is validated in TandC as " + frgtpergalvalue.getText());
				}
				else{


					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in TandC as " + frgtpergalvalue.getText());
					test1.fail("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in TandC as " + frgtpergalvalue.getText());

				}

			}

			for(int i=1,j=20,k=24; i<=2&&j<=21&&k<=25; i++,j++,k++){

				WebElement margin$pergallon=driver.findElement(By.xpath("//*[@id='ProductsSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[5]"));

				String expectedmargin$pergallonvalue=ExcelUtils.getCellData(j, colnum);
				String actualmargin$pergallonvalue=margin$pergallon.getText();
				if(actualmargin$pergallonvalue.equalsIgnoreCase(expectedmargin$pergallonvalue)){

					System.out.println("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in TandC as " + margin$pergallon.getText());
					test1.pass("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in TandC as " + margin$pergallon.getText());
				}
				else{


					System.out.println("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in TandC as " + margin$pergallon.getText());
					test1.fail("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in TandC as " + margin$pergallon.getText());
				}

			}
			
		} catch (Exception e) {


			test1.error(e.getMessage());
		}
		
	}
	
	@Test(priority =9)
	public  void Verify_Final_Total_And_Ranking_Basis_in_TandC() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Final_Total_And_Ranking_Basis_in_TandC", "Verify final total and Ranking Basis in TandC");
		test1.log(Status.INFO, "Verify final total and Ranking Basis in TandC");
		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollLow, 0)");

		
		try {
			
			WebElement finaltotal=driver.findElement(By.id("ldlfinaltotal"));
			System.out.println("Final Total has value as " + finaltotal.getText());
			
			WebElement closetandc=driver.findElement(By.id("btnClose"));
			closetandc.click();
			Thread.sleep(3000);
			
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("createRFP_RFPEndDate")));


			WebElement rankingbasis=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[16]"));
			System.out.println("Ranking basis has value as " + rankingbasis.getText());
			
			String excepted=ExcelUtils.getCellData(73, colnum);
			String actual=rankingbasis.getText();

			if(actual.equalsIgnoreCase(excepted)){


				System.out.println("Final Total and Ranking Basis in TandC are same and validated and values are " + rankingbasis.getText());
				test1.pass("Final Total and Ranking Basis in TandC are same and validated and values are " + rankingbasis.getText());
			}
			else{


				System.out.println("Final Total and Ranking Basis are not same in TandC and validated");
				test1.fail("Final Total and Ranking Basis are not same in TandC and validated");

			}
			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}

		


	}

	@Test(priority =10)
	public  void Open_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Open_Workbench", "Open Workbench View");
		test1.log(Status.INFO, "Open Workbench View");

		WebElement workbench=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[38]/a"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", workbench);

		test1.info("Click Workbench View in Manage Rounds");

	}	
	
	@Test(priority =11)
	public  void Verify_Buyer_Calculations_on_Workbench() throws Exception{

		WebDriverWait wait1 = new WebDriverWait(driver, 100);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='expandallequp']/label[1]")));
		

		ExtentTest test1 = extent.createTest("Verify_Buyer_Calculations_on_Workbench", "Verify Buyer Calculations on Workbench");
		test1.log(Status.INFO, "Verify Buyer Calculations on Workbench");

		try {

	
			Thread.sleep(4000);

			WebElement StemTime=driver.findElement(By.id("WorkdBenchTerms_StemLoadingTime"));
			String expectedStemTime=ExcelUtils.getCellData(59, colnum);
			String actualStemTime=StemTime.getAttribute("value");

			if(actualStemTime.equalsIgnoreCase(expectedStemTime)){

				System.out.println("Stem Time value is validated in workbench as "+ StemTime.getAttribute("value"));
				test1.pass("Stem Time value is validated in workbench as "+ StemTime.getAttribute("value"));
			}

			else{

				System.out.println("Stem Time value is not validated in workbench as "+ StemTime.getAttribute("value"));
				test1.fail("Stem Time value is not validated in workbench as "+ StemTime.getAttribute("value"));

			}

			WebElement LoadingTime=driver.findElement(By.id("WorkdBenchTerms_LoadingTime"));
			String expectedLoadingTime=ExcelUtils.getCellData(60, colnum);
			String actualLoadingTime=LoadingTime.getAttribute("value");

			if(actualLoadingTime.equalsIgnoreCase(expectedLoadingTime)){

				System.out.println("Loading Time value is validated in workbench as "+ LoadingTime.getAttribute("value"));
				test1.pass("Loading Time value is validated in workbench as "+ LoadingTime.getAttribute("value"));
			}

			else{

				System.out.println("Loading Time value is not validated in workbench as "+ LoadingTime.getAttribute("value"));
				test1.fail("Loading Time value is not validated in workbench as "+ LoadingTime.getAttribute("value"));

			}

			WebElement AvgFuelingTimeperUnitmins=driver.findElement(By.id("WorkdBenchTerms_GalsPerMinutePump"));
			String expectedAvgFuelingTimeperUnitmins=ExcelUtils.getCellData(61, colnum);
			String actualAvgFuelingTimeperUnitmins=AvgFuelingTimeperUnitmins.getAttribute("value");

			if(actualAvgFuelingTimeperUnitmins.equalsIgnoreCase(expectedAvgFuelingTimeperUnitmins)){

				System.out.println("Avg. Fueling Time Per unit mins value is validated in workbench as " + AvgFuelingTimeperUnitmins.getAttribute("value"));
				test1.pass("Avg. Fueling Time Per unit mins value is validated in workbench as " + AvgFuelingTimeperUnitmins.getAttribute("value"));

			}

			else{

				System.out.println("Avg. Fueling Time Per unit mins value is not validated in workbench as " + AvgFuelingTimeperUnitmins.getAttribute("value"));
				test1.fail("Avg. Fueling Time Per unit mins value is not validated in workbench as " + AvgFuelingTimeperUnitmins.getAttribute("value"));

			}

			WebElement FueledUnits=driver.findElement(By.id("eqwbFueledUnits"));
			String expectedFueledUnits=ExcelUtils.getCellData(62, colnum);
			String actualFueledUnits=FueledUnits.getText();

			if(actualFueledUnits.equalsIgnoreCase(expectedFueledUnits)){

				System.out.println("# Fueled Units value is validated in workbench as " + FueledUnits.getText());
				test1.pass("# Fueled Units value is validated in workbench as " + FueledUnits.getText());

			}

			else{

				System.out.println("# Fueled Units value is not validated in workbench as " + FueledUnits.getText());
				test1.fail("# Fueled Units value is not validated in workbench as " + FueledUnits.getText());

			}

			WebElement TotalTimeperDelmins=driver.findElement(By.id("eqwblblTimePerDelivery"));
			String expectedTotalTimeperDelmins=ExcelUtils.getCellData(63, colnum);
			String actualTotalTimeperDelmins=TotalTimeperDelmins.getText();

			if(actualTotalTimeperDelmins.equalsIgnoreCase(expectedTotalTimeperDelmins)){

				System.out.println("Total Time per Del. (mins) value is valdated in Workbench as " + TotalTimeperDelmins.getText());
				test1.pass("Total Time per Del. (mins) value is valdated in Workbench as " + TotalTimeperDelmins.getText());

			}

			else{

				System.out.println("Total Time per Del. (mins) value is not valdated in Workbench as " + TotalTimeperDelmins.getText());
				test1.fail("Total Time per Del. (mins) value is not valdated in Workbench as " + TotalTimeperDelmins.getText());

			}

			WebElement AvgMarginFreightDel=driver.findElement(By.id("eqwbFrietPerDelivery"));
			String expectedTotalRoundTripDeliveryTimemins=ExcelUtils.getCellData(64, colnum);
			String actualTotalRoundTripDeliveryTimemins=AvgMarginFreightDel.getText();

			if(actualTotalRoundTripDeliveryTimemins.equalsIgnoreCase(expectedTotalRoundTripDeliveryTimemins)){

				System.out.println("Avg. Margin & Freight/Del. value is validated in workbench as " + AvgMarginFreightDel.getText());
				test1.pass("Avg. Margin & Freight/Del. value is validated in workbench as " + AvgMarginFreightDel.getText());

			}

			else{

				System.out.println("Avg. Margin & Freight/Del. value is not validated in workbench as " + AvgMarginFreightDel.getText());
				test1.fail("Avg. Margin & Freight/Del. value is not validated in workbench as " + AvgMarginFreightDel.getText());

			}

			WebElement GrossMargin$Hour=driver.findElement(By.id("eqwblblTotalTimetooltip"));
			String expectedGrossMargin$Hour=ExcelUtils.getCellData(65, colnum);
			String actualGrossMargin$Hour=GrossMargin$Hour.getText();

			if(actualGrossMargin$Hour.equalsIgnoreCase(expectedGrossMargin$Hour)){

				System.out.println("Gross Margin $/Hour value is validated in workbench as " + GrossMargin$Hour.getText());
				test1.pass("Gross Margin $/Hour value is validated in workbench as " + GrossMargin$Hour.getText());
			}

			else{

				System.out.println("Gross Margin $/Hour value is not validated in workbench as " + GrossMargin$Hour.getText());
				test1.fail("Gross Margin $/Hour value is not validated in workbench as " + GrossMargin$Hour.getText());

			}

			WebElement distance=driver.findElement(By.id("lblDistance"));
			String expecteddistance=ExcelUtils.getCellData(66, colnum);
			String actualdistance=distance.getText();

			if(actualdistance.equalsIgnoreCase(expecteddistance)){

				System.out.println("Distance value is validated in workbench as " + distance.getText());
				test1.pass("Distance value is validated in workbench as " + distance.getText());
			}

			else{

				System.out.println("Distance value is not validated in workbench as " + distance.getText());
				test1.fail("Distance value is not validated in workbench as " + distance.getText());
			}


		} catch (Exception e) {

		}		

	}

	@Test(priority =12)
	public  void Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_Workbench", "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in Workbench");
		test1.log(Status.INFO, "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in Workbench");

		try {

			WebElement tandcclick=driver.findElement(By.id("WorkdBenchTerms_FreightPerDelivery"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tandcclick);

			tandcclick.click();

			WebDriverWait wait = new WebDriverWait(driver, 150);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement benchmark=driver.findElement(By.id("WorkdBenchTerms_FreightPerDelivery"));

			String expectedbenchmark=ExcelUtils.getCellData(16, colnum);
			String actualbenchmarkvalue=benchmark.getAttribute("value");

			if(actualbenchmarkvalue.equalsIgnoreCase(expectedbenchmark)){

				System.out.println("Delivery Flat Fee value is validated in Workbench as " + benchmark.getAttribute("value"));
				test1.pass("Delivery Flat Fee value is validated in workbench as " + benchmark.getAttribute("value"));	

			}

			else{

				System.out.println("Delivery Flat Fee value is not validated in TandC as " + benchmark.getAttribute("value"));
				test1.fail("Delivery Flat Fee value is not validated in TandC as " + benchmark.getAttribute("value"));

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}

	@Test(priority =13)
	public  void Verify_Freight_And_Margin_$_Per_Gallon_for_Seller_Freight_Rates_In_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Freight_And_Margin_$_Per_Gallon_in_Seller_Freight_Rates_In_Workbench", "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in Workbench");
		test1.log(Status.INFO, "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in Workbench");

		try {
			
			for(int i=1,j=17,k=24; i<=2&&j<=18&&k<=25; i++,j++,k++){

				WebElement frgtpergalvalue=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[14]"));
				String expectedfrgtpergalvalue=ExcelUtils.getCellData(j, colnum);
				String actualfrghtpergal=frgtpergalvalue.getText();
				if(actualfrghtpergal.equalsIgnoreCase(expectedfrgtpergalvalue)){

					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in workbench as " + frgtpergalvalue.getText());
					test1.pass("Freight $/Gal for  " +ExcelUtils.getCellData(k, colnum)+" Category is validated in workbench as " + frgtpergalvalue.getText());
				}
				else{


					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in workbench as " + frgtpergalvalue.getText());
					test1.fail("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in workbench as " + frgtpergalvalue.getText());

				}

			}

			for(int i=1,j=20,k=24; i<=2&&j<=21&&k<=25; i++,j++,k++){

				WebElement margin$pergallon=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[15]"));

				String expectedmargin$pergallonvalue=ExcelUtils.getCellData(j, colnum);
				String actualmargin$pergallonvalue=margin$pergallon.getText();
				if(actualmargin$pergallonvalue.equalsIgnoreCase(expectedmargin$pergallonvalue)){

					System.out.println("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in Workbench as " + margin$pergallon.getText());
					test1.pass("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in Workbench as " + margin$pergallon.getText());
				}
				else{


					System.out.println("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in Workbench as " + margin$pergallon.getText());
					test1.fail("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in Workbench as " + margin$pergallon.getText());
				}

			}
			
		} catch (Exception e) {


			test1.error(e.getMessage());
		}
		
	}
	
	@Test(priority =14)
	public  void Verify_$_Per_Del_Values_in_Workbench() throws Exception{

		//Verify $/Del Values in Workbench

		ExtentTest test1 = extent.createTest("Verify_$_Per_Del_Values_in_Workbench", "Verify and Validated $/Del values in Workbench");
		test1.log(Status.INFO, "Verify and Validated $/Del values in Workbench");

		try {

			for(int i=1,j=37,k=24; i<=2&&j<=37&&k<=25; i++,j--,k++){

				WebElement $perdel=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[16]"));

				String excepted$perdel=ExcelUtils.getCellData(j, colnum);
				String actual$perdel=$perdel.getText();
				if(actual$perdel.equalsIgnoreCase(excepted$perdel)){

					System.out.println("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value is validated in Workbench as "+ $perdel.getText());
					test1.pass("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value is validated in Workbench as "+ $perdel.getText());

				}

				else
				{
					System.out.println("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value is not validated in Workbench as "+ $perdel.getText());
					test1.fail("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value is not validated in Workbench as "+ $perdel.getText());


				}
			}

			WebElement total$delvalue=driver.findElement(By.id("CstperdelftPlh"));
			
			String expectedtotal$perdel=ExcelUtils.getCellData(51, colnum);
			String actualtotal$perdel=total$delvalue.getText();
			
			if(actualtotal$perdel.equalsIgnoreCase(expectedtotal$perdel)){

				System.out.println("Total $/Del Value is validated in Workbench as "+ total$delvalue.getText());
				test1.pass("Total $/Del Value is validated in Workbench as "+ total$delvalue.getText());

			}

			else
			{
				System.out.println("Total $/Del Value is not validated in Workbench as "+ total$delvalue.getText());
				test1.fail("Total $/Del Value is not validated in Workbench as "+ total$delvalue.getText());


			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnworkbenchClose")));

	}
	
	@Test(priority =15)
	public  void Close_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Close_Workbench", "Close Workbench");
		test1.log(Status.INFO, "Close Workbench");

		WebElement close=driver.findElement(By.id("btnworkbenchClose"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", close);
		Thread.sleep(2000);
		test1.pass("Close Workbench");
		driver.findElement(By.xpath("//*[@id='ReviewRFPDeliveryLocationrGrid']/div[3]")).click();
		//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


	}
	
	@Test(priority =16)
	public  void Award_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Award_Quote", "Award Quote for seller");
		test1.log(Status.INFO, "Award Quote for seller");


		try {

			//Click Contigent Award
			WebElement status=driver.findElement(By.linkText("Contingent Award"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", status);
			Thread.sleep(2000);
			test1.pass("Click Contigent Status");

			//Click Yes in pop-up
			WebElement statusyes=driver.findElement(By.id("jqDialog_yes"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", statusyes);
			Thread.sleep(2000);
			test1.pass("Click on Yes in pop-up");

			//Click yes in Award pop-up
			WebElement awardedyes=driver.findElement(By.id("jqDialog_ok"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", awardedyes);

			WebDriverWait wait0 = new WebDriverWait(driver, 120);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			test1.pass("Click on Yes for Award Yes");

			//click Won 
			WebElement won=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[12]/a[1]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", won);
			Thread.sleep(3000);
			test1.pass("Click on Won button");
			
			//Click Yes in Confimration
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("/html/body/div[45]/div[2]/button[1]")));
			Thread.sleep(3000);
			test1.pass("Click on Yes in pop-up");

			//Click Ok in Alert
			WebElement awardedok=driver.findElement(By.xpath("/html/body/div[45]/div[2]/button[3]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", awardedok);
			Thread.sleep(2000);
			test1.pass("Click on Award Ok");


			WebDriverWait wait2 = new WebDriverWait(driver, 100);
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			Thread.sleep(3000);
			
			/*//Select Product
			WebElement selectproduct=driver.findElement(By.xpath("//*[@id='QuoteProductLineItems']/div[2]/table/tbody/tr[1]/td[2]/input"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", selectproduct);
			Thread.sleep(1000);
			
			
			WebElement searchcategoryclick=driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/div/div[2]/div/div[1]/div/table/thead/tr/th[4]/a[1]/span"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", searchcategoryclick);
			Thread.sleep(1000);

			WebElement selectchekcbox=driver.findElement(By.xpath("//input[@type='checkbox'][@value='Gasoline']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", selectchekcbox);
			Thread.sleep(1000);
			
					
			WebElement selectfilter=driver.findElement(By.xpath("/html/body/div[47]/form/div/div[3]/button[1]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", selectfilter);
			Thread.sleep(1000);
			
			WebElement select2product=driver.findElement(By.xpath("//*[@id='QuoteProductLineItems']/div[2]/table/tbody/tr[1]/td[2]/input"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", select2product);
			Thread.sleep(1000);
			
			//Click Save
			WebElement saveproduct=driver.findElement(By.id("btnSaveProducts"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", saveproduct);
			Thread.sleep(2000);
			test1.pass("Click on Won button");
			
			//Click ok
			WebElement productok=driver.findElement(By.xpath("/html/body/div[48]/div[2]/button[3]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", productok);

			WebDriverWait wait2 = new WebDriverWait(driver, 100);
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			Thread.sleep(3000);
*/
		} catch (Exception e) {

			test1.error(e.getMessage());

		}



	}
	
	@Test(priority =17)
	public  void Verify_Quote_in_Awarded_Tab() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Quote_in_Awarded_Tab", "Verify Awarded quote in Award tab");
		test1.log(Status.INFO, "Verify Awarded quote in Award tab");

		try {

			int ok_size=driver.findElements(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div[1]/div/div[1]/div/a/span/i")).size();
			driver.findElements(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div[1]/div/div[1]/div/a/span/i")).get(ok_size-1).click();
			Thread.sleep(3000);

			WebElement queryforrecord=driver.findElement(By.id("awardedRFPSearch"));
			queryforrecord.sendKeys(ExcelUtils.getCellData(12, colnum));
			queryforrecord.sendKeys(Keys.CONTROL,"a");
			test1.info("Enter Awarded Quote as " + ExcelUtils.getCellData(12, colnum));

			try {

				if (driver.findElement(By.xpath("//*[@id='AwardedRFPGrid']/div[3]/table/tbody/tr/td[3]")).isDisplayed()) {

					WebElement activequotedata=driver.findElement(By.xpath("//*[@id='AwardedRFPGrid']/div[3]/table/tbody/tr/td[3]"));
					String excpected_active_quote=ExcelUtils.getCellData(12, colnum);
					String actual_active_quote=activequotedata.getText();
					if(excpected_active_quote.equals(actual_active_quote)){

						System.out.println(activequotedata.getText() + " Record is in Awarded Tab");
						test1.pass(activequotedata.getText() + " Record is in Awarded Tab");
					}
					else{


						System.out.println(activequotedata.getText() + " is not in Awarded quotes");
						test1.fail(activequotedata.getText() + " is not in Awarded quotes");
					}

				} else {


				}

			} catch (Exception e) {

			} 

		} catch (Exception e) {
			test1.error(e.getMessage());
		}

	}
	
	@Test(priority =18)
	public  void Open_Awarded_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Open_Awarded_Quote", "open awarded quote");
		test1.log(Status.INFO, "open awarded quote");

		try {

			WebElement activequotedata=driver.findElement(By.xpath("//*[@id='AwardedRFPGrid']/div[3]/table/tbody/tr/td[3]/a"));
			activequotedata.click();
			Thread.sleep(2000);
			test1.info("Click on Awarded quote");

			WebDriverWait wait2 = new WebDriverWait(driver, 100);
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));


			WebElement deliverylocationclick=driver.findElement(By.xpath("//*[@id='ReviewRFPDeliveryLocationrGrid']/div[3]/table/tbody/tr/td[2]/a"));
			System.out.println("Delivery Location is " + deliverylocationclick.getText());
			test1.info("Delivery Location is " + deliverylocationclick.getText());
			deliverylocationclick.click();


			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));


		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}
	
	@Test(priority =19)
	public  void Verify_Page_Navigated_To_Delivery_Location_in_Awarded_Quote() throws Exception{

		WebDriverWait wait0 = new WebDriverWait(driver, 20);
		wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

		ExtentTest test1 = extent.createTest("Verify_Page_Navigated_To_Delivery_Location_in_Awarded_Quote", "Verify Page is Navigated to Delivery Location in Awarded Quote");
		test1.log(Status.INFO, "Verify Page is Navigated to Delivery Location in Awarded Quote");

		try {

			WebElement deliverylocationclick=driver.findElement(By.xpath("//*[@id='ReviewRFPDeliveryLocationrGrid']/div[3]/table/tbody/tr/td[2]/a"));

			if (deliverylocationclick.isDisplayed()) {



				System.out.println("Page is Navigated to Delivery Location List");
				test1.pass("Page is Navigated to Delivery Location List");
			}
			else{


				System.out.println("Page is not Navigated to Delivery Location List");
				test1.fail("Page is not Navigated to Delivery Location List");
			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}



	}
	
	@Test(priority =20)
	public  void Verify_awarded_Quote_Status() throws Exception{


		ExtentTest test1 = extent.createTest("Verify_awarded_Quote_Status", "Verify awarded Quote status");
		test1.log(Status.INFO, "Verify awarded Quote status");


		try {

			WebElement quotestatus=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[12]"));
			String excpected_active_quote="Won";
			String actual_active_quote=quotestatus.getText();
			if(excpected_active_quote.equals(actual_active_quote)){


				System.out.println(ExcelUtils.getCellData(12, colnum) + " Quote status is "+ quotestatus.getText());
				test1.pass(ExcelUtils.getCellData(12, colnum) + " Quote status is "+ quotestatus.getText());
			}
			else{


				System.out.println(ExcelUtils.getCellData(12, colnum) + " Quote status is not in "+ quotestatus.getText());
				test1.fail(ExcelUtils.getCellData(12, colnum) + " Quote status is not in "+ quotestatus.getText());
			}


		} catch (Exception e) {

			test1.error(e.getMessage());
		}




	}

	
	@Test(priority =21)
	public  void Open_View_in_TandC_in_Awarded_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Open_View_in_TandC_in_Awarded_Quote", "Open View in TandC in Awarded Quote");
		test1.log(Status.INFO, "Open View in TandC in Awarded Quote");

		try {
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("createRFP_RFPEndDate")));

			
			WebElement tandcview=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[39]/a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", tandcview);
			test1.info("View TandC in Manage Rounds is Opened");


		} catch (Exception e) {


			test1.error(e.getMessage());
		}

	}	
	
	@Test(priority =22)
	public  void Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_TandC_for_Awarded_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_TandC_for_Awarded_Quote", "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in TandC for awarded quote");
		test1.log(Status.INFO, "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in TandC for awarded quote");

		try {

			WebDriverWait wait = new WebDriverWait(driver, 150);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement benchmark=driver.findElement(By.id("Terms_FreightPerDelivery"));

			String expectedbenchmark=ExcelUtils.getCellData(16, colnum);
			String actualbenchmarkvalue=benchmark.getAttribute("value");

			if(actualbenchmarkvalue.equalsIgnoreCase(expectedbenchmark)){

				System.out.println("Delivery Flat Fee value is validated in TandC as " + benchmark.getAttribute("value"));
				test1.pass("Delivery Flat Fee value is validated in TandC as " + benchmark.getAttribute("value"));	

			}

			else{

				System.out.println("Delivery Flat Fee value is not validated in TandC as " + benchmark.getAttribute("value"));
				test1.fail("Delivery Flat Fee value is not validated in TandC as " + benchmark.getAttribute("value"));

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}	

	@Test(priority =23)
	public  void Verify_Freight_And_Margin_$_Per_Gallon_for_Seller_Freight_Rates_In_TandC_for_Awarded_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Freight_And_Margin_$_Per_Gallon_for_Seller_Freight_Rates_In_TandC_for_Awarded_Quote", "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in TandC for Awarded Quote");
		test1.log(Status.INFO, "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in TandC for Awarded Quote");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("Terms_FreightPerDelivery")));

		try {
			
			for(int i=1,j=17,k=24; i<=2&&j<=18&&k<=25; i++,j++,k++){

				WebElement frgtpergalvalue=driver.findElement(By.xpath("//*[@id='ProductsSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[4]"));
				String expectedfrgtpergalvalue=ExcelUtils.getCellData(j, colnum);
				String actualfrghtpergal=frgtpergalvalue.getText();
				if(actualfrghtpergal.equalsIgnoreCase(expectedfrgtpergalvalue)){

					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in TandC as " + frgtpergalvalue.getText());
					test1.pass("Freight $/Gal for  " +ExcelUtils.getCellData(k, colnum)+" Category is validated in TandC as " + frgtpergalvalue.getText());
				}
				else{


					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in TandC as " + frgtpergalvalue.getText());
					test1.fail("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in TandC as " + frgtpergalvalue.getText());

				}

			}

			for(int i=1,j=20,k=24; i<=2&&j<=21&&k<=25; i++,j++,k++){

				WebElement margin$pergallon=driver.findElement(By.xpath("//*[@id='ProductsSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[5]"));

				String expectedmargin$pergallonvalue=ExcelUtils.getCellData(j, colnum);
				String actualmargin$pergallonvalue=margin$pergallon.getText();
				if(actualmargin$pergallonvalue.equalsIgnoreCase(expectedmargin$pergallonvalue)){

					System.out.println("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in TandC as " + margin$pergallon.getText());
					test1.pass("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in TandC as " + margin$pergallon.getText());
				}
				else{


					System.out.println("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in TandC as " + margin$pergallon.getText());
					test1.fail("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in TandC as " + margin$pergallon.getText());
				}

			}
			
		} catch (Exception e) {


			test1.error(e.getMessage());
		}
		
	}
	
	@Test(priority =24)
	public  void Verify_Final_Total_And_Ranking_Basis_in_TandC_for_Awarded_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Final_Total_And_Ranking_Basis_in_TandC_for_Awarded_Quote", "Verify final total and Ranking Basis in TandC for Awarded Quote");
		test1.log(Status.INFO, "Verify final total and Ranking Basis in TandC for Awarded Quote");
		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollLow, 0)");

		
		try {
			
			WebElement finaltotal=driver.findElement(By.id("ldlfinaltotal"));
			System.out.println("Final Total has value as " + finaltotal.getText());
			
			WebElement closetandc=driver.findElement(By.id("btnClose"));
			closetandc.click();
			Thread.sleep(3000);
			
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("createRFP_RFPEndDate")));


			WebElement rankingbasis=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[16]"));
			System.out.println("Ranking basis has value as " + rankingbasis.getText());
			
			String excepted=ExcelUtils.getCellData(73, colnum);
			String actual=rankingbasis.getText();

			if(actual.equalsIgnoreCase(excepted)){


				System.out.println("Final Total and Ranking Basis in TandC are same and validated and values are " + rankingbasis.getText());
				test1.pass("Final Total and Ranking Basis in TandC are same and validated and values are " + rankingbasis.getText());
			}
			else{


				System.out.println("Final Total and Ranking Basis are not same in TandC and validated");
				test1.fail("Final Total and Ranking Basis are not same in TandC and validated");

			}
			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}

		


	}
	
	@Test(priority =25)
	public  void Open_Workbench_for_Awarded_quote() throws Exception{

		ExtentTest test1 = extent.createTest("Open_Workbench_for_Awarded_quote", "Open Workbench View for Awarded Quote");
		test1.log(Status.INFO, "Open Workbench View for Awarded Quote");

		WebElement workbench=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[38]/a"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", workbench);

		test1.info("Click Workbench View in Manage Rounds");

	}	
	
	@Test(priority =26)
	public  void Verify_Buyer_Calculations_on_Workbench_for_Awarded_quote() throws Exception{

		WebDriverWait wait1 = new WebDriverWait(driver, 100);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='expandallequp']/label[1]")));
		

		ExtentTest test1 = extent.createTest("Verify_Buyer_Calculations_on_Workbench_for_Awarded_quote", "Verify Buyer Calculations on Workbench for Awarded Quote");
		test1.log(Status.INFO, "Verify Buyer Calculations on Workbench for Awarded Quote");

		try {

	
			Thread.sleep(4000);

			WebElement StemTime=driver.findElement(By.id("WorkdBenchTerms_StemLoadingTime"));
			String expectedStemTime=ExcelUtils.getCellData(59, colnum);
			String actualStemTime=StemTime.getAttribute("value");

			if(actualStemTime.equalsIgnoreCase(expectedStemTime)){

				System.out.println("Stem Time value is validated in workbench as "+ StemTime.getAttribute("value"));
				test1.pass("Stem Time value is validated in workbench as "+ StemTime.getAttribute("value"));
			}

			else{

				System.out.println("Stem Time value is not validated in workbench as "+ StemTime.getAttribute("value"));
				test1.fail("Stem Time value is not validated in workbench as "+ StemTime.getAttribute("value"));

			}

			WebElement LoadingTime=driver.findElement(By.id("WorkdBenchTerms_LoadingTime"));
			String expectedLoadingTime=ExcelUtils.getCellData(60, colnum);
			String actualLoadingTime=LoadingTime.getAttribute("value");

			if(actualLoadingTime.equalsIgnoreCase(expectedLoadingTime)){

				System.out.println("Loading Time value is validated in workbench as "+ LoadingTime.getAttribute("value"));
				test1.pass("Loading Time value is validated in workbench as "+ LoadingTime.getAttribute("value"));
			}

			else{

				System.out.println("Loading Time value is not validated in workbench as "+ LoadingTime.getAttribute("value"));
				test1.fail("Loading Time value is not validated in workbench as "+ LoadingTime.getAttribute("value"));

			}

			WebElement AvgFuelingTimeperUnitmins=driver.findElement(By.id("WorkdBenchTerms_GalsPerMinutePump"));
			String expectedAvgFuelingTimeperUnitmins=ExcelUtils.getCellData(61, colnum);
			String actualAvgFuelingTimeperUnitmins=AvgFuelingTimeperUnitmins.getAttribute("value");

			if(actualAvgFuelingTimeperUnitmins.equalsIgnoreCase(expectedAvgFuelingTimeperUnitmins)){

				System.out.println("Avg. Fueling Time Per unit mins value is validated in workbench as " + AvgFuelingTimeperUnitmins.getAttribute("value"));
				test1.pass("Avg. Fueling Time Per unit mins value is validated in workbench as " + AvgFuelingTimeperUnitmins.getAttribute("value"));

			}

			else{

				System.out.println("Avg. Fueling Time Per unit mins value is not validated in workbench as " + AvgFuelingTimeperUnitmins.getAttribute("value"));
				test1.fail("Avg. Fueling Time Per unit mins value is not validated in workbench as " + AvgFuelingTimeperUnitmins.getAttribute("value"));

			}

			WebElement FueledUnits=driver.findElement(By.id("eqwbFueledUnits"));
			String expectedFueledUnits=ExcelUtils.getCellData(62, colnum);
			String actualFueledUnits=FueledUnits.getText();

			if(actualFueledUnits.equalsIgnoreCase(expectedFueledUnits)){

				System.out.println("# Fueled Units value is validated in workbench as " + FueledUnits.getText());
				test1.pass("# Fueled Units value is validated in workbench as " + FueledUnits.getText());

			}

			else{

				System.out.println("# Fueled Units value is not validated in workbench as " + FueledUnits.getText());
				test1.fail("# Fueled Units value is not validated in workbench as " + FueledUnits.getText());

			}

			WebElement TotalTimeperDelmins=driver.findElement(By.id("eqwblblTimePerDelivery"));
			String expectedTotalTimeperDelmins=ExcelUtils.getCellData(63, colnum);
			String actualTotalTimeperDelmins=TotalTimeperDelmins.getText();

			if(actualTotalTimeperDelmins.equalsIgnoreCase(expectedTotalTimeperDelmins)){

				System.out.println("Total Time per Del. (mins) value is valdated in Workbench as " + TotalTimeperDelmins.getText());
				test1.pass("Total Time per Del. (mins) value is valdated in Workbench as " + TotalTimeperDelmins.getText());

			}

			else{

				System.out.println("Total Time per Del. (mins) value is not valdated in Workbench as " + TotalTimeperDelmins.getText());
				test1.fail("Total Time per Del. (mins) value is not valdated in Workbench as " + TotalTimeperDelmins.getText());

			}

			WebElement AvgMarginFreightDel=driver.findElement(By.id("eqwbFrietPerDelivery"));
			String expectedTotalRoundTripDeliveryTimemins=ExcelUtils.getCellData(64, colnum);
			String actualTotalRoundTripDeliveryTimemins=AvgMarginFreightDel.getText();

			if(actualTotalRoundTripDeliveryTimemins.equalsIgnoreCase(expectedTotalRoundTripDeliveryTimemins)){

				System.out.println("Avg. Margin & Freight/Del. value is validated in workbench as " + AvgMarginFreightDel.getText());
				test1.pass("Avg. Margin & Freight/Del. value is validated in workbench as " + AvgMarginFreightDel.getText());

			}

			else{

				System.out.println("Avg. Margin & Freight/Del. value is not validated in workbench as " + AvgMarginFreightDel.getText());
				test1.fail("Avg. Margin & Freight/Del. value is not validated in workbench as " + AvgMarginFreightDel.getText());

			}

			WebElement GrossMargin$Hour=driver.findElement(By.id("eqwblblTotalTimetooltip"));
			String expectedGrossMargin$Hour=ExcelUtils.getCellData(65, colnum);
			String actualGrossMargin$Hour=GrossMargin$Hour.getText();

			if(actualGrossMargin$Hour.equalsIgnoreCase(expectedGrossMargin$Hour)){

				System.out.println("Gross Margin $/Hour value is validated in workbench as " + GrossMargin$Hour.getText());
				test1.pass("Gross Margin $/Hour value is validated in workbench as " + GrossMargin$Hour.getText());
			}

			else{

				System.out.println("Gross Margin $/Hour value is not validated in workbench as " + GrossMargin$Hour.getText());
				test1.fail("Gross Margin $/Hour value is not validated in workbench as " + GrossMargin$Hour.getText());

			}

			WebElement distance=driver.findElement(By.id("lblDistance"));
			String expecteddistance=ExcelUtils.getCellData(66, colnum);
			String actualdistance=distance.getText();

			if(actualdistance.equalsIgnoreCase(expecteddistance)){

				System.out.println("Distance value is validated in workbench as " + distance.getText());
				test1.pass("Distance value is validated in workbench as " + distance.getText());
			}

			else{

				System.out.println("Distance value is not validated in workbench as " + distance.getText());
				test1.fail("Distance value is not validated in workbench as " + distance.getText());
			}


		} catch (Exception e) {

		}		

	}

	@Test(priority =27)
	public  void Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_Workbench_for_Awarded_quote() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_Workbench_for_Awarded_quote", "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in Workbench for Awarded Quote");
		test1.log(Status.INFO, "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in Workbench for Awarded Quote");

		try {

			WebElement tandcclick=driver.findElement(By.id("WorkdBenchTerms_FreightPerDelivery"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tandcclick);

			tandcclick.click();

			WebDriverWait wait = new WebDriverWait(driver, 150);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement benchmark=driver.findElement(By.id("WorkdBenchTerms_FreightPerDelivery"));

			String expectedbenchmark=ExcelUtils.getCellData(16, colnum);
			String actualbenchmarkvalue=benchmark.getAttribute("value");

			if(actualbenchmarkvalue.equalsIgnoreCase(expectedbenchmark)){

				System.out.println("Delivery Flat Fee value is validated in Workbench as " + benchmark.getAttribute("value"));
				test1.pass("Delivery Flat Fee value is validated in workbench as " + benchmark.getAttribute("value"));	

			}

			else{

				System.out.println("Delivery Flat Fee value is not validated in TandC as " + benchmark.getAttribute("value"));
				test1.fail("Delivery Flat Fee value is not validated in TandC as " + benchmark.getAttribute("value"));

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}

	@Test(priority =28)
	public  void Verify_Freight_And_Margin_$_Per_Gallon_for_Seller_Freight_Rates_In_Workbench_for_Awarded_quote() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Freight_And_Margin_$_Per_Gallon_in_Seller_Freight_Rates_In_Workbench", "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in Workbench");
		test1.log(Status.INFO, "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in Workbench");

		try {
			
			for(int i=1,j=17,k=24; i<=2&&j<=18&&k<=25; i++,j++,k++){

				WebElement frgtpergalvalue=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[14]"));
				String expectedfrgtpergalvalue=ExcelUtils.getCellData(j, colnum);
				String actualfrghtpergal=frgtpergalvalue.getText();
				if(actualfrghtpergal.equalsIgnoreCase(expectedfrgtpergalvalue)){

					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in workbench as " + frgtpergalvalue.getText());
					test1.pass("Freight $/Gal for  " +ExcelUtils.getCellData(k, colnum)+" Category is validated in workbench as " + frgtpergalvalue.getText());
				}
				else{


					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in workbench as " + frgtpergalvalue.getText());
					test1.fail("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in workbench as " + frgtpergalvalue.getText());

				}

			}

			for(int i=1,j=20,k=24; i<=2&&j<=21&&k<=25; i++,j++,k++){

				WebElement margin$pergallon=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[15]"));

				String expectedmargin$pergallonvalue=ExcelUtils.getCellData(j, colnum);
				String actualmargin$pergallonvalue=margin$pergallon.getText();
				if(actualmargin$pergallonvalue.equalsIgnoreCase(expectedmargin$pergallonvalue)){

					System.out.println("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in Workbench as " + margin$pergallon.getText());
					test1.pass("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in Workbench as " + margin$pergallon.getText());
				}
				else{


					System.out.println("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in Workbench as " + margin$pergallon.getText());
					test1.fail("Margin $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in Workbench as " + margin$pergallon.getText());
				}

			}
			
		} catch (Exception e) {


			test1.error(e.getMessage());
		}
		
	}
	
	@Test(priority =29)
	public  void Verify_$_Per_Del_Values_in_Workbench_for_Awarded_quote() throws Exception{

		//Verify $/Del Values in Workbench

		ExtentTest test1 = extent.createTest("Verify_$_Per_Del_Values_in_Workbench_for_Awarded_quote", "Verify and Validated $/Del values in Workbench for Awarded Quote");
		test1.log(Status.INFO, "Verify and Validated $/Del values in Workbench for Awarded Quote");

		try {

			for(int i=1,j=37,k=24; i<=2&&j<=37&&k<=25; i++,j--,k++){

				WebElement $perdel=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[16]"));

				String excepted$perdel=ExcelUtils.getCellData(j, colnum);
				String actual$perdel=$perdel.getText();
				if(actual$perdel.equalsIgnoreCase(excepted$perdel)){

					System.out.println("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value is validated in Workbench as "+ $perdel.getText());
					test1.pass("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value is validated in Workbench as "+ $perdel.getText());

				}

				else
				{
					System.out.println("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value is not validated in Workbench as "+ $perdel.getText());
					test1.fail("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value is not validated in Workbench as "+ $perdel.getText());


				}
			}

			WebElement total$delvalue=driver.findElement(By.id("CstperdelftPlh"));
			
			String expectedtotal$perdel=ExcelUtils.getCellData(51, colnum);
			String actualtotal$perdel=total$delvalue.getText();
			
			if(actualtotal$perdel.equalsIgnoreCase(expectedtotal$perdel)){

				System.out.println("Total $/Del Value is validated in Workbench as "+ total$delvalue.getText());
				test1.pass("Total $/Del Value is validated in Workbench as "+ total$delvalue.getText());

			}

			else
			{
				System.out.println("Total $/Del Value is not validated in Workbench as "+ total$delvalue.getText());
				test1.fail("Total $/Del Value is not validated in Workbench as "+ total$delvalue.getText());


			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnworkbenchClose")));

	}
	
	@Test(priority =30)
	public  void Close_Workbench_for_Awarded_quote() throws Exception{

		ExtentTest test1 = extent.createTest("Close_Workbench_for_Awarded_quote", "Close Workbench for Awarded Quote");
		test1.log(Status.INFO, "Close Workbench for Awarded Quote");

		WebElement close=driver.findElement(By.id("btnworkbenchClose"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", close);
		Thread.sleep(2000);
		test1.pass("Close Workbench");
		driver.findElement(By.xpath("//*[@id='ReviewRFPDeliveryLocationrGrid']/div[3]")).click();
		//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


	}
	
	@Test(priority =31)
	public void Veify_Buyer_Logout() throws Exception{

		ExtentTest test1 = extent.createTest("Veify_Buyer_Logout", "verifying Buyer is logged out succesfully");
		test1.log(Status.INFO, "verifying quote in Active tab");

		WebElement logout=driver.findElement(By.xpath("//*[@id='wrapper']/div[1]/div/div/div/ul/li[5]/a"));
		logout.click();
		Thread.sleep(3000);

		WebElement username=driver.findElement(By.name("Username"));
		if(username.isDisplayed()){


			System.out.println(ExcelUtils.getCellData(90, colnum)+ " Buyer Logged Out Successfully");
			test1.pass(ExcelUtils.getCellData(90, colnum)+ " Buyer Logged Out Successfully");


		}
		else {


			System.out.println(ExcelUtils.getCellData(90, colnum)+ " Buyer is not Logged Out Successfully");
			test1.fail(ExcelUtils.getCellData(90, colnum)+ " Buyer is not Logged Out Successfully");

		}


	}
	
	@AfterTest
	public void teardowntest(){

		driver.close();

	}

	@AfterSuite
	public void teardown(){

		extent.flush();

	}



}



