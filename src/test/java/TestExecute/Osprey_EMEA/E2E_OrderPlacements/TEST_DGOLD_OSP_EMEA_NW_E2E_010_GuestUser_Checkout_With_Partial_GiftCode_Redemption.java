package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2EHelper;
import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGOLD_OSP_EMEA_NW_E2E_010_GuestUser_Checkout_With_Partial_GiftCode_Redemption {

	String datafile = "Osprey_EMEA//OSPEMEA_E2E_orderDetails.xlsx";
	OspreyEMEA_E2EHelper Osprey_ReEu = new OspreyEMEA_E2EHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void GuestUser_Checkout_With_Partial_GiftCode_Redemption_Product_QTY2 () throws Exception {

		try {
			
			Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");
			String Description = "Norway Store-Partial Gift Code Redemption(The gift code should be placed above the product 1 quantity price) + CC";
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.search_product("SKU-10004993 -2QTY");
			Osprey_ReEu.simple_addtocart("SKU-10004993 -2QTY");
			Osprey_ReEu.minicart_Checkout();
			Osprey_ReEu.addDeliveryAddress_Guestuser("Norway-GuestAddress");
//			Osprey_ReEu.selectshippingmethod("GroundShipping method");
			Osprey_ReEu.Enter_Gift_card("Partial_Giftcard");
			String Used_GiftCode="Yes";
//			Osprey_ReEu.clickSubmitbutton_Shippingpage();
			String OrderNumber =Osprey_ReEu.updatePaymentAndSubmitOrder("CCDiscovercard");
			Osprey_ReEu.Admin_signin("Login Details");
		    Osprey_ReEu.click_Sales();
			HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
			Osprey_ReEu.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("workato"),Used_GiftCode);  
			
			
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}


	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
//		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
		
		String url = "https://mcloud-na-preprod.osprey.com/no_nb/";
		System.setProperty("url", url);
        Login.signIn();
        

	}

}
