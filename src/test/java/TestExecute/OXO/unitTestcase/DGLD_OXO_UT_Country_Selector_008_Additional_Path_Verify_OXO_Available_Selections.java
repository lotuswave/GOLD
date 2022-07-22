package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class DGLD_OXO_UT_Country_Selector_008_Additional_Path_Verify_OXO_Available_Selections {
	String datafile = "Oxo//OxoTestData.xlsx";	
	OxoHelper Oxo=new OxoHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Country_Selector_Available_Selections () throws Exception {

		try {
	       
			Oxo.clickcountryselector();
			Oxo.Verify_Available_Selections("CountrySelector");
			
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
		System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		 
		  
	  }



}


