package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_ProdealSignup_67_to_71_AlternatePath_AuthenticatedUser_SelectstoEnter_AccessCode {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_AuthenticatedUser_SelectstoEnter_AccessCode() throws Exception {

		try {
			
			Hydro.cookiebanner();
			Hydro.verifingHomePage();
			Hydro.ProdelerPage("AccountDetails");
			Hydro.Prodeal_invalid_access_code("Prodeal invalid access code");
			Hydro.prodeal_access_code("Prodeal access code");
			Hydro.click_MyAccount();
		    Hydro.MyAccount_Prodeal();
		    
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

	}
	
}


