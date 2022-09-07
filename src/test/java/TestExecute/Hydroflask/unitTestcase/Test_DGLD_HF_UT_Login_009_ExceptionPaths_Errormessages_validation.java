package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Login_009_ExceptionPaths_Errormessages_validation {

	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Login_Page() throws Exception {

		try {

			Hydro.click_singinButton();
			Hydro.login_Invalidemail_Errormsg_Validation("Invalid Email");
			Hydro.login_Invalidcrdentials_Errormsg_Validation("invalidpassword");
			Hydro.login_Invalidcrdentials_Errormsg_Validation("UnregisteredEmail");
			Hydro.login_Missingfields_Errormsg_Validation();
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
		 Hydro.ClosADD();

	}

}
