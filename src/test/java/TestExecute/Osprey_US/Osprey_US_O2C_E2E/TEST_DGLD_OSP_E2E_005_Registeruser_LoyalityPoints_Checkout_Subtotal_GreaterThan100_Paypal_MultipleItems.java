package TestExecute.Osprey_US.Osprey_US_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_E2E_005_Registeruser_LoyalityPoints_Checkout_Subtotal_GreaterThan100_Paypal_MultipleItems {
	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSE2EHelper Osprey_ReEu = new GoldOspreyUSE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Registeruser_LoyalityPoints_Checkout_Subtotal_GreaterThan100_Paypal_MultipleItems () throws Exception {

		try {
			 
			 Osprey_ReEu.prepareOrdersData("OspreyUS_E2E_orderDetails.xlsx");
			 String Description ="Register user loyality points checkout + Subtotal > 100$ + Paypal + Multiple items ";
			  Osprey_ReEu.click_singinButton();
		      Osprey_ReEu.Login_Account("Account");
		     Osprey_ReEu.search_product("SKU-10005235 -2QTY");
		     Osprey_ReEu.addtocart("SKU-10005235 -2QTY");
//		     Osprey_ReEu.search_product("SKU-10005151");
//		     Osprey_ReEu.addtocart("SKU-10005151");
		     Osprey_ReEu.minicart_Checkout();
		     Osprey_ReEu.RegaddDeliveryAddress("Account");
		     Osprey_ReEu.selectshippingmethod("GroundShipping method");
		     String Used_GiftCode="NULL";
		     Osprey_ReEu.TwentyFive_percent_Reward_Points("$25 Off (500 point)");
		     Osprey_ReEu.clickSubmitbutton_Shippingpage();
		     String OrderNumber= Osprey_ReEu.payPal_Payment("PaypalDetails");
		     Osprey_ReEu.Admin_signin("Login Details");
		     Osprey_ReEu.click_Sales();
			 HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
			 Osprey_ReEu.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Used_GiftCode);
		    
	 
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        Osprey_ReEu.acceptPrivacy();
        

	}
	
	
}
