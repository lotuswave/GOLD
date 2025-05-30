package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_EU_BCT_008_Register_User_Checkout_Simple_Configurable_Bundle_products_with_Afterpay_Payments {

	String datafile ="Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile, "Bundle");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Checkout_with_Register_UserCC_Simple_Configurable_and_Bundle_products() throws Exception {

		try {

			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.search_product("Bundle product");
			Hydro.Addtocart_Bundle("Bundle product");
			Hydro.search_product("Product");
			Hydro.addtocart("Product");
			Hydro.bottles_headerlinks("Bottles & Drinkware");
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("AccountDetails");
			Hydro.selectshippingaddress("GroundShipping method");
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
		String url="https://mcloud-na-stage4.hydroflask.com/eu";
		System.setProperty("url", url);
		Login.signIn();
		Hydro.close_add();
		Hydro.acceptPrivacy();

	}

}
