package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_CheckoutShippingStep_046_AdditionalPath_Authenticateduser_edits_saved_address {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Authenticateduser_edits_saved_address() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product_pdp("search");

			//Hydro.headerlinks("Accessories");
			//Hydro.addtocart("Product");
			Hydro.minicart_viewcart();
			Hydro.minicart_Checkout();
			Hydro.validate_ExistingUser_Login_Checkoutpage("AccountDetails");
			Hydro.Validate_Signin_Checkoutpage();
			Hydro.click_EditAddress();
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
		  Hydro.ClosADD();

	}

}
