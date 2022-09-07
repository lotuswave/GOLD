package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Header_004_NormalPath_AuthenticatedUser_viewscontent {

	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_authenticateduser_views_headercontent() throws Exception {

		try {
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.toplevelnavigation("headerlinks");
			Hydro.Validating_search();
			Hydro.minicart();
			Hydro.Validate_Myaccountoptions("MyAccountoptions");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();

	}

	 @BeforeTest
	
	    public void startTest() throws Exception {
			System.setProperty("configFile", "Hydroflask\\config.properties");
   Login.signIn();
   Hydro.ClosADD();
	         

	      }


}
