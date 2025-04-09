package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_CHE_IT_E2E_019_Register_User__Multiple_items_and_qty_with_GC_and_Discount_CC {

	String datafile = "Osprey_EMEA//OSPEMEA_E2E_orderDetails.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Register_User__Multiple_items_and_qty_with_GC_and_Discount_CC () throws Exception {

		try {
		Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");	
		String Description ="Register user multiple items + multiple QTY + GC + Discount + CC";
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Account");
        Osprey_ReEu.search_product("SKU-10000645 -2QTY");
		Osprey_ReEu.addtocart("SKU-10000645 -2QTY");
		Osprey_ReEu.search_product("SKU-10004993 -2QTY IT");
		Osprey_ReEu.addtocart("SKU-10004993 -2QTY IT");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Sweden-IT Address");
        Osprey_ReEu.selectshippingmethod("IT groundshipping"); 
        Osprey_ReEu.discountCode("Discount");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        String Used_GiftCode=Osprey_ReEu.Enter_Gift_card("CHE_IT_Partial_Giftcard");
        HashMap<String,String> Details=Osprey_ReEu.ordersummary_Details();
        String OrderNumber=Osprey_ReEu.addPaymentDetails("CCVisacard");
//        Osprey_ReEu.Admin_signin("Login Details");
//        Osprey_ReEu.click_Sales();
//		HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
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
		String url = "https://mcloud-na-preprod.osprey.com/che_it/";
		System.setProperty("url", url);
        Login.signIn();

	}

}
