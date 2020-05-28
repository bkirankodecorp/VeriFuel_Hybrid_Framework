package VF_Quote_Creation_And_Response;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.Constant;
import Utility.ExcelUtils;

public class LTL_MF_Quote_Seller_Response_Ranking_Basis_Per_Gal {

	private static WebDriver driver;
	private static int colnum=5;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentSparkReporter sparkReporter;	

	@BeforeSuite
	public void setUp(){

		htmlReporter= new ExtentHtmlReporter("../Veri-Fuel_Hybrid_Framework/LTL Mobile Fueling/LTL_Seller_Response_Ranking_Basis_$_Per_Gal.html");

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
	public void Open_Browser() throws Exception {


		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"VF_LTL_MF_Seller_Response");
		ExtentTest test1 = extent.createTest("Open D1 Fuel Connect", "Validating dev site");
		test1.log(Status.INFO, "Validating dev site");

		String Url = ExcelUtils.getCellData(4, colnum);

		driver.get(Url);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		try {

			if (driver.findElement(By.name("Username")).isDisplayed()) {

				System.out.print("Page is navigated to \n" + Url);
				test1.pass("Page is navigated to \n" + Url);
				System.out.println(driver.getTitle());


			} else {

				System.out.print("Page is not navigated to \n" + Url);
				test1.fail("Page is not navigated to \n" + Url);


			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}


	@Test(priority =1)
	public  void Seller_Login() throws Exception{

		ExtentTest test1 = extent.createTest("Seller_Login", "Login with Valid Credentials");
		test1.log(Status.INFO, "Enter valid Username and Password");

		try {


			WebElement UserName =driver.findElement(By.name("Username"));
			UserName.sendKeys(ExcelUtils.getCellData(5, colnum));
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Username is " + ExcelUtils.getCellData(5, colnum));

			WebElement Password =driver.findElement(By.name("Password"));
			Password.sendKeys(ExcelUtils.getCellData(6, colnum));
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Password is " + ExcelUtils.getCellData(6, colnum));

			WebElement Login = driver.findElement(By.id("loginbtn"));
			Login.click();
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		} 

		catch (Exception e) {

			test1.error(e.getMessage());

		}

	}

	@Test(priority =2)
	public  void verify_Seller_login() throws Exception{

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		WebElement  newquote = driver.findElement(By.id("dashboardnewSellerClick"));
		ExtentTest test1 = extent.createTest("verify_Seller_login", "Verify Seller is logged in dev site");
		test1.log(Status.INFO, "Verify Seller is logged in dev site");

		try {

			if(newquote.isDisplayed()){
				System.out.println("\n"+ ExcelUtils.getCellData(5, colnum) + " Seller logged in successfully");
				test1.pass(ExcelUtils.getCellData(5, colnum) + " Seller logged in successfully");

			}
			else{
				System.out.println("Seller logged in is Unsuccessful");
				test1.fail(ExcelUtils.getCellData(5, colnum) + " Seller logged in is Unsuccessful");


			}

		} catch (Exception e) {

			test1.error(e.getMessage());


		}
	}

	@Test(priority =3)
	public  void Navigate_to_Review_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Navigate_to_Review_Quote", "Verify Seller is navigtaed to Review Quote Page");
		test1.log(Status.INFO, "Verify Seller is navigtaed to Review Quote Page");

		try {

			WebElement element=driver.findElement(By.id("26"));
			WebElement element1=driver.findElement(By.id("29"));

			Actions action=new Actions(driver);
			action.moveToElement(element).build().perform();
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			action.moveToElement(element1).build().perform();
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			element1.click();
			Thread.sleep(3000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			WebElement newquote=driver.findElement(By.xpath("//h3[contains(text(),'New Quotes')]"));
			if(newquote.isDisplayed()){

				System.out.println("Page is navigated to Review Quote");
				test1.pass("Page is navigated to Review Quote");



			}

			else{

				System.out.println("Page is not navigated to Review Quote");
				test1.fail("Page is not navigated to Review Quote");


			}


		} catch (Exception e) {

			test1.error(e.getMessage());


		}

	}

	@Test(priority =4)
	public  void Seller_New_Quote_Search() throws Exception{


		ExtentTest test1 = extent.createTest("Seller_New_Quote_Search", "Query with New Quote in Searchbox");
		test1.log(Status.INFO, "Query with New Quote in Searchbox");

		try {

			Thread.sleep(2000);
			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			WebElement newsearch=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div/div/div[1]/div/div[1]/div/div[1]/div/a/span/i"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", newsearch);
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement quotesearch=driver.findElement(By.id("txtSearch"));
			quotesearch.sendKeys(ExcelUtils.getCellData(12, colnum));

			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			quotesearch.sendKeys(Keys.CONTROL,"a");
			Thread.sleep(2000);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Edting Quote in New Tab is " + ExcelUtils.getCellData(12, colnum));

			WebElement clickquote=driver.findElement(By.xpath("//*[@id='ReviewRFPGrid']/div[3]/table/tbody/tr[1]/td[10]"));
			clickquote.click();
			Thread.sleep(2000);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Click Quote in New Tab is " + ExcelUtils.getCellData(12, colnum));




		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}

	@Test(priority =5)
	public  void Verify_Quote_status_as_NEW() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Quote_status_as_New", "Verifying Quote status as New in Quick Response Grid");
		test1.log(Status.INFO, "Verifying Quote status as New in Quick Response Grid");

		try {

			if(driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr[1]/td[19]")).isDisplayed()){

				WebElement quotestatus=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr[1]/td[19]"));
				String expectedquotestatus=ExcelUtils.getCellData(14, colnum);
				String actualquotestatus=quotestatus.getText();
				if(actualquotestatus.equalsIgnoreCase(expectedquotestatus)){

					System.out.println(ExcelUtils.getCellData(12, colnum) + " is in " + quotestatus.getText() + " status");
					test1.pass(ExcelUtils.getCellData(12, colnum) + " is in " + quotestatus.getText() + " status");
				}

				else{


					System.out.println(ExcelUtils.getCellData(12, colnum) + " is not in " + quotestatus.getText() + " status");
					test1.fail(ExcelUtils.getCellData(12, colnum) + " is not in " + quotestatus.getText() + " status");

				}

			}

		} catch (Exception e) {

		}

	}
	@Test(priority =6)
	public  void Enter_Delivery_Flat_Fee_And_freight_$_per_gal_in_Quick_response_grid() throws Exception{

		ExtentTest test1 = extent.createTest("Enter_Benchmark_And_freight_$_per_gal_in_Quick_response_grid", "Enter valid Benchmark and Freight $/Gallon values for first and second category");
		test1.log(Status.INFO, "Enter valid Benchmark and Freight $/Gallon values for first and second category");

		try {

			WebElement deliveryflattfee=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr[1]/td[13]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", deliveryflattfee);

			WebElement deliveryflattfeevalue=driver.findElement(By.id("FreightPerDelivery"));
			deliveryflattfeevalue.sendKeys(ExcelUtils.getCellData(16, colnum));
			test1.info("Delivery Flat Fee value is " + ExcelUtils.getCellData(16, colnum));

			WebElement div=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]"));
			div.click();

			for(int i=1,j=17; i<=2&&j<=18; i++,j++){

				WebElement freightpergallon=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr["+i+"]/td[14]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", freightpergallon);

				WebElement frightpergallonsenter=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div/div/div[1]/div/div[3]/div[2]/div/div[1]/div/div[4]/div[3]/table/tbody/tr["+i+"]/td[14]/input"));
				frightpergallonsenter.sendKeys(ExcelUtils.getCellData(j, colnum));
				div.click();
				test1.info("Freight $/Gallon" +i+ " values is " + ExcelUtils.getCellData(j, colnum));


			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}
	@Test(priority =7)
	public  void Verify_Quote_status_as_HoldAsDraft() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Quote_status_as_HoldAsDraft", "Verify Quote Status in Hold As Draft Status");
		test1.log(Status.INFO, "Verify Quote Status in Hold As Draft Status");

		try {

			if(driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr[1]/td[19]")).isDisplayed()){

				WebElement quotestatus=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr[1]/td[19]"));
				String expectedquotestatus=ExcelUtils.getCellData(19, colnum);
				String actualquotestatus=quotestatus.getText();
				if(actualquotestatus.equalsIgnoreCase(expectedquotestatus)){

					System.out.println(ExcelUtils.getCellData(12, colnum) + " is in Hold As draft status");
					test1.pass(ExcelUtils.getCellData(12, colnum) + " is in Hold As draft status");

				}
				else{


					System.out.println(ExcelUtils.getCellData(12, colnum) + " is not in Hold As draft status");
					test1.fail(ExcelUtils.getCellData(12, colnum) + " is not in Hold As draft status");

				}

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}

	}

	@Test(priority =8)
	public  void Enter_valid_Margin_$_Per_Gal() throws Exception{

		ExtentTest test1 = extent.createTest("Enter_valid_Margin_$_Per_Gal", "Enter valid data on Margin $/Gal");
		test1.log(Status.INFO, "Enter valid data on Margin $/Gal");

		try {
			
			WebElement div=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]"));


			for(int i=1,j=20; i<=2&&j<=21; i++,j++){

				WebElement marginpergallon=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr["+i+"]/td[15]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", marginpergallon);

				WebElement margintpergallonsenter=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div/div/div[1]/div/div[3]/div[2]/div/div[1]/div/div[4]/div[3]/table/tbody/tr["+i+"]/td[15]/input"));
				margintpergallonsenter.sendKeys(ExcelUtils.getCellData(j, colnum));
				div.click();
				test1.info("Margin $/Gallon" +i+ " values is " + ExcelUtils.getCellData(j, colnum));


			}
			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}
		
		
	}

	@Test(priority =9)
	public  void Select_OPIS_State_And_City() throws Exception{

		ExtentTest test1 = extent.createTest("Select_OPIS_State_And_City", "Select OPIS State and City in Quick Response grid");
		test1.log(Status.INFO, "Select OPIS State and City in Quick Response grid");

		try {

			WebElement opisstate = driver.findElement(By.id("opistate"));
			WebElement deliverytimes=driver.findElement(By.xpath("//th[@data-title='Delivery Times']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deliverytimes);
			VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			opisstate.click();

			WebElement opisstateclick=driver.findElement(By.xpath("//span[@aria-owns='GlobalStateDropDown_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
			opisstateclick.click();
			Thread.sleep(2000);

			By mySelector = By.xpath(".//*[@id='GlobalStateDropDown_listbox']");
			List<WebElement> myElements = driver.findElements(mySelector);
			for(WebElement e : myElements) {
				System.out.println("OPIS State Has Following Dropdown List \n" + e.getText());
				test1.info("OPIS State Has Following Dropdown List \n" + e.getText());
			}

			List<WebElement> statelist=driver.findElements(By.xpath("//ul[@id='GlobalStateDropDown_listbox']//li"));
			for(WebElement ele:statelist)
			{

				String stateselect=ele.getText();
				if(stateselect.equalsIgnoreCase(ExcelUtils.getCellData(22, colnum)))
				{
					ele.click();
					VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
					break;
				}
			}

			System.out.println("OPIS State is " +ExcelUtils.getCellData(22, colnum));
			test1.info("OPIS State is " +ExcelUtils.getCellData(22, colnum));

			WebElement update=driver.findElement(By.id("btnSubmit"));
			update.click();
			Thread.sleep(2000);


			WebElement opiscity = driver.findElement(By.id("opiscity"));
			opiscity.click();

			WebElement opiscityclick=driver.findElement(By.xpath("//span[@aria-owns='GlobalRackCityDropDown_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
			opiscityclick.click();
			Thread.sleep(2000);

			By mySelector1 = By.xpath(".//*[@id='GlobalRackCityDropDown_listbox']");
			List<WebElement> myElements1 = driver.findElements(mySelector1);
			for(WebElement e : myElements1) {
				System.out.println("OPIS City Has Following Dropdown List \n" + e.getText());
				test1.info("OPIS City Has Following Dropdown List \n" + e.getText());
			}

			List<WebElement> opiscitylist=driver.findElements(By.xpath("//ul[@id='GlobalRackCityDropDown_listbox']//li"));
			for(WebElement ele:opiscitylist)
			{

				String opiscityselect=ele.getText();
				if(opiscityselect.equalsIgnoreCase(ExcelUtils.getCellData(23, colnum)))
				{
					ele.click();
					VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
					break;
				}
			}

			System.out.println("OPIS City is " + ExcelUtils.getCellData(23, colnum));
			test1.info("OPIS City is " + ExcelUtils.getCellData(23, colnum));
			update.click();



		} catch (Exception e) {

			test1.error(e.getMessage());


		}

	}

	@Test(priority =10)
	public  void Set_Product_Category_Values() throws Exception{

		ExtentTest test1 = extent.createTest("Set_Product_Category_Values", "Set Product Category values on quick response grid");
		test1.log(Status.INFO, "Set Product Category values on quick response grid");

		try {

			for(int i=1,j=24; i<=2&&j<=25; i++,j++){

				WebElement productcategory=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr["+i+"]/td[9]"));
				ExcelUtils.setCellData(productcategory.getText(), j, colnum);
				test1.info("Product Category is " +i+ productcategory.getText());

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}




	}


	@Test(priority =11)
	public  void Save_Response_in_Quick_Response() throws Exception{

		ExtentTest test1 = extent.createTest("Save_Response_in_Quick_Response", "Save data in quick response by clicking Save Respone");
		test1.log(Status.INFO, "Save data in quick response by clicking Save Respone");

		try {

			WebElement saveresponse=driver.findElement(By.id("btnsaveseller"));
			saveresponse.click();
			test1.info("Click Save Response in Quick Response grid");
			Thread.sleep(2000);

			WebElement ok=driver.findElement(By.id("jqDialog_ok"));
			ok.click();
			Thread.sleep(2000);
			test1.info("Click ok in pop-up in Quick Response grid");

		} catch (Exception e) {

			test1.error(e.getMessage());

		}




	}

	@Test(priority =12)
	public  void Verify_Quote_status_as_ReadytoSubmit() throws Exception{


		ExtentTest test1 = extent.createTest("Verify_Quote_status_as_ReadytoSubmit", "Verify Quote status is in Ready To Submit");
		test1.log(Status.INFO, "Verify Quote status is in Ready To Submit");
		
		try {
			
			if(driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr[1]/td[19]")).isDisplayed()){

				WebElement quotestatus=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr[1]/td[19]"));
				String expectedquotestatus=ExcelUtils.getCellData(27, colnum);
				String actualquotestatus=quotestatus.getText();
				if(actualquotestatus.equalsIgnoreCase(expectedquotestatus)){

					System.out.println(ExcelUtils.getCellData(12, colnum) + " is in Ready to Submit");
					test1.info(ExcelUtils.getCellData(12, colnum) + " is in Ready to Submit");
				}
				else{


					System.out.println(ExcelUtils.getCellData(12, colnum) + " is not in Ready to Submit");
					test1.fail(ExcelUtils.getCellData(12, colnum) + " is not in Ready to Submit");

				}

			}

			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}


		

	}



	@Test(priority =13)
	public  void Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_TandC() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_TandC", "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in TandC");
		test1.log(Status.INFO, "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in TandC");

		try {

			WebElement tandcclick=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']//a[@class='k-button k-button-icontext k-grid-Workbench']//span[@class='fa fa-file-excel-o']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tandcclick);

			tandcclick.click();

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

	@Test(priority =14)
	public  void Verify_Freight_And_Margin_$_Per_Gallon_for_Seller_Freight_Rates_In_TandC() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Freight_And_Margin_$_Per_Gallon_in_Seller_Freight_Rates_In_TandC", "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in TandC");
		test1.log(Status.INFO, "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in TandC");

		try {
			
			for(int i=1,j=18,k=25; i<=2&&j<=18&&k<=25; i++,j--,k--){

				WebElement frgtpergalvalue=driver.findElement(By.xpath("//*[@id='ProductsGrid']/div[3]/table/tbody/tr["+i+"]/td[7]"));
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

			for(int i=1,j=21,k=25; i<=2&&j<=21&&k<=25; i++,j--,k--){

				WebElement margin$pergallon=driver.findElement(By.xpath("//*[@id='ProductsGrid']/div[3]/table/tbody/tr["+i+"]/td[8]"));

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

	@Test(priority =15)
	public  void Retrieve_$_Per_Del_Values_From_TandC() throws Exception{

		//Retrieve $/Del Values

		ExtentTest test1 = extent.createTest("Retrieve_$_Per_Del_Values_From_TandC", "Retrieve $/Del Values from TandC");
		test1.log(Status.INFO, "Retrieve $/Del Values from TandC");

		try {

			for(int i=1,j=36,k=25; i<=2&&j<=37&&k<=25; i++,j++,k--){

				WebElement $perdel=driver.findElement(By.xpath("//*[@id='ProductsGrid']/div[3]/table/tbody/tr["+i+"]/td[9]"));
				ExcelUtils.setCellData($perdel.getText(), j, colnum);
				System.out.println("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value in TandC is "+ $perdel.getText());
				test1.info("$/Del for " +ExcelUtils.getCellData(k, colnum)+ " category Value in TandC is "+ $perdel.getText());
			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}
	}

	@Test(priority =16)
	public  void Retrieve_$_Per_Gal_Values_From_TandC() throws Exception{

		//Retrieve $/Gal Values

		ExtentTest test1 = extent.createTest("Retrieve_$_Per_Gal_Values_From_TandC", "Retrieve $/Gal Values from TandC");
		test1.log(Status.INFO, "Retrieve $/Gal Values from TandC");

		try {

			for(int i=1,j=38,k=25; i<=2&&j<=39&&k<=25; i++,j++,k--){

				WebElement $pergal=driver.findElement(By.xpath("//*[@id='ProductsGrid']/div[3]/table/tbody/tr["+i+"]/td[10]"));
				ExcelUtils.setCellData($pergal.getText(), j, colnum);
				System.out.println("$/Gal for " +ExcelUtils.getCellData(k, colnum)+ " category Value in TandC is "+ $pergal.getText());
				test1.info("$/Gal for " +ExcelUtils.getCellData(k, colnum)+ " category Value in TandC is "+ $pergal.getText());
			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}
	}

	@Test(priority =17)
	public  void Retrieve_$_Per_Period_Values_From_TandC() throws Exception{

		//Retrieve $/Period Values

		ExtentTest test1 = extent.createTest("Retrieve_$_Per_Period_Values_From_TandC", "Retrieve $/Period Values from TandC");
		test1.log(Status.INFO, "Retrieve $/Period Values from TandC");

		try {

			for(int i=1,j=40,k=25; i<=2&&j<=41&&k<=25; i++,j++,k--){

				WebElement $perperiod=driver.findElement(By.xpath("//*[@id='ProductsGrid']/div[3]/table/tbody/tr["+i+"]/td[11]"));
				ExcelUtils.setCellData($perperiod.getText(), j, colnum);
				System.out.println("$/Period for  " +ExcelUtils.getCellData(k, colnum)+ " category Value in TandC is "+ $perperiod.getText());
				test1.info("$/Period for  " +ExcelUtils.getCellData(k, colnum)+ " category Value in TandC is "+ $perperiod.getText());
			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}


	}


	@Test(priority =18)
	public  void Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Delivery_Flat_Fee_for_Seller_Freight_Rates_In_Workbench", "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in workbench");
		test1.log(Status.INFO, "Verify and Validate Delivery Flat Fee value for Seller Freight Rates in workbench");

		try {

			WebElement workbench=driver.findElement(By.xpath("//span[contains(text(),'Workbench')]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", workbench);
			Thread.sleep(2000);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("CstperdelftPlh")));
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement benchmark=driver.findElement(By.id("Terms_WorkbenchFreightPerDelivery"));

			String expectedbenchmark=ExcelUtils.getCellData(16, colnum);
			String actualbenchmarkvalue=benchmark.getAttribute("value");

			if(actualbenchmarkvalue.equalsIgnoreCase(expectedbenchmark)){

				System.out.println("Delivery Flat Fee value is validated in Workbench as " + benchmark.getAttribute("value"));
				test1.pass("Delivery Flat Fee value is validated in Workbench as " + benchmark.getAttribute("value"));	

			}

			else{

				System.out.println("Delivery Flat Fee value is not validated in Workbench as " + benchmark.getAttribute("value"));
				test1.fail("Delivery Flat Fee value is not validated in Workbench as " + benchmark.getAttribute("value"));

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}

	@Test(priority =19)
	public  void Verify_Freight_And_Margin_$_Per_Gallon_for_Seller_Freight_Rates_In_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Freight_And_Margin_$_Per_Gallon_for_Seller_Freight_Rates_In_Workbench", "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in Workbench");
		test1.log(Status.INFO, "Verify and Validate Freight and Margin $/Gallon values for Seller Freight Rates in Workbench");

		try {
			
			for(int i=1,j=18,k=25; i<=2&&j<=18&&k<=25; i++,j--,k--){

				WebElement frgtpergalvalue=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[15]"));
				String expectedfrgtpergalvalue=ExcelUtils.getCellData(j, colnum);
				String actualfrghtpergal=frgtpergalvalue.getText();
				if(actualfrghtpergal.equalsIgnoreCase(expectedfrgtpergalvalue)){

					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is validated in Workbench as " + frgtpergalvalue.getText());
					test1.pass("Freight $/Gal for  " +ExcelUtils.getCellData(k, colnum)+" Category is validated in Workbench as " + frgtpergalvalue.getText());
				}
				else{


					System.out.println("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in Workbench as " + frgtpergalvalue.getText());
					test1.fail("Freight $/Gal for " +ExcelUtils.getCellData(k, colnum)+" Category is not validated in Workbench as " + frgtpergalvalue.getText());

				}

			}

			for(int i=1,j=21,k=25; i<=2&&j<=21&&k<=25; i++,j--,k--){

				WebElement margin$pergallon=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[16]"));

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

	@Test(priority =20)
	public  void Verify_$_Per_Del_Values_in_Workbench() throws Exception{

		//Verify $/Del Values in Workbench

		ExtentTest test1 = extent.createTest("Verify_$_Per_Del_Values_in_Workbench", "Verify and Validated $/Del values in Workbench");
		test1.log(Status.INFO, "Verify and Validated $/Del values in Workbench");

		try {

			for(int i=1,j=36,k=25; i<=2&&j<=37&&k<=25; i++,j++,k--){

				WebElement $perdel=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[17]"));

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
			ExcelUtils.setCellData(total$delvalue.getText(), 51, colnum);
			System.out.println("Total $/Del. value is " + total$delvalue.getText());
			test1.info("Total $/Del. value is " + total$delvalue.getText());

		} catch (Exception e) {

			test1.error(e.getMessage());

		}
	}

	@Test(priority =21)
	public  void Verify_$_Per_Gal_Values_in_Workbench() throws Exception{

		//Verify $/Gal Values in Workbench

		ExtentTest test1 = extent.createTest("Verify_$_Per_Gal_Values_in_Workbench", "Verify and Validate $/Gal values in Workbech");
		test1.log(Status.INFO, "Verify and Validate $/Gal values in Workbech");

		try {

			for(int i=1,j=38,k=25; i<=2&&j<=39&&k<=25; i++,j++,k--){

				WebElement $pergal=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[18]"));

				String excepted$pergal=ExcelUtils.getCellData(j, colnum);
				String actual$pergal=$pergal.getText();

				if(actual$pergal.equalsIgnoreCase(excepted$pergal)){

					System.out.println("$/Gal for " +ExcelUtils.getCellData(k, colnum)+ " category Value is validated in Workbench as "+ $pergal.getText());
					test1.pass("$/Gal for " +ExcelUtils.getCellData(k, colnum)+ " category Value is validated in Workbench as "+ $pergal.getText());


				}

				else{

					System.out.println("$/Gal for " +ExcelUtils.getCellData(k, colnum)+ " category Value is not validated in Workbench as "+ $pergal.getText());
					test1.fail("$/Gal for " +ExcelUtils.getCellData(k, colnum)+ " category Value is not validated in Workbench as "+ $pergal.getText());
				}


			}

			WebElement total$galvalue=driver.findElement(By.id("CstperperiodftPlh"));
			ExcelUtils.setCellData(total$galvalue.getText(), 54, colnum);
			System.out.println("Total $/Gal. value is " + total$galvalue.getText());
			test1.info("Total $/Gal. value is " + total$galvalue.getText());

		} catch (Exception e) {
			test1.error(e.getMessage());
		}
	}

	@Test(priority =22)
	public  void Verify_$_Per_Period_Values_in_Workbench() throws Exception{

		//Retrieve $/Period Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_Period_Values_in_Workbench", "Verify and validate $/Period Values in Workbench");
		test1.log(Status.INFO, "Verify and validate $/Period Values in Workbench");

		try {

			for(int i=1,j=40,k=25; i<=2&&j<=41&&k<=25; i++,j++,k--){

				WebElement $perperiod=driver.findElement(By.xpath("//*[@id='ProductsWorkbenchGrid']/div[3]/table/tbody/tr["+i+"]/td[19]"));

				String excepted$perperiod=ExcelUtils.getCellData(j, colnum);
				String actual$perperiod=$perperiod.getText();

				if(actual$perperiod.equalsIgnoreCase(excepted$perperiod)){

					System.out.println("$/Period for  " +ExcelUtils.getCellData(k, colnum)+ " category Value is validated in Workbench as "+ $perperiod.getText());
					test1.pass("$/Period for  " +ExcelUtils.getCellData(k, colnum)+ " category Value is validated in Workbench as "+ $perperiod.getText());		
				}

				else {

					System.out.println("$/Period for  " +ExcelUtils.getCellData(k, colnum)+ " category Value is not validated in Workbench as "+ $perperiod.getText());
					test1.fail("$/Period for  " +ExcelUtils.getCellData(k, colnum)+ " category Value is not validated in Workbench as "+ $perperiod.getText());

				}

			}

			WebElement total$periodvalue=driver.findElement(By.id("CstperiodftPlh"));
			ExcelUtils.setCellData(total$periodvalue.getText(), 57, colnum);
			System.out.println("Total $/period. value is " + total$periodvalue.getText());
			test1.info("Total $/period. value is " + total$periodvalue.getText());

		} catch (Exception e) {
			test1.error(e.getMessage());
		}


	}

	@Test(priority =23)
	public  void check_Publish_To_Buyer_Calculator() throws Exception{

		ExtentTest test1 = extent.createTest("check_Publish_To_Buyer_Calculator", "Check Publish to buyer calculator checkbox");
		test1.log(Status.INFO, "Check Publish to buyer calculator checkbox");
		
		try {
			
			WebElement buyercalculator=driver.findElement(By.xpath("/html/body/div[22]/div[2]/div[1]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div/div[1]/div[3]/div/div[1]/div/div[2]/div/div[11]/div[2]/span/input[1]"));
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", buyercalculator);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Checked Publish to buyer calculator checkbox");
			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}

		


	}

	@Test(priority =24)
	public  void Get_Values_from_Buyer_Calculator() throws Exception{

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnQRWorkBenchSave")));
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("Get_Values_from_Buyer_Calculator", "Retrieve Buyer Calucations in Workbench");
		test1.log(Status.INFO, "Retrieve Buyer Calucations in Workbench");

		try {

			

			WebElement StemTime=driver.findElement(By.xpath("/html/body/div[22]/div[2]/div[1]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div/div[1]/div[3]/div/div[1]/div/div[2]/div/div[1]/div/div[1]/input"));
			ExcelUtils.setCellData(StemTime.getAttribute("value"), 59, colnum);
			

			System.out.println("Stem Time is "+ StemTime.getAttribute("value"));
			test1.info("Stem Time is "+ StemTime.getAttribute("value"));

			WebElement LoadingTime=driver.findElement(By.id("Terms_WorkbenchLoadingTime"));
			ExcelUtils.setCellData(LoadingTime.getAttribute("value"), 60, colnum);

			System.out.println("Loading Time is "+ LoadingTime.getAttribute("value"));
			test1.info("Loading Time is "+ LoadingTime.getAttribute("value"));

			WebElement AvgFuelingTimeperUnitmins=driver.findElement(By.id("Terms_WorkbenchGalsPerMinutePump"));
			BigDecimal val = new BigDecimal(AvgFuelingTimeperUnitmins.getAttribute("value"));
			DecimalFormat decimalFormat = new DecimalFormat("0.0");
			String numberAsString = decimalFormat.format(val);
			
			ExcelUtils.setCellData(numberAsString, 61, colnum);

			System.out.println("Avg. Fueling Time Per Unit min " + numberAsString);
			test1.info("Avg. Fueling Time Per Unit min " + numberAsString);

			WebElement FueledUnits=driver.findElement(By.id("eqwbFueledUnits"));
			ExcelUtils.setCellData(FueledUnits.getText(), 62, colnum);

			System.out.println("# Fuel Units is " + FueledUnits.getText());
			test1.info("# Fuel Units is " + FueledUnits.getText());

			WebElement TotalTimeperDelmins=driver.findElement(By.id("eqwblblTimePerDelivery"));
			ExcelUtils.setCellData(TotalTimeperDelmins.getText(), 63, colnum);

			System.out.println("Total Time Per Del Mins is " + TotalTimeperDelmins.getText());
			test1.info("Total Time Per Del Mins is " + TotalTimeperDelmins.getText());

			WebElement AvgMarginFreightDel=driver.findElement(By.id("eqwbFrietPerDelivery"));
			ExcelUtils.setCellData(AvgMarginFreightDel.getText(), 64, colnum);

			System.out.println("Avg. Margin & Freight/Del. is " + AvgMarginFreightDel.getText());
			test1.info("Avg. Margin & Freight/Del. is " + AvgMarginFreightDel.getText());

			WebElement GrossMarginHour=driver.findElement(By.id("eqwblblTotalTimetooltip"));
			ExcelUtils.setCellData(GrossMarginHour.getText(), 65, colnum);

			System.out.println("Gross Margin $/Hour is " + GrossMarginHour.getText());
			test1.info("Gross Margin $/Hour is " + GrossMarginHour.getText());

			WebElement Distance=driver.findElement(By.id("lblDistance"));
			ExcelUtils.setCellData(Distance.getText(), 66, colnum);

			System.out.println("Distance is " + Distance.getText());
			test1.info("Distance is " + Distance.getText());

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}
	
	@Test(priority =25)
	public  void Verify_finaltotal_and_RankingBasis_Values() throws Exception{

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollLow, 0)");

		ExtentTest test1 = extent.createTest("Verify_finaltotal_and_RankingBasis_Values", "Verify Final Total anf Ranking Basis value");
		test1.log(Status.INFO, "Verify Final Total anf Ranking Basis value");

		try {

			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollLow, 0)");

			WebElement finaltotal=driver.findElement(By.id("ldlfinaltotal"));
			System.out.println("Final Total has value as " + finaltotal.getText());

			ExcelUtils.setCellData(finaltotal.getText(), 69, colnum);
			WebElement savetandc=driver.findElement(By.id("btnQRWorkBenchSave"));
			savetandc.click();
			Thread.sleep(2000);
			
			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			WebElement tandcsaveok=driver.findElement(By.id("jqDialog_ok"));
			tandcsaveok.click();
			Thread.sleep(2000);
			
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			WebElement tandcclose=driver.findElement(By.id("btnSellerQuickResponseWindowClose"));
			tandcclose.click();
			Thread.sleep(2000);
			
			WebDriverWait wait2 = new WebDriverWait(driver, 100);
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

			WebElement rankingbasis=driver.findElement(By.xpath("//*[@id='QuickResponseGrid']/div[3]/table/tbody/tr[1]/td[24]"));
			System.out.println("Ranking basis has value as " + rankingbasis.getText());
			ExcelUtils.setCellData(rankingbasis.getText(), 73, colnum);

			String excepted=ExcelUtils.getCellData(69, colnum);
			String actual=rankingbasis.getText();

			if(actual.equalsIgnoreCase(excepted)){

				System.out.println("Total Final and Ranking Basis are same and validated and value is " + ExcelUtils.getCellData(69, colnum));
				test1.pass("Total Final and Ranking Basis are same and validated and value is " + ExcelUtils.getCellData(69, colnum));

			}
			else{

				System.out.println("Total Final and Ranking Basis are same and validated and value is " + ExcelUtils.getCellData(69, colnum));
				test1.pass("Total Final and Ranking Basis are same and validated and value is " + ExcelUtils.getCellData(69, colnum));

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}


	@Test(priority =26)
	public  void Submit_response() throws Exception{
		
		ExtentTest test1 = extent.createTest("Submit_response", "Submit quote response in quick response grid");
		test1.log(Status.INFO, "Submit quote response in quick response grid");


		try {
			
			WebElement submitresponse=driver.findElement(By.id("btnsubmitseller"));
			submitresponse.click();
			Thread.sleep(2000);

			WebElement alertcontent=driver.findElement(By.id("divpopupmessages"));
			System.out.println(alertcontent.getText());
			test1.info(alertcontent.getText());


			WebElement submitresponseok=driver.findElement(By.id("btnOK"));
			submitresponseok.click();
			Thread.sleep(2000);

			WebElement ok=driver.findElement(By.id("jqDialog_ok"));
			ok.click();
			Thread.sleep(2000);
			test1.info("Quote is submitted from Seller Side");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollLow, 0)");
			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}

	}

	@Test(priority =27)
	public  void verify_Submitted_quote_in_Active() throws Exception{

		ExtentTest test1 = extent.createTest("verify_Submitted_quote_in_Active", "Verify Submitted quote in Active tabd from seller side");
		test1.log(Status.INFO, "Verify Submitted quote in Active tabd from seller side");

		try {
			
			Thread.sleep(3000);
			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			 
			WebElement activequotes=driver.findElement(By.xpath("//span[contains(text(),'Active')]"));
			activequotes.click();
			Thread.sleep(3000);

			WebElement newsearch=driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/div/a/span/i"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsearch);
			Thread.sleep(3000);

			WebElement quotesearch=driver.findElement(By.id("draftRFPSearch"));
			quotesearch.sendKeys(ExcelUtils.getCellData(12, colnum));
			Thread.sleep(3000);
			quotesearch.sendKeys(Keys.CONTROL,"a");
			Thread.sleep(3000);
			VF_Screenshot.LTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement verifyactivequote=driver.findElement(By.xpath("//*[@id='DraftsRFPGrid']/div[3]/table/tbody/tr/td[8]"));
			System.out.println(verifyactivequote.getText());

			String excepted=ExcelUtils.getCellData(12, colnum);
			String actual=verifyactivequote.getText();

			if(actual.equalsIgnoreCase(excepted)){

				System.out.println(verifyactivequote.getText() + " quote is in Active tab");
				test1.pass(verifyactivequote.getText() + " quote is in Active tab");

			}
			else{

				System.out.println(verifyactivequote.getText() + " quote is not in Active tab");
				test1.fail(verifyactivequote.getText() + " quote is not in Active tab");

			}
			
			Thread.sleep(3000);
			
		} catch (Exception e) {
			
			test1.error(e.getMessage());
			
		}
		

	}

	@Test(priority =28)
	public void Logout_seller() throws Exception{

		ExtentTest test1 = extent.createTest("Logout_seller", "Verify Seller is logged out successfully");
		test1.log(Status.INFO, "Verify Seller is logged out successfully");

		try {

			WebElement logout=driver.findElement(By.xpath("//*[@id='wrapper']/div[1]/div/div/div/ul/li[5]/a"));
			logout.click();
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.presenceOfElementLocated(By.name("Username")));

			WebElement username=driver.findElement(By.name("Username"));
			if(username.isDisplayed()){


				System.out.println(ExcelUtils.getCellData(5, colnum)+ " Seller Logged Out Successfully");
				test1.pass(ExcelUtils.getCellData(5, colnum)+ " Seller Logged Out Successfully");

			}
			else {


				System.out.println(ExcelUtils.getCellData(5, colnum)+ " Seller is not Logged Out Successfully");
				test1.fail(ExcelUtils.getCellData(5, colnum)+ " Seller is not Logged Out Successfully");

			}

		} catch (Exception e) {

			test1.error(e.getMessage());
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


