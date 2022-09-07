package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_CheckoutShippingStep_037_AlternatePath_UnauthenticatedUserEntersEmailAssociatedWithAnAccount {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void UnauthenticatedUserEntersEmailAssociatedWithAnAccount() throws Exception {

		try {
			Hydro.verifingHomePage();
			//Hydro.headerlinks("QA_Testing");
			Hydro.search_product("Product");
			Hydro.addtocart("Product");
			Hydro.minicart_viewcart();
			Hydro.minicart_Checkout();
			Hydro.validate_ExistingUser_Login_Checkoutpage("AccountDetails");
			Hydro.Validate_Signin_Checkoutpage();
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
