package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_StoreLocator_017_AlternatePath_UserSelects_a_Retailer_Hourstab {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro = new HydroHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Newsletter_Footer() throws Exception {

		try {
			Hydro.validate_Homepage();
	        Hydro.Click_Findstore();
	        Hydro.click_Retailer();
	        Hydro.verifingRetailerHours();
	       
	       
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
