package VF_Screenshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class LTL_Seller_Quote_Response_screenshot {
	
	public static void CaptureScreenshot(WebDriver driver){
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try{
			
			FileUtils.copyFile(src, new File("..//Veri-Fuel_Hybrid_Framework//Output Screenshots//LTL_Seller_Quote_Response//"+System.currentTimeMillis()+".png"));
		}
		catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		
		
	}
	
	
	

}
