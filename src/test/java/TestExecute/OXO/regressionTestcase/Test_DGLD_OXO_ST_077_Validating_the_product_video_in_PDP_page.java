package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_077_Validating_the_product_video_in_PDP_page {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo = new GoldOxoHyvaHelper(datafile,"PDP");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Product_Video_in_PDP_page() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.search_product("Product");
			Oxo.PDP_video_validation("Product");
			
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



