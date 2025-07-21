package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_EMEA_RT_036_Write_a_Review_and_ask_a_question {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"Sheet1");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Review_and_Questions () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Review product"); 
			Hydro.review("review");
			Hydro.Ask_a_question("Ask a question");
			
			
			
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}

}
