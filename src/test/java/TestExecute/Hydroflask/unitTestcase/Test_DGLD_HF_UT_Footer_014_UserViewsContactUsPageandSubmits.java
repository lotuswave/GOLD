package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Footer_014_UserViewsContactUsPageandSubmits {

	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_contactus_page_submits() throws Exception {

		try {
	       Hydro.verifingHomePage();
	      Hydro.UserViewsContactUsPageandSubmits("AccountDetails");
	       
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.ClosADD();
		  
	  }


}
