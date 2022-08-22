package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_CheckoutShippingStep_051_AlternatePath_Authenticated_User_Has_No_Saved_Addresses {

	
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Authenticated_User_Has_No_Saved_Addresses() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.validateCreateAccountpageNavigation();
			Hydro.validateaccountcreationPassword("AccountDetails");
			Hydro.headerlinks("Accessories");
			Hydro.addtocart("Product");
			Hydro.minicart_viewcart();
			Hydro.minicart_Checkout();
		
		Hydro.populate_Shippingaddress_fields("AccountDetails");
		Hydro.selectshippingmethod("AccountDetails");
		Hydro.Validate_Paymentpage();
		
			

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
