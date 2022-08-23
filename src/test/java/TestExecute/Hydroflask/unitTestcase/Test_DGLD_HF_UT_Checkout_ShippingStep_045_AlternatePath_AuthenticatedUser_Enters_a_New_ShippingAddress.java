package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Checkout_ShippingStep_045_AlternatePath_AuthenticatedUser_Enters_a_New_ShippingAddress {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_AuthenticatedUser_Enters_a_New_ShippingAddress() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product_pdp("search");
			Hydro.minicart_Checkout();
			Hydro.validate_ExistingUser_Login_Checkoutpage("AccountDetails");
			Hydro.Validate_Signin_Checkoutpage();
			Hydro.click_AddNewAdress_ShippingPage();
			Hydro.ShippingAddress("ShippingAddress");
			Hydro.Validate_Update_NewAddress_Verification("ShippingAddress");

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
