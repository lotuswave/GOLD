package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class DGLD_HF_UT_Header_001_NormalPath_Unauthenticateduser_viewscontent {


	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void customerLogin() throws Exception {

		try {
	        Hydro.Navigate_MYAccoun("pagetitle");
	        Hydro.minicart();
	       
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

		  Login.signIn();
		 
		  
	  }

	
	
}
