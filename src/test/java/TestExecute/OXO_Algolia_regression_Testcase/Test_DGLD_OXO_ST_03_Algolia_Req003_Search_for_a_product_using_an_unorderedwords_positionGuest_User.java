package TestExecute.OXO_Algolia_regression_Testcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroAlgolia_Helper;
import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_03_Algolia_Req003_Search_for_a_product_using_an_unorderedwords_positionGuest_User {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldHydroAlgolia_Helper Oxo=new GoldHydroAlgolia_Helper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Search_Results() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.orderprodcut_search("oxo pop container");
			Oxo.unorderprodcut_search("pop container oxo");
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
