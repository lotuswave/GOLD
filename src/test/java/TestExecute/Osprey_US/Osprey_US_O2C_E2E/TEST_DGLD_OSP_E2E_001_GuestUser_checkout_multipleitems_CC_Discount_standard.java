package TestExecute.Osprey_US.Osprey_US_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_E2E_001_GuestUser_checkout_multipleitems_CC_Discount_standard {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSE2EHelper Osprey_ReEu = new GoldOspreyUSE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_checkout_multipleitems_CC_Discount_standard () throws Exception {

		try {
			 
			 Osprey_ReEu.prepareOrdersData("OspreyUS_E2E_orderDetails.xlsx");
			 String Description ="Guest user checkout  multiple items + CC + Discount + standard";
		     Osprey_ReEu.search_product("SKU-10005235 -3QTY");
		     Osprey_ReEu.addtocart("SKU-10005235 -3QTY");
		     Osprey_ReEu.search_product("SKU-10005151 -2QTY");
		     Osprey_ReEu.addtocart("SKU-10005151 -2QTY");
		     Osprey_ReEu.minicart_Checkout();
		     Osprey_ReEu.addDeliveryAddress_Guestuser("Account");
		     Osprey_ReEu.selectshippingmethod("GroundShipping method");
		     String Used_GiftCode="NULL";
		     Osprey_ReEu.discountCode("Discount");
		     Osprey_ReEu.clickSubmitbutton_Shippingpage();
		     HashMap<String,String> Details=Osprey_ReEu.ordersummary_Details();
		     String OrderNumber= Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
//		     Osprey_ReEu.Admin_signin("Login Details");
//		     Osprey_ReEu.click_Sales();
//			 HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
//			 Osprey_ReEu.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("workato"),Orderstatus1.get("AdminOrderstatus"),Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"),Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));  
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}
	
	
}
