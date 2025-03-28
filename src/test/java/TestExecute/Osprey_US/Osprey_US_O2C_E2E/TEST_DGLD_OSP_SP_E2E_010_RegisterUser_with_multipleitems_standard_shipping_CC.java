package TestExecute.Osprey_US.Osprey_US_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_SP_E2E_010_RegisterUser_with_multipleitems_standard_shipping_CC {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSE2EHelper Osprey_ReEu = new GoldOspreyUSE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_with_multipleitems_standard_shipping_CC () throws Exception {

		try {
			 
			 Osprey_ReEu.prepareOrdersData("OspreyUS_E2E_orderDetails.xlsx");
			 String Description ="Register User with multiple  items + standard shipping +CC in spanish store ";
			 Osprey_ReEu.click_singinButton();
		     Osprey_ReEu.Login_Account("Account");
		     Osprey_ReEu.search_product("SKU-10005235 -2QTY");
		     Osprey_ReEu.addtocart("SKU-10005235 -2QTY");
		     Osprey_ReEu.search_product("SKU-10005151 -2QTY");
		     Osprey_ReEu.addtocart("SKU-10005151 -2QTY");
		     Osprey_ReEu.minicart_Checkout();
		     Osprey_ReEu.RegaddDeliveryAddress("sp address");
		     Osprey_ReEu.selectshippingmethod("Sp Shipping Method");
		     String Used_GiftCode="NULL";
		     Osprey_ReEu.clickSubmitbutton_Shippingpage();
		     HashMap<String,String> Details=Osprey_ReEu.ordersummary_Details();
		     String OrderNumber= Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
//		     Osprey_ReEu.Admin_signin("Login Details");
//		     Osprey_ReEu.click_Sales();
//			 HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
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
//		System.setProperty("configFile", "Osprey_US\\config.properties");
		String url = "https://mcloud-na-preprod.osprey.com/us/es/";  

		System.setProperty("url", url);
        Login.signIn();
        

	}
	
	
}

