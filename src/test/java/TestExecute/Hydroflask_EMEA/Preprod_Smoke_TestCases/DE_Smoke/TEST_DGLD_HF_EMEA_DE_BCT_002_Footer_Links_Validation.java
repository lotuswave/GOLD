package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.DE_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_DE_BCT_002_Footer_Links_Validation {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"FooterLinks");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Footer_Links_Validation () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.Kustomer_Links("Kustomer_DE_Pre");
			Hydro.Footer_Links("Footer_DE");
			Hydro.Footer_Dogood("Do Good");
			Hydro.Terms_and_privacy_and_AntiHumanTrafficking();
			
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
		String url="https://mcloud-na-preprod.hydroflask.com/de/";
		System.setProperty("url", url);
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
