package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_047_Submit_Product_RecallForm {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo = new GoldOxoHelper(datafile,"Forms");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Product_RecallForm() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_Voulantry_Recall();
			Oxo.voluntary_Recall("contactusEmail"); // Prod
			
			
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