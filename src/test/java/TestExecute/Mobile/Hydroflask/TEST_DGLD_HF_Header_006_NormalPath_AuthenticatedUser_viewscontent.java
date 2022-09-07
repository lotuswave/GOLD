package TestExecute.Mobile.Hydroflask;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestComponent.Hydroflask.HydroHelper_Mobile;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;
import io.appium.java_client.remote.MobileCapabilityType;

public class TEST_DGLD_HF_Header_006_NormalPath_AuthenticatedUser_viewscontent {


	String datafile = "Hydroflask//HydroTestData.xlsx";	
	
	HydroHelper_Mobile Hydro=new HydroHelper_Mobile(datafile, "DataSet");

	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_authenticateduser_viewscontent() throws Exception {

		try {
			String device=System.getProperty("dev","ios");
		   Hydro.click_singinButton();
			if(device.equalsIgnoreCase("ios")) {
	       Hydro.login_Hydroflask("AccountDetails");
	       Hydro.Validating_search();
	       Hydro.minicart();
	       Hydro.Validate_Myaccountoptions("MyAccountoptions");}
	       
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	
	@BeforeTest
	  public void startTest() throws Exception {

		
		String device=System.getProperty("dev","ANDRIOD");
		System.setProperty("configFile", "Hydroflask/mobile_config.properties");
		if(device.equalsIgnoreCase("ios")) {
			System.setProperty("configFile", "Hydroflask/mobile_config_ios.properties");
		}
		  Login.mobilesignIn(device);
			/*
			 * String Device="ios"; DesiredCapabilities cap = new DesiredCapabilities();
			 * if(Device.equalsIgnoreCase("ios")) {
			 * 
			 * cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.0");
			 * //cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4");
			 * cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 13");
			 * //cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12");
			 * cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			 * cap.setCapability(MobileCapabilityType.BROWSER_NAME, "safari"); } else {
			 * 
			 * 
			 * cap.setCapability("browserName", "Chrome");
			 * 
			 * cap.setCapability("platformVersion","11");
			 * 
			 * cap.setCapability("deviceName","sdk_pixel3");
			 * cap.setCapability("udid","emulator-5554");
			 * 
			 * cap.setCapability("platformName","ANDROID");
			 * cap.setCapability("noReset",true); } //AppiumDriver<MobileElement> driver=new
			 * AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			 * 
			 * RemoteWebDriver driver = new RemoteWebDriver(new
			 * URL("http://127.0.0.1:4723/wd/hub"), cap);
			 * //driver.get("https://jetrails-stg.hydroflask.com/");
			 * driver.get("https://mcloud-na-stage.hydroflask.com/");
			 * BaseDriver.driver=driver;
			 */
		  
	  }

	
	
}
