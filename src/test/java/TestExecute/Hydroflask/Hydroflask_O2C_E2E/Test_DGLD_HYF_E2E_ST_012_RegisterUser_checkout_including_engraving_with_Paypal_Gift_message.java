package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_012_RegisterUser_checkout_including_engraving_with_Paypal_Gift_message {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_RegisterUser_checkout_including_engraving_with_Paypal_Gift_messag () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Register user checkout including engraving with Paypal (standard)+ Gift message (More than 50 characters)";
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.search_product("SKU-P-S21001 - 1"); 
			Hydro.Add_Myhydro_Text("SKU-P-S21001 - 1");
			Hydro.enraving_Checkout("SKU-P-S21001 - 1");
			Hydro.search_product("SKU-TT32PS678");     
			Hydro.addtocart("SKU-TT32PS678");
			Hydro.search_product("SKU-SCS415 -1QTY");     
			Hydro.addtocart("SKU-SCS415 -1QTY");
			Hydro.minicart_viewcart();
			Hydro.Gift_message("Gift Message above 50");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("AccountDetails");
			String Used_GiftCode = "NULL";
            Hydro.selectshippingaddress("GroundShipping method");
            HashMap<String,String> Details=Hydro.ordersummary_Details();
            String OrderNumber=Hydro.payPal_Payment("PaypalDetails");
        
			Hydro.writeOrderNumber(Description,OrderNumber,Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"));

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
