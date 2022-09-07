package TestExecute.Mobile.Hydroflask;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestComponent.Hydroflask.HydroHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class DGLD_HF_UT_Login_005_ExceptionPaths_Errormessages_validation {

	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper_Mobile Hydro=new HydroHelper_Mobile(datafile, "DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Login_Page() throws Exception {

		try {
	       
	        // Hydro.click_singinButton();
	        Hydro.login_Invalidemail_Errormsg_Validation("Invalid Email");
	        Hydro.login_Invalidcrdentials_Errormsg_Validation("invalidpassword");
	        Hydro.login_Invalidcrdentials_Errormsg_Validation("UnregisteredEmail");
	        Hydro.login_Missingfields_Errormsg_Validation();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
	
	
	@BeforeTest
	  public void startTest() throws Exception {

		//  Login.signIn();
		System.setProperty("configFile", "Hydroflask/mobile_config.properties");
		  Login.mobilesignIn("ios");
		 
		  
	  }

}
