package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.FR_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_FR_RT_009_Register_user_checkout_PP_with_Multiple_products_with_Discount_differentbilling_and_shipping {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"Bundle");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_user_checkout_PP_with_Multiple_products_with_Discount_differentbilling_and_shipping () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
//			Hydro.search_product("Bundle product"); 
//			Hydro.Addtocart_Bundle("Bundle product");
			Hydro.search_product("Product");     
			Hydro.addtocart("Product");
			Hydro.bottles_headerlinks("Bottles & Drinkware");
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("fr_Address");
			Hydro.discountCode("Discount");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();
            Hydro.register_billingAddress("fr_BillingDetails");
            Hydro.payPal_Payment("PaypalDetails");

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
		String url="https://mcloud-na-stage4.hydroflask.com/fr";
		System.setProperty("url", url);
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
