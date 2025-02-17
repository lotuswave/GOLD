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

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Osprey_E2E");

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
        String Used_GiftCode=Osprey_ReEu.Enter_Gift_card("Partial_Giftcard");
        HashMap<String,String> Details=Osprey_ReEu.ordersummary_Details();
        String OrderNumber=Osprey_ReEu.addPaymentDetails("CCVisacard");
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn(); 

	}

}
