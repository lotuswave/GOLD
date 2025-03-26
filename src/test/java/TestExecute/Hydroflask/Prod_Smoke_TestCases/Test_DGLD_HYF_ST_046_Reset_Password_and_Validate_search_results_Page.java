package TestExecute.Hydroflask.Prod_Smoke_TestCases;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_ST_046_Reset_Password_and_Validate_search_results_Page {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyva_PRODHelper Hydro = new GoldHydroHyva_PRODHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Reset_Password_Validate_search_Results_Page() throws Exception {

		try {
			
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.Forgot_password("AccountDetails");
			Hydro.Validating_search("aaabbcc");
			Hydro.search_results("aaabbcc");
			Hydro.popular_searches();
			Hydro.search_product("Product");
			
			
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
		System.setProperty("configFile", "Hydroflask\\prodconfig.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}