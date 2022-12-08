package TestExecute.Hydroflask_Algolia_Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroAlgolia_Helper;
import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_03_Algolia_Req003_Search_for_a_product_using_an_unorderedwords_positionGuest_User {
	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroAlgolia_Helper Hydro = new GoldHydroAlgolia_Helper(datafile,"Forms");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_the_searchforunorder_prodcuts () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.orderprodcut_search("32 oz mouth");
			Hydro.unorderprodcut_search("mouth wide oz 32");
			
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
         Login.signIn();
         Hydro.close_add();
         Hydro.acceptPrivacy();

         
                
         
	}

}
