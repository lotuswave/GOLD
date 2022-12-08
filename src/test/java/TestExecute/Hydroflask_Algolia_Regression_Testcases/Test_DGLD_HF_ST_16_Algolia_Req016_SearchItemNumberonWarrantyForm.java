package TestExecute.Hydroflask_Algolia_Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroAlgolia_Helper;
import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_16_Algolia_Req016_SearchItemNumberonWarrantyForm {
	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroAlgolia_Helper Hydro = new GoldHydroAlgolia_Helper(datafile,"Forms");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_the_Warranty_form_page () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.clickWarrantylink();
			Hydro.Warrantysearch_results("CAP LAGUNA");

			
			
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