package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Account_Information_075_AlternatePath_User_Changes_Password {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Account_Information_AlternatePath_User_Changes_Email() throws Exception {

		try {
			
			
		 Hydro.validateCreateAccountpageNavigation();
		 String Email=Hydro.ValidateAccountcreationeyeicon("AccountDetails");
		 Hydro.Validate_Createaccount_signup_newsletter();	
		 Hydro.Validate_Accountinformation_change_password("AccountDetails");
	    Hydro.validate_Accountinformation_invalid_password("invalidpassword");
	    Hydro.Changepassword_signin("invalidpassword");
	    
        	
	
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
		  Hydro.AcceptAll();
		  
	  }

	

}