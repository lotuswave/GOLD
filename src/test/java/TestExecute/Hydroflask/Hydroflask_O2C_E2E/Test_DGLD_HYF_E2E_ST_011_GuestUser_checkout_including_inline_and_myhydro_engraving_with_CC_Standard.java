package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_011_GuestUser_checkout_including_inline_and_myhydro_engraving_with_CC_Standard {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_GuestUser_checkout_including_inline_and_myhydro_engraving_with_CC_Standard () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Guest user checkout including inline & my hydro engraving with CC - Standard";
			Hydro.verifingHomePage();
			Hydro.search_product("SKU-TT40PS474");     
			Hydro.Configurable_addtocart_pdp("SKU-TT40PS474");
			Hydro.search_product("SKU-P-S21001 - 1"); 
			Hydro.Myhydro_Graphic("SKU-P-S21001 - 1");
			Hydro.enraving_Checkout("Graphic");
			Hydro.search_product("SKU-SCS415 -1QTY");     
			Hydro.addtocart("SKU-SCS415 -1QTY");
			Hydro.search_product("SKU-BO56 -1QTY");     
			Hydro.addtocart("SKU-BO56 -1QTY");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
			String Used_GiftCode = "NULL";
            Hydro.selectshippingaddress("GroundShipping method");
            HashMap<String,String> Details=Hydro.ordersummary_Details();
            String OrderNumber=Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
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
