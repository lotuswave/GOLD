package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.DE_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_DE_BCT_012_Checkout_with_RegisterUserCC_Simple_Configurable_and_Bundle_products_cc {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"Bundle");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Checkout_with_Register_UserCC_Simple_Configurable_and_Bundle_products () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("de_Address");
			Hydro.search_product("Product");       
			Hydro.addtocart("Product");
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
//			Hydro.search_product("Bundle product"); 
//			Hydro.Addtocart_Bundle("Bundle product");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("de_Address");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
			
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
		String url="https://mcloud-na-preprod.hydroflask.com/de";
		System.setProperty("url", url);
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}

}
