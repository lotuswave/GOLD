package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_002_Registeruser_checkout_with_CC_4Items_each_qty2_Standard_Giftmessage {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Registeruser_checkout_with_CC_4Items_each_qty2_Standard_Giftmessage () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Register user checkout with CC -  4 Items+each qty 2 + Standard + Gift message(Less than 50 charecters)";
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.search_product("SKU-TT32PS678 -2QTY");     
			Hydro.addtocart("SKU-TT32PS678 -2QTY");
			Hydro.search_product("SKU-TT40PS474 -2QTY");     
			Hydro.Configurable_addtocart_pdp("SKU-TT40PS474 -2QTY");
			Hydro.search_product("SKU-T20CPB464 -2QTY");     
			Hydro.addtocart("SKU-T20CPB464 -2QTY");
			Hydro.search_product("SKU-BO56 -2QTY");     
			Hydro.addtocart("SKU-BO56 -2QTY");
			Hydro.minicart_viewcart();
			Hydro.Gift_message("GiftMessage");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("AccountDetails");
			String Used_GiftCode = "NULL";
            Hydro.selectshippingaddress("GroundShipping method");
            String OrderNumber=Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
            Hydro.Admin_signin("Login Details");
            Hydro.click_Sales();
			HashMap<String,String> Orderstatus1 = Hydro.Admin_Order_Details(OrderNumber);
			Hydro.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("warkato"),Used_GiftCode);

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
