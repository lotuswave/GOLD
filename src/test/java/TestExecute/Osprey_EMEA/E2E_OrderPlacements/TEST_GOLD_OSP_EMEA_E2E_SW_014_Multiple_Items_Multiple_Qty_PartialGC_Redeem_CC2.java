package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_GOLD_OSP_EMEA_E2E_SW_014_Multiple_Items_Multiple_Qty_PartialGC_Redeem_CC2 {

	String datafile = "Osprey_EMEA//OSPEMEA_E2E_orderDetails.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verify_Two_Items_Qty2_PartialGC_Redeem () throws Exception {

		try {
			
			Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");
			String Description = "Sweden Store-Multiple Items + Multiple Qty + Partial GC redeem (The gift code should be placed above the product 1 quantity price)+ CC";
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.search_product("SKU-10004993 -3QTY");
			Osprey_ReEu.simple_addtocart("SKU-10004993 -3QTY");
			Osprey_ReEu.search_product("SKU-10004966 -3QTY");
			Osprey_ReEu.simple_addtocart("SKU-10004966 -3QTY");
			Osprey_ReEu.minicart_Checkout();
			Osprey_ReEu.addDeliveryAddress_Guestuser("Sweden-GuestAddress");
//			Osprey_ReEu.selectshippingmethod("GroundShipping method");
			String Used_GiftCode=Osprey_ReEu.Enter_Gift_card("sweden_Partial_Giftcard");
//			Osprey_ReEu.clickSubmitbutton_Shippingpage();
			HashMap<String,String> Details=Osprey_ReEu.ordersummary_Details();
			String OrderNumber =Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
//			Osprey_ReEu.Admin_signin("Login Details");
//		    Osprey_ReEu.click_Sales();
//			HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
			Osprey_ReEu.writeOrderNumber(Description,OrderNumber,Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"));
			
			
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
//		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
		
		String url = "https://mcloud-na-preprod.osprey.com/se_sv/";
		System.setProperty("url", url);
        Login.signIn();
        

	}

}
