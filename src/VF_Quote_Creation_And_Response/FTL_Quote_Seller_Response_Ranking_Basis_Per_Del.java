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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.Constant;
import Utility.ExcelUtils;
public class FTL_Quote_Seller_Response_Ranking_Basis_Per_Del {

	private static WebDriver driver;
	private static int colnum=4;
	

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentSparkReporter sparkReporter;	

	@BeforeSuite
	public void setUp(){

		htmlReporter= new ExtentHtmlReporter("../Veri-Fuel_Hybrid_Framework/FTL Freight Only/FTL_Seller_Response_Ranking_Basis_$_Per_Del.html");

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

		ExtentTest test1 = extent.createTest("Open D1 Fuel Connect", "Validating dev site");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"VF_FTL_Seller_Response");
		String Url = ExcelUtils.getCellData(4, colnum);
		driver.get(Url);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		test1.log(Status.INFO, "Navigating to dev site");
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dashboardnewSellerClick")));
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
			wait0.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(),'New Quotes')]")));

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
			quotesearch.sendKeys(ExcelUtils.getCellData(11, colnum));

			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			quotesearch.sendKeys(Keys.CONTROL,"a");
			Thread.sleep(2000);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Edting Quote in New Tab is " + ExcelUtils.getCellData(11, colnum));

			WebElement clickquote=driver.findElement(By.xpath("//*[@id='ReviewRFPGrid']/div[3]/table/tbody/tr[1]/td[10]"));
			clickquote.click();
			Thread.sleep(2000);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Click Quote in New Tab is " + ExcelUtils.getCellData(11, colnum));

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}

	@Test(priority =5)
	public  void Verify_Quote_status_as_New() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Quote_status_as_New", "Verifying Quote status as New in Quick Response Grid");
		test1.log(Status.INFO, "Verifying Quote status as New in Quick Response Grid");


		try {

			WebElement next = driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[22]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			if(driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[22]")).isDisplayed()){

				WebElement quotestatus=driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[22]"));
				String expectedquotestatus=ExcelUtils.getCellData(14, colnum);
				String actualquotestatus=quotestatus.getText();
				if(actualquotestatus.equalsIgnoreCase(expectedquotestatus)){
					
					System.out.println(ExcelUtils.getCellData(11, colnum) + " is in " + quotestatus.getText() + " status");
					test1.pass(ExcelUtils.getCellData(11, colnum) + " is in " + quotestatus.getText() + " status");
				}
				else{

					
					System.out.println(ExcelUtils.getCellData(11, colnum) + " is not in New status but it is in " + quotestatus.getText() + " status");
					test1.fail(ExcelUtils.getCellData(11, colnum) + " is not in New status but it is in " + quotestatus.getText() + " status");

				}

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}

	@Test(priority =6)
	public  void Enter_Tolls_on_Quick_Respose_grid() throws Exception{

		ExtentTest test1 = extent.createTest("Enter_Tolls_on_Quick_Respose_grid", "Enter Valid Toll data in Quick Response Grid");
		test1.log(Status.INFO, "Enter Valid Toll data in Quick Response Grid");

		try {

			for (int i=1,j=16; i<=3&&j<=18;i++,j++)

			{

				WebElement toll1=driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr["+i+"]/td[31]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", toll1);
				VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

				WebElement toll=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div/div/div[1]/div/div[4]/div[2]/div/div[1]/div/div[3]/div[3]/table/tbody/tr["+i+"]/td[31]/input"));
				toll.sendKeys(Keys.CONTROL,"a");
				VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
				toll.sendKeys(Keys.DELETE);
				VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
				toll.sendKeys(ExcelUtils.getCellData(j, colnum));
				VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

				WebElement div=driver.findElement(By.xpath("//*[@id='FreightSellerResponseTabStrip-1']/div/div[1]/div"));
				div.click();
				VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
				test1.info("Toll"+i+" value is " + ExcelUtils.getCellData(j, colnum));


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

			if(driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[22]")).isDisplayed()){

				WebElement quotestatus=driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[22]"));
				String expectedquotestatus=ExcelUtils.getCellData(19, colnum);
				String actualquotestatus=quotestatus.getText();
				if(actualquotestatus.equalsIgnoreCase(expectedquotestatus)){

					System.out.println(ExcelUtils.getCellData(11, colnum) + " is in " + quotestatus.getText() + " status");
					test1.pass(ExcelUtils.getCellData(11, colnum) + " is in " + quotestatus.getText() + " status");
				}

				else{


					System.out.println(ExcelUtils.getCellData(11, colnum) + " is not in Hold As Draft Status but in " + quotestatus.getText() + " status");
					test1.fail(ExcelUtils.getCellData(11, colnum) + " is not in Hold As Draft Status but in " + quotestatus.getText() + " status");

				}

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}
	}

	@Test(priority =8)
	public  void Enter_data_on_Freight_Per_Gallon_in_Quick_Response_Grid() throws Exception{

		ExtentTest test1 = extent.createTest("Enter_data_on_Freight_Per_Gallon_in_Quick_Response_Grid", "Enter Valid Data on Freight Per Gallons for all Product categories in Quick response Grid");
		test1.log(Status.INFO, "Enter Valid Data on Freight Per Gallons for all Product categories in Quick response Grid");

		try {

			for(int i=1,j=21; i<=9&&j<=29; i++,j++){

				WebElement freigtpergallon1=driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr["+i+"]/td[32]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", freigtpergallon1);
				VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

				WebElement freight=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div/div/div[1]/div/div[4]/div[2]/div/div[1]/div/div[3]/div[3]/table/tbody/tr["+i+"]/td[32]/input"));
				freight.sendKeys(ExcelUtils.getCellData(j, colnum));

				WebElement div=driver.findElement(By.xpath("//*[@id='FreightSellerResponseTabStrip-1']/div/div[1]/div"));
				div.click();		
				VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
				test1.info("Freight Per Gallon "+i+ " is " + ExcelUtils.getCellData(j, colnum));

			}



		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =9)
	public  void Enter_FuelSurcharge() throws Exception{

		ExtentTest test1 = extent.createTest("Enter_FuelSurcharge", "Enter Valid Data on Fuel Surcharge in Quick Response Grid");
		test1.log(Status.INFO, "Enter Valid Data on Fuel Surcharge in Quick Response Grid");

		try {


			WebElement fuelsurcharge=driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[33]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", fuelsurcharge);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebElement fuelsurchargeenter=driver.findElement(By.id("FuelSurchargeRate"));
			fuelsurchargeenter.sendKeys(Keys.CONTROL,"a");
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			fuelsurchargeenter.sendKeys(Keys.DELETE);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			fuelsurchargeenter.sendKeys(ExcelUtils.getCellData(30, colnum));
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Fuel Surcharge is " + ExcelUtils.getCellData(30, colnum));

			WebElement div=driver.findElement(By.xpath("//*[@id='FreightSellerResponseTabStrip-1']/div/div[1]/div"));
			div.click();		
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		} catch (Exception e) {
			test1.error(e.getMessage());
		}




	}

	@Test(priority =10)
	public  void save_response_tandc() throws Exception{

		ExtentTest test1 = extent.createTest("save_response_tandc", "Save Values in Quick Response");
		test1.log(Status.INFO, "Save Values in Quick Response");

		try {

			WebElement saveresponse=driver.findElement(By.id("btnsavefreightseller"));
			saveresponse.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebElement ok=driver.findElement(By.id("jqDialog_ok"));
			ok.click();
			WebDriverWait wait2 = new WebDriverWait(driver, 100);
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			test1.info("Values are saved in Quick Response Grid");

		} catch (Exception e) {

			test1.error(e.getMessage());
		}



	}

	@Test(priority =11)
	public  void Verify_Quote_status_as_ReadytoSubmit() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Quote_status_as_ReadytoSubmit", "Verify Quote status is in Ready To Submit");
		test1.log(Status.INFO, "Verify Quote status is in Ready To Submit");

		try {

			if(driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[22]")).isDisplayed()){

				WebElement quotestatus=driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[22]"));
				String expectedquotestatus=ExcelUtils.getCellData(32, colnum);
				String actualquotestatus=quotestatus.getText();
				if(actualquotestatus.equalsIgnoreCase(expectedquotestatus)){

					System.out.println(ExcelUtils.getCellData(11, colnum) + " is in " + quotestatus.getText() + " status");
					test1.pass(ExcelUtils.getCellData(11, colnum) + " is in " + quotestatus.getText() + " status");
				}
				else{


					System.out.println(ExcelUtils.getCellData(11, colnum) + " is not in " + quotestatus.getText() + " status");
					test1.fail(ExcelUtils.getCellData(11, colnum) + " is not in " + quotestatus.getText() + " status");
				}

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}

	@Test(priority =12)
	public  void verify_FuelSurcharge_AccessorialFees_And_Tolls_data_on_tandc() throws Exception{

		ExtentTest test1 = extent.createTest("verify_FuelSurcharge_AccessorialFees_And_Tolls_data_on_tandc", "Verify and Validate Toll values for Primary, Backup1 and Backup2 in TandC");
		test1.log(Status.INFO, "Verify and Validate Toll values for Primary, Backup1 and Backup2 in TandC");

		try {

			Thread.sleep(3000);
			
			WebElement tandcclick=driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']//a[@class='k-button k-button-icontext k-grid-Workbench']//span[@class='fa fa-file-excel-o']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tandcclick);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			tandcclick.click();
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait = new WebDriverWait(driver, 150);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement ftlfregihtonly=driver.findElement(By.xpath("//*[@id='expandallfreight']/label[1]"));

			ftlfregihtonly.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement fuelsurcharge=driver.findElement(By.id("FreightTerms_FuelSurchargeRate"));

			String expectedfuelsurchargevalue=ExcelUtils.getCellData(30, colnum);
			String actualfuelsurchargegal=fuelsurcharge.getAttribute("value");
			if(actualfuelsurchargegal.equalsIgnoreCase(expectedfuelsurchargevalue)){

				System.out.println("Fuel Surcharge (%) value in TandC is " + fuelsurcharge.getAttribute("value"));
				test1.pass("Fuel Surcharge (%) value in TandC is " + fuelsurcharge.getAttribute("value"));
			}
			else{

				System.out.println("Fuel Surcharge (%) value is not validated in TandC and Values is " + fuelsurcharge.getAttribute("value"));
				test1.fail("Fuel Surcharge (%) value is not validated in TandC and Values is " + fuelsurcharge.getAttribute("value"));
			}

			//Set Accessorial Fees

			WebElement accessoriesfees=driver.findElement(By.id("lblAccservicefee"));
			ExcelUtils.setCellData(accessoriesfees.getText(), 36, colnum);
			System.out.println("Accessorial Service Fees (Per Delivery) : in TandC is " + accessoriesfees.getText());
			test1.info("Accessorial Service Fees (Per Delivery) : in TandC is " + accessoriesfees.getText());

			WebElement addservice=driver.findElement(By.xpath("//*[@id='AccessorialPricingIndexGrid']/div[1]/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addservice);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			//Verify Tolls in TandC

			for(int i=1,j=16; i<=3&&j<=18; i++,j++){

				WebElement verifytolls=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[9]"));
				String expectedverifytollvalue=ExcelUtils.getCellData(j, colnum);
				String actualverifytoll=verifytolls.getText();

				if(actualverifytoll.equalsIgnoreCase(expectedverifytollvalue)){

					System.out.println("Toll" +i+" value in TandC is " + verifytolls.getText());
					test1.pass("Toll" +i+" value in TandC is " + verifytolls.getText());
				}
				else{

					System.out.println("Toll" +i+" value is not validated in TandC is " + verifytolls.getText());
					test1.fail("Toll" +i+" value is not validated in TandC is " + verifytolls.getText());

				}

			}



		} catch (Exception e) {

			test1.error(e.getMessage());

		}

	}


	@Test(priority =13)
	public  void Verify_Freight_Per_Gallon_Values_in_TandC() throws Exception{

		//Verify Freight Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Freight_Per_Gallon_Values_in_TandC", "Verify and Validate Freight per gallon values for all product categories and Terminal (Primary, Backup1 and Backup2) in TandC");
		test1.log(Status.INFO, "Verify and Validate Freight per gallon values for all product categories and Terminal (Primary, Backup1 and Backup2) in TandC");

		try {

			for(int i=1,j=21; i<=9&&j<=29; i++,j++)
			{
				WebElement verifyfreightpergallon=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[10]"));
				String expectedverifyfreightpergallonvalue=ExcelUtils.getCellData(j, colnum);
				String actualverifyfreightpergallon=verifyfreightpergallon.getText();
				if(actualverifyfreightpergallon.equalsIgnoreCase(expectedverifyfreightpergallonvalue)){

					System.out.println("Freight Per Gallon" +i+ " value in TandC is " + verifyfreightpergallon.getText());
					test1.pass("Freight Per Gallon" +i+ " value in TandC is " + verifyfreightpergallon.getText());
				}
				else{

					System.out.println("Freight Per Gallon" +i+ " value is not validated in TandC is " + verifyfreightpergallon.getText());
					test1.fail("Freight Per Gallon" +i+ " value is not validated in TandC is " + verifyfreightpergallon.getText());
				}

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}




	}


	@Test(priority =14)
	public  void Retrieve_Total_charges_Frt_Per_Gallon_Values_From_TandC() throws Exception{

		//Retrieve Total Charges Frt Per Gallon

		ExtentTest test1 = extent.createTest("Retrieve_Total_charges_Frt_Per_Gallon_Values_From_TandC", "Retrieve Total Charges Frt Per Gallon from TandC");
		test1.log(Status.INFO, "Retrieve Total Charges Frt Per Gallon from TandC");

		try {

			for(int i=1,j=52; i<=9&&j<=60; i++,j++){

				WebElement totalchargesfregithpergallon=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[17]"));
				ExcelUtils.setCellData(totalchargesfregithpergallon.getText(), j, colnum);
				System.out.println("Total Charge Frt Per Gallon" +i+" Value in TandC is "+ totalchargesfregithpergallon.getText());
				test1.info("Total Charge Frt Per Gallon" +i+" Value in TandC is "+ totalchargesfregithpergallon.getText());
			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}




	}


	@Test(priority =15)
	public  void Retrieve_$_Per_Period_Values_From_TandC() throws Exception{

		//Retrieve $/Period Values

		ExtentTest test1 = extent.createTest("Retrieve_$_Per_Period_Values_From_TandC", "Retrieve $/Period Values from TandC");
		test1.log(Status.INFO, "Retrieve $/Period Values from TandC");

		try {

			for(int i=1,j=62; i<=3&&j<=64; i++,j++){

				WebElement $perperiod=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[18]"));
				ExcelUtils.setCellData($perperiod.getText(), j, colnum);
				System.out.println("$/Period" +i+ " Value in TandC is "+ $perperiod.getText());
				test1.info("$/Period" +i+ " Value in TandC is "+ $perperiod.getText());
			}

			WebElement total$perperiodvalue=driver.findElement(By.id("frtlodftrgal"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr[1]/td[20]")));
			ExcelUtils.setCellData(total$perperiodvalue.getText(), 65, colnum);

			System.out.println("Total $/Period Value in TandC is "+ total$perperiodvalue.getText());
			test1.info("Total $/Period Value in TandC is "+ total$perperiodvalue.getText());


		} catch (Exception e) {
			test1.error(e.getMessage());
		}


	}

	@Test(priority =16)
	public  void Retrieve_$_Per_Del_Values_From_TandC() throws Exception{

		//Retrieve $/Del Values

		ExtentTest test1 = extent.createTest("Retrieve_$_Per_Del_Values_From_TandC", "Retrieve $/Del Values from TandC");
		test1.log(Status.INFO, "Retrieve $/Del Values from TandC");

		try {

			for(int i=1,j=67; i<=3&&j<=69; i++,j++){

				WebElement $perdel=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[19]"));
				ExcelUtils.setCellData($perdel.getText(), j, colnum);
				System.out.println("$/Del" +i+ " Value in TandC is "+ $perdel.getText());
				test1.info("$/Del" +i+ " Value in TandC is "+ $perdel.getText());
			}

			WebElement total$perdelvalue=driver.findElement(By.id("frtperdel"));
			ExcelUtils.setCellData(total$perdelvalue.getText(), 70, colnum);

			System.out.println("Total $/Del Value in TandC is "+ total$perdelvalue.getText());
			test1.info("Total $/Del Value in TandC is "+ total$perdelvalue.getText());

		} catch (Exception e) {

			test1.error(e.getMessage());

		}




	}

	@Test(priority =17)
	public  void Retrieve_$_Per_Gal_Values_From_TandC() throws Exception{

		//Retrieve $/Gal Values

		ExtentTest test1 = extent.createTest("Retrieve_$_Per_Gal_Values_From_TandC", "Retrieve $/Gal Values from TandC");
		test1.log(Status.INFO, "Retrieve $/Gal Values from TandC");

		try {

			for(int i=1,j=72; i<=3&&j<=74; i++,j++){

				WebElement $pergal=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[20]"));
				ExcelUtils.setCellData($pergal.getText(), j, colnum);
				System.out.println("$/Gal" +i+ " Value in TandC is "+ $pergal.getText());
				test1.info("$/Gal" +i+ " Value in TandC is "+ $pergal.getText());
			}


			WebElement total$pergalvalue=driver.findElement(By.id("frtrankingbasisgal"));
			ExcelUtils.setCellData(total$pergalvalue.getText(), 75, colnum);
			
			System.out.println("Total $/Gal Value in TandC is "+ total$pergalvalue.getText());
			test1.info("Total $/Gal Value in TandC is "+ total$pergalvalue.getText());

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnQRWorkBenchSave")));
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		} catch (Exception e) {
			test1.error(e.getMessage());
		}




	}

	@Test(priority =18)
	public  void Verify_Fuelsurcharge_And_Accessorial_Service_on_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Fuelsurcharge_And_Accessorial_Service_on_Workbench", "Verify and Validate Fuel Surcharge and Accessorial Fees in Workbwench");
		test1.log(Status.INFO, "Verify and Validate Fuel Surcharge and Accessorial Fees in Workbwench");

		try {


			WebElement workbench=driver.findElement(By.xpath("//span[contains(text(),'Workbench')]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", workbench);
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='tabstripworkbench-4']/div[9]/div[1]/div/div[3]/label/span")));
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebElement fuelsurcharge=driver.findElement(By.id("FreightTerms_WorkbenchFuelSurchargeRate"));
			String expectedfuelsurchargevalue=ExcelUtils.getCellData(30, colnum);
			String actualfuelsurchargegal=fuelsurcharge.getAttribute("value");

			if(actualfuelsurchargegal.equalsIgnoreCase(expectedfuelsurchargevalue)){

				System.out.println("Fuel Surcharge (%) value in Workbench is " + fuelsurcharge.getAttribute("value"));
				test1.pass("Fuel Surcharge (%) value in Workbench is " + fuelsurcharge.getAttribute("value"));
			}
			else{


				System.out.println("Fuel Surcharge is not validated and value in Workbench is " + fuelsurcharge.getAttribute("value"));
				test1.fail("Fuel Surcharge is not validated and value in Workbench is " + fuelsurcharge.getAttribute("value"));

			}
			Thread.sleep(2000);
			
			WebElement accessoriesfees=driver.findElement(By.xpath("/html/body/div[22]/div[2]/div[1]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div/div[4]/div[5]/div[2]/div[1]/div[4]/div/label[2]"));
			String expectedfuelaccessoriesfees=ExcelUtils.getCellData(36, colnum);
			String actualaccessoriesfees=accessoriesfees.getText();
			if(actualaccessoriesfees.equalsIgnoreCase(expectedfuelaccessoriesfees)){

				System.out.println("Accessorial Fees value in Workbench is " + accessoriesfees.getText());
				test1.pass("Accessorial Fees value in Workbench is " + accessoriesfees.getText());
			}
			else{


				System.out.println("Accessorial Fees value in Workbench is not validated and value is " + accessoriesfees.getText());
				test1.fail("Accessorial Fees value in Workbench is not validated and value is " + accessoriesfees.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}






	}

	@Test(priority =19)
	public  void Verify_Tolls_in_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Tolls_in_Workbench", "Verify and Validate Tolls in Workbwench");
		test1.log(Status.INFO, "Verify and Validate Tolls in Workbwench");

		try {

			for(int i=1,j=16; i<=3&&j<=18; i++,j++){

				WebElement verifytollinworkbench=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[9]"));
				String expectedverifytollinwb=ExcelUtils.getCellData(j, colnum);
				String actualverifytollwb=verifytollinworkbench.getText();
				if(actualverifytollwb.equalsIgnoreCase(expectedverifytollinwb)){

					System.out.println("Toll" +i+ " value in Workbench is " + verifytollinworkbench.getText());
					test1.pass("Toll" +i+ " value in Workbench is " + verifytollinworkbench.getText());
				}

				else{

					System.out.println("Toll" +i+ " value is not validated in Workbench is " + verifytollinworkbench.getText());
					test1.fail("Toll" +i+ " value is not validated in Workbench is " + verifytollinworkbench.getText());

				}
			}


		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =20)
	public  void Verify_Freight_Per_Gallon_Values_in_WorkBench() throws Exception{

		//Verify Freight Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Freight_Per_Gallon_Values_in_WorkBench", "Verify and Validate Freight Per Gallons in Workbwench");
		test1.log(Status.INFO, "Verify and Validate Freight Per Gallons in Workbwench");

		try {

			for(int i=1,j=21; i<=9&&j<=29; i++,j++){

				WebElement verifyfreightpergalloninwb=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[10]"));

				String expectedverifyfreightpergallon1value=ExcelUtils.getCellData(j, colnum);
				String actualverifyfreightpergallon1=verifyfreightpergalloninwb.getText();
				if(actualverifyfreightpergallon1.equalsIgnoreCase(expectedverifyfreightpergallon1value)){

					System.out.println("Freight Per Gallon" +i+ " value in workbench is " + verifyfreightpergalloninwb.getText());
					test1.pass("Freight Per Gallon" +i+ " value in workbench is " + verifyfreightpergalloninwb.getText());
				}
				else{


					System.out.println("Freight Per Gallon" +i+ " value is not validated in workbench is " + verifyfreightpergalloninwb.getText());
					test1.fail("Freight Per Gallon" +i+ " value is not validated in workbench is " + verifyfreightpergalloninwb.getText());

				}


			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}


	@Test(priority =21)
	public  void Verify_Total_Charges_Frt_$_Per_Gall_in_Workbench() throws Exception{



		//Verify Total Charges Frt Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Total_Charges_Frt_$_Per_Gall_in_Workbench", "Verify and Validate Total Charges Freight Per Gallons in Workbwench");
		test1.log(Status.INFO, "Verify and Validate Total Charges Freight Per Gallons in Workbwench");

		try {

			for(int i=1,j=52; i<=9&&j<=60; i++,j++){

				WebElement totalchargeinwb=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[17]"));

				String expectedtotalcharge1=ExcelUtils.getCellData(j, colnum);
				String actualverifytotalcharge1=totalchargeinwb.getText();
				if(actualverifytotalcharge1.equalsIgnoreCase(expectedtotalcharge1)){

					System.out.println("Total Charge Frt Per Gallon" +i+" Value in Workbench is "+ totalchargeinwb.getText());
					test1.pass("Total Charge Frt Per Gallon" +i+" Value in Workbench is "+ totalchargeinwb.getText());
				}
				else{


					System.out.println("Total Charge Frt Per Gallon" +i+" Value is not validated in Workbench is "+ totalchargeinwb.getText());
					test1.fail("Total Charge Frt Per Gallon" +i+" Value is not validated in Workbench is "+ totalchargeinwb.getText());
				}

			}

		} catch (Exception e) {

			test1.error(e.getMessage());
		}


	}


	@Test(priority =22)
	public  void Verify_$_Per_Period_Values_on_Workbench() throws Exception{

		//Verify $/Period Values on Workbench

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchSellerGrid']/div[3]/table/tbody/tr[1]/td[20]")));
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("Verify_$_Per_Period_Values_on_Workbench", "Verify and Validate $/Period values in Workbwench");
		test1.log(Status.INFO, "Verify and Validate $/Period values in Workbwench");


		try {

			for(int i=1,j=62; i<=3&&j<=64; i++,j++){

				WebElement $perperiod=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[18]"));

				String expectedt$perperiod1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perperiod1=$perperiod.getText();
				if(actualverify$perperiod1.equalsIgnoreCase(expectedt$perperiod1)){

					System.out.println("$/Period" +i+" Value in Workbench is "+ $perperiod.getText());
					test1.pass("$/Period" +i+" Value in Workbench is "+ $perperiod.getText());
				}
				else{


					System.out.println("$/Period" +i+" Value is not validated in Workbench is "+ $perperiod.getText());
					test1.fail("$/Period" +i+" Value is not validated in Workbench is "+ $perperiod.getText());
				}

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}

		WebElement total$perperiodvalue=driver.findElement(By.id("frtlodwbftrgal"));
		String expectedttotal$perperiodvalue=ExcelUtils.getCellData(65, colnum);
		String actualverifytotal$perperiodvalue=total$perperiodvalue.getText();
		if(actualverifytotal$perperiodvalue.equalsIgnoreCase(expectedttotal$perperiodvalue)){

			System.out.println("Total $/Period Value in workbench is "+ total$perperiodvalue.getText());
			test1.pass("Total $/Period Value in workbench is "+ total$perperiodvalue.getText());

		}
		else{


			System.out.println("Total $/Period Value is not validated and value is "+ total$perperiodvalue.getText());
			test1.pass("Total $/Period Value is not validated and value is "+ total$perperiodvalue.getText());


		}

	}

	@Test(priority =23)
	public  void Verify_$_Per_del_Values_on_Workbench() throws Exception{

		//Verify $/Del Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_del_Values_on_Workbench", "Verify and Validate $/Del values in Workbwench");
		test1.log(Status.INFO, "Verify and Validate $/Del values in Workbwench");


		try {

			for(int i=1,j=67; i<=3&&j<=69; i++,j++){

				WebElement $perdel1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[19]"));
				String expectedt$perdel1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perdel1=$perdel1.getText();
				if(actualverify$perdel1.equalsIgnoreCase(expectedt$perdel1)){

					System.out.println("$/Del" +i+ " Value in Workbench is "+ $perdel1.getText());
					test1.pass("$/Del" +i+ " Value in Workbench is "+ $perdel1.getText());
				}
				else{


					System.out.println("$/Del" +i+ " Value is not validated in Workbench is "+ $perdel1.getText());
					test1.fail("$/Del" +i+ " Value is not validated in Workbench is "+ $perdel1.getText());

				}

			}

		} catch (Exception e) {

		}

		WebElement total$perdelvalue=driver.findElement(By.id("frtwbperdel"));
		String expectedttotal$perdelvalue=ExcelUtils.getCellData(70, colnum);
		String actualverifytotal$perdelvalue=total$perdelvalue.getText();
		if(actualverifytotal$perdelvalue.equalsIgnoreCase(expectedttotal$perdelvalue)){

			System.out.println("Toral $/Del  Value in Workbench is "+ total$perdelvalue.getText());
			test1.pass("Toral $/Del  Value in Workbench is "+ total$perdelvalue.getText());
		}
		else{


			System.out.println("Total $/Del Value is Value is not validated and value is "+ total$perdelvalue.getText());
			test1.fail("Total $/Del Value is Value is not validated and value is "+ total$perdelvalue.getText());

		}
	}

	@Test(priority =24)
	public  void Verify_$_Per_Gal_Values_on_Workbench() throws Exception{

		//Verify $/Gal Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_Gal_Values_on_Workbench", "Verify and Validate $/Gal values in Workbwench");
		test1.log(Status.INFO, "Verify and Validate $/Gal values in Workbwench");

		try {

			for(int i=1,j=72; i<=3&&j<=74; i++,j++){

				WebElement $pergal1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[20]"));
				String expectedt$pergal1=ExcelUtils.getCellData(j, colnum);
				String actualverify$pergal1=$pergal1.getText();
				if(actualverify$pergal1.equalsIgnoreCase(expectedt$pergal1)){

					System.out.println("$/Gal" +i+" Value in Workbench is "+ $pergal1.getText());
					test1.pass("$/Gal" +i+" Value in Workbench is "+ $pergal1.getText());

				}
				else{


					System.out.println("$/Gal" +i+" Value is not validated in Workbench is "+ $pergal1.getText());
					test1.fail("$/Gal" +i+" Value is not validated in Workbench is "+ $pergal1.getText());

				}



			}
		} catch (Exception e) {
			test1.error(e.getMessage());
		}


		WebElement total$pergalvalue=driver.findElement(By.id("frtlodwbftrrank"));
		String expectedttotal$pergalvalue=ExcelUtils.getCellData(75, colnum);
		String actualverifytotal$pergalvalue=total$pergalvalue.getText();
		if(actualverifytotal$pergalvalue.equalsIgnoreCase(expectedttotal$pergalvalue)){

			System.out.println("Total $/Gal Value in Workbench is "+ total$pergalvalue.getText());
			test1.pass("Total $/Gal Value in Workbench is "+ total$pergalvalue.getText());
		}
		else{


			System.out.println("Total $/Gal Value in Workbench is not validated and value is "+ total$pergalvalue.getText());
			test1.fail("Total $/Gal Value in Workbench is not validated and value is "+ total$pergalvalue.getText());
		}

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnQRWorkBenchSave")));

	}



	@Test(priority =25)
	public  void check_Publish_To_Buyer_Calculator() throws Exception{

		ExtentTest test1 = extent.createTest("check_Publish_To_Buyer_Calculator", "Check Publish to buyer calculator checkbox");
		test1.log(Status.INFO, "Check Publish to buyer calculator checkbox");

		WebElement buyercalculator=driver.findElement(By.xpath("/html/body/div[22]/div[2]/div[1]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div[13]/div/span/input[1]"));
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", buyercalculator);
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
		test1.info("Checked Publish to buyer calculator checkbox");


	}

	@Test(priority =26)
	public  void Get_Values_from_Buyer_Calculator() throws Exception{

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnQRWorkBenchSave")));
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("Get_Values_from_Buyer_Calculator", "Retrieve Buyer Calucations in Workbench");
		test1.log(Status.INFO, "Retrieve Buyer Calucations in Workbench");

		try {

			WebElement TruckDroporPumpRateGalspermins=driver.findElement(By.id("FreightTerms_WorkbenchGalsPerMinutePump"));
			ExcelUtils.setCellData(TruckDroporPumpRateGalspermins.getAttribute("value"), 122, colnum);

			System.out.println("Truck Drop or Pump Rate (Gals per mins) value is "+ TruckDroporPumpRateGalspermins.getAttribute("value"));
			test1.info("Truck Drop or Pump Rate (Gals per mins) value is "+ TruckDroporPumpRateGalspermins.getAttribute("value"));

			WebElement RoundTripTime=driver.findElement(By.xpath("/html/body/div[22]/div[2]/div[1]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div[2]/div/div[1]/input"));
			ExcelUtils.setCellData(RoundTripTime.getAttribute("value"), 123, colnum);

			System.out.println("Round Trip Time value is "+ RoundTripTime.getAttribute("value"));
			test1.info("Round Trip Time value is "+ RoundTripTime.getAttribute("value"));

			WebElement TerminalLoadingTime=driver.findElement(By.id("FreightTerms_WorkbenchLoadingTime"));
			ExcelUtils.setCellData(TerminalLoadingTime.getAttribute("value"), 124, colnum);

			System.out.println("Terminal Loading Time value is " + TerminalLoadingTime.getAttribute("value"));
			test1.info("Terminal Loading Time value is " + TerminalLoadingTime.getAttribute("value"));

			WebElement AvgFuelingDroporPumpOffTimemins=driver.findElement(By.id("freightwbavgFuelingtime"));
			ExcelUtils.setCellData(AvgFuelingDroporPumpOffTimemins.getText(), 125, colnum);

			System.out.println("Avg. Fueling Drop or Pump Off Time (mins) value is " + AvgFuelingDroporPumpOffTimemins.getText());
			test1.info("Avg. Fueling Drop or Pump Off Time (mins) value is " + AvgFuelingDroporPumpOffTimemins.getText());

			WebElement GallonsDropped=driver.findElement(By.id("freightwbgallonsdropped"));
			ExcelUtils.setCellData(GallonsDropped.getText(), 126, colnum);

			System.out.println("Gallons Dropped value is " + GallonsDropped.getText());
			test1.info("Gallons Dropped value is " + GallonsDropped.getText());

			WebElement TotalRoundTripDeliveryTimemins=driver.findElement(By.id("freightwblblTimePerDelivery"));
			ExcelUtils.setCellData(TotalRoundTripDeliveryTimemins.getText(), 127, colnum);

			System.out.println("Total Round Trip Delivery Time (mins) value is " + TotalRoundTripDeliveryTimemins.getText());
			test1.info("Total Round Trip Delivery Time (mins) value is " + TotalRoundTripDeliveryTimemins.getText());

			WebElement Freight$Del=driver.findElement(By.id("freightwbFrietPerDelivery"));
			ExcelUtils.setCellData(Freight$Del.getText(), 128, colnum);

			System.out.println("Freight $/Del. value is " + Freight$Del.getText());
			test1.info("Freight $/Del. value is " + Freight$Del.getText());

			WebElement Freightevenue$Hour=driver.findElement(By.id("freightwblblTotalTimetooltip"));
			ExcelUtils.setCellData(Freightevenue$Hour.getText(), 129, colnum);

			System.out.println("Freight Revenue $/Hour value is " + Freightevenue$Hour.getText());
			test1.info("Freight Revenue $/Hour value is " + Freightevenue$Hour.getText());

			WebElement RoundTripMiles=driver.findElement(By.id("freightwbloadedmiles"));
			ExcelUtils.setCellData(RoundTripMiles.getText(), 130, colnum);

			System.out.println("Round Trip Miles value is " + RoundTripMiles.getText());
			test1.info("Round Trip Miles value is " + RoundTripMiles.getText());

			WebElement $perRoundTripMile=driver.findElement(By.id("freightwblblperloadedmiles"));
			ExcelUtils.setCellData($perRoundTripMile.getText(), 131, colnum);

			System.out.println("$ per Round Trip Mile value is " + $perRoundTripMile.getText());
			test1.info("$ per Round Trip Mile value is " + $perRoundTripMile.getText());

			WebElement PCMilerOneWayDistanceandTime=driver.findElement(By.id("lblDistanceFreight"));
			ExcelUtils.setCellData(PCMilerOneWayDistanceandTime.getText(), 132, colnum);

			System.out.println("PC Miler One Way Distance and Time value is " + PCMilerOneWayDistanceandTime.getText());
			test1.info("PC Miler One Way Distance and Time value is " + PCMilerOneWayDistanceandTime.getText());

		} catch (Exception e) {

			test1.error(e.getMessage());

		}





	}


	@Test(priority =27)
	public  void Verify_finaltotal_and_RankingBasis_Values() throws Exception{

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollLow, 0)");
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("Verify_finaltotal_and_RankingBasis_Values", "Verify Final Total anf Ranking Basis value");
		test1.log(Status.INFO, "Verify Final Total anf Ranking Basis value");

		try {


			WebElement finaltotal=driver.findElement(By.id("ldlfinaltotal"));
			ExcelUtils.setCellData(finaltotal.getText(), 134, colnum);

			System.out.println("Final Total value is " + finaltotal.getText());
			test1.info("Final Total value is " + finaltotal.getText());

			WebElement savetandc=driver.findElement(By.id("btnQRWorkBenchSave"));
			savetandc.click();
			Thread.sleep(5000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));


			WebElement tandcsaveok=driver.findElement(By.id("jqDialog_ok"));
			tandcsaveok.click();
			Thread.sleep(3000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			WebElement tandcclose=driver.findElement(By.id("btnSellerQuickResponseWindowClose"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", tandcclose);
			Thread.sleep(3000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebDriverWait wait2 = new WebDriverWait(driver, 100);
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[36]")));


			WebElement rankingbasis=driver.findElement(By.xpath("//*[@id='FreightQuickResponseGrid']/div[3]/table/tbody/tr[1]/td[36]"));
			ExcelUtils.setCellData(rankingbasis.getText(), 136, colnum);

			System.out.println("Ranking basis has value as " + rankingbasis.getText());
			test1.info("Ranking basis has value as " + rankingbasis.getText());

			String excepted=ExcelUtils.getCellData(134, colnum);
			String actual=rankingbasis.getText();

			if(actual.equalsIgnoreCase(excepted)){


				System.out.println("Total Final and Ranking Basis are same and validated and value is " + ExcelUtils.getCellData(134, colnum));
				test1.pass("Total Final and Ranking Basis are same and validated and value is " + ExcelUtils.getCellData(134, colnum));

			}
			else{


				System.out.println("Total Final and Ranking Basis are not same and validated");
				test1.fail("Total Final and Ranking Basis are not same and validated");

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}



	}

	@Test(priority =28)
	public  void Submit_response() throws Exception{

		ExtentTest test1 = extent.createTest("Submit_response", "Submit quote response in quick response grid");
		test1.log(Status.INFO, "Submit quote response in quick response grid");

		try {

			WebElement submitresponse=driver.findElement(By.id("btnsubmitfreightseller"));
			submitresponse.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebElement alertcontent=driver.findElement(By.id("divfrtpopupmessages"));
			System.out.println(alertcontent.getText());
			test1.info(alertcontent.getText());

			WebElement submitresponseok=driver.findElement(By.id("btnfrtOK"));
			submitresponseok.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebElement ok=driver.findElement(By.id("jqDialog_ok"));
			ok.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			test1.info("Quote is submitted from Seller Side");

			((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollLow, 0)");
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


		} catch (Exception e) {
			test1.error(e.getMessage());
		}





	}

	@Test(priority =29)
	public  void verify_Submitted_quote_in_Active() throws Exception{

		ExtentTest test1 = extent.createTest("verify_Submitted_quote_in_Active", "Verify Submitted quote in Active tabd from seller side");
		test1.log(Status.INFO, "Verify Submitted quote in Active tabd from seller side");

		try {

			Thread.sleep(3000);
			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/div/div[1]/div/div[1]/div/div[1]/div/a/span/i")));
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement activequotes=driver.findElement(By.xpath("//span[contains(text(),'Active')]"));
			activequotes.click();
			Thread.sleep(3000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebElement newsearch=driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/div/a/span/i"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsearch);
			Thread.sleep(3000);
			newsearch.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement quotesearch=driver.findElement(By.id("draftRFPSearch"));
			quotesearch.sendKeys(ExcelUtils.getCellData(11, colnum));
			Thread.sleep(3000);
			quotesearch.sendKeys(Keys.CONTROL,"a");
			Thread.sleep(3000);
			VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			WebElement verifyactivequote=driver.findElement(By.xpath("//*[@id='DraftsRFPGrid']/div[3]/table/tbody/tr/td[8]"));
			System.out.println(verifyactivequote.getText());

			String excepted=ExcelUtils.getCellData(11, colnum);
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

	@Test(priority =30)
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


