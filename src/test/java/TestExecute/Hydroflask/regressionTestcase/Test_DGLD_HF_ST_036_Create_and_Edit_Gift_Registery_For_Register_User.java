package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_036_Create_and_Edit_Gift_Registery_For_Register_User {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile,"Sheet1");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Create_and_Edit_Gift_Registery_For_Register_User () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.giftCreation("Birthday");
			Hydro.edit_gift("AccountDetails");

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
	}

}
