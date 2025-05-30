package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.ES_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_ES_BCT_003_Create_Account_Functionality {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Create_Account_Functionality() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.createaccount_verfication("Invalid details");
            Hydro.create_account("New Account Details");
			
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
		String url="https://mcloud-na-stage4.hydroflask.com/es";
		System.setProperty("url", url);
         Login.signIn();
         Hydro.close_add();
         Hydro.acceptPrivacy();

	}

}
