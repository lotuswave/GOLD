package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Login_010_NormalPath_User_Logins_Logout {

	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Login_logout() throws Exception {

		try {
			
	       Hydro.click_singinButton();
	      Hydro.Validate_eyeicon("AccountDetails");
	      Hydro.validate_signin();
	     Hydro.signout();
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

		  Login.signIn();
		  Hydro.ClosADD();
		 
		  
	  }

	

}
