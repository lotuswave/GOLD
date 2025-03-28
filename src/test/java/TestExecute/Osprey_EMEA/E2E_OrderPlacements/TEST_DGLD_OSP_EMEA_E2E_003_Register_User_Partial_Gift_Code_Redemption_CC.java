package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_EMEA_E2E_003_Register_User_Partial_Gift_Code_Redemption_CC {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Osprey_E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Register_User_Partial_Gift_Code_Redemption_CC  () throws Exception {

		try {
		Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");	
		String Description ="Register_User_Partial Gift Code Redemption(The gift code should be placed above the product 1 quantity price) + CC";
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Account");
        Osprey_ReEu.search_product("10002928 - 2");
        Osprey_ReEu.addtocart("10002928 - 2");
        Osprey_ReEu.search_product("10004998 -2");
        Osprey_ReEu.addtocart("10004998 -2");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");      
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        String Used_GiftCode=Osprey_ReEu.Gift_card("Partial Giftcard");
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn(); 

	}

}
