package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_001_Registereduser_checkout_with_Master_CC_4Items_each_qty_2_Giftmessage_Standard {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Registereduser_checkout_with_Master_CC_4Items_each_qty_2_Giftmessage_Standard () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Registered user checkout with Master CC + 4 Items, each qty - 2 + Gift message + Standard";
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails_TC1");
			Hydro.search_product("SKU-T28PS110 -2QTY");     
			Hydro.addtocart("SKU-T28PS110 -2QTY");
			Hydro.search_product("SKU-T20CPB001 -2QTY");     
			Hydro.addtocart("SKU-T20CPB001 -2QTY");
			Hydro.search_product("SKU-S24FS678 -2QTY");     
			Hydro.addtocart("SKU-S24FS678 -2QTY");
			Hydro.search_product("SKU-TT32PS678 -2QTY");     
			Hydro.addtocart("SKU-TT32PS678 -2QTY");
			Hydro.minicart_viewcart();
			Hydro.Gift_message("Gift Message above 50");
			Hydro.minicart_Checkout();
//			Hydro.RegaddDeliveryAddress("AccountDetails");
			String Used_GiftCode = "NULL";
            Hydro.selectshippingaddress("GroundShipping method");
        	HashMap<String,String> Details=Hydro.ordersummary_Details();
            String OrderNumber=Hydro.updatePaymentAndSubmitOrder("CCMastercard");
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
