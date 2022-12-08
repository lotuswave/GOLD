package TestExecute.OXO_Algolia_regression_Testcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroAlgolia_Helper;
import TestComponent.OXO.GoldOxoLoq_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_05_Algolia_Req005_Search_for_a_product_using_different_synonyms {
	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldHydroAlgolia_Helper Oxo = new GoldHydroAlgolia_Helper(datafile,"Loqata");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Search_for_a_product_using_different_synonyms () throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.Validating_searchs("Jar");
			Oxo.oxo_logo();
			Oxo.Validating_searchs("STRAINER");
			
		
			
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




