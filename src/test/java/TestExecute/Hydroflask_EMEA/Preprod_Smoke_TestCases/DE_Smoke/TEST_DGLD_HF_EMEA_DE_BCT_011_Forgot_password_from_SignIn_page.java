package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.DE_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_DE_BCT_011_Forgot_password_from_SignIn_page {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Forgot_password_from_SignIn_page() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.Forgot_password("AccountDetails");

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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
		Login.signIn();
		Hydro.close_add();
		Hydro.acceptPrivacy();

	}

}
