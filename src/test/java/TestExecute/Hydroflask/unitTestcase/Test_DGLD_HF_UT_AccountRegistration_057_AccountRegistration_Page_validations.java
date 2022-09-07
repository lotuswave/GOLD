package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_AccountRegistration_057_AccountRegistration_Page_validations {

	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_AccountRegistation_page_validation() throws Exception {

		try {
			
			
			Hydro.validateCreateAccountpageNavigation();
			String Email=Hydro.ValidateAccountcreationeyeicon("AccountDetails");
		Hydro.Validate_Createaccount_signup_newsletter();			
		Hydro.Admin_signin("AccountDetails");
       	Hydro.Customers();
        	Hydro.Allcustomers();
        	Hydro.Viewcustomer(Email);
        	Hydro.Clearfilter();
        	Hydro.Marketing();
        	Hydro.Admin_newsletter_validation(Email);
        	
	
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


