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
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.Constant;
import Utility.ExcelUtils;
public class LTL_TW_Quote_Creation_Buyer_Ranking_Basis_Per_Gal {

	private static WebDriver driver;
	private static int colnum=5;


	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeSuite
	public void setUp(){

		htmlReporter= new ExtentHtmlReporter("../Veri-Fuel_Hybrid_Framework/LTL Tankwagon/LTL_Quote_Buyer_Creation__Ranking_Basis_$_Per_Gal.html");

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
		test1.log(Status.INFO, "Navigating to dev site");


		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"VF_LTL_TW_Quote_Creation_Buyer");

		String Url = ExcelUtils.getCellData(4, colnum);

		driver.get(Url);
		Thread.sleep(2000);
		driver.manage().window().maximize();

		try {

			if (driver.findElement(By.name("Username")).isDisplayed()) {

				System.out.print("Page is navigated to \n" + Url);
				test1.log(Status.PASS, "Page is navigated to \n" + Url);
				

			} else {

				System.out.print("Page is not navigated to \n" + Url);
				test1.log(Status.FAIL, "Page is not navigated to \n" + Url);

			}

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}


	@Test(priority =1)
	public  void Buyer_Login() throws Exception{

		ExtentTest test1 = extent.createTest("Buyer_Login", "Login with Buyer account by providing valid Username and Password");
		test1.log(Status.INFO, "Login with Buyer account by providing valid Username and Password");

		try {

			WebElement UserName =driver.findElement(By.name("Username"));
			UserName.sendKeys(ExcelUtils.getCellData(5, colnum));
			test1.info(ExcelUtils.getCellData(5, colnum) + " as Username is entered");
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement Password =driver.findElement(By.name("Password"));
			Password.sendKeys(ExcelUtils.getCellData(6, colnum));
			test1.info(ExcelUtils.getCellData(6, colnum) + " as password is entered");
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement Login = driver.findElement(By.id("loginbtn"));
			Login.click();
			test1.info("Login is clicked");
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

		} catch (Exception e) {
			test1.error(e.getMessage());
		}


	}

	@Test(priority =2)
	public  void verify_buyer_login() throws Exception{

		ExtentTest test1 = extent.createTest("verify_buyer_login", "Verify buyer is looged in");
		test1.log(Status.INFO, "Verify buyer is looged in");

		try {

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement  newquote = driver.findElement(By.id("dashboardnewBuyerClick"));

			if(newquote.isDisplayed()){
				System.out.println("\n"+ ExcelUtils.getCellData(5, 4) + " Buyer logged in successfully");
				test1.log(Status.PASS, "\n"+ ExcelUtils.getCellData(5, 4) + " Buyer logged in successfully");

			}
			else{
				System.out.println("Buyer logged in Unsuccessful");
				test1.log(Status.FAIL, "Buyer logged in Unsuccessful");


			}

		} catch (Exception e) {
			test1.error(e.getMessage());
		}



	}

