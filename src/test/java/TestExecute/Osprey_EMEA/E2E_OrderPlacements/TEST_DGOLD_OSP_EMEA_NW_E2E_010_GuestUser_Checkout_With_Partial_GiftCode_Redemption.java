package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGOLD_OSP_EMEA_NW_E2E_010_GuestUser_Checkout_With_Partial_GiftCode_Redemption {

	String datafile = "Osprey_EMEA//OSPEMEA_E2E_orderDetails.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void GuestUser_Checkout_With_Partial_GiftCode_Redemption_Product_QTY2 () throws Exception {

		try {
			
			Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");
			String Description = "Norway Store-Partial Gift Code Redemption(The gift code should be placed above the product 1 quantity price) + CC";
//			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.search_product("SKU-10004993 -3QTY");
			Osprey_ReEu.simple_addtocart("SKU-10004993 -3QTY");
			Osprey_ReEu.minicart_Checkout();
			Osprey_ReEu.addDeliveryAddress_Guestuser("Norway-GuestAddress");
//			Osprey_ReEu.selectshippingmethod("GroundShipping method");
			Osprey_ReEu.Enter_Gift_card("Partial_Giftcard");
			String Used_GiftCode="Yes";
//			Osprey_ReEu.clickSubmitbutton_Shippingpage();
			  HashMap<String,String> Details=Osprey_ReEu.ordersummary_Details();
			String OrderNumber =Osprey_ReEu.updatePaymentAndSubmitOrder("CCDiscovercard");
			Osprey_ReEu.Admin_signin("Login Details");
		    Osprey_ReEu.click_Sales();
			HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
			Osprey_ReEu.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("workato"),Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"),Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));
			
			
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
		
		String url = "https://mcloud-na-preprod.osprey.com/no_nb/";
		System.setProperty("url", url);
        Login.signIn();
        

	}

}
