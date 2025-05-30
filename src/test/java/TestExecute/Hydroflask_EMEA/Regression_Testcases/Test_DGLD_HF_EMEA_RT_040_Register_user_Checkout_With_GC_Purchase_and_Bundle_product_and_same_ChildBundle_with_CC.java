package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_EMEA_RT_040_Register_user_Checkout_With_GC_Purchase_and_Bundle_product_and_same_ChildBundle_with_CC {

	String datafile ="Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile, "Bundle");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Register_user_Checkout_With_GC_Purchase_and_Bundle_product_and_same_ChildBundle_with_CC () throws Exception {

		try {

			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.Gift_cards("Hydro Gift Card");
			Hydro.Card_Value("price");
//			Hydro.search_product("Bundle product");
//			Hydro.Addtocart_Bundle("Bundle product");
//			Hydro.search_product("Product");                                    //needs to add buddle child product
//			Hydro.addtocart("Product");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("AccountDetails");
			Hydro.selectshippingaddress("GroundShipping method");
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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
		Login.signIn();
		Hydro.close_add();
		Hydro.acceptPrivacy();

	}

}