	@Test(priority =3)
	public  void Navigate_to_Create_Or_View_Page() throws Exception{

		ExtentTest test1 = extent.createTest("Navigate_to_Create_Or_View_Page", "Navigtae to Create or View Page for buyer");
		test1.log(Status.INFO, "Navigtae to Create or View Page for buyer");

		try {

			WebElement element=driver.findElement(By.id("26"));
			WebElement element1=driver.findElement(By.id("28"));

			Actions action=new Actions(driver);
			action.moveToElement(element).build().perform();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			action.moveToElement(element1).build().perform();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			element1.click();
			Thread.sleep(3000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait0 = new WebDriverWait(driver, 20);
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
	public  void Enter_Details_In_Create_Quote() throws Exception{

		WebDriverWait wait0 = new WebDriverWait(driver, 10);
		wait0.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

		ExtentTest test1 = extent.createTest("Enter_Details_In_Create_Quote", "Enter valid details in create page");
		test1.log(Status.INFO, "Enter valid details in create page");

		try {

			WebElement create=driver.findElement(By.linkText("Create"));
			create.click();
			test1.pass("Page is Navgated to Create Quote");

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));

			WebElement quotename=driver.findElement(By.id("createRFP_Name"));
			quotename.sendKeys(ExcelUtils.getCellData(12, colnum));
			test1.pass("Quote Name as " +ExcelUtils.getCellData(12, colnum)+ " is entered");

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement potentialstartdate=driver.findElement(By.id("createRFP_StartDate"));
			potentialstartdate.clear();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			potentialstartdate.sendKeys(ExcelUtils.getCellData(13, colnum));
			test1.pass("Potential start Date as " +ExcelUtils.getCellData(13, colnum)+ " is entered");

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement potentialenddate=driver.findElement(By.id("createRFP_EndDate"));
			potentialenddate.clear();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			potentialenddate.sendKeys(ExcelUtils.getCellData(14, colnum));
			test1.pass("Potential End Date as " +ExcelUtils.getCellData(14, colnum)+ " is entered");

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement round1enddate=driver.findElement(By.id("createRFP_RFPEndDate"));
			round1enddate.clear();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			round1enddate.sendKeys(ExcelUtils.getCellData(15, colnum));
			test1.pass("Round1 End Date as  " +ExcelUtils.getCellData(15, colnum)+ " is entered");

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement service=driver.findElement(By.id("createRFP_ServiceType"));
			Select servicetype= new Select(service);
			servicetype.selectByVisibleText(ExcelUtils.getCellData(16, colnum));
			System.out.println("Service Type is " + ExcelUtils.getCellData(16, colnum));
			test1.pass("Service Type  " +ExcelUtils.getCellData(16, colnum)+ " is entered");

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement ranking=driver.findElement(By.xpath(ExcelUtils.getCellData(17, colnum)));
			ranking.click();
			test1.pass("Ranking type is $/Gal is Selected");
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

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
			Thread.sleep(1000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement divisionsearchbox=driver.findElement(By.xpath("/html/body/div[23]/form/div[1]/input"));
			divisionsearchbox.sendKeys(ExcelUtils.getCellData(18, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement divisionfilterclick=driver.findElement(By.xpath("/html/body/div[23]/form/div[1]/div[2]/button[1]"));
			divisionfilterclick.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement division=driver.findElement(By.id("chkSelectAllDiv"));
			division.click();
			test1.pass("Division is " + ExcelUtils.getCellData(18, colnum));

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement next = driver.findElement(By.id("btnNext"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[4]/div/form/div[1]/div[1]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[2]/input[1]")));

			WebElement deliverylocationfilter=driver.findElement(By.xpath("//th[@data-field='CustName'][@data-index='3']//span[@title='Filter']"));
			deliverylocationfilter.click();		
			Thread.sleep(1000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement deliverylocationsearchbox=driver.findElement(By.xpath("/html/body/div[24]/form/div[1]/input"));
			deliverylocationsearchbox.sendKeys(ExcelUtils.getCellData(19, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement deliverylocationfilterclick=driver.findElement(By.xpath("/html/body/div[24]/form/div[1]/div[2]/button[1]"));
			deliverylocationfilterclick.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);


			WebElement deliverylocation=driver.findElement(By.id("chkSelectAllProductCat"));
			deliverylocation.click();
			test1.pass("Delivery Location is " + ExcelUtils.getCellData(19, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

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

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement getsellers = driver.findElement(By.id("btnGetVendor"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getsellers);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			Thread.sleep(2000);

			WebElement modify=driver.findElement(By.linkText("Modify"));
			modify.click();
			test1.pass("Modify Button is clicked for Delivery Location");

			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait2 = new WebDriverWait(driver, 150);
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

		} catch (Exception e) {

			test1.error(e.getMessage());

		}


	}

	@Test(priority =7)
	public  void Enter_Details_In_Tand() throws Exception{

		ExtentTest test1 = extent.createTest("Enter_Details_In_TandC", "Enter Details on TandC");
		test1.log(Status.INFO, "Enter Details on TandC");

		try {
			
			WebElement ltltankwagon=driver.findElement(By.xpath("//*[@id='expandallltlmeter']/label[1]"));

			try {

				if (ltltankwagon.isDisplayed()) {

					WebElement expandall=driver.findElement(By.xpath("//*[@id='expandallltlmeter']/label[1]"));
					expandall.click();
					Thread.sleep(2000);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement usageperiod=driver.findElement(By.id("Rfpviewmodel_LTLTerms_GallonsPeriod"));
					Select usageperiod1= new Select(usageperiod);
					usageperiod1.selectByVisibleText(ExcelUtils.getCellData(24, colnum));
					test1.info("Usage Period is " + ExcelUtils.getCellData(24, colnum));
					System.out.println("Usage Period is " + ExcelUtils.getCellData(24, colnum));
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement addproduct=driver.findElement(By.linkText("Add Product"));
					addproduct.click();
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement productcategory=driver.findElement(By.xpath("//span[@aria-owns='ProductCateId_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
					productcategory.click();
					Thread.sleep(2000);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

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
						if(prodcateoryselect.equalsIgnoreCase(ExcelUtils.getCellData(28, colnum)))
						{
							ele.click();
							VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("prd.cate. is " + ExcelUtils.getCellData(28, colnum));
					test1.info("prd.cate. is " + ExcelUtils.getCellData(28, colnum));

					WebElement prodsubcat1cell=driver.findElement(By.xpath("//*[@id='ProductsLTLGridModify']/div[3]/table/tbody/tr/td[2]"));
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click();", prodsubcat1cell);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement prodsubcat1=driver.findElement(By.xpath("//span[@aria-owns='SubCategory1_listbox']//span[@class='k-icon k-i-arrow-60-down']"));
					JavascriptExecutor executor1 = (JavascriptExecutor)driver;
					executor1.executeScript("arguments[0].click();", prodsubcat1);
					Thread.sleep(2000);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);


					By mySelector1 = By.xpath(".//*[@id='SubCategory1_listbox']");
					List<WebElement> myElements1 = driver.findElements(mySelector1);
					for(WebElement e : myElements1) {
						System.out.println("prod. Sub cate. Has Following Dropdown List \n" + e.getText());
						test1.info("prod. Sub cate. Has Following Dropdown List \n" + e.getText());
					}

					List<WebElement> prodcateorysubcate=driver.findElements(By.xpath("//ul[@id='SubCategory1_listbox']//li"));
					for(WebElement ele:prodcateorysubcate)
					{

						String prodcateorysubcateselect=ele.getText();
						if(prodcateorysubcateselect.equalsIgnoreCase(ExcelUtils.getCellData(31, colnum)))
						{
							ele.click();
							VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("prd.sub cate. is " + ExcelUtils.getCellData(31, colnum));
					test1.info("prd.sub cate. is " + ExcelUtils.getCellData(31, colnum));

					WebDriverWait wait5 = new WebDriverWait(driver, 100);
					wait5.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
					
					WebElement div=driver.findElement(By.xpath("//*[@id='ProductsLTLGridModify']/div[3]"));

					WebElement estgallonsperiodcell=driver.findElement(By.xpath("//*[@id='ProductsLTLGridModify']/div[3]/table/tbody/tr/td[6]"));
					JavascriptExecutor executor11 = (JavascriptExecutor)driver;
					executor11.executeScript("arguments[0].click();", estgallonsperiodcell);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement estgallonsperiod=driver.findElement(By.id("EstimatedGallons"));
					estgallonsperiod.clear();
					estgallonsperiod.sendKeys(ExcelUtils.getCellData(32, colnum));
					test1.info("Estimated Gallons Period " + ExcelUtils.getCellData(32, colnum));
					System.out.println("Estimated Gallons Period " + ExcelUtils.getCellData(32, colnum));
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					div.click();
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement tanksizecell=driver.findElement(By.xpath("//*[@id='ProductsLTLGridModify']/div[3]/table/tbody/tr/td[9]"));
					JavascriptExecutor executor15 = (JavascriptExecutor)driver;
					executor15.executeScript("arguments[0].click();", tanksizecell);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement tanksize=driver.findElement(By.id("TankSize"));
					tanksize.clear();
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					tanksize.sendKeys(ExcelUtils.getCellData(33, colnum));
					test1.info("Tank Size is " + ExcelUtils.getCellData(33, colnum));
					System.out.println("Tank Size is " + ExcelUtils.getCellData(33, colnum));
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					div.click();
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);



					//Add 2nd Product category


					addproduct.click();
					Thread.sleep(2000);
					test1.info("Add 2nd Product");
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement productcategory2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[2]/div[3]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[2]/td[1]/span[1]/span/span[2]/span"));
					productcategory2.click();
					Thread.sleep(2000);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);


					List<WebElement> prodcateory2=driver.findElements(By.xpath("//ul[@id='ProductCateId_listbox']//li"));
					for(WebElement ele:prodcateory2)
					{

						String prodcateory2select=ele.getText();
						if(prodcateory2select.equalsIgnoreCase(ExcelUtils.getCellData(38, colnum)))
						{
							ele.click();
							VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("prd.cate. is " + ExcelUtils.getCellData(38, colnum));
					test1.info("prd.cate. is " + ExcelUtils.getCellData(38, colnum));

					WebElement prodsubcatcell2=driver.findElement(By.xpath("//*[@id='ProductsLTLGridModify']/div[3]/table/tbody/tr[2]/td[2]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", prodsubcatcell2);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement prodsubcat2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[2]/div[3]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[2]/td[2]/span[1]/span/span[2]/span"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", prodsubcat2);
					Thread.sleep(2000);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					List<WebElement> prodcateorysubcate2=driver.findElements(By.xpath("//ul[@id='SubCategory1_listbox']//li"));
					for(WebElement ele:prodcateorysubcate2)
					{

						String prodcateorysubcateselect2=ele.getText();
						if(prodcateorysubcateselect2.equalsIgnoreCase(ExcelUtils.getCellData(41, colnum)))
						{
							ele.click();
							VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
							break;
						}
					}

					System.out.println("prd.sub cate. is " + ExcelUtils.getCellData(41, colnum));
					test1.info("prd.sub cate. is " + ExcelUtils.getCellData(41, colnum));

					WebDriverWait wait6 = new WebDriverWait(driver, 100);
					wait6.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
					
					WebElement div1=driver.findElement(By.xpath("//*[@id='ProductsLTLGridModify']/div[3]"));

					WebElement estgallonsperiodcell2=driver.findElement(By.xpath("//*[@id='ProductsLTLGridModify']/div[3]/table/tbody/tr[2]/td[6]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", estgallonsperiodcell2);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement estgallonsperiod2=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[2]/div[3]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[2]/td[6]/input"));
					estgallonsperiod2.clear();
					estgallonsperiod2.sendKeys(ExcelUtils.getCellData(42, colnum));
					test1.info("Estimated Gallons period is " + ExcelUtils.getCellData(42, colnum));
					System.out.println("Estimated Gallons period is " + ExcelUtils.getCellData(42, colnum));
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					div1.click();
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement tanksizecell2=driver.findElement(By.xpath("//*[@id='ProductsLTLGridModify']/div[3]/table/tbody/tr[2]/td[9]"));
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", tanksizecell2);
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					WebElement tanksize1=driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div[1]/div/div[1]/div/div/div/div/div/div[2]/div[3]/div/div[2]/div[3]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[2]/td[9]/input"));
					tanksize1.clear();
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
					tanksize1.sendKeys(ExcelUtils.getCellData(43, colnum));
					test1.info("Tank Size is " + ExcelUtils.getCellData(43, colnum));
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

					div.click();
					VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);



				} else {

				}

			} catch (Exception e) {

			}
			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}
		
		



	}

	@Test(priority =8)
	public  void Select_Payment_and_Service_Schedule_Pattern_in_TandC() throws Exception{
		
		ExtentTest test1 = extent.createTest("Select_Payment_and_Service_Schedule_Pattern_in_TandC", "Select Valid Payment and Service Schedules in TandC");
		test1.log(Status.INFO, "Select Valid Payment and Service Schedules in TandC");
		
		try {
			
			Thread.sleep(2000);
			WebElement deliveryhoursfrom=driver.findElement(By.id("Rfpviewmodel_LTLDeliveryHoursFrom"));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deliveryhoursfrom);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement paymenterms=driver.findElement(By.id("Rfpviewmodel_LTLprice_PaymentTermsID"));
			Select paymentermsselect= new Select(paymenterms);
			paymentermsselect.selectByVisibleText(ExcelUtils.getCellData(44, colnum));
			test1.info("Payment Term is " + ExcelUtils.getCellData(44, colnum));
			
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			
			WebElement week1serviceselect = driver.findElement(By.id(ExcelUtils.getCellData(45, colnum)));
			week1serviceselect.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Week1 is Monday");			
			
			WebElement week2serviceselect = driver.findElement(By.id(ExcelUtils.getCellData(46, colnum)));
			week2serviceselect.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Week2 is Wednesday");			


			Select deliveryhoursfromselect= new Select(deliveryhoursfrom);
			deliveryhoursfromselect.selectByVisibleText(ExcelUtils.getCellData(47, colnum));
			test1.info("Delivery Hours From are " + ExcelUtils.getCellData(47, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement deliveryhoursto=driver.findElement(By.id("Rfpviewmodel_LTLDeliveryHoursTo"));
			Select deliveryhourstoselect= new Select(deliveryhoursto);
			deliveryhourstoselect.selectByVisibleText(ExcelUtils.getCellData(48, colnum));
			test1.info("Delivery Hours To are " + ExcelUtils.getCellData(48, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement settlethrough=driver.findElement(By.id("Rfpviewmodel_LTLprice_BillingAgencyID"));
			Select settlethroughselect= new Select(settlethrough);
			settlethroughselect.selectByVisibleText(ExcelUtils.getCellData(49, colnum));
			test1.info("Settle though " + ExcelUtils.getCellData(49, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement savetandc = driver.findElement(By.id("btnUpdate"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", savetandc);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			savetandc.click();
			WebDriverWait wait5 = new WebDriverWait(driver, 100);
			wait5.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			
		} catch (Exception e) {
			
			test1.error(e.getMessage());
			
		}

		
	}


	@Test(priority =9)
	public  void Quote_Save_As_Draft() throws Exception{
		
		ExtentTest test1 = extent.createTest("Quote_Save_As_Draft", "Save quote as draft by clicking Save");
		test1.log(Status.INFO, "Save quote as draft by clicking Save");

		try {
			
			WebElement div2=driver.findElement(By.xpath("//*[@id='CustomerGrid']/div[3]"));
			div2.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			Thread.sleep(1000);

			WebElement getsellers = driver.findElement(By.id("btnGetVendor"));
			getsellers.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Click Get Sellers");

			JavascriptExecutor js1 = ((JavascriptExecutor) driver);
			js1.executeScript("window.scrollTo(document.body.scrollLow, 0)");
			Thread.sleep(2000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement savequote = driver.findElement(By.id("btnsavesubmit"));
			savequote.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Click Save");

			WebDriverWait wait5 = new WebDriverWait(driver, 100);
			wait5.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			
		} catch (Exception e) {
			
			test1.error(e.getMessage());
		}
		
		

	}
	
	@Test(priority =10)
	public  void Select_Sellers_for_Draft_Quote() throws Exception{
		
		ExtentTest test1 = extent.createTest("Select_Sellers_for_Draft_Quote", "Select Sellers for Draft quote");
		test1.log(Status.INFO, "Select Sellers for Draft quote");
		
		try {
			
			WebElement searchsymbol = driver.findElement(By.id("ancrUOMSearch"));
			searchsymbol.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement enteronsearch = driver.findElement(By.id("draftRFPSearch"));
			enteronsearch.sendKeys(ExcelUtils.getCellData(12, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Edit Draft quote " + ExcelUtils.getCellData(12, colnum));

			WebElement editquote=driver.findElement(By.xpath("//*[@id='DraftsRFPGrid']//td[@data-field='Name']//a"));
			System.out.println(editquote.getText());
			editquote.click();
			Thread.sleep(1000);
			WebDriverWait wait51 = new WebDriverWait(driver, 100);
			wait51.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement sellerscroll=driver.findElement(By.xpath("//*[@id='VendorGrid']/div[4]/span[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sellerscroll);
			Thread.sleep(2000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			((JavascriptExecutor) driver)
			.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			String mainseller=ExcelUtils.getCellData(55, colnum);
			String query="//input[@type='checkbox'][@name='" + mainseller + "']";

			WebElement sellerselect = driver.findElement(By.xpath(query));
			sellerselect.click();
			Thread.sleep(2000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Main Seller Network is " + ExcelUtils.getCellData(55, colnum));

			WebElement selectallsellers=driver.findElement(By.id("chkSelectAllVendorCat"));
			selectallsellers.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement sellerfilter=driver.findElement(By.xpath("//th[@data-field='VendorName']//span[@title='Filter']"));
			sellerfilter.click();		
			Thread.sleep(2000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement sellersearchbox=driver.findElement(By.xpath("/html/body/div[41]/form/div[1]/input"));
			sellersearchbox.sendKeys(ExcelUtils.getCellData(56, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement sellerfilterclick=driver.findElement(By.xpath("/html/body/div[41]/form/div[1]/div[2]/button[1]"));
			sellerfilterclick.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			test1.info("Seller is " + ExcelUtils.getCellData(56, colnum));

			selectallsellers.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			
		} catch (Exception e) {
			
			test1.error(e.getMessage());
			
		}
		
		

		
	}

	@Test(priority =11)
	public  void Save_And_Send_Draft_Quote() throws Exception{

		ExtentTest test1 = extent.createTest("Save_And_Send_Draft_Quote", "Save and Send draft Quote");
		test1.log(Status.INFO, "Save and Send draft Quote");


		try {
			
			JavascriptExecutor js1 = ((JavascriptExecutor) driver);
			js1.executeScript("window.scrollTo(document.body.scrollLow, 0)");
			Thread.sleep(2000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement saveandsend = driver.findElement(By.id("btnsubmit"));
			saveandsend.click();
			Thread.sleep(2000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement quotereceipts = driver.findElement(By.id("divpopupmessages"));
			System.out.println(quotereceipts.getText());
			test1.info(quotereceipts.getText());


			WebElement ok = driver.findElement(By.id("btnOK"));
			ok.click();
			Thread.sleep(2000);
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebDriverWait wait51 = new WebDriverWait(driver, 100);
			wait51.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);
			
		} catch (Exception e) {
			test1.error(e.getMessage());
		}

		
	}

	@Test(priority =12)
	public  void Verify_Save_And_Send_Quote_in_Active_tab() throws Exception{

		ExtentTest test1 = extent.createTest("Verify_Save_And_Send_Quote_in_Active_tab", "Verify Save and Send quote in active tab");
		test1.log(Status.INFO, "Verify Save and Send quote in active tab");
		
		try {

			WebElement activequotes=driver.findElement(By.xpath("//span[contains(text(),'Active')]"));
			activequotes.click();
			Thread.sleep(3000);

			WebElement activesearch = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div/div/div/div[1]/div/div[1]/div/a/span/i"));
			activesearch.click();
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

			WebElement enteronsearch = driver.findElement(By.id("txtSearch"));
			enteronsearch.sendKeys(ExcelUtils.getCellData(12, colnum));
			VF_Screenshot.LTL_Buyer_Quote_Creation_screenshot.CaptureScreenshot(driver);

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
			
		} catch (Exception e) {
			
			test1.error(e.getMessage());
			
		}

		

	}
	@Test(priority =13)
	public void Logout_Buyer() throws Exception{
		
		ExtentTest test1 = extent.createTest("Verify_Logout_Buyer", "Verify Buyer is logged out successfully");
		test1.log(Status.INFO, "Verify Buyer is logged out successfully");
		
		try {
			
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
