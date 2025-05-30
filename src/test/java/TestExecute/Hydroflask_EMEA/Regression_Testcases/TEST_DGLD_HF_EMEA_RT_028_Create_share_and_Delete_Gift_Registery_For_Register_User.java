package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_028_Create_share_and_Delete_Gift_Registery_For_Register_User {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"Sheet1");
	

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Create_share_and_Delete_Gift_Registery_For_Register_User () throws Exception {

		try {
			
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("Giftaccount");
			Hydro.giftCreation("Birthday");
			Hydro.share_giftcard("AccountDetails");
			Hydro.delete_giftcard();
      
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
