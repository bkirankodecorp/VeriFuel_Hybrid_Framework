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
public class FTL_Quote_Buyer_Side_Verification_Ranking_Basis_Per_Gal {

	private static WebDriver driver;
	private static int colnum=5;


	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeSuite
	public void setUp(){

		htmlReporter= new ExtentHtmlReporter("../Veri-Fuel_Hybrid_Framework/FTL Freight Only/FTL_Quote_Buyer_Side_Calculations_Verification_$_Per_Gal.html");

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

		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"VF_FTL_Seller_Response");

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
			UserName.sendKeys(ExcelUtils.getCellData(154, colnum));
			test1.pass(ExcelUtils.getCellData(154, colnum)+ " as Username is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement Password =driver.findElement(By.name("Password"));
			Password.sendKeys(ExcelUtils.getCellData(155, colnum));
			test1.pass(ExcelUtils.getCellData(155, colnum) + " as Password is entered");
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
		//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("verify_buyer_login", "Verify Buyer is logged in dev site");
		test1.log(Status.INFO, "Verify Buyer logged in");

		WebElement  newquote = driver.findElement(By.id("dashboardnewBuyerClick"));

		if(newquote.isDisplayed()){

			System.out.println("\n"+ ExcelUtils.getCellData(154, colnum) + " Buyer logged in successfully");
			test1.pass(ExcelUtils.getCellData(154, colnum) + " Buyer Logged in Succesfully");


		}
		else{

			System.out.println(ExcelUtils.getCellData(154, colnum) + " Buyer Loggedin is not Succesfully");
			test1.fail(ExcelUtils.getCellData(154, colnum) + " Buyer Loggedin is not Succesfully");


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

			int ok_size=driver.findElements(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div[2]/div/ul/li[2]/span[2]")).size();
			driver.findElements(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div[2]/div/ul/li[2]/span[2]")).get(ok_size-1).click();
			Thread.sleep(3000);
			test1.info("Click Active Quote Tab");

			WebElement activesearch = driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div[2]/div/div[2]/div/div/div/div[1]/div/div[1]/div/a/span/i"));
			activesearch.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.pass("Click Active Search Icon");

			WebElement enteronsearch = driver.findElement(By.id("txtSearch"));
			enteronsearch.sendKeys(ExcelUtils.getCellData(161, colnum));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.pass("Query on Searchbox with " + ExcelUtils.getCellData(161, colnum));

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
	public  void Open_Delivery_Location_list() throws Exception{

		ExtentTest test1 = extent.createTest("Open_Delivery_Location_list", "click on Delivery Location List");
		test1.log(Status.INFO, "click on Delivery Location List");

		try {

			WebElement deliverylocationclick=driver.findElement(By.xpath("//*[@id='ReviewRFPDeliveryLocationrGrid']/div[3]/table/tbody/tr/td[2]/a"));
			System.out.println("Delivery Location is " + deliverylocationclick.getText() + " Clicked");
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", deliverylocationclick);
			test1.info("Delivery Location is " + deliverylocationclick.getText() + "  Clicked");

		} catch (Exception e) {


			test1.error(e.getMessage());
		}



	}

	@Test(priority =7)
	public  void Verify_Page_Is_Navigated_To_Manage_Rounds() throws Exception{

		WebDriverWait wait0 = new WebDriverWait(driver, 120);
		wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

		ExtentTest test1 = extent.createTest("Verify_Page_Is_Navigated_To_Manage_Rounds", "Verify Page is navigated to Manage Rounds");
		test1.log(Status.INFO, "Verify Page is navigated to Manage Rounds");

		try {

			WebElement roundenddate=driver.findElement(By.id("createRFP_RFPEndDate"));
			if(roundenddate.isDisplayed()){


				System.out.println("Page is Navigated to Manage Rounds");
				test1.pass("Page is Navigated to Manage Rounds");
			}

			else{


				System.out.println("Page is not Navigated to Manage Rounds");
				test1.fail("Page is not Navigated to Manage Rounds");

			}

		} catch (Exception e) {


		}

	}	


	@Test(priority =8)
	public  void Open_View_in_TandC() throws Exception{

		ExtentTest test1 = extent.createTest("Open_View_in_TandC", "Open View in TandC");
		test1.log(Status.INFO, "Open View in TandC");

		try {

			WebElement tandcview=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[33]/a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", tandcview);
			test1.info("View in TandC in Manaage Rouds is Opened");


		} catch (Exception e) {


			test1.error(e.getMessage());
		}

	}	


	@Test(priority =9)
	public  void Verify_FuelSurcharge_and_Accessorial_Service_Fees_Per_Delivery_in_TandC() throws Exception{

		WebDriverWait wait0 = new WebDriverWait(driver, 100);
		wait0.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='expandallfreight']/label[1]")));
		Thread.sleep(2000);

		ExtentTest test1 = extent.createTest("Verify_FuelSurcharge_and_Accessorial_Service_Fees_(Per Delivery)_in_TandC", "Verify Fuel Surcharge and Accessorial Service Fees (Per Delivery) : in TandC");
		test1.log(Status.INFO, "Verify Fuel Surcharge and Accessorial Fees in TandC");

		try {

			WebElement expandall=driver.findElement(By.xpath("//*[@id='expandallfreight']/label[1]"));
			expandall.click();
			Thread.sleep(2000);
			test1.info("Click Expand All");

			WebElement fuelsurcharge=driver.findElement(By.id("FreightTerms_FuelSurchargeRate"));
			String expectedfuelsurchargevalue=ExcelUtils.getCellData(30, colnum);
			String actualfuelsurchargegal=fuelsurcharge.getAttribute("value");

			if(actualfuelsurchargegal.equalsIgnoreCase(expectedfuelsurchargevalue)){

				System.out.println("Fuel Surcharge (%) value in TandC is " + fuelsurcharge.getAttribute("value"));
				test1.pass("Fuel Surcharge (%) value in TandC is " + fuelsurcharge.getAttribute("value"));
			}
			else{


				System.out.println("Fuel Surcharge is not validated and value in TandC is " + fuelsurcharge.getAttribute("value"));
				test1.fail("Fuel Surcharge is not validated and value in TandC is " + fuelsurcharge.getAttribute("value"));

			}


			WebElement accessoriesfees=driver.findElement(By.id("lblAccservicefee"));
			String expectedfuelaccessoriesfees=ExcelUtils.getCellData(36, colnum);
			String actualaccessoriesfees=accessoriesfees.getText();
			if(actualaccessoriesfees.equalsIgnoreCase(expectedfuelaccessoriesfees)){

				System.out.println("Accessorial Service Fees (Per Delivery) : value in TandC is " + accessoriesfees.getText());
				test1.pass("Accessorial Service Fees (Per Delivery) : value in TandC is " + accessoriesfees.getText());
			}
			else{


				System.out.println("Accessorial Fees value in TandC is not validated and value is " + accessoriesfees.getText());
				test1.fail("Accessorial Fees value in TandC is not validated and value is " + accessoriesfees.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}
	}	


	@Test(priority =10)
	public  void verify_Tolls_data_on_tandc() throws Exception{

		ExtentTest test1 = extent.createTest("verify_Tolls_data_on_tandc", "Verify Tolls in TandC");
		test1.log(Status.INFO, "Verify Tolls in TandC");

		try {

			WebElement optionalcolumns=driver.findElement(By.xpath("//*[@id='priceindexSeller']/div[1]/div/div[3]/label/span"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionalcolumns);
			//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			Thread.sleep(2000);

			WebElement targetlow=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr[1]/td[20]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetlow);


			for(int i=1,j=16; i<=3&&j<=18; i++,j++){

				WebElement verifytoll=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[8]"));
				String expectedverifytoll1value=ExcelUtils.getCellData(j, colnum);
				String actualverifytoll1=verifytoll.getText();
				if(actualverifytoll1.equalsIgnoreCase(expectedverifytoll1value)){

					System.out.println("Toll" +i+" value in TandC is " + verifytoll.getText());
					test1.pass("Toll" +i+" value in TandC is " + verifytoll.getText());
				}
				else{


					System.out.println("Toll" +i+" value is not validated in TandC is " + verifytoll.getText());
					test1.fail("Toll" +i+" value is not validated in TandC is " + verifytoll.getText());

				}

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =11)
	public  void Verify_Freight_Per_Gallon_Values_in_TandC() throws Exception{

		//Verify Freight Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Freight_Per_Gallon_Values_in_TandC", "Verify Freight per gallon values in TandC");
		test1.log(Status.INFO, "Verify Freight per gallon values in TandC");

		try {

			for(int i=1,j=21; i<=9&&j<=29; i++,j++){

				WebElement verifyfreightpergallon=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[9]"));
				String expectedverifyfreightpergallon1value=ExcelUtils.getCellData(j, colnum);
				String actualverifyfreightpergallon1=verifyfreightpergallon.getText();

				if(actualverifyfreightpergallon1.equalsIgnoreCase(expectedverifyfreightpergallon1value)){

					System.out.println("Freight Per Gallon" +i+" value in TandC is " + verifyfreightpergallon.getText());
					test1.pass("Freight Per Gallon" +i+" value in TandC is " + verifyfreightpergallon.getText());
				}
				else{


					System.out.println("Freight Per Gallon" +i+" value is not validated in TandC is " + verifyfreightpergallon.getText());
					test1.fail("Freight Per Gallon" +i+" value is not validated in TandC is " + verifyfreightpergallon.getText());

				}

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}


	@Test(priority =12)
	public  void Verify_Total_Charges_Frt_$_Per_Gall_in_TandC() throws Exception{

		//Verify Total Charges Frt Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Total_Charges_Frt_$_Per_Gall_in_TandC", "Verify Total Charges Frt Per Gallons in TandC");
		test1.log(Status.INFO, "Verify Total Charges Frt Per Gallons in TandC");

		try {

			for(int i=1,j=52; i<=9&&j<=60; i++,j++){

				WebElement totalcharge1=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[16]"));
				String expectedtotalcharge1=ExcelUtils.getCellData(j, colnum);
				String actualverifytotalcharge1=totalcharge1.getText();
				if(actualverifytotalcharge1.equalsIgnoreCase(expectedtotalcharge1)){

					System.out.println("Total Charge Frt Per Gallon" +i+" Value in TandC is "+ totalcharge1.getText());
					test1.pass("Total Charge Frt Per Gallon" +i+" Value in TandC is "+ totalcharge1.getText());
				}
				else{


					System.out.println("Total Charge Frt Per Gallon" +i+" Value is not validated in TandC is "+ totalcharge1.getText());
					test1.fail("Total Charge Frt Per Gallon" +i+" Value is not validated in TandC is "+ totalcharge1.getText());

				}


			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}

	}

	@Test(priority =13)
	public  void Verify_$_Per_Period_Values_on_TandC() throws Exception{

		//Verify $/Period Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_Period_Values_on_TandC", "Verify $/Period values on TandC");
		test1.log(Status.INFO, "Verify $/Period values on TandC");

		try {

			for(int i=1,j=62; i<=3&&j<=64; i++,j++){


				WebElement $perperiod1=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[17]"));
				String expectedt$perperiod1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perperiod1=$perperiod1.getText();

				if(actualverify$perperiod1.equalsIgnoreCase(expectedt$perperiod1)){

					System.out.println("$/Period" +i+" Value in TandC is "+ $perperiod1.getText());
					test1.pass("$/Period" +i+" Value in TandC is "+ $perperiod1.getText());
				}
				else{


					System.out.println("$/Period" +i+" Value is not validated in TandC is "+ $perperiod1.getText());
					test1.fail("$/Period" +i+" Value is not validated in TandC is "+ $perperiod1.getText());

				}

			}

			WebElement total$perperiodvalue=driver.findElement(By.id("frtlodftrgal"));
			String expectedttotal$perperiodvalue=ExcelUtils.getCellData(65, colnum);
			String actualverifytotal$perperiodvalue=total$perperiodvalue.getText();
			if(actualverifytotal$perperiodvalue.equalsIgnoreCase(expectedttotal$perperiodvalue)){

				System.out.println("Total $/Period Value in TandC is "+ total$perperiodvalue.getText());
				test1.pass("Total $/Period Value in TandC is "+ total$perperiodvalue.getText());
			}
			else{


				System.out.println("Total $/Period Value in TandC is not validated and value is "+ total$perperiodvalue.getText());
				test1.fail("Total $/Period Value in TandC is not validated and value is "+ total$perperiodvalue.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =14)
	public  void Verify_$_Per_del_Values_on_TandC() throws Exception{

		//Verify $/Del Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_del_Values_on_TandC", "Verify $/Del values on TandC");
		test1.log(Status.INFO, "Verify $/Del values on TandC");

		try {

			for(int i=1,j=67; i<=3&&j<=69; i++,j++){

				WebElement $perdel1=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[18]"));
				String expectedt$perdel1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perdel1=$perdel1.getText();
				if(actualverify$perdel1.equalsIgnoreCase(expectedt$perdel1)){

					System.out.println("$/Del" +i+"  Value in TandC is "+ $perdel1.getText());
					test1.pass("$/Del" +i+"  Value in TandC is "+ $perdel1.getText());
				}
				else{


					System.out.println("$/Del" +i+ " Value is Value in TandC is not validated and value is "+ $perdel1.getText());
					test1.fail("$/Del" +i+ " Value is Value in TandC is not validated and value is "+ $perdel1.getText());

				}

			}


			WebElement total$perdelvalue=driver.findElement(By.id("frtperdel"));
			String expectedttotal$perdelvalue=ExcelUtils.getCellData(70, colnum);
			String actualverifytotal$perdelvalue=total$perdelvalue.getText();
			if(actualverifytotal$perdelvalue.equalsIgnoreCase(expectedttotal$perdelvalue)){

				System.out.println("Toral $/Del  Value in TandC is "+ total$perdelvalue.getText());
				test1.pass("Toral $/Del  Value in TandC is "+ total$perdelvalue.getText());
			}
			else{


				System.out.println("Total $/Del Value in TandC not validated and value is "+ total$perdelvalue.getText());
				test1.fail("Total $/Del Value in TandC not validated and value is "+ total$perdelvalue.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}


	}

	@Test(priority =15)
	public  void Verify_$_Per_Gal_Values_on_TandC() throws Exception{

		//Verify $/Gal Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_Gal_Values_on_TandC", "Verify $/Gal values on TandC");
		test1.log(Status.INFO, "Verify $/Gal values on TandC");

		try {

			for(int i=1,j=72; i<=3&&j<=74; i++,j++){

				WebElement $pergal1=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[19]"));
				String expectedt$pergal1=ExcelUtils.getCellData(j, colnum);
				String actualverify$pergal1=$pergal1.getText();
				if(actualverify$pergal1.equalsIgnoreCase(expectedt$pergal1)){

					System.out.println("$/Gal" +i+" Value in TandC is "+ $pergal1.getText());
					test1.pass("$/Gal" +i+" Value in TandC is "+ $pergal1.getText());
				}
				else{


					System.out.println("$/Gal" +i+" Value in TandC is not validated and value is "+ $pergal1.getText());
					test1.fail("$/Gal" +i+" Value in TandC is not validated and value is "+ $pergal1.getText());

				}


			}


			WebElement total$pergalvalue=driver.findElement(By.id("frtrankingbasisgal"));
			String expectedttotal$pergalvalue=ExcelUtils.getCellData(75, colnum);
			String actualverifytotal$pergalvalue=total$pergalvalue.getText();
			if(actualverifytotal$pergalvalue.equalsIgnoreCase(expectedttotal$pergalvalue)){

				System.out.println("Total $/Gal Value in TandC is "+ total$pergalvalue.getText());
				test1.pass("Total $/Gal Value in TandC is "+ total$pergalvalue.getText());
			}
			else{


				System.out.println("Total $/Gal Value in TandC is not validated and value is "+ total$pergalvalue.getText());
				test1.fail("Total $/Gal Value in TandC is not validated and value is "+ total$pergalvalue.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =16)
	public  void Verify_finaltotal_and_RankingBasis_Values_in_TandC() throws Exception{

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("ldlfinaltotal")));
		//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("Verify_finaltotal_and_RankingBasis_Values_in_TandC", "Verify Final Total and Ranking Basis Values in TandC");
		test1.log(Status.INFO, "Verify Final Total and Ranking Basis Values in TandC");

		try {

			WebElement finaltotal=driver.findElement(By.id("ldlfinaltotal"));
			System.out.println("Final Total value is " + finaltotal.getText());

			WebElement closetandc=driver.findElement(By.id("btnClose"));
			closetandc.click();
			Thread.sleep(3000);
			//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("createRFP_RFPEndDate")));


			WebElement rankingbasis=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[15]"));
			System.out.println("Ranking basis has value as " + rankingbasis.getText());

			String excepted=ExcelUtils.getCellData(134, colnum);
			String actual=rankingbasis.getText();

			if(actual.equalsIgnoreCase(excepted)){


				System.out.println("Total Final and Ranking Basis in TandC are same and validated and values are " + rankingbasis.getText());
				test1.pass("Total Final and Ranking Basis in TandC are same and validated and values are " + rankingbasis.getText());
			}
			else{


				System.out.println("Total Final and Ranking Basis are not same in TandC and validated");
				test1.fail("Total Final and Ranking Basis are not same in TandC and validated");

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =17)
	public  void Open_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Open_Workbench", "Open Workbench View");
		test1.log(Status.INFO, "Open Workbench View");

		WebElement workbench=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[32]/a"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", workbench);

		test1.info("Click Workbench View in Manage Rounds");

	}	

	@Test(priority =18)
	public  void Verify_Buyer_Calculations_on_Workbench() throws Exception{

		WebDriverWait wait1 = new WebDriverWait(driver, 100);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='expandallfrtwb']/label[1]")));
		//Thread.sleep(4000);
		
		WebDriverWait wait11 = new WebDriverWait(driver, 100);
		wait11.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
		Thread.sleep(4000);

		ExtentTest test1 = extent.createTest("Verify_Buyer_Calculations_on_Workbench", "Verify Buyer Calculations on Workbench");
		test1.log(Status.INFO, "Verify Buyer Calculations on Workbench");

		try {

			WebElement wbexpandall=driver.findElement(By.xpath("//*[@id='expandallfrtwb']/label[1]"));
			wbexpandall.click();
			Thread.sleep(2000);

			WebElement TruckDroporPumpRateGalspermins=driver.findElement(By.id("WorkdBenchFreightTerms_GalsPerMinutePump"));
			String expectedTruckDroporPumpRateGalspermins=ExcelUtils.getCellData(122, colnum);
			String actualTruckDroporPumpRateGalspermins=TruckDroporPumpRateGalspermins.getAttribute("value");

			if(actualTruckDroporPumpRateGalspermins.equalsIgnoreCase(expectedTruckDroporPumpRateGalspermins)){

				System.out.println("Truck Drop or Pump Rate (Gals per mins) value is "+ TruckDroporPumpRateGalspermins.getAttribute("value"));
				test1.pass("Truck Drop or Pump Rate (Gals per mins) value is "+ TruckDroporPumpRateGalspermins.getAttribute("value"));
			}

			else{

				System.out.println("Truck Drop or Pump Rate (Gals per mins) value is not validated in Workbench and value is  "+ TruckDroporPumpRateGalspermins.getAttribute("value"));
				test1.fail("Truck Drop or Pump Rate (Gals per mins) value is not validated in Workbench and value is  "+ TruckDroporPumpRateGalspermins.getAttribute("value"));

			}

			WebElement RoundTripTime=driver.findElement(By.id("WorkdBenchFreightTerms_StemLoadingTime"));
			String expectedRoundTripTime=ExcelUtils.getCellData(123, colnum);
			String actualRoundTripTime=RoundTripTime.getAttribute("value");

			if(actualRoundTripTime.equalsIgnoreCase(expectedRoundTripTime)){

				System.out.println("Round Trip Time value is "+ RoundTripTime.getAttribute("value"));
				test1.pass("Round Trip Time value is "+ RoundTripTime.getAttribute("value"));
			}

			else{

				System.out.println("Round Trip Time value is not validated in workbench and value is "+ RoundTripTime.getAttribute("value"));
				test1.fail("Round Trip Time value is not validated in workbench and value is "+ RoundTripTime.getAttribute("value"));

			}

			WebElement TerminalLoadingTime=driver.findElement(By.id("WorkdBenchFreightTerms_LoadingTime"));
			String expectedTerminalLoadingTime=ExcelUtils.getCellData(124, colnum);
			String actualTerminalLoadingTime=TerminalLoadingTime.getAttribute("value");

			if(actualTerminalLoadingTime.equalsIgnoreCase(expectedTerminalLoadingTime)){

				System.out.println("Terminal Loading Time value is " + TerminalLoadingTime.getAttribute("value"));
				test1.pass("Terminal Loading Time value is " + TerminalLoadingTime.getAttribute("value"));

			}

			else{

				System.out.println("Terminal Loading Time value is not validated and value is " + TerminalLoadingTime.getAttribute("value"));
				test1.fail("Terminal Loading Time value is not validated and value is " + TerminalLoadingTime.getAttribute("value"));

			}

			WebElement AvgFuelingDroporPumpOffTimemins=driver.findElement(By.id("freightwbavgFuelingtime"));
			String expectedAvgFuelingDroporPumpOffTimemins=ExcelUtils.getCellData(125, colnum);
			String actualAvgFuelingDroporPumpOffTimemins=AvgFuelingDroporPumpOffTimemins.getText();

			if(actualAvgFuelingDroporPumpOffTimemins.equalsIgnoreCase(expectedAvgFuelingDroporPumpOffTimemins)){

				System.out.println("Avg. Fueling Drop or Pump Off Time (mins) value is " + AvgFuelingDroporPumpOffTimemins.getText());
				test1.pass("Avg. Fueling Drop or Pump Off Time (mins) value is " + AvgFuelingDroporPumpOffTimemins.getText());

			}

			else{

				System.out.println("Avg. Fueling Drop or Pump Off Time (mins) value in workbench is not validated and value is " + AvgFuelingDroporPumpOffTimemins.getText());
				test1.fail("Avg. Fueling Drop or Pump Off Time (mins) value in workbench is not validated and value is " + AvgFuelingDroporPumpOffTimemins.getText());

			}

			WebElement GallonsDropped=driver.findElement(By.id("freightwbgallonsdropped"));
			String expectedGallonsDropped=ExcelUtils.getCellData(126, colnum);
			String actualGallonsDropped=GallonsDropped.getText();

			if(actualGallonsDropped.equalsIgnoreCase(expectedGallonsDropped)){

				System.out.println("Gallons Dropped value is " + GallonsDropped.getText());
				test1.pass("Gallons Dropped value is " + GallonsDropped.getText());

			}

			else{

				System.out.println("Gallons Dropped value is not validated in workbench and value is " + GallonsDropped.getText());
				test1.fail("Gallons Dropped value is not validated in workbench and value is " + GallonsDropped.getText());

			}

			WebElement TotalRoundTripDeliveryTimemins=driver.findElement(By.id("freightwblblTimePerDelivery"));
			String expectedTotalRoundTripDeliveryTimemins=ExcelUtils.getCellData(127, colnum);
			String actualTotalRoundTripDeliveryTimemins=TotalRoundTripDeliveryTimemins.getText();

			if(actualTotalRoundTripDeliveryTimemins.equalsIgnoreCase(expectedTotalRoundTripDeliveryTimemins)){

				System.out.println("Total Round Trip Delivery Time (mins) value is " + TotalRoundTripDeliveryTimemins.getText());
				test1.pass("Total Round Trip Delivery Time (mins) value is " + TotalRoundTripDeliveryTimemins.getText());

			}

			else{

				System.out.println("Total Round Trip Delivery Time (mins) value in workbench is not validated and value is " + TotalRoundTripDeliveryTimemins.getText());
				test1.fail("Total Round Trip Delivery Time (mins) value in workbench is not validated and value is " + TotalRoundTripDeliveryTimemins.getText());

			}

			WebElement Freight$Del=driver.findElement(By.id("freightwbFrietPerDelivery"));
			String expectedFreight$Del=ExcelUtils.getCellData(128, colnum);
			String actualFreight$Del=Freight$Del.getText();

			if(actualFreight$Del.equalsIgnoreCase(expectedFreight$Del)){

				System.out.println("Freight $/Del. value is " + Freight$Del.getText());
				test1.pass("Freight $/Del. value is " + Freight$Del.getText());
			}

			else{

				System.out.println("Freight $/Del. value in workbench is not validated and value is  " + Freight$Del.getText());
				test1.fail("Freight $/Del. value in workbench is not validated and value is  " + Freight$Del.getText());

			}

			WebElement Freightevenue$Hour=driver.findElement(By.id("freightwblblTotalTimetooltip"));
			String expectedFreightevenue$Hour=ExcelUtils.getCellData(129, colnum);
			String actualFreightevenue$Hour=Freightevenue$Hour.getText();

			if(actualFreightevenue$Hour.equalsIgnoreCase(expectedFreightevenue$Hour)){

				System.out.println("Freight Revenue $/Hour value is " + Freightevenue$Hour.getText());
				test1.pass("Freight Revenue $/Hour value is " + Freightevenue$Hour.getText());
			}

			else{

				System.out.println("Freight Revenue $/Hour value in workbench is not validated and value is  " + Freightevenue$Hour.getText());
				test1.fail("Freight Revenue $/Hour value in workbench is not validated and value is  " + Freightevenue$Hour.getText());
			}


			WebElement RoundTripMiles=driver.findElement(By.id("freightwbloadedmiles"));
			String expectedRoundTripMiles=ExcelUtils.getCellData(130, colnum);
			String actualRoundTripMiles=RoundTripMiles.getText();

			if(actualRoundTripMiles.equalsIgnoreCase(expectedRoundTripMiles)){

				System.out.println("Round Trip Miles value is " + RoundTripMiles.getText());
				test1.pass("Round Trip Miles value is " + RoundTripMiles.getText());

			}

			else{

				System.out.println("Round Trip Miles value is not vaidated on workbench and value is " + RoundTripMiles.getText());
				test1.fail("Round Trip Miles value is not vaidated on workbench and value is " + RoundTripMiles.getText());

			}

			WebElement $perRoundTripMile=driver.findElement(By.id("freightwblblperloadedmiles"));
			String expected$perRoundTripMile=ExcelUtils.getCellData(131, colnum);
			String actual$perRoundTripMile=$perRoundTripMile.getText();

			if(actual$perRoundTripMile.equalsIgnoreCase(expected$perRoundTripMile)){

				System.out.println("$ per Round Trip Mile value is " + $perRoundTripMile.getText());
				test1.pass("$ per Round Trip Mile value is " + $perRoundTripMile.getText());

			}

			else{

				System.out.println("$ per Round Trip Mile value is not validated on workbench and value is " + $perRoundTripMile.getText());
				test1.fail("$ per Round Trip Mile value is not validated on workbench and value is " + $perRoundTripMile.getText());

			}

			WebElement PCMilerOneWayDistanceandTime=driver.findElement(By.xpath("//*[@id='lblDistanceFreight']"));
			String expectedPCMilerOneWayDistanceandTime=ExcelUtils.getCellData(132, colnum);
			String actualPCMilerOneWayDistanceandTime=PCMilerOneWayDistanceandTime.getText();

			if(actualPCMilerOneWayDistanceandTime.equalsIgnoreCase(expectedPCMilerOneWayDistanceandTime)){

				System.out.println("PC Miler One Way Distance and Time value is " + PCMilerOneWayDistanceandTime.getText());
				test1.pass("PC Miler One Way Distance and Time value is " + PCMilerOneWayDistanceandTime.getText());

			}

			else{

				System.out.println("PC Miler One Way Distance and Time is not validated on workbench and value is " + PCMilerOneWayDistanceandTime.getText());
				test1.fail("PC Miler One Way Distance and Time is not validated on workbench and value is " + PCMilerOneWayDistanceandTime.getText());

			}

		} catch (Exception e) {

		}		

	}

	@Test(priority =19)
	public  void Verify_FuelSurcharge_and_Accesorialfees_in_Workbench() throws Exception{

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='tabstripworkbench-4']/div[5]/div[1]/div/div[3]/label/span")));
		Thread.sleep(2000);

		ExtentTest test1 = extent.createTest("Verify_FuelSurcharge_and_Accesorialfees_in_Workbench", "Verify Fuel Surcharge and Accessorial Fees in Workbench");
		test1.log(Status.INFO, "Verify Fuel Surcharge and Accessorial Fees in Workbench");

		try {

			WebElement fuelsurcharge=driver.findElement(By.id("WorkdBenchFreightTerms_WorkbenchFuelSurchargeRate"));
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


			WebElement accessoriesfees=driver.findElement(By.id("lblwbAccservicefee"));
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

	@Test(priority =20)
	public  void Verify_Tolls_in_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Tolls_in_Workbench", "Verify Tolls data in Workbench");
		test1.log(Status.INFO, "Verify Tolls data in Workbench");

		try {

			for(int i=1,j=16; i<=3&&j<=18; i++,j++){

				WebElement verifytoll1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[9]"));
				String expectedverifytoll1value=ExcelUtils.getCellData(j, colnum);
				String actualverifytoll1=verifytoll1.getText();
				if(actualverifytoll1.equalsIgnoreCase(expectedverifytoll1value)){

					System.out.println("Toll" +i+" value in Workbench is " + verifytoll1.getText());
					test1.pass("Toll" +i+" value in Workbench is " + verifytoll1.getText());
				}
				else{


					System.out.println("Toll" +i+" is not validated in workbech and Values is " + verifytoll1.getText());
					test1.fail("Toll" +i+" is not validated in workbech and Values is " + verifytoll1.getText());

				}

			}

		} catch (Exception e) {


			test1.error(e.getMessage());
		}	

	}

	@Test(priority =21)
	public  void Verify_Freight_Per_Gallon_Values_in_WorkBench() throws Exception{

		//Verify Freight Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Freight_Per_Gallon_Values_in_WorkBench", "Verify Freight per gallon values data in Workbench");
		test1.log(Status.INFO, "Verify Freight per gallon values data in Workbench");


		try {

			for(int i=1,j=21; i<=9&&j<=29; i++,j++){

				WebElement verifyfreightpergallon1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[10]"));
				String expectedverifyfreightpergallon1value=ExcelUtils.getCellData(j, colnum);
				String actualverifyfreightpergallon1=verifyfreightpergallon1.getText();
				if(actualverifyfreightpergallon1.equalsIgnoreCase(expectedverifyfreightpergallon1value)){

					System.out.println("Freight Per Gallon" +i+" value in workbench is " + verifyfreightpergallon1.getText());
					test1.pass("Freight Per Gallon" +i+" value in workbench is " + verifyfreightpergallon1.getText());
				}
				else{


					System.out.println("Freight Per Gallon" +i+" is not validated in workbench and Values is " + verifyfreightpergallon1.getText());
					test1.fail("Freight Per Gallon" +i+" is not validated in workbench and Values is " + verifyfreightpergallon1.getText());
				}

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}


	@Test(priority =22)
	public  void Verify_Total_Charges_Frt_$_Per_Gall_in_Workbench() throws Exception{

		//Verify Total Charges Frt Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Total_Charges_Frt_$_Per_Gall_in_Workbench", "Verify Total charges Frt per gallon values data in Workbench");
		test1.log(Status.INFO, "Verify Total charges Frt per gallon values data in Workbench");

		try {

			for(int i=1,j=52; i<=9&&j<=60; i++,j++){

				WebElement totalcharge1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[17]"));
				String expectedtotalcharge1=ExcelUtils.getCellData(j, colnum);
				String actualverifytotalcharge1=totalcharge1.getText();
				if(actualverifytotalcharge1.equalsIgnoreCase(expectedtotalcharge1)){

					System.out.println("Total Charge Frt Per Gallon" +i+" Value in Workbench is "+ totalcharge1.getText());
					test1.pass("Total Charge Frt Per Gallon" +i+" Value in Workbench is "+ totalcharge1.getText());
				}
				else{


					System.out.println("Total Charge Frt Per Gallon" +i+" Value in Workbench is not validated and value is "+ totalcharge1.getText());
					test1.fail("Total Charge Frt Per Gallon" +i+" Value in Workbench is not validated and value is "+ totalcharge1.getText());
				}


			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}



	}

	@Test(priority =23)
	public  void Verify_$_Per_Period_Values_on_Workbench() throws Exception{

		//Verify $/Period Values on Workbench

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr[1]/td[20]")));
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("Verify_$_Per_Period_Values_on_Workbench", "Verify $/Period values data in Workbench");
		test1.log(Status.INFO, "Verify $/Period values data in Workbench");

		try {

			for(int i=1,j=62; i<=3&&j<=64; i++,j++){

				WebElement $perperiod1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[18]"));
				String expectedt$perperiod1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perperiod1=$perperiod1.getText();
				if(actualverify$perperiod1.equalsIgnoreCase(expectedt$perperiod1)){

					System.out.println("$/Period" +i+" Value in Workbench is "+ $perperiod1.getText());
					test1.pass("$/Period" +i+" Value in Workbench is "+ $perperiod1.getText());
				}
				else{


					System.out.println("$/Period" +i+" Value is Value in Workbench is not validated and value is "+ $perperiod1.getText());
					test1.fail("$/Period" +i+" Value is Value in Workbench is not validated and value is "+ $perperiod1.getText());

				}


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
				test1.fail("Total $/Period Value is not validated and value is "+ total$perperiodvalue.getText());

			}

		} catch (Exception e) {


			test1.error(e.getMessage());
		}



	}


	@Test(priority =24)
	public  void Verify_$_Per_del_Values_on_Workbench() throws Exception{

		//Verify $/Del Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_del_Values_on_Workbench", "Verify $/Del values data in Workbench");
		test1.log(Status.INFO, "Verify $/Del values data in Workbench");

		try {

			for(int i=1,j=67; i<=3&&j<=69; i++,j++){


				WebElement $perdel1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[19]"));
				String expectedt$perdel1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perdel1=$perdel1.getText();
				if(actualverify$perdel1.equalsIgnoreCase(expectedt$perdel1)){

					System.out.println("$/Del" +i+" Value in Workbench is "+ $perdel1.getText());
					test1.pass("$/Del" +i+" Value in Workbench is "+ $perdel1.getText());
				}
				else{


					System.out.println("$/Del" +i+" Value is Value is not validated and value is "+ $perdel1.getText());
					test1.fail("$/Del" +i+" Value is Value is not validated and value is "+ $perdel1.getText());

				}

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

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}


	@Test(priority =25)
	public  void Verify_$_Per_Gal_Values_on_Workbench() throws Exception{

		//Verify $/Gal Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_Gal_Values_on_Workbench", "Verify $/Gal values data in Workbench");
		test1.log(Status.INFO, "Verify $/Gal values data in Workbench");

		try {

			for(int i=1,j=72; i<=3&&j<=74; i++,j++){

				WebElement $pergal1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[20]"));
				String expectedt$pergal1=ExcelUtils.getCellData(j, colnum);
				String actualverify$pergal1=$pergal1.getText();
				if(actualverify$pergal1.equalsIgnoreCase(expectedt$pergal1)){

					System.out.println("$/Gal" +i+" Value in Workbench is "+ $pergal1.getText());
					test1.pass("$/Gal" +i+" Value in Workbench is "+ $pergal1.getText());
				}
				else{


					System.out.println("$/Gal" +i+ " Value is Value is not validated and value is "+ $pergal1.getText());
					test1.fail("$/Gal" +i+ " Value is Value is not validated and value is "+ $pergal1.getText());

				}

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

		} catch (Exception e) {

			test1.error(e.getMessage());
		}



		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnworkbenchClose")));

	}


	@Test(priority =26)
	public  void Close_Workbench() throws Exception{

		ExtentTest test1 = extent.createTest("Close_Workbench", "Close Workbench");
		test1.log(Status.INFO, "Close Workbench");

		WebElement close=driver.findElement(By.id("btnworkbenchClose"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", close);
		Thread.sleep(2000);
		test1.pass("Close Workbench");
		//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


	}



	@Test(priority =27)
	public  void Award_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Award_Quote", "Award Quote for seller");
		test1.log(Status.INFO, "Award Quote for seller");


		try {

			//Click Contigent Award
			WebElement status=driver.findElement(By.linkText("Contingent Award"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", status);
			Thread.sleep(2000);
			//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

			//wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			test1.pass("Click Contigent Status");

			//Click Yes in pop-up
			WebElement statusyes=driver.findElement(By.id("jqDialog_yes"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", statusyes);
			Thread.sleep(2000);
			//wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			test1.pass("Click on Yes in pop-up");

			//Click yes in Award pop-up
			WebElement awardedyes=driver.findElement(By.id("jqDialog_yes"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", awardedyes);

			WebDriverWait wait0 = new WebDriverWait(driver, 120);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			test1.pass("Click on Yes for Award Yes");

			//click Won
			WebElement won=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[12]/a[1]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", won);
			Thread.sleep(3000);
			//wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			test1.pass("Click on Won button");

			//Click Yes in Confimration
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("/html/body/div[50]/div[2]/button[1]")));
			Thread.sleep(3000);
			//wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			test1.pass("Click on Yes in pop-up");

			//Click Ok in Alert
			WebElement awardedok=driver.findElement(By.xpath("/html/body/div[50]/div[2]/button[3]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", awardedok);
			Thread.sleep(2000);
			//wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			test1.pass("Click on Award Ok");


			WebDriverWait wait2 = new WebDriverWait(driver, 100);
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));


		} catch (Exception e) {

			test1.error(e.getMessage());

		}



	}

	@Test(priority =28)
	public  void Verify_Quote_in_Awarded_Tab() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Quote_in_Awarded_Tab", "Verify Awarded quote in Award tab");
		test1.log(Status.INFO, "Verify Awarded quote in Award tab");

		try {


			/*((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[2]/div/div/div/div[2]/div/ul/li[3]/span[2]")));
			Thread.sleep(2000);*/

			int ok_size=driver.findElements(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div[1]/div/div[1]/div/a/span/i")).size();
			driver.findElements(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div[1]/div/div[1]/div/a/span/i")).get(ok_size-1).click();
			Thread.sleep(3000);

			WebElement queryforrecord=driver.findElement(By.id("awardedRFPSearch"));
			queryforrecord.sendKeys(ExcelUtils.getCellData(161, colnum));
			queryforrecord.sendKeys(Keys.CONTROL,"a");
			test1.info("Enter Awarded Quote as " + ExcelUtils.getCellData(161, colnum));

			try {

				if (driver.findElement(By.xpath("//*[@id='AwardedRFPGrid']/div[3]/table/tbody/tr/td[3]")).isDisplayed()) {

					WebElement activequotedata=driver.findElement(By.xpath("//*[@id='AwardedRFPGrid']/div[3]/table/tbody/tr/td[3]"));
					String excpected_active_quote=ExcelUtils.getCellData(161, colnum);
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


				System.out.println("Quote is not in Active Tab");
				test1.fail("Quote is not in Active Tab");

			} 

		} catch (Exception e) {
			test1.error(e.getMessage());
		}




	}


	@Test(priority =29)
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
			//Thread.sleep(15000);

			WebDriverWait wait0 = new WebDriverWait(driver, 100);
			wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));


		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}


	@Test(priority =30)
	public  void Verify_awarded_Quote_Status() throws Exception{


		ExtentTest test1 = extent.createTest("Verify_awarded_Quote_Status", "Verify awarded Quote status");
		test1.log(Status.INFO, "Verify awarded Quote status");


		try {

			WebElement quotestatus=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[12]"));
			String excpected_active_quote="Won";
			String actual_active_quote=quotestatus.getText();
			if(excpected_active_quote.equals(actual_active_quote)){


				System.out.println("Quote status is "+ quotestatus.getText());
				test1.pass("Quote status is "+ quotestatus.getText());
			}
			else{


				System.out.println("Quote status is not "+ quotestatus.getText());
				test1.fail("Quote status is not "+ quotestatus.getText());
			}


		} catch (Exception e) {

			test1.error(e.getMessage());
		}




	}

	@Test(priority =31)
	public  void Open_TandC_for_Awarded_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Open_View_in_TandC", "Open View in TandC");
		test1.log(Status.INFO, "Open View in TandC");

		try {

			WebElement tandcview=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[33]/a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", tandcview);
			test1.info("View in TandC in Manaage Rouds is Opened");


		} catch (Exception e) {


			test1.error(e.getMessage());
		}


	}

	@Test(priority =32)
	public  void Verify_FuelSurcharge_and_Accesorialfees_in_TandC_for_Awarded_Quote() throws Exception{



		WebDriverWait wait0 = new WebDriverWait(driver, 100);
		wait0.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='expandallfreight']/label[1]")));
		Thread.sleep(2000);

		ExtentTest test1 = extent.createTest("Verify_FuelSurcharge_and_Accessorial_Service_Fees_(Per Delivery)_in_TandC_for_Awarded_Quote", "Verify Fuel Surcharge and Accessorial Service Fees (Per Delivery) : in TandC");
		test1.log(Status.INFO, "Verify Fuel Surcharge and Accessorial Fees in TandC");

		try {

			WebElement expandall=driver.findElement(By.xpath("//*[@id='expandallfreight']/label[1]"));
			expandall.click();
			Thread.sleep(2000);
			test1.info("Click Expand All");

			WebElement fuelsurcharge=driver.findElement(By.id("FreightTerms_FuelSurchargeRate"));
			String expectedfuelsurchargevalue=ExcelUtils.getCellData(30, colnum);
			String actualfuelsurchargegal=fuelsurcharge.getAttribute("value");

			if(actualfuelsurchargegal.equalsIgnoreCase(expectedfuelsurchargevalue)){

				System.out.println("Fuel Surcharge (%) value in TandC is " + fuelsurcharge.getAttribute("value"));
				test1.pass("Fuel Surcharge (%) value in TandC is " + fuelsurcharge.getAttribute("value"));
			}
			else{


				System.out.println("Fuel Surcharge is not validated and value in TandC is " + fuelsurcharge.getAttribute("value"));
				test1.fail("Fuel Surcharge is not validated and value in TandC is " + fuelsurcharge.getAttribute("value"));

			}


			WebElement accessoriesfees=driver.findElement(By.id("lblAccservicefee"));
			String expectedfuelaccessoriesfees=ExcelUtils.getCellData(36, colnum);
			String actualaccessoriesfees=accessoriesfees.getText();
			if(actualaccessoriesfees.equalsIgnoreCase(expectedfuelaccessoriesfees)){

				System.out.println("Accessorial Service Fees (Per Delivery) : value in TandC is " + accessoriesfees.getText());
				test1.pass("Accessorial Service Fees (Per Delivery) : value in TandC is " + accessoriesfees.getText());
			}
			else{


				System.out.println("Accessorial Fees value in TandC is not validated and value is " + accessoriesfees.getText());
				test1.fail("Accessorial Fees value in TandC is not validated and value is " + accessoriesfees.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}




	}	



	@Test(priority =33)
	public  void verify_Tolls_data_on_tandc_for_Awarded_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("verify_Tolls_data_on_tandc_for_Awarded_Quote", "Verify Tolls in TandC");
		test1.log(Status.INFO, "Verify Tolls in TandC");

		try {

			WebElement optionalcolumns=driver.findElement(By.xpath("//*[@id='priceindexSeller']/div[1]/div/div[3]/label/span"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionalcolumns);
			//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);
			Thread.sleep(2000);

			WebElement targetlow=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr[1]/td[20]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetlow);


			for(int i=1,j=16; i<=3&&j<=18; i++,j++){

				WebElement verifytoll=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[8]"));
				String expectedverifytoll1value=ExcelUtils.getCellData(j, colnum);
				String actualverifytoll1=verifytoll.getText();
				if(actualverifytoll1.equalsIgnoreCase(expectedverifytoll1value)){

					System.out.println("Toll" +i+" value in TandC is " + verifytoll.getText());
					test1.pass("Toll" +i+" value in TandC is " + verifytoll.getText());
				}
				else{


					System.out.println("Toll" +i+" value is not validated in TandC is " + verifytoll.getText());
					test1.fail("Toll" +i+" value is not validated in TandC is " + verifytoll.getText());

				}

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}





	}



	@Test(priority =34)
	public  void Verify_Freight_Per_Gallon_Values_in_TandC_for_Awarded_quote() throws Exception{

		//Verify Freight Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Freight_Per_Gallon_Values_in_TandC_for_Awarded_quote_for_Awarded_Quote", "Verify Freight per gallon values in TandC");
		test1.log(Status.INFO, "Verify Freight per gallon values in TandC");

		try {

			for(int i=1,j=21; i<=9&&j<=29; i++,j++){

				WebElement verifyfreightpergallon=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[9]"));
				String expectedverifyfreightpergallon1value=ExcelUtils.getCellData(j, colnum);
				String actualverifyfreightpergallon1=verifyfreightpergallon.getText();

				if(actualverifyfreightpergallon1.equalsIgnoreCase(expectedverifyfreightpergallon1value)){

					System.out.println("Freight Per Gallon" +i+" value in TandC is " + verifyfreightpergallon.getText());
					test1.pass("Freight Per Gallon" +i+" value in TandC is " + verifyfreightpergallon.getText());
				}
				else{


					System.out.println("Freight Per Gallon" +i+" value is not validated in TandC is " + verifyfreightpergallon.getText());
					test1.fail("Freight Per Gallon" +i+" value is not validated in TandC is " + verifyfreightpergallon.getText());

				}

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}





	}

	@Test(priority =35)
	public  void Verify_Total_Charges_Frt_$_Per_Gall_in_TandC_for_Awarded_quote() throws Exception{

		//Verify Total Charges Frt Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Total_Charges_Frt_$_Per_Gall_in_TandC_for_Awarded_Quote", "Verify Total Charges Frt Per Gallons in TandC");
		test1.log(Status.INFO, "Verify Total Charges Frt Per Gallons in TandC");

		try {

			for(int i=1,j=52; i<=9&&j<=60; i++,j++){

				WebElement totalcharge1=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[16]"));
				String expectedtotalcharge1=ExcelUtils.getCellData(j, colnum);
				String actualverifytotalcharge1=totalcharge1.getText();
				if(actualverifytotalcharge1.equalsIgnoreCase(expectedtotalcharge1)){

					System.out.println("Total Charge Frt Per Gallon" +i+" Value in TandC is "+ totalcharge1.getText());
					test1.pass("Total Charge Frt Per Gallon" +i+" Value in TandC is "+ totalcharge1.getText());
				}
				else{


					System.out.println("Total Charge Frt Per Gallon" +i+" Value is not validated in TandC is "+ totalcharge1.getText());
					test1.fail("Total Charge Frt Per Gallon" +i+" Value is not validated in TandC is "+ totalcharge1.getText());

				}


			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =36)
	public  void Verify_$_Per_Period_Values_on_TandC_for_Awarded_quote() throws Exception{

		//Verify $/Period Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_Period_Values_on_TandC_for_Awarded_Quote", "Verify $/Period values on TandC");
		test1.log(Status.INFO, "Verify $/Period values on TandC");

		try {

			for(int i=1,j=62; i<=3&&j<=64; i++,j++){


				WebElement $perperiod1=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[17]"));
				String expectedt$perperiod1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perperiod1=$perperiod1.getText();

				if(actualverify$perperiod1.equalsIgnoreCase(expectedt$perperiod1)){

					System.out.println("$/Period" +i+" Value in TandC is "+ $perperiod1.getText());
					test1.pass("$/Period" +i+" Value in TandC is "+ $perperiod1.getText());
				}
				else{


					System.out.println("$/Period" +i+" Value is not validated in TandC is "+ $perperiod1.getText());
					test1.fail("$/Period" +i+" Value is not validated in TandC is "+ $perperiod1.getText());

				}

			}

			WebElement total$perperiodvalue=driver.findElement(By.id("frtlodftrgal"));
			String expectedttotal$perperiodvalue=ExcelUtils.getCellData(65, colnum);
			String actualverifytotal$perperiodvalue=total$perperiodvalue.getText();
			if(actualverifytotal$perperiodvalue.equalsIgnoreCase(expectedttotal$perperiodvalue)){

				System.out.println("Total $/Period Value in TandC is "+ total$perperiodvalue.getText());
				test1.pass("Total $/Period Value in TandC is "+ total$perperiodvalue.getText());
			}
			else{


				System.out.println("Total $/Period Value in TandC is not validated and value is "+ total$perperiodvalue.getText());
				test1.fail("Total $/Period Value in TandC is not validated and value is "+ total$perperiodvalue.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}





	}

	@Test(priority =37)
	public  void Verify_$_Per_del_Values_on_TandC_for_Awarded_quote() throws Exception{

		//Verify $/Del Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_del_Values_on_TandC_for_Awarded_Quote", "Verify $/Del values on TandC");
		test1.log(Status.INFO, "Verify $/Del values on TandC");

		try {

			for(int i=1,j=67; i<=3&&j<=69; i++,j++){

				WebElement $perdel1=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[18]"));
				String expectedt$perdel1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perdel1=$perdel1.getText();
				if(actualverify$perdel1.equalsIgnoreCase(expectedt$perdel1)){

					System.out.println("$/Del" +i+"  Value in TandC is "+ $perdel1.getText());
					test1.pass("$/Del" +i+"  Value in TandC is "+ $perdel1.getText());
				}
				else{


					System.out.println("$/Del" +i+ " Value is Value in TandC is not validated and value is "+ $perdel1.getText());
					test1.fail("$/Del" +i+ " Value is Value in TandC is not validated and value is "+ $perdel1.getText());

				}

			}


			WebElement total$perdelvalue=driver.findElement(By.id("frtperdel"));
			String expectedttotal$perdelvalue=ExcelUtils.getCellData(70, colnum);
			String actualverifytotal$perdelvalue=total$perdelvalue.getText();
			if(actualverifytotal$perdelvalue.equalsIgnoreCase(expectedttotal$perdelvalue)){

				System.out.println("Toral $/Del  Value in TandC is "+ total$perdelvalue.getText());
				test1.pass("Toral $/Del  Value in TandC is "+ total$perdelvalue.getText());
			}
			else{


				System.out.println("Total $/Del Value in TandC not validated and value is "+ total$perdelvalue.getText());
				test1.fail("Total $/Del Value in TandC not validated and value is "+ total$perdelvalue.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =38)
	public  void Verify_$_Per_Gal_Values_on_TandC_for_Awarded_quote() throws Exception{

		//Verify $/Gal Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_Gal_Values_on_TandC_for_Awarded_Quote", "Verify $/Gal values on TandC");
		test1.log(Status.INFO, "Verify $/Gal values on TandC");

		try {

			for(int i=1,j=72; i<=3&&j<=74; i++,j++){

				WebElement $pergal1=driver.findElement(By.xpath("//*[@id='ProductsFreightSellerGrid']/div[3]/table/tbody/tr["+i+"]/td[19]"));
				String expectedt$pergal1=ExcelUtils.getCellData(j, colnum);
				String actualverify$pergal1=$pergal1.getText();
				if(actualverify$pergal1.equalsIgnoreCase(expectedt$pergal1)){

					System.out.println("$/Gal" +i+" Value in TandC is "+ $pergal1.getText());
					test1.pass("$/Gal" +i+" Value in TandC is "+ $pergal1.getText());
				}
				else{


					System.out.println("$/Gal" +i+" Value in TandC is not validated and value is "+ $pergal1.getText());
					test1.fail("$/Gal" +i+" Value in TandC is not validated and value is "+ $pergal1.getText());

				}


			}


			WebElement total$pergalvalue=driver.findElement(By.id("frtrankingbasisgal"));
			String expectedttotal$pergalvalue=ExcelUtils.getCellData(75, colnum);
			String actualverifytotal$pergalvalue=total$pergalvalue.getText();
			if(actualverifytotal$pergalvalue.equalsIgnoreCase(expectedttotal$pergalvalue)){

				System.out.println("Total $/Gal Value in TandC is "+ total$pergalvalue.getText());
				test1.pass("Total $/Gal Value in TandC is "+ total$pergalvalue.getText());
			}
			else{


				System.out.println("Total $/Gal Value in TandC is not validated and value is "+ total$pergalvalue.getText());
				test1.fail("Total $/Gal Value in TandC is not validated and value is "+ total$pergalvalue.getText());

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}





	}



	@Test(priority =39)
	public  void Verify_finaltotal_and_RankingBasis_Values_in_TandC_for_Awarded_quote_for_Awarded_Quote() throws Exception{

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("ldlfinaltotal")));
		//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("Verify_finaltotal_and_RankingBasis_Values_in_TandC", "Verify Final Total and Ranking Basis Values in TandC");
		test1.log(Status.INFO, "Verify Final Total and Ranking Basis Values in TandC");

		try {

			WebElement finaltotal=driver.findElement(By.id("ldlfinaltotal"));
			System.out.println("Final Total value is " + finaltotal.getText());

			WebElement closetandc=driver.findElement(By.id("btnClose"));
			closetandc.click();
			Thread.sleep(3000);
			//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("createRFP_RFPEndDate")));


			WebElement rankingbasis=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[15]"));
			System.out.println("Ranking basis has value as " + rankingbasis.getText());

			String excepted=ExcelUtils.getCellData(134, colnum);
			String actual=rankingbasis.getText();

			if(actual.equalsIgnoreCase(excepted)){


				System.out.println("Total Final and Ranking Basis in TandC are same and validated and values are " + rankingbasis.getText());
				test1.pass("Total Final and Ranking Basis in TandC are same and validated and values are " + rankingbasis.getText());
			}
			else{


				System.out.println("Total Final and Ranking Basis are not same in TandC and validated");
				test1.fail("Total Final and Ranking Basis are not same in TandC and validated");

			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}





	}


	@Test(priority =40)
	public  void Open_Workbench_for_Awarded_quote() throws Exception{

		ExtentTest test1 = extent.createTest("Open_Workbench_for_Awarded_Quote", "Open Workbench View");
		test1.log(Status.INFO, "Open Workbench View");

		WebElement workbench=driver.findElement(By.xpath("//*[@id='ReviewRFPSubGrid']/div[3]/table/tbody/tr/td[32]/a"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", workbench);

		test1.info("Click Workbench View in Manage Rounds");

	}	

	@Test(priority =41)
	public  void Verify_Buyer_Calculations_on_Workbench_for_Awarded_quote() throws Exception{

		WebDriverWait wait1 = new WebDriverWait(driver, 100);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='expandallfrtwb']/label[1]")));
		//Thread.sleep(4000);
		
		WebDriverWait wait11 = new WebDriverWait(driver, 100);
		wait11.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
		Thread.sleep(4000);

		ExtentTest test1 = extent.createTest("Verify_Buyer_Calculations_on_Workbench_for_Awarded_Quote", "Verify Buyer Calculations on Workbench");
		test1.log(Status.INFO, "Verify Buyer Calculations on Workbench");

		try {

			WebElement wbexpandall=driver.findElement(By.xpath("//*[@id='expandallfrtwb']/label[1]"));
			wbexpandall.click();
			Thread.sleep(2000);

			WebElement TruckDroporPumpRateGalspermins=driver.findElement(By.id("WorkdBenchFreightTerms_GalsPerMinutePump"));
			String expectedTruckDroporPumpRateGalspermins=ExcelUtils.getCellData(122, colnum);
			String actualTruckDroporPumpRateGalspermins=TruckDroporPumpRateGalspermins.getAttribute("value");

			if(actualTruckDroporPumpRateGalspermins.equalsIgnoreCase(expectedTruckDroporPumpRateGalspermins)){

				System.out.println("Truck Drop or Pump Rate (Gals per mins) value is "+ TruckDroporPumpRateGalspermins.getAttribute("value"));
				test1.pass("Truck Drop or Pump Rate (Gals per mins) value is "+ TruckDroporPumpRateGalspermins.getAttribute("value"));
			}

			else{

				System.out.println("Truck Drop or Pump Rate (Gals per mins) value is not validated in Workbench and value is  "+ TruckDroporPumpRateGalspermins.getAttribute("value"));
				test1.fail("Truck Drop or Pump Rate (Gals per mins) value is not validated in Workbench and value is  "+ TruckDroporPumpRateGalspermins.getAttribute("value"));

			}

			WebElement RoundTripTime=driver.findElement(By.id("WorkdBenchFreightTerms_StemLoadingTime"));
			String expectedRoundTripTime=ExcelUtils.getCellData(123, colnum);
			String actualRoundTripTime=RoundTripTime.getAttribute("value");

			if(actualRoundTripTime.equalsIgnoreCase(expectedRoundTripTime)){

				System.out.println("Round Trip Time value is "+ RoundTripTime.getAttribute("value"));
				test1.pass("Round Trip Time value is "+ RoundTripTime.getAttribute("value"));
			}

			else{

				System.out.println("Round Trip Time value is not validated in workbench and value is "+ RoundTripTime.getAttribute("value"));
				test1.fail("Round Trip Time value is not validated in workbench and value is "+ RoundTripTime.getAttribute("value"));

			}

			WebElement TerminalLoadingTime=driver.findElement(By.id("WorkdBenchFreightTerms_LoadingTime"));
			String expectedTerminalLoadingTime=ExcelUtils.getCellData(124, colnum);
			String actualTerminalLoadingTime=TerminalLoadingTime.getAttribute("value");

			if(actualTerminalLoadingTime.equalsIgnoreCase(expectedTerminalLoadingTime)){

				System.out.println("Terminal Loading Time value is " + TerminalLoadingTime.getAttribute("value"));
				test1.pass("Terminal Loading Time value is " + TerminalLoadingTime.getAttribute("value"));

			}

			else{

				System.out.println("Terminal Loading Time value is not validated and value is " + TerminalLoadingTime.getAttribute("value"));
				test1.fail("Terminal Loading Time value is not validated and value is " + TerminalLoadingTime.getAttribute("value"));

			}

			WebElement AvgFuelingDroporPumpOffTimemins=driver.findElement(By.id("freightwbavgFuelingtime"));
			String expectedAvgFuelingDroporPumpOffTimemins=ExcelUtils.getCellData(125, colnum);
			String actualAvgFuelingDroporPumpOffTimemins=AvgFuelingDroporPumpOffTimemins.getText();

			if(actualAvgFuelingDroporPumpOffTimemins.equalsIgnoreCase(expectedAvgFuelingDroporPumpOffTimemins)){

				System.out.println("Avg. Fueling Drop or Pump Off Time (mins) value is " + AvgFuelingDroporPumpOffTimemins.getText());
				test1.pass("Avg. Fueling Drop or Pump Off Time (mins) value is " + AvgFuelingDroporPumpOffTimemins.getText());

			}

			else{

				System.out.println("Avg. Fueling Drop or Pump Off Time (mins) value in workbench is not validated and value is " + AvgFuelingDroporPumpOffTimemins.getText());
				test1.fail("Avg. Fueling Drop or Pump Off Time (mins) value in workbench is not validated and value is " + AvgFuelingDroporPumpOffTimemins.getText());

			}

			WebElement GallonsDropped=driver.findElement(By.id("freightwbgallonsdropped"));
			String expectedGallonsDropped=ExcelUtils.getCellData(126, colnum);
			String actualGallonsDropped=GallonsDropped.getText();

			if(actualGallonsDropped.equalsIgnoreCase(expectedGallonsDropped)){

				System.out.println("Gallons Dropped value is " + GallonsDropped.getText());
				test1.pass("Gallons Dropped value is " + GallonsDropped.getText());

			}

			else{

				System.out.println("Gallons Dropped value is not validated in workbench and value is " + GallonsDropped.getText());
				test1.fail("Gallons Dropped value is not validated in workbench and value is " + GallonsDropped.getText());

			}

			WebElement TotalRoundTripDeliveryTimemins=driver.findElement(By.id("freightwblblTimePerDelivery"));
			String expectedTotalRoundTripDeliveryTimemins=ExcelUtils.getCellData(127, colnum);
			String actualTotalRoundTripDeliveryTimemins=TotalRoundTripDeliveryTimemins.getText();

			if(actualTotalRoundTripDeliveryTimemins.equalsIgnoreCase(expectedTotalRoundTripDeliveryTimemins)){

				System.out.println("Total Round Trip Delivery Time (mins) value is " + TotalRoundTripDeliveryTimemins.getText());
				test1.pass("Total Round Trip Delivery Time (mins) value is " + TotalRoundTripDeliveryTimemins.getText());

			}

			else{

				System.out.println("Total Round Trip Delivery Time (mins) value in workbench is not validated and value is " + TotalRoundTripDeliveryTimemins.getText());
				test1.fail("Total Round Trip Delivery Time (mins) value in workbench is not validated and value is " + TotalRoundTripDeliveryTimemins.getText());

			}

			WebElement Freight$Del=driver.findElement(By.id("freightwbFrietPerDelivery"));
			String expectedFreight$Del=ExcelUtils.getCellData(128, colnum);
			String actualFreight$Del=Freight$Del.getText();

			if(actualFreight$Del.equalsIgnoreCase(expectedFreight$Del)){

				System.out.println("Freight $/Del. value is " + Freight$Del.getText());
				test1.pass("Freight $/Del. value is " + Freight$Del.getText());
			}

			else{

				System.out.println("Freight $/Del. value in workbench is not validated and value is  " + Freight$Del.getText());
				test1.fail("Freight $/Del. value in workbench is not validated and value is  " + Freight$Del.getText());

			}

			WebElement Freightevenue$Hour=driver.findElement(By.id("freightwblblTotalTimetooltip"));
			String expectedFreightevenue$Hour=ExcelUtils.getCellData(129, colnum);
			String actualFreightevenue$Hour=Freightevenue$Hour.getText();

			if(actualFreightevenue$Hour.equalsIgnoreCase(expectedFreightevenue$Hour)){

				System.out.println("Freight Revenue $/Hour value is " + Freightevenue$Hour.getText());
				test1.pass("Freight Revenue $/Hour value is " + Freightevenue$Hour.getText());
			}

			else{

				System.out.println("Freight Revenue $/Hour value in workbench is not validated and value is  " + Freightevenue$Hour.getText());
				test1.fail("Freight Revenue $/Hour value in workbench is not validated and value is  " + Freightevenue$Hour.getText());
			}


			WebElement RoundTripMiles=driver.findElement(By.id("freightwbloadedmiles"));
			String expectedRoundTripMiles=ExcelUtils.getCellData(130, colnum);
			String actualRoundTripMiles=RoundTripMiles.getText();

			if(actualRoundTripMiles.equalsIgnoreCase(expectedRoundTripMiles)){

				System.out.println("Round Trip Miles value is " + RoundTripMiles.getText());
				test1.pass("Round Trip Miles value is " + RoundTripMiles.getText());

			}

			else{

				System.out.println("Round Trip Miles value is not vaidated on workbench and value is " + RoundTripMiles.getText());
				test1.fail("Round Trip Miles value is not vaidated on workbench and value is " + RoundTripMiles.getText());

			}

			WebElement $perRoundTripMile=driver.findElement(By.id("freightwblblperloadedmiles"));
			String expected$perRoundTripMile=ExcelUtils.getCellData(131, colnum);
			String actual$perRoundTripMile=$perRoundTripMile.getText();

			if(actual$perRoundTripMile.equalsIgnoreCase(expected$perRoundTripMile)){

				System.out.println("$ per Round Trip Mile value is " + $perRoundTripMile.getText());
				test1.pass("$ per Round Trip Mile value is " + $perRoundTripMile.getText());

			}

			else{

				System.out.println("$ per Round Trip Mile value is not validated on workbench and value is " + $perRoundTripMile.getText());
				test1.fail("$ per Round Trip Mile value is not validated on workbench and value is " + $perRoundTripMile.getText());

			}

			WebElement PCMilerOneWayDistanceandTime=driver.findElement(By.xpath("//*[@id='lblDistanceFreight']"));
			String expectedPCMilerOneWayDistanceandTime=ExcelUtils.getCellData(132, colnum);
			String actualPCMilerOneWayDistanceandTime=PCMilerOneWayDistanceandTime.getText();

			if(actualPCMilerOneWayDistanceandTime.equalsIgnoreCase(expectedPCMilerOneWayDistanceandTime)){

				System.out.println("PC Miler One Way Distance and Time value is " + PCMilerOneWayDistanceandTime.getText());
				test1.pass("PC Miler One Way Distance and Time value is " + PCMilerOneWayDistanceandTime.getText());

			}

			else{

				System.out.println("PC Miler One Way Distance and Time is not validated on workbench and value is " + PCMilerOneWayDistanceandTime.getText());
				test1.fail("PC Miler One Way Distance and Time is not validated on workbench and value is " + PCMilerOneWayDistanceandTime.getText());

			}

		} catch (Exception e) {

		}


	}

	@Test(priority =42)
	public  void Verify_FuelSurcharge_and_Accesorialfees_in_Workbench_for_Awarded_quote() throws Exception{

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='tabstripworkbench-4']/div[5]/div[1]/div/div[3]/label/span")));
		Thread.sleep(2000);

		ExtentTest test1 = extent.createTest("Verify_FuelSurcharge_and_Accesorialfees_in_Workbench_for_Awarded_Quote", "Verify Fuel Surcharge and Accessorial Fees in Workbench");
		test1.log(Status.INFO, "Verify Fuel Surcharge and Accessorial Fees in Workbench");

		try {

			WebElement fuelsurcharge=driver.findElement(By.id("WorkdBenchFreightTerms_WorkbenchFuelSurchargeRate"));
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


			WebElement accessoriesfees=driver.findElement(By.id("lblwbAccservicefee"));
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

	@Test(priority =43)
	public  void Verify_Tolls_in_Workbench_for_Awarded_quote() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Tolls_in_Workbench_for_Awarded_Quote", "Verify Tolls data in Workbench");
		test1.log(Status.INFO, "Verify Tolls data in Workbench");

		try {

			for(int i=1,j=16; i<=3&&j<=18; i++,j++){

				WebElement verifytoll1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[9]"));
				String expectedverifytoll1value=ExcelUtils.getCellData(j, colnum);
				String actualverifytoll1=verifytoll1.getText();
				if(actualverifytoll1.equalsIgnoreCase(expectedverifytoll1value)){

					System.out.println("Toll" +i+" value in Workbench is " + verifytoll1.getText());
					test1.pass("Toll" +i+" value in Workbench is " + verifytoll1.getText());
				}
				else{


					System.out.println("Toll" +i+" is not validated in workbech and Values is " + verifytoll1.getText());
					test1.fail("Toll" +i+" is not validated in workbech and Values is " + verifytoll1.getText());

				}

			}

		} catch (Exception e) {


			test1.error(e.getMessage());
		}	

	}

	@Test(priority =44)
	public  void Verify_Freight_Per_Gallon_Values_in_WorkBench_for_Awarded_quote() throws Exception{

		//Verify Freight Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Freight_Per_Gallon_Values_in_WorkBench_for_Awarded_Quote", "Verify Freight per gallon values data in Workbench");
		test1.log(Status.INFO, "Verify Freight per gallon values data in Workbench");


		try {

			for(int i=1,j=21; i<=9&&j<=29; i++,j++){

				WebElement verifyfreightpergallon1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[10]"));
				String expectedverifyfreightpergallon1value=ExcelUtils.getCellData(j, colnum);
				String actualverifyfreightpergallon1=verifyfreightpergallon1.getText();
				if(actualverifyfreightpergallon1.equalsIgnoreCase(expectedverifyfreightpergallon1value)){

					System.out.println("Freight Per Gallon" +i+" value in workbench is " + verifyfreightpergallon1.getText());
					test1.pass("Freight Per Gallon" +i+" value in workbench is " + verifyfreightpergallon1.getText());
				}
				else{


					System.out.println("Freight Per Gallon" +i+" is not validated in workbench and Values is " + verifyfreightpergallon1.getText());
					test1.fail("Freight Per Gallon" +i+" is not validated in workbench and Values is " + verifyfreightpergallon1.getText());
				}

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}


	@Test(priority =45)
	public  void Verify_Total_Charges_Frt_$_Per_Gall_in_Workbench_for_Awarded_quote() throws Exception{

		//Verify Total Charges Frt Per Gallon

		ExtentTest test1 = extent.createTest("Verify_Total_Charges_Frt_$_Per_Gall_in_Workbench_for_Awarded_Quote", "Verify Total charges Frt per gallon values data in Workbench");
		test1.log(Status.INFO, "Verify Total charges Frt per gallon values data in Workbench");

		try {

			for(int i=1,j=52; i<=9&&j<=60; i++,j++){

				WebElement totalcharge1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[17]"));
				String expectedtotalcharge1=ExcelUtils.getCellData(j, colnum);
				String actualverifytotalcharge1=totalcharge1.getText();
				if(actualverifytotalcharge1.equalsIgnoreCase(expectedtotalcharge1)){

					System.out.println("Total Charge Frt Per Gallon" +i+" Value in Workbench is "+ totalcharge1.getText());
					test1.pass("Total Charge Frt Per Gallon" +i+" Value in Workbench is "+ totalcharge1.getText());
				}
				else{


					System.out.println("Total Charge Frt Per Gallon" +i+" Value in Workbench is not validated and value is "+ totalcharge1.getText());
					test1.fail("Total Charge Frt Per Gallon" +i+" Value in Workbench is not validated and value is "+ totalcharge1.getText());
				}


			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}



	}

	@Test(priority =46)
	public  void Verify_$_Per_Period_Values_on_Workbench_for_Awarded_quote() throws Exception{

		//Verify $/Period Values on Workbench

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr[1]/td[20]")));
		VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);

		ExtentTest test1 = extent.createTest("Verify_$_Per_Period_Values_on_Workbench_for_Awarded_Quote", "Verify $/Period values data in Workbench");
		test1.log(Status.INFO, "Verify $/Period values data in Workbench");

		try {

			for(int i=1,j=62; i<=3&&j<=64; i++,j++){

				WebElement $perperiod1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[18]"));
				String expectedt$perperiod1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perperiod1=$perperiod1.getText();
				if(actualverify$perperiod1.equalsIgnoreCase(expectedt$perperiod1)){

					System.out.println("$/Period" +i+" Value in Workbench is "+ $perperiod1.getText());
					test1.pass("$/Period" +i+" Value in Workbench is "+ $perperiod1.getText());
				}
				else{


					System.out.println("$/Period" +i+" Value is Value in Workbench is not validated and value is "+ $perperiod1.getText());
					test1.fail("$/Period" +i+" Value is Value in Workbench is not validated and value is "+ $perperiod1.getText());

				}


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
				test1.fail("Total $/Period Value is not validated and value is "+ total$perperiodvalue.getText());

			}

		} catch (Exception e) {


			test1.error(e.getMessage());
		}



	}


	@Test(priority =47)
	public  void Verify_$_Per_del_Values_on_Workbench_for_Awarded_quote() throws Exception{

		//Verify $/Del Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_del_Values_on_Workbench_for_Awarded_Quote", "Verify $/Del values data in Workbench");
		test1.log(Status.INFO, "Verify $/Del values data in Workbench");

		try {

			for(int i=1,j=67; i<=3&&j<=69; i++,j++){


				WebElement $perdel1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[19]"));
				String expectedt$perdel1=ExcelUtils.getCellData(j, colnum);
				String actualverify$perdel1=$perdel1.getText();
				if(actualverify$perdel1.equalsIgnoreCase(expectedt$perdel1)){

					System.out.println("$/Del" +i+" Value in Workbench is "+ $perdel1.getText());
					test1.pass("$/Del" +i+" Value in Workbench is "+ $perdel1.getText());
				}
				else{


					System.out.println("$/Del" +i+" Value is Value is not validated and value is "+ $perdel1.getText());
					test1.fail("$/Del" +i+" Value is Value is not validated and value is "+ $perdel1.getText());

				}

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

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}


	@Test(priority =48)
	public  void Verify_$_Per_Gal_Values_on_Workbench_for_Awarded_quote() throws Exception{

		//Verify $/Gal Values

		ExtentTest test1 = extent.createTest("Verify_$_Per_Gal_Values_on_Workbench_for_Awarded_Quote", "Verify $/Gal values data in Workbench");
		test1.log(Status.INFO, "Verify $/Gal values data in Workbench");

		try {

			for(int i=1,j=72; i<=3&&j<=74; i++,j++){

				WebElement $pergal1=driver.findElement(By.xpath("//*[@id='ProductsFreightWorkbenchBuyerGrid']/div[3]/table/tbody/tr["+i+"]/td[20]"));
				String expectedt$pergal1=ExcelUtils.getCellData(j, colnum);
				String actualverify$pergal1=$pergal1.getText();
				if(actualverify$pergal1.equalsIgnoreCase(expectedt$pergal1)){

					System.out.println("$/Gal" +i+" Value in Workbench is "+ $pergal1.getText());
					test1.pass("$/Gal" +i+" Value in Workbench is "+ $pergal1.getText());
				}
				else{


					System.out.println("$/Gal" +i+ " Value is Value is not validated and value is "+ $pergal1.getText());
					test1.fail("$/Gal" +i+ " Value is Value is not validated and value is "+ $pergal1.getText());

				}

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

		} catch (Exception e) {

			test1.error(e.getMessage());
		}



		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnworkbenchClose")));

	}


	@Test(priority =49)
	public  void Close_Workbench_for_Awarded_quote() throws Exception{

		ExtentTest test1 = extent.createTest("Close_Workbench_for_Awarded_Quote", "Close Workbench");
		test1.log(Status.INFO, "Close Workbench");

		WebElement close=driver.findElement(By.id("btnworkbenchClose"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", close);
		Thread.sleep(2000);
		test1.pass("Close Workbench");
		//VF_Screenshot.FTL_Seller_Quote_Response_screenshot.CaptureScreenshot(driver);


	}


	@Test(priority =50)
	public void Veify_Buyer_Logout() throws Exception{

		ExtentTest test1 = extent.createTest("Veify_Buyer_Logout", "verifying Buyer is logged out succesfully");
		test1.log(Status.INFO, "verifying quote in Active tab");

		WebElement logout=driver.findElement(By.xpath("//*[@id='wrapper']/div[1]/div/div/div/ul/li[5]/a"));
		logout.click();
		Thread.sleep(3000);

		WebElement username=driver.findElement(By.name("Username"));
		if(username.isDisplayed()){


			System.out.println(ExcelUtils.getCellData(154, colnum)+ " Buyer Logged Out Successfully");
			test1.pass(ExcelUtils.getCellData(154, colnum)+ " Buyer Logged Out Successfully");


		}
		else {


			System.out.println(ExcelUtils.getCellData(154, colnum)+ " Buyer is not Logged Out Successfully");
			test1.fail(ExcelUtils.getCellData(154, colnum)+ " Buyer is not Logged Out Successfully");

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



