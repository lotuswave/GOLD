package TestExecute.OXO_Algolia_regression_Testcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroAlgolia_Helper;
import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_01_Algolia_Req001_Search_for_a_product_Guest_User {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldHydroAlgolia_Helper Oxo=new GoldHydroAlgolia_Helper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Search_Results() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.Validating_Algolia_search("pop");
			//Oxo.Algolia_search_results("pop");
		//	Oxo.popular_searches();
		//	Oxo.carousel();
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








