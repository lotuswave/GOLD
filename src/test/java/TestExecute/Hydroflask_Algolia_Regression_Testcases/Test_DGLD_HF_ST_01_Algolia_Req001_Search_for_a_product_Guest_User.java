package TestExecute.Hydroflask_Algolia_Regression_Testcases;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroAlgolia_Helper;
import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;
  
public class Test_DGLD_HF_ST_01_Algolia_Req001_Search_for_a_product_Guest_User {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroAlgolia_Helper Hydro = new GoldHydroAlgolia_Helper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Search_Results() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.Validating_Algolia_search("bottle");
			Hydro.search_Algolia_results("bottle");
			//Hydro.popular_searches();
			//Hydro.carousel();
			
			

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
