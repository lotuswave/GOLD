package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class DGLD_HF_UT_Footer_003_ExceptionPath_UserEntersInvalidEmailforNewsletter {
	
		
		String datafile = "Hydroflask//HydroTestData.xlsx";	
		HydroHelper Hydro=new HydroHelper(datafile);
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validate_newsletter_subscription_footer() throws Exception {

			try {
		       
		        Hydro.Invalid_email_newsletter("Invalid Email");
		        Hydro.Empty_Email();
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
			 
			  
		  }


}
