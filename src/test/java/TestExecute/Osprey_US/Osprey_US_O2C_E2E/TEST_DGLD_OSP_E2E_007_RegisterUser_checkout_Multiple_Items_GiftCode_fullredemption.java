package TestExecute.Osprey_US.Osprey_US_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_E2E_007_RegisterUser_checkout_Multiple_Items_GiftCode_fullredemption {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSE2EHelper Osprey_ReEu = new GoldOspreyUSE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_checkout_Multiple_Items_GiftCode_fullredemption () throws Exception {

		try {
			 
			 Osprey_ReEu.prepareOrdersData("OspreyUS_E2E_orderDetails.xlsx");
			 String Description ="Register user checkout Multiple Items + Gift Code full redemption";
			 Osprey_ReEu.click_singinButton();
		     Osprey_ReEu.Login_Account("Account");
		     Osprey_ReEu.search_product("SKU-10002926 -2QTY");
		     Osprey_ReEu.addtocart("SKU-10002926 -2QTY");
		     Osprey_ReEu.search_product("SKU-10005235 -3QTY");
		     Osprey_ReEu.addtocart("SKU-10005235 -3QTY");
		     Osprey_ReEu.minicart_Checkout();
		     Osprey_ReEu.RegaddDeliveryAddress("Account");
		     Osprey_ReEu.selectshippingmethod("GroundShipping method");
		     String Used_GiftCode=Osprey_ReEu.Gift_card("Full_RedeemGiftcard");;
		     String OrderNumber= Osprey_ReEu.giftCardSubmitOrder();
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
		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}
	
	
}
