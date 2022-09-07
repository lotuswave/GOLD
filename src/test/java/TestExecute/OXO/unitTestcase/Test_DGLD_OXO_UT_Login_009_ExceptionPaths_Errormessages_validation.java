package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Login_009_ExceptionPaths_Errormessages_validation {

	String datafile = "Oxo//OxoTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Login_Page() throws Exception {

		try {
	       
			Oxo.click_singinButton();
			Oxo.login_Invalidemail_Errormsg_Validation("Invalid Email");
			Oxo.login_Invalidcrdentials_Errormsg_Validation("invalidpassword");
			Oxo.login_Invalidcrdentials_Errormsg_Validation("UnregisteredEmail");
			Oxo.login_Missingfields_Errormsg_Validation();
			
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
//		Common.closeAll();

	}
	
	
	@BeforeTest
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		 
		  
	  }


}
