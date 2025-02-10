package TestExecute.Hydroflask.Archive;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_131_GuestCheckout_Promocode_with_Afterpay {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Checkout_with_Register_UserCC_Simple_Configurable_and_Bundle_products() throws Exception {

		try {

			Hydro.verifingHomePage();
			Hydro.search_product("Product");
			Hydro.addtocart("Product");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
			Hydro.selectshippingaddress("GroundShipping method");
		     Hydro.discountCode("Discount");
			Hydro.After_Pay_payment("Afterpay");
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
		Hydro.acceptPrivacy();

	}

}
