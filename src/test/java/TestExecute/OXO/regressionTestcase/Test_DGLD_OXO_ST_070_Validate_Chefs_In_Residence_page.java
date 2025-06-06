package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_070_Validate_Chefs_In_Residence_page {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo = new GoldOxoHyvaHelper(datafile,"ChefsInResidence");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Chefs_In_Residence() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.chefs_and_Residence("Chefs in Residence");
			Oxo.alumini_Chefs("Chefs in Residence");
			
			
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}


}