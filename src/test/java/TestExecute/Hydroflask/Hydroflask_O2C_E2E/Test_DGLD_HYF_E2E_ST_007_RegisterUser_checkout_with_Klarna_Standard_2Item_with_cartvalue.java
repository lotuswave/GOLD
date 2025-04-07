package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_007_RegisterUser_checkout_with_Klarna_Standard_2Item_with_cartvalue {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_RegisterUser_checkout_with_Klarna_Standard_2Item_with_cartvalue () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Register user checkout with Klarna (Standard) - 2 Item with cart value < $39";
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails_TC7");
			Hydro.search_product("SKU-T20CPB001");     
			Hydro.addtocart("SKU-T20CPB001");
			Hydro.search_product("SKU-CFX454");     
			Hydro.Configurable_addtocart_pdp("SKU-CFX454");
			Hydro.minicart_Checkout();
//			Hydro.RegaddDeliveryAddress("AccountDetails");
			String Used_GiftCode = "NULL";
            Hydro.selectshippingaddress("GroundShipping method");
            HashMap<String,String> Details=Hydro.ordersummary_Details();
            String OrderNumber= Hydro.Kalrna_Payment("Klarna Visa Payment");
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
