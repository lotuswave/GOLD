package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_CHE_FR_E2E_018_Guest_User_Multiple_Item_Full_Gift_code_Redeem_with_Multiple_QTY {

	String datafile = "Osprey_EMEA//OSPEMEA_E2E_orderDetails.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guest_User_Multiple_Item_Full_Gift_code_Redeem_with_Multiple_QTY () throws Exception {

		try {
			
	     	Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");
			String Description = "Full Gift Code Redemption + Multiple Item + Multiple Qty ";
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.search_product("CHE SKU-10002926");
			Osprey_ReEu.Configurable_addtocart_pdp("SKU-10002926 -2QTY CHE");
			Osprey_ReEu.search_product("SKU-10000645 -2QTY");
			Osprey_ReEu.addtocart("SKU-10000645 -2QTY");
			Osprey_ReEu.minicart_Checkout();
			Osprey_ReEu.addDeliveryAddress_Guestuser("Sweden-fr Address");
			String Used_GiftCode=Osprey_ReEu.Enter_Gift_card("CHE_FR_Full Gift Card");
			Osprey_ReEu.selectshippingmethod("CHE groundshipping");
			HashMap<String,String> Details=Osprey_ReEu.ordersummary_Details();
			String OrderNumber=Osprey_ReEu.giftCardSubmitOrder();
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
		
		String url = "https://mcloud-na-preprod.osprey.com/che_fr/";
		System.setProperty("url", url);
        Login.signIn();
        

	}

}
