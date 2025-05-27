package TestExecute.Hydroflask.Smoke_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_040_Guest_User_Checkout_Simple_Configurable_Bundle_products_with_Afterpay_Payments {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile, "Bundle");

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
			if(Common.getCurrentURL().contains("https://mcloud-na-preprod.hydroflask.com/")) {
			Hydro.After_Pay_payment("Afterpay");
			}else {
		            Hydro.selectshippingaddress("2 Day method");
		            Hydro.clickSubmitbutton_Shippingpage();
					Hydro.After_Pay_payment("Afterpay");
					Hydro.Kalrna_Payment("Klarna Visa Payment");
			}
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
