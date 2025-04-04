package TestExecute.Drybar_US.Drybar_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_006_Guestuser_checkout_3items_Expeditedshipping_GiftCode_redeeem_Partial_Paypal {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"Drybar_E2E");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guestuser_checkout_3items_Expeditedshipping_GiftCode_redeeem () throws Exception {

		try {
			Drybar.prepareOrdersData("Drybar_E2E_orderDetails.xlsx");
			String Description="Guest user checkout with 3 items + Expedited shipping + Gift Code redeeem Partial + Paypal";
			Drybar.Verify_Homepage();
			Drybar.search_product("900-2230-1 Product");
	        Drybar.addtocart("900-2230-1 Product");
	        Drybar.search_product("900-0630-1 Product");
	        Drybar.addtocart("900-0630-1 Product");
	        Drybar.search_product("900-0700-4 Product");
	        Drybar.addtocart("900-0700-4 Product");
	        Drybar.minicart_Checkout();
	        Drybar.addDeliveryAddress_Guestuser("Address");
	        String Used_GiftCode= Drybar.gitCard("DRY-PRPD-TEST-QA");
	        Drybar.selectshippingmethod("ExpeditedShipping method");
	    	HashMap<String,String> Details=Drybar.ordersummary_Details();
	        String OrderNumber=Drybar.payPal_Payment("PaypalDetails");
//	        Drybar.Admin_signin("AccountDetails");
//	        Drybar.click_Sales();
//	        HashMap<String, String> Orderstatus1= Drybar.order_verfication(OrderNumber);
	        Drybar.writeOrderNumber(Description,OrderNumber,Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"));
	         
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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
//        Drybar.close_add();
        

	}

}


