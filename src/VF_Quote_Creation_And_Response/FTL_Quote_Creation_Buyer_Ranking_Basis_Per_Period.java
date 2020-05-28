package VF_Quote_Creation_And_Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
public class FTL_Quote_Creation_Buyer_Ranking_Basis_Per_Period {

	public  WebDriver driver;
	private static int colnum=6;
	
	


	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentSparkReporter sparkReporter;

	@BeforeSuite
	public void setUp(){

		htmlReporter= new ExtentHtmlReporter("../Veri-Fuel_Hybrid_Framework/FTL Freight Only/FTL_Quote_Creation_Buyer_Ranking_Basis_$_per_Period.html");

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
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"VF_FTL_Quote_Creation_Buyer");

		String Url = ExcelUtils.getCellData(4, colnum);

		driver.get(Url);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		test1.log(Status.INFO, "Navigating to dev site");
		VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

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
			UserName.sendKeys(ExcelUtils.getCellData(5, colnum));
			test1.pass(ExcelUtils.getCellData(5, colnum)+ " as Username is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement Password =driver.findElement(By.name("Password"));
			Password.sendKeys(ExcelUtils.getCellData(6, colnum));
			test1.pass(ExcelUtils.getCellData(6, colnum) + " as Password is entered");
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dashboardnewBuyerClick")));
		VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

		WebElement  newquote = driver.findElement(By.id("dashboardnewBuyerClick"));
		ExtentTest test1 = extent.createTest("verify_buyer_login", "Verify Buyer is logged in dev site");
		test1.log(Status.INFO, "Verify Buyer logged in");

		try {

			if(newquote.isDisplayed()){
				System.out.println("\n"+ ExcelUtils.getCellData(5, 4) + " Buyer logged in successfully");
				test1.pass(ExcelUtils.getCellData(5, 4) + " Buyer Logged in Succesfully");
				
			}
			else{
				System.out.println("Buyer logged in Unsuccessful");
				test1.fail(ExcelUtils.getCellData(5, 4) + " Buyer Loggedin is not Succesfully");

				
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

			WebDriverWait wait0 = new WebDriverWait(driver, 20);
			wait0.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Create")));

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
	public  void Enter_Details_In_Create_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Enter Details in Create Quote", "Enter valid details in create page");
		test1.log(Status.INFO, "Enter valid details in create page");

		WebDriverWait wait0 = new WebDriverWait(driver, 100);
		wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

		try {


			WebElement create=driver.findElement(By.linkText("Create"));
			create.click();
			test1.pass("Page is Navgated to Create Quote");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("createRFP_ServiceType")));

			WebElement quotename=driver.findElement(By.id("createRFP_Name"));
			quotename.sendKeys(ExcelUtils.getCellData(12, colnum));
			test1.pass("Quote Name as " +ExcelUtils.getCellData(12, colnum)+ " is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement potentialstartdate=driver.findElement(By.id("createRFP_StartDate"));
			potentialstartdate.clear();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			potentialstartdate.sendKeys(ExcelUtils.getCellData(13, colnum));
			test1.pass("Potential start Date as " +ExcelUtils.getCellData(13, colnum)+ " is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement potentialenddate=driver.findElement(By.id("createRFP_EndDate"));
			potentialenddate.clear();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			potentialenddate.sendKeys(ExcelUtils.getCellData(14, colnum));
			test1.pass("Potential End Date as " +ExcelUtils.getCellData(14, colnum)+ " is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement round1enddate=driver.findElement(By.id("createRFP_RFPEndDate"));
			round1enddate.clear();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			round1enddate.sendKeys(ExcelUtils.getCellData(15, colnum));
			test1.pass("Round1 End Date as  " +ExcelUtils.getCellData(15, colnum)+ " is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement service=driver.findElement(By.id("createRFP_ServiceType"));
			Select servicetype= new Select(service);
			servicetype.selectByVisibleText(ExcelUtils.getCellData(16, colnum));
			System.out.println("Service Type is " + ExcelUtils.getCellData(16, colnum));
			test1.pass("Service Type  " +ExcelUtils.getCellData(16, colnum)+ " is entered");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement ranking=driver.findElement(By.xpath(ExcelUtils.getCellData(17, colnum)));
			ranking.click();
			test1.pass("Ranking type is $/Period is Selected");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);


		} catch (Exception e) {
			test1.error(e.getMessage());
		}




	}


	@Test(priority =5)
	public  void Select_Division_And_Delivery_Location() throws Exception{

		ExtentTest test1 = extent.createTest("Select_Division_And_Delivery_Location", "Selecting valid Division and Delivery Location");
		test1.log(Status.INFO, "Selecting valid Division and Delivery Location");

		try {

			WebElement divisionfilter=driver.findElement(By.xpath("//th[@data-field='DivisionName']//span[@title='Filter']"));
			divisionfilter.click();		
			Thread.sleep(2000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement divisionsearchbox=driver.findElement(By.xpath("/html/body/div[23]/form/div[1]/input"));
			divisionsearchbox.sendKeys(ExcelUtils.getCellData(18, colnum));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement divisionfilterclick=driver.findElement(By.xpath("/html/body/div[23]/form/div[1]/div[2]/button[1]"));
			divisionfilterclick.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement division=driver.findElement(By.id("chkSelectAllDiv"));
			division.click();
			test1.pass("Division is " + ExcelUtils.getCellData(18, colnum));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement next = driver.findElement(By.id("btnNext"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[4]/div/form/div[1]/div[1]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[2]/input[1]")));

			WebElement deliverylocationfilter=driver.findElement(By.xpath("//th[@data-field='CustName'][@data-index='3']//span[@title='Filter']"));
			deliverylocationfilter.click();		
			Thread.sleep(2000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement deliverylocationsearchbox=driver.findElement(By.xpath("/html/body/div[24]/form/div[1]/input"));
			deliverylocationsearchbox.sendKeys(ExcelUtils.getCellData(19, colnum));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			Thread.sleep(2000);

			WebElement deliverylocationfilterclick=driver.findElement(By.xpath("/html/body/div[24]/form/div[1]/div[2]/button[1]"));
			deliverylocationfilterclick.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);


			WebElement deliverylocation=driver.findElement(By.id("chkSelectAllProductCat"));
			deliverylocation.click();
			test1.pass("Delivery Location is " + ExcelUtils.getCellData(19, colnum));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}


	@Test(priority =6)
	public  void Open_Modify() throws Exception{

		ExtentTest test1 = extent.createTest("Open_Modify", "Click Modify for Delivery Location");
		test1.log(Status.INFO, "Click Modify for Delivery Location");

		try {

			WebElement next = driver.findElement(By.id("btnNext"));
			next.click();
			test1.pass("Next is clicked");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement getsellers = driver.findElement(By.id("btnGetVendor"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getsellers);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			Thread.sleep(2000);

			WebElement modify=driver.findElement(By.linkText("Modify"));
			modify.click();
			test1.pass("Modify Button is clicked for Delivery Location");
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait2 = new WebDriverWait(driver, 150);
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);



		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}

	@Test(priority =7)
	public  void Enter_Details_In_Tand() throws Exception{

		ExtentTest test1 = extent.createTest("Enter_Details_In_TandC", "Enter Details on TandC");
		test1.log(Status.INFO, "Enter Details on TandC");

		try {

			WebElement freightonly=driver.findElement(By.xpath("//*[@id='expandallfrt']/label[1]"));

			try {

				if (freightonly.isDisplayed()) {

					WebElement expandall=driver.findElement(By.xpath("//*[@id='expandallfrt']/label[1]"));
					expandall.click();
					Thread.sleep(2000);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement addproduct=driver.findElement(By.linkText("Add Product"));
					addproduct.click();
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement productcategory=driver.findElement(By.xpath("//span[@aria-owns='ProductCateId_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
					productcategory.click();
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					By mySelector = By.xpath(".//*[@id='ProductCateId_listbox']");
					List<WebElement> myElements = driver.findElements(mySelector);
					for(WebElement e : myElements) {
						System.out.println("prod. cate. Has Following Dropdown List \n" + e.getText());
						test1.info("prod. cate. Has Following Dropdown List \n" + e.getText());
					}

					List<WebElement> prodcateory=driver.findElements(By.xpath("//ul[@id='ProductCateId_listbox']//li"));
					for(WebElement ele:prodcateory)
					{

						String prodcateoryselect=ele.getText();
						if(prodcateoryselect.equalsIgnoreCase(ExcelUtils.getCellData(48, colnum)))
						{
							ele.click();
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("prd.cate. is " + ExcelUtils.getCellData(48, colnum));
					test1.info("prd. cate. is " + ExcelUtils.getCellData(48, colnum));


					WebElement mingalloncell=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr/td[3]"));
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click();", mingalloncell);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);	

					WebElement mingallons=driver.findElement(By.id("MinimumGallons"));
					mingallons.clear();
					mingallons.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(49, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Minimum Gallons is " + String.valueOf(ExcelUtils.getCellDataNum(49, colnum)));


					WebElement terminalstatecell=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr/td[6]"));
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					executor1.executeScript("arguments[0].click();", terminalstatecell);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement terminalstateclick=driver.findElement(By.xpath("//span[@aria-owns='TerminalMarketState_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
					JavascriptExecutor executor2 = (JavascriptExecutor)driver;
					executor2.executeScript("arguments[0].click();", terminalstateclick);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					By mySelector1 = By.xpath(".//*[@id='TerminalMarketState_listbox']");
					List<WebElement> myElements1 = driver.findElements(mySelector1);
					for(WebElement e : myElements1) {
						System.out.println("terminal state Has Following Dropdown List \n" + e.getText());
						test1.info("Terminal State list has following dropdown values\n" + e.getText());

					}

					List<WebElement> terminalstatecat=driver.findElements(By.xpath("//ul[@id='TerminalMarketState_listbox']//li"));
					for(WebElement ele:terminalstatecat)
					{

						String terminalstatecatselect=ele.getText();
						if(terminalstatecatselect.equalsIgnoreCase(ExcelUtils.getCellData(52, colnum)))
						{
							ele.click();
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("Terminal state is " + ExcelUtils.getCellData(52, colnum));
					test1.info("Terminal State is " + ExcelUtils.getCellData(52, colnum));


					WebElement div=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]"));
					div.click();
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement terminalmarketcell=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr/td[7]"));
					JavascriptExecutor executor3 = (JavascriptExecutor)driver;
					executor3.executeScript("arguments[0].click();", terminalmarketcell);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement terminalmarketclick=driver.findElement(By.xpath("//span[@aria-owns='CityID_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
					JavascriptExecutor executor4 = (JavascriptExecutor)driver;
					executor4.executeScript("arguments[0].click();", terminalmarketclick);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					By mySelector2 = By.xpath(".//*[@id='CityID_listbox']");
					List<WebElement> myElements2 = driver.findElements(mySelector2);
					for(WebElement e : myElements2) {
						System.out.println("terminal Market Has Following Dropdown List \n" + e.getText());
						test1.info("Terminal Market list has following dropdown values\n" + e.getText());

					}


					List<WebElement> terminalmarketcat=driver.findElements(By.xpath("//ul[@id='CityID_listbox']//li"));
					for(WebElement ele:terminalmarketcat)
					{

						String terminalmarketselect=ele.getText();
						if(terminalmarketselect.equalsIgnoreCase(ExcelUtils.getCellData(55, colnum)))
						{
							ele.click();
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("Terminal Market is " + ExcelUtils.getCellData(55, colnum));
					test1.info("Terminal Market is " + ExcelUtils.getCellData(55, colnum));


					WebElement loadsperreqlabel = driver.findElement(By.xpath("//span[contains(text(),'Tank Type')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loadsperreqlabel);
					Thread.sleep(2000);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement milestodelactive=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr/td[9]"));
					JavascriptExecutor executor15 = (JavascriptExecutor)driver;
					executor15.executeScript("arguments[0].click();", milestodelactive);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement milestodel=driver.findElement(By.id("TerminalDestinationMileage"));
					milestodel.clear();
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					milestodel.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(56, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Miles to Del is " + ExcelUtils.getCellData(56, colnum));


					WebElement estannaulavolcell=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr/td[10]"));
					JavascriptExecutor executor5 = (JavascriptExecutor)driver;
					executor5.executeScript("arguments[0].click();", estannaulavolcell);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement estannualvol=driver.findElement(By.id("EstimatedGallons"));
					estannualvol.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(57, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Estimated Annual volume is " + ExcelUtils.getCellData(57, colnum));


					WebElement tanktypecell=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr/td[11]"));
					JavascriptExecutor executor6 = (JavascriptExecutor)driver;
					executor6.executeScript("arguments[0].click();", tanktypecell);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement tanktypeclick=driver.findElement(By.xpath("//span[@aria-owns='UnitTypeID_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
					JavascriptExecutor executor7 = (JavascriptExecutor)driver;
					executor7.executeScript("arguments[0].click();", tanktypeclick);
					Thread.sleep(2000);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					By mySelector3 = By.xpath(".//*[@id='UnitTypeID_listbox']");
					List<WebElement> myElements3 = driver.findElements(mySelector3);
					for(WebElement e : myElements3) {
						System.out.println("Tank Type Has Following Dropdown List \n" + e.getText());
						test1.info("Tank Type Has Following Dropdown List \n" + e.getText());

					}

					List<WebElement> tanktypecat=driver.findElements(By.xpath("//ul[@id='UnitTypeID_listbox']//li"));
					for(WebElement ele:tanktypecat)
					{

						String tanktypeselect=ele.getText();
						if(tanktypeselect.equalsIgnoreCase(ExcelUtils.getCellData(60, colnum)))
						{
							ele.click();
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}


					System.out.println("Tank type is " + ExcelUtils.getCellData(60, colnum));
					test1.info("Tank type is " + ExcelUtils.getCellData(60, colnum));


					div.click();
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);



					//Backup1 for first Product

					WebElement addterminal = driver.findElement(By.linkText("Add Terminal"));
					addterminal.click();
					test1.info("Click Add Terminal (Backup1) for 1st product");

					WebElement backup1milestodel=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[2]/td[9]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", backup1milestodel);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement milestodelvaluebackup1=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[2]/td[9]/input"));
					milestodelvaluebackup1.clear();
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					milestodelvaluebackup1.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(63, colnum)));				
					div.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnUpdate")));
					Thread.sleep(2000);
					test1.info("Miles to Del for backup1 is " + String.valueOf(ExcelUtils.getCellDataNum(63, colnum)));

					//Backup2 for first Product

					WebElement addterminal2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[1]/td[1]/a[2]"));
					addterminal2.click();				
					test1.info("Click Add Terminal (Backup2) for 1st product");

					WebElement backup2milestodel=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[3]/td[9]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", backup2milestodel);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement milestodelbackup2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[3]/td[9]/input"));
					milestodelbackup2.clear();
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					milestodelbackup2.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(66, colnum)));
					div.click();

					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("btnUpdate")));
					test1.info("Miles to Del for backup2 is " + String.valueOf(ExcelUtils.getCellDataNum(66, colnum)));




					//Adding 2nd Product

					addproduct.click();
					test1.info("Add 2nd Product Category");

					WebElement productcatactive=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[4]/td[2]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", productcatactive);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement prdouctcategory2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[4]/td[2]/span[1]/span/span[2]/span"));
					prdouctcategory2.click();
					Thread.sleep(2000);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					By mySelector4 = By.xpath(".//*[@id='ProductCateId_listbox']");
					List<WebElement> myElements4 = driver.findElements(mySelector4);
					for(WebElement e : myElements4) {
						System.out.println("prod. cate. Has Following Dropdown List \n" + e.getText());
						test1.info("prod. cate. Has Following Dropdown List \n" + e.getText());

					}

					List<WebElement> prodcateory4=driver.findElements(By.xpath("//ul[@id='ProductCateId_listbox']//li"));
					for(WebElement ele:prodcateory4)
					{

						String prodcateoryselect4=ele.getText();
						if(prodcateoryselect4.equalsIgnoreCase(ExcelUtils.getCellData(71, colnum)))
						{
							ele.click();
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("prd.cate. is " + ExcelUtils.getCellData(71, colnum));
					test1.info("prd.cate. is " + ExcelUtils.getCellData(71, colnum));



					WebElement mingalloncell1=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[4]/td[3]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", mingalloncell1);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);	

					WebElement minimungallonsvalueproduct2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[4]/td[3]/input"));
					minimungallonsvalueproduct2.clear();
					minimungallonsvalueproduct2.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(72, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Minimum Gallons for 2nd Product is " + String.valueOf(ExcelUtils.getCellDataNum(72, colnum)));


					WebElement milestodelactivecell2=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[4]/td[9]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", milestodelactivecell2);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);	

					WebElement milestodelvalueproduct2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[4]/td[9]/input"));
					milestodelvalueproduct2.clear();
					milestodelvalueproduct2.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(73, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Miles to Del for 2nd Product is " + String.valueOf(ExcelUtils.getCellDataNum(73, colnum)));

					WebElement estimatedvolactivecell2=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[4]/td[10]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", estimatedvolactivecell2);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement estimatedannualvolproduct2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[4]/td[10]/input"));
					estimatedannualvolproduct2.clear();
					estimatedannualvolproduct2.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(74, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Estimated annual volume for 2nd Product is " + String.valueOf(ExcelUtils.getCellDataNum(74, colnum)));




					//Adding 3rd Product


					addproduct.click();
					test1.info("Adding 3rd Product");

					WebElement productcatactive1=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[7]/td[2]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", productcatactive1);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement productcategory3=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[7]/td[2]/span[1]/span/span[2]/span"));
					productcategory3.click();
					Thread.sleep(2000);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					By mySelector10 = By.xpath(".//*[@id='ProductCateId_listbox']");
					List<WebElement> myElements10 = driver.findElements(mySelector10);
					for(WebElement e : myElements10) {
						System.out.println("prod. cate. Has Following Dropdown List \n" + e.getText());
						test1.info("prod. cate. Has Following Dropdown List \n" + e.getText());

					}

					List<WebElement> prodcateory10=driver.findElements(By.xpath("//ul[@id='ProductCateId_listbox']//li"));
					for(WebElement ele:prodcateory10)
					{

						String prodcateoryselect10=ele.getText();
						if(prodcateoryselect10.equalsIgnoreCase(ExcelUtils.getCellData(79, colnum)))
						{
							ele.click();
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("prd.cate. is " + ExcelUtils.getCellData(79, colnum));
					test1.info("prd.cate. is " + ExcelUtils.getCellData(79, colnum));


					WebElement mingalloncell2=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[7]/td[3]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", mingalloncell2);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);	

					WebElement mingallosproduct3=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[7]/td[3]/input"));
					mingallosproduct3.clear();
					mingallosproduct3.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(80, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Minimum Gallons for 3rd Product is " + String.valueOf(ExcelUtils.getCellDataNum(80, colnum)));

					WebElement milestodelactivecell3=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[7]/td[9]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", milestodelactivecell3);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);	

					WebElement mimlestodelproduct3=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[7]/td[9]/input"));
					mimlestodelproduct3.clear();
					mimlestodelproduct3.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(81, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Miles to Del for 3rd Product is " + String.valueOf(ExcelUtils.getCellDataNum(81, colnum)));

					WebElement estimatedvolactivecell3=driver.findElement(By.xpath("//*[@id='ProductsFreightModifyGrid']/div[3]/table/tbody/tr[7]/td[10]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", estimatedvolactivecell3);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);	

					WebElement estimatedvoluproduct3=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[7]/td[10]/input"));
					estimatedvoluproduct3.clear();
					estimatedvoluproduct3.sendKeys(String.valueOf(ExcelUtils.getCellDataNum(82, colnum)));
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Estimated annual volume for 3rd Product is " + String.valueOf(ExcelUtils.getCellDataNum(82, colnum)));


					//Adding Service Types

					WebElement addservice = driver.findElement(By.linkText("Add Service"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addservice);
					VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					test1.info("Adding Service Types");


					try {

						Thread.sleep(3000);
						WebElement servicealreadyadd=driver.findElement(By.xpath("//*[@id='AccessorialPricingModifyIndexGrid']/div[3]/table/tbody/tr/td[2]"));

						if(servicealreadyadd.isDisplayed()){

							System.out.println("Service Type is " + servicealreadyadd.getText());
							test1.info("Service Type is " + servicealreadyadd.getText());


							//Add 2nd service Types
							addservice.click();
							Thread.sleep(2000);
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

							WebElement servicetypeclick=driver.findElement(By.xpath("//span[@aria-owns='ID_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
							JavascriptExecutor executor71 = (JavascriptExecutor)driver;
							executor71.executeScript("arguments[0].click();", servicetypeclick);
							Thread.sleep(2000);
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

							By mySelector5 = By.xpath(".//*[@id='ID_listbox']");
							List<WebElement> myElements5 = driver.findElements(mySelector5);
							for(WebElement e : myElements5) {
								System.out.println("service Type Has Following Dropdown List \n" + e.getText());
								test1.info("service Type Has Following Dropdown List \n" + e.getText());

							}

							List<WebElement> sercivetypetypecat=driver.findElements(By.xpath("//ul[@id='ID_listbox']//li"));
							for(WebElement ele:sercivetypetypecat)
							{

								String servicetypeselect=ele.getText();
								if(servicetypeselect.equalsIgnoreCase(ExcelUtils.getCellData(87, colnum)))
								{
									ele.click();
									VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
									break;
								}
							}

							System.out.println("Service type is " + ExcelUtils.getCellData(87, colnum));
							test1.info("2nd Service type is " + ExcelUtils.getCellData(87, colnum));


							driver.findElement(By.xpath("//*[@id='AccessorialPricingModifyIndexGrid']/div[3]")).click();
							Thread.sleep(2000);
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

							//Add 3rd service type

							addservice.click();
							Thread.sleep(2000);

							WebElement addservice3=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[3]/div[2]/div/div[3]/table/tbody/tr[3]/td[2]/span[1]/span/span[2]/span"));
							addservice3.click();
							Thread.sleep(2000);

							List<WebElement> sercivetypetypecat2=driver.findElements(By.xpath("//ul[@id='ID_listbox']//li"));
							for(WebElement ele:sercivetypetypecat2)
							{

								String servicetypeselect2=ele.getText();
								if(servicetypeselect2.equalsIgnoreCase(ExcelUtils.getCellData(89, colnum)))
								{
									ele.click();
									VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
									break;
								}
							}

							Thread.sleep(2000);
							System.out.println("Service type is " + ExcelUtils.getCellData(89, colnum));
							test1.info("3rd Service type is " + ExcelUtils.getCellData(89, colnum));


							driver.findElement(By.xpath("//*[@id='AccessorialPricingModifyIndexGrid']/div[3]")).click();
							Thread.sleep(2000);
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

							WebElement savetandc = driver.findElement(By.id("btnUpdate"));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", savetandc);
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

							savetandc.click();
							Thread.sleep(2000);
							VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							test1.info("Saved TandC Data");



						}

					} catch (Exception e1) {

						addservice.click();
						Thread.sleep(2000);
						VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
						test1.info("Adding 3 Service Types");

						WebElement servicetypeclick=driver.findElement(By.xpath("//span[@aria-owns='ID_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
						JavascriptExecutor executor71 = (JavascriptExecutor)driver;
						executor71.executeScript("arguments[0].click();", servicetypeclick);
						Thread.sleep(2000);
						VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

						By mySelector5 = By.xpath(".//*[@id='ID_listbox']");
						List<WebElement> myElements5 = driver.findElements(mySelector5);
						for(WebElement e : myElements5) {
							System.out.println("service Type Has Following Dropdown List \n" + e.getText());
						}

						List<WebElement> sercivetypetypecat=driver.findElements(By.xpath("//ul[@id='ID_listbox']//li"));
						for(WebElement ele:sercivetypetypecat)
						{

							String servicetypeselect=ele.getText();
							if(servicetypeselect.equalsIgnoreCase(ExcelUtils.getCellData(87, colnum)))
							{
								ele.click();
								VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
								break;
							}
						}

						System.out.println("Service type is " + ExcelUtils.getCellData(87, colnum));
						test1.info("ist Service type is " + ExcelUtils.getCellData(87, colnum));


						driver.findElement(By.xpath("//*[@id='AccessorialPricingModifyIndexGrid']/div[3]")).click();
						Thread.sleep(2000);
						VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

						//Add 2nd service type

						addservice.click();
						Thread.sleep(2000);

						WebElement addserice2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[3]/div[2]/div/div[3]/table/tbody/tr[2]/td[2]/span[1]/span/span[2]/span"));
						addserice2.click();
						Thread.sleep(2000);


						List<WebElement> sercivetypetypecat2=driver.findElements(By.xpath("//ul[@id='ID_listbox']//li"));
						for(WebElement ele:sercivetypetypecat2)
						{

							String servicetypeselect2=ele.getText();
							if(servicetypeselect2.equalsIgnoreCase(ExcelUtils.getCellData(89, colnum)))
							{
								ele.click();
								VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
								break;
							}
						}

						System.out.println("Service type is " + ExcelUtils.getCellData(89, colnum));
						test1.info("2nd Service type is " + ExcelUtils.getCellData(89, colnum));


						driver.findElement(By.xpath("//*[@id='AccessorialPricingModifyIndexGrid']/div[3]")).click();
						Thread.sleep(2000);
						VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

						//Add 3rd service type

						addservice.click();
						Thread.sleep(2000);


						WebElement addservice3=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[4]/div[3]/div[2]/div/div[3]/table/tbody/tr[3]/td[2]/span[1]/span/span[2]/span"));
						addservice3.click();
						Thread.sleep(2000);



						List<WebElement> sercivetypetypecat3=driver.findElements(By.xpath("//ul[@id='ID_listbox']//li"));
						for(WebElement ele:sercivetypetypecat3)
						{

							String servicetypeselect3=ele.getText();
							if(servicetypeselect3.equalsIgnoreCase(ExcelUtils.getCellData(91, colnum)))
							{
								ele.click();
								VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
								break;
							}
						}

						System.out.println("Service type is " + ExcelUtils.getCellData(91, colnum));
						test1.info("3rd Service type is " + ExcelUtils.getCellData(91, colnum));

						driver.findElement(By.xpath("//*[@id='AccessorialPricingModifyIndexGrid']/div[3]")).click();
						Thread.sleep(2000);
						VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

						WebElement savetandc = driver.findElement(By.id("btnUpdate"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", savetandc);
						VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

						savetandc.click();
						Thread.sleep(2000);
						VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
						test1.info("Saved TandC Data");



					}



				} else {

				}

			} catch (Exception e) {

			}



		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}

	@Test(priority =8)
	public  void Quote_Save_As_Draft() throws Exception{

		ExtentTest test1 = extent.createTest("Quote_Save_As_Draft", "Save quote as draft");
		test1.log(Status.INFO, "Save quote as draft");

		try {

			WebElement div2=driver.findElement(By.xpath("//*[@id='CustomerGrid']/div[3]"));
			div2.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			Thread.sleep(2000);
			WebElement getsellers = driver.findElement(By.id("btnGetVendor"));
			getsellers.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Click Get Sellers");

			JavascriptExecutor js1 = ((JavascriptExecutor) driver);
			js1.executeScript("window.scrollTo(document.body.scrollLow, 0)");
			Thread.sleep(2000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement savequote = driver.findElement(By.id("btnsavesubmit"));
			savequote.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info(ExcelUtils.getCellData(12, colnum) + " Quote is saved as draft");

			WebDriverWait wait5 = new WebDriverWait(driver, 100);
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Create")));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);


		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}

	@Test(priority =9)
	public  void Select_Sellers_For_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Select_Sellers_For_Quote", "Selecting Sellers for Quote");
		test1.log(Status.INFO, "Selecting Sellers for Quote");

		try {

			Thread.sleep(3000);
			WebDriverWait wait0 = new WebDriverWait(driver, 10);
			wait0.until(ExpectedConditions.presenceOfElementLocated(By.id("ancrUOMSearch")));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement searchsymbol = driver.findElement(By.id("ancrUOMSearch"));
			searchsymbol.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement enteronsearch = driver.findElement(By.id("draftRFPSearch"));
			enteronsearch.sendKeys(ExcelUtils.getCellData(12, colnum));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Edting Draft Quote is " + ExcelUtils.getCellData(12, colnum));

			WebElement editquote=driver.findElement(By.xpath("//*[@id='DraftsRFPGrid']//td[@data-field='Name']//a"));
			System.out.println(editquote.getText());
			editquote.click();
			Thread.sleep(3000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("createRFP_Name")));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement sellerscroll=driver.findElement(By.xpath("//*[@id='VendorGrid']/div[4]/span[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sellerscroll);
			Thread.sleep(3000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			((JavascriptExecutor) driver)
			.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			String mainseller=ExcelUtils.getCellData(97, colnum);
			String query="//input[@type='checkbox'][@name='" + mainseller + "']";
			test1.info("Main Seller Network is " + ExcelUtils.getCellData(97, colnum));


			WebElement sellerselect = driver.findElement(By.xpath(query));
			sellerselect.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement selectallsellers=driver.findElement(By.id("chkSelectAllVendorCat"));
			selectallsellers.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement sellerfilter=driver.findElement(By.xpath("//th[@data-field='VendorName']//span[@title='Filter']"));
			sellerfilter.click();		
			Thread.sleep(2000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement sellersearchbox=driver.findElement(By.xpath("/html/body/div[41]/form/div[1]/input"));
			sellersearchbox.sendKeys(ExcelUtils.getCellData(98, colnum));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Seller Network is " + ExcelUtils.getCellData(98, colnum));


			WebElement sellerfilterclick=driver.findElement(By.xpath("/html/body/div[41]/form/div[1]/div[2]/button[1]"));
			sellerfilterclick.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			selectallsellers.click();
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);


		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}

	@Test(priority =10)
	public  void Save_And_Send_Quote_To_Seller() throws Exception{

		ExtentTest test1 = extent.createTest("Save_And_Send_Quote_To_Seller", "Save and Send quote to Seller");
		test1.log(Status.INFO, "Save and Send quote to Seller");

		try {

			JavascriptExecutor js1 = ((JavascriptExecutor) driver);
			js1.executeScript("window.scrollTo(document.body.scrollLow, 0)");
			Thread.sleep(2000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement saveandsend = driver.findElement(By.id("btnsubmit"));
			saveandsend.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement quotereceipts = driver.findElement(By.id("divpopupmessages"));
			System.out.println(quotereceipts.getText());
			test1.info(quotereceipts.getText());

			WebElement ok = driver.findElement(By.id("btnOK"));
			ok.click();
			Thread.sleep(2000);
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait5 = new WebDriverWait(driver, 100);
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Create")));
			VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);


		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}

	@Test(priority =11)
	public  void Verify_Save_And_Send_Quote_In_Active_Quotes() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Save_And_Send_Quote_In_Active_Quotes", "verifying quote in Active tab");
		test1.log(Status.INFO, "verifying quote in Active tab");

		Thread.sleep(3000);

		WebElement activequotes=driver.findElement(By.xpath("//span[contains(text(),'Active')]"));
		activequotes.click();
		Thread.sleep(3000);

		WebElement activesearch = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div/div/div/div[1]/div/div[1]/div/a/span/i"));
		activesearch.click();
		VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

		WebElement enteronsearch = driver.findElement(By.id("txtSearch"));
		enteronsearch.sendKeys(ExcelUtils.getCellData(12, colnum));
		VF_Screenshot.FTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
		test1.pass("Edting " + ExcelUtils.getCellData(12, colnum));

		try {

			if (driver.findElement(By.xpath("//*[@id='ReviewRFPGrid']/div[3]/table/tbody/tr/td[3]")).isDisplayed()) {

				WebElement activequotedata=driver.findElement(By.xpath("//*[@id='ReviewRFPGrid']/div[3]/table/tbody/tr/td[3]"));
				String excpected_active_quote=ExcelUtils.getCellData(12, colnum);
				String actual_active_quote=activequotedata.getText();
				if(excpected_active_quote.equals(actual_active_quote)){

					
					System.out.println(activequotedata.getText() + " Record is in Active Tab");
					test1.pass(activequotedata.getText() + " Record is in Active Tab");
				}
				else{


					
					System.out.println(activequotedata.getText() + " is not in Active quotes");
					test1.fail(activequotedata.getText() + " is not in Active quotes");

				}

			} else {


			}

		} catch (Exception e) {

			
			System.out.println("Quote is not in Active Tab");

		}

	}


	@Test(priority =12)
	public void Veify_Buyer_Logout() throws Exception{

		ExtentTest test1 = extent.createTest("Veify_Buyer_Logout", "verifying Buyer is logged out succesfully");
		test1.log(Status.INFO, "verifying quote in Active tab");

		WebElement logout=driver.findElement(By.xpath("//*[@id='wrapper']/div[1]/div/div/div/ul/li[5]/a"));
		logout.click();
		Thread.sleep(3000);

		WebElement username=driver.findElement(By.name("Username"));
		if(username.isDisplayed()){

			
			System.out.println(ExcelUtils.getCellData(5, colnum)+ " Buyer Logged Out Successfully");
			test1.pass(ExcelUtils.getCellData(5, colnum)+ " Buyer Logged Out Successfully");


		}
		else {

			
			System.out.println(ExcelUtils.getCellData(5, colnum)+ " Buyer is not Logged Out Successfully");
			test1.fail(ExcelUtils.getCellData(5, colnum)+ " Buyer is not Logged Out Successfully");

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
