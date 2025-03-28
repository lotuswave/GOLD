package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_DEN_E2E_015_Register_user_checkout_with_Multiple_Products_Discount_CC {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Osprey_E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Register_user_checkout_with_Multiple_Products_Discount_CC () throws Exception {

		try {
		Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");	
		String Description ="Register user+ multiple prodcuts + discount + CC";
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Account");
        Osprey_ReEu.search_product("10004993- 3");
        Osprey_ReEu.addtocart("10004993- 3");
        Osprey_ReEu.search_product("10005000 - 3");
        Osprey_ReEu.addtocart("10005000 - 3");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Den Address");
        Osprey_ReEu.selectshippingmethod("Den shipping method"); 
        Osprey_ReEu.discountCode("Discount");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        String Used_GiftCode="NULL";
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
		String url = "https://mcloud-na-preprod.osprey.com/dk_en/";
		System.setProperty("url", url);
        Login.signIn();

	}

}
